package mg.rindra.poisonpill.model;

import java.util.Observer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class Worker implements Callable<Object>
{
    private int index = 0;
    private BlockingQueue<Message> queue;
    private Observer listener;

    public Worker(int index, BlockingQueue<Message> queue, Observer listener)
    {
        this.index = index;
        this.queue = queue;
        this.listener = listener;
    }

    @Override
    public Object call() throws Exception
    {
        boolean stop = false;

        while(!stop)
        {
            try
            {
                Message process = queue.take();

                if(Message.POISON_PILL.equals(process))
                {
                    System.out.println("I eat a poison pill. Me the worker/consumer n" + index + ", I was be betrayed. I dead...");
                    stop = true;
                }
                else
                {
                    System.out.println("Start processing by worker n" + index + "... Message is : "+ process.getContent());
                }

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }

//        int sleepTime = new Random().nextInt(2000);
//        System.out.println("Sleeping for " + sleepTime + "ms");
//        Thread.sleep(sleepTime);
        listener.update(null, null);
        return null;
    }
}
