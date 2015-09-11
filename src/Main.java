import java.util.concurrent.*;

public class Main
{
    public static void main(String[] args)
    {
        int nbMsg = 5;

        BlockingQueue<Message> queue = new LinkedBlockingDeque<Message>(nbMsg);
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i=0 ; i<nbMsg ; i++)
        {
            executorService.submit(new Task(queue));
        }

        try
        {
            for(int i=0 ; i<nbMsg ; i++)
            {
                queue.put(new Message("message n"+ (i+1)));
            }

            for(int i=0 ; i<nbMsg ; i++)
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
