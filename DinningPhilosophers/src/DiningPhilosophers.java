/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 15.03.14
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */

import java.util.Scanner;

/**
 * Class DiningPhilosophers
 * The main starter.
 */
public class DiningPhilosophers
{
	 /**
     * This default may be overridden from the command line
     */
    public static final int DEFAULT_NUMBER_OF_PHILOSOPHERS = 4;

    /**
     * Dining "iterations" per philosopher thread
     * while they are socializing there
     */
    public static final int DINING_STEPS = 10;

    /**
     * Our shared monitor for the philosphers to consult
     */
    public static Monitor soMonitor = null;

    /**
     * Main system starts up right here
     */
    public static void main(String[] argv)
    {
        try
        {
            int iPhilosophers = 0;
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input the default number of philosophers, please");
            String s = scanner.nextLine();
            if(s.length() > 0){
                try{

                    if(Integer.parseInt(s) < 0){
                        System.out.println("\"" + s + "\" is not a positive decimal integer");
                        System.out.println(" Usage: java DiningPhilosophers\n[NUMBER_OF_PHILOSOPHERS]");
                        System.exit(1);
                    }
                    iPhilosophers = Integer.parseInt(s);
                }   catch (Exception e){
                    System.out.println("\"" + s + "\" is not a positive decimal integer");
                    System.out.println(" Usage: java DiningPhilosophers\n[NUMBER_OF_PHILOSOPHERS]");
                    System.exit(1);
                }
            }else{
                   iPhilosophers = DEFAULT_NUMBER_OF_PHILOSOPHERS;
            }
            // Make the monitor aware of how many philosophers there are
            soMonitor = new Monitor(iPhilosophers);

            // Space for all the philosophers
            Philosopher aoPhilosophers[] = new Philosopher[iPhilosophers];

            // Let 'em sit down
            for(int j = 0; j < iPhilosophers; j++)
            {
                aoPhilosophers[j] = new Philosopher();
                aoPhilosophers[j].start();
            }

            System.out.println
                    (
                            iPhilosophers +
                                    " philosopher(s) came in for a dinner."
                    );

            // Main waits for all its children to die...
            // I mean, philosophers to finish their dinner.
            for(int j = 0; j < iPhilosophers; j++)
                aoPhilosophers[j].join();

            System.out.println("All philosophers have left. System terminates normally.");
        }
        catch(InterruptedException e)
        {
            System.err.println("main():");
            reportException(e);
            System.exit(1);
        }
    } // main()

    /**
     * Outputs exception information to STDERR
     * @param poException Exception object to dump to STDERR
     */
    public static void reportException(Exception poException)
    {
        System.err.println("Caught exception : " + poException.getClass().getName());
        System.err.println("Message          : " + poException.getMessage());
        System.err.println("Stack Trace      : ");
        poException.printStackTrace(System.err);
    }
}
