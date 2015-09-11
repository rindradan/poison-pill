package mg.rindra.poisonpill.service.impl;

import mg.rindra.poisonpill.model.Message;
import mg.rindra.poisonpill.model.Worker;
import mg.rindra.poisonpill.service.PoisonPillService;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

public class PoisonPillServiceImpl implements PoisonPillService
{
    public final int THREAD_SIZE = 5;

    @Override
    public void execute(int messageCount)
    {
        BlockingQueue<Message> queue = new LinkedBlockingDeque<Message>(messageCount);
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_SIZE);

        for(int i=0 ; i< messageCount ; i++)
        {
            System.out.println("Submit worker " + (i + 1) + " into the thread pool");
            executorService.submit(new Worker(i + 1, queue));
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

        executorService.shutdown();
    }
}
