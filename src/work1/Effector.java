package work1;

import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors Claire, Esther & Orann
 */
public class Effector {

    Environment environment;

    /**
     * Constructor
     *
     * @param e environment in which the actions will be done
     */
    public Effector(Environment e) {
        environment = e;
    }

    /**
     * This function removes the current action from the intentions, changes the
     * current position of the agent and call environment's method update() If
     * the agent exceeds the time of the exploration frequency, his list of
     * intentions is emptied and he restarts an exploration.
     *
     * @param intentions not empty
     * @param position
     */
    public boolean doAction(LinkedList<Action> intentions, Position position, int freqExploration) {
        boolean doExploration = false;
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;
        Action act;
        while (!intentions.isEmpty() && elapsedTime < freqExploration * 1000) {
            act = intentions.pop();
            System.out.println("Action executed : " + act);
            environment.update(position, act);
            position.move(act);
            //The agent takes 5 seconds to execute his intention :
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
            elapsedTime = (new Date()).getTime() - startTime;
        }
        if (!intentions.isEmpty()) {
            intentions.clear();
            doExploration = true;
        }
        return doExploration;
    }
}