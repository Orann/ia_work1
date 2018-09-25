package work1;

import java.util.LinkedList;

/**
 *
 * @authors Claire, ESther & Orann
 */
public class Effector {
    Environment environment;
    
    /**
     * This function removes the current action from the intentions, changes the
     * current position of the agent and call environment's method update()
     * @param intentions not empty
     * @param position
     * @param action
     */
    public void doAction(LinkedList<Action> intentions, Position position, Action action){
        // Question : a-t-on réellement besoin de l'argument action, puisque logiquement c'est le premier éléments des intentions ? 
        Action act = intentions.pop();
        environment.update(position, act);
        position.move(act);
    }
    
}
