package mg.rindra.poisonpill.service.poisonpill;

import java.util.Observer;
import java.util.Random;
import java.util.concurrent.Callable;

public class AsyncProcessor implements Callable<Object>
{
    private Observer listener;
    public AsyncProcessor(Observer listener)
    {
        this.listener = listener;
    }

    public Object call() throws Exception
    {
        int sleepTime = new Random().nextInt(2000);
        System.err.println("Sleeping for " + sleepTime + "ms");
        Thread.sleep(sleepTime);
        listener.update(null, null);
        return null;
    }
}
