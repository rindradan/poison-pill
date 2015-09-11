import java.util.concurrent.BlockingQueue;

public class Task implements Runnable
{
    private BlockingQueue<Message> queue;

    public Task(BlockingQueue<Message> queue)
    {
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
                    stop = true;
                }
                else
                {
                    System.out.println("Start processing...");
                    System.out.println("Message is : "+ process.getContent());

                }

            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}
