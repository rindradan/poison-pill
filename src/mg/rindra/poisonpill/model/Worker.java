package mg.rindra.poisonpill.model;

import mg.rindra.poisonpill.model.Message;

import java.util.concurrent.BlockingQueue;

public class Worker implements Runnable
{
    private int index = 0;
    private BlockingQueue<Message> queue;

    public Worker(int index, BlockingQueue<Message> queue)
    {
        this.index = index;
        this.queue = queue;
    }

    @Override
    public void run()
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
    }
}
