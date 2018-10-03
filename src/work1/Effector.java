package work1;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors Claire, ESther & Orann
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
     * current position of the agent and call environment's method update()
     *
     * @param intentions not empty
     * @param position
     */
    public void doAction(LinkedList<Action> intentions, Position position) {
        Action act;
        while (!intentions.isEmpty()) {
            act = intentions.pop();
            System.out.println(act);
            environment.update(position, act);
            position.move(act);
            //The agent takes 5 seconds to execute his intention :
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
