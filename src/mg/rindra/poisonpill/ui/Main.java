package mg.rindra.poisonpill.ui;

import mg.rindra.poisonpill.model.Message;
import mg.rindra.poisonpill.model.Worker;
import mg.rindra.poisonpill.service.PoisonPillService;
import mg.rindra.poisonpill.service.impl.PoisonPillServiceImpl;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.Scanner;
import java.util.concurrent.*;

public class Main
{
    public static PoisonPillService poisonPillService;

    public static void main(String[] args)
    {
        poisonPillService = new PoisonPillServiceImpl();

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the number of the messages which you want to process");
        int input = keyboard.nextInt();

        poisonPillService.execute(input);
    }

}
