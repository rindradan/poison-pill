package main.java;

import java.util.Queue;

public class Task implements Runnable
{
    private boolean run=false;
    private Queue<Message> queue;

    public Task(Queue<Message> queue)
    {
        this.queue = queue;
    }

    @Override
    public void run()
    {
        while(!run)
        {
        }
    }
}
