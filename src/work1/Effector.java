package work1;

import java.util.LinkedList;

/**
 *
 * @authors Claire, ESther & Orann
 */
public class Effector {
    Environment environment;
    
    //Constructor ?
    
    
    /**
     * This function removes the current action from the intentions, changes the
     * current position of the agent and call environment's method update()
     * @param intentions not empty
     * @param position
     */
    public void doAction(LinkedList<Action> intentions, Position position){
        Action act = intentions.pop();
        environment.update(position, act);
        position.move(act);
    }
    
}
