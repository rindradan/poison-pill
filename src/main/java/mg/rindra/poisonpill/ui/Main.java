package mg.rindra.poisonpill.ui;

import mg.rindra.poisonpill.service.poisonpill.PoisonPillService;
import mg.rindra.poisonpill.service.poisonpill.impl.PoisonPillServiceImpl;

import java.util.Scanner;

public class Main
{
    public static PoisonPillService poisonPillService;

    public static void main(String[] args) throws InterruptedException {
        poisonPillService = new PoisonPillServiceImpl();

        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the number of the messages which you want to process");
        int input = keyboard.nextInt();

        poisonPillService.execute(input);
    }

}
