package work1;

import java.util.LinkedList;

/**
 *
 * @authors Claire, ESther & Orann
 */
public class Effector {
    Environment environment;
    
    
    /**
     * Constructor
     * @param e environment in which the actions will be done
     */
    public Effector(Environment e){
        environment = e;
    }
    
    
    /**
     * This function removes the current action from the intentions, changes the
     * current position of the agent and call environment's method update()
     * @param intentions not empty
     * @param position
     */
    public void doAction(LinkedList<Action> intentions, Position position){
        Action act;
        while (!intentions.isEmpty()) {
            act = intentions.pop();
            environment.update(position, act);
            position.move(act);
        }
    }
    
}
