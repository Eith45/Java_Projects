/**
 * Created with IntelliJ IDEA.
 * User: Melancholia
 * Date: 15.03.14
 * Time: 20:15
 * To change this template use File | Settings | File Templates.
 */
/**
 * Class Monitor
 * To synchronize dining philosophers.
 */
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */
    // number of forks
    private int forks;
    /* phillStates array for saving philosophers state
     * 0 - thinking
     * 1 - waiting for fork,
     * 2 - eating,
     * 3 - talking,
     */
    private int [] phillStates;
    //false - in use, true - free
    boolean forkStates[];
    /**
     * Constructor
     */
    public Monitor(int piNumberOfPhilosophers)
    {
        //initialising
        forks = piNumberOfPhilosophers;
        phillStates = new int[forks + 1];
        forkStates = new boolean[forks + 1];
        for(int i = 0; i < forks + 1; i++){
            phillStates[i] = 0;
            forkStates[i] = true;
        }
    }

    public synchronized boolean test4Talking(final int piTID){
        if((phillStates[piTID] == 3) && (!someoneTalks(piTID))){
            return true;
        }
        return false;
    }
    //Check whether someone speaks.
    public synchronized boolean someoneTalks(final int piTID){
        for(int i = 0; i < phillStates.length; i++){
            if(i != piTID){
                //if someone already talking now, return true
                if(phillStates[i] == 3){
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * Grants request (returns) to eat when both chopsticks/forks are available.
     * Else forces the philosopher to wait()
     */

    public synchronized void pickUp(final int piTID){
        // while it can't have both forks, wait
        while(!forkStates[piTID] || (!forkStates[(piTID + 1)%forks])){
            //philosopher waits for fork
            phillStates[piTID] = 1;
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // it gets released
        phillStates[piTID] = 2;//eating
        forkStates[piTID] = false;//left fork in use
        forkStates[(piTID + 1)%forks] = false;//right fork in use
    }

    /**
     * When a given philosopher's done eating, they put the chopstiks/forks down
     * and let others know they are available.
     */
    public synchronized void putDown(final int piTID){
        forkStates[piTID] = true;//left fork is availble
        forkStates[(piTID + 1)%forks] = true;//and right fork is available
        phillStates[piTID] = 0;//thinking
        notify();//free the Phil that has waited the longest
    }

    /**
     * Only one philopher at a time is allowed to philosophy
     * (while she is not eating).
     */

//    public synchronized void requestTalk(final int piTID){
//
//        if(test4Talking(piTID)){
//            phillStates[piTID] = 3;
//        }else{
//            try {
//                wait();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        };
//    }


    public synchronized void requestTalk(final int piTID){
        //if some one else is talking now, philosopher has wait.
        /*  test 4 talking returns true if some one is talking
         * it prevents that some philosopher begin to talk
         * if some one else is talking at time
         */
        while(test4Talking(piTID)){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //Finally he can tell something!
        phillStates[piTID] = 3;
    }

    /**
     * When one philosopher is done talking stuff, others
     * can feel free to start talking.
     */
    public synchronized void endTalk(final int piTID){
        phillStates[piTID] = 0;//thinking
        notifyAll();//others can feel free to start talking
    }
}
