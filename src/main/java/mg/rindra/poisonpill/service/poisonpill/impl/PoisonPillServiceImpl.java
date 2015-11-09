package mg.rindra.poisonpill.service.poisonpill.impl;

import mg.rindra.poisonpill.model.Message;
import mg.rindra.poisonpill.model.Worker;
import mg.rindra.poisonpill.service.poisonpill.PoisonPillService;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.*;

public class PoisonPillServiceImpl implements PoisonPillService
{
    public final int THREAD_SIZE = 5;

    @Override
    public void execute(int messageCount) throws InterruptedException
    {
        final CountDownLatch latch = new CountDownLatch(messageCount);
        BlockingQueue<Message> queue = new LinkedBlockingDeque<Message>(messageCount);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);

        for(int i=0 ; i< messageCount ; i++)
        {
            System.out.println("Submit worker " + (i + 1) + " into the thread pool");
            executorService.submit(new Worker(i + 1, queue, new Observer() {
                @Override
                public void update(Observable o, Object arg) {
                    System.err.println("Counting down... " + (latch.getCount()));
                    latch.countDown();
                }
            }));
        }

        try
        {
            for(int i=0 ; i < messageCount ; i++)
            {
                System.out.println("Put message n" + (i+1) + " into the queue");
                queue.put(new Message("message n"+ (i+1)));
            }

            for(int i=0 ; i < messageCount ; i++)
            {
                System.out.println("Put poison pill "+ (i+1) +" to kill thread");
                queue.put(Message.POISON_PILL);
            }

        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        System.err.println("Submitted tasks. Time to wait... " + (latch.getCount()) + " ms");
        long time = System.currentTimeMillis();
        latch.await(5000, TimeUnit.MILLISECONDS); // bail after a reasonable time
        long totalTime = System.currentTimeMillis() - time;

        System.err.println("I awaited for " + totalTime +
                "ms. Did latch count down? " + (latch.getCount() == 0));


        executorService.shutdown();
    }
}
