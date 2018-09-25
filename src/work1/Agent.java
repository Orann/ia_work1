package work1;

import java.util.LinkedList;

/**
 *
 * @authors Claire, Esther & Orann
 */
public class Agent {
    RoomState[][] beliefs;
    Position position;
    LinkedList<Action> intentions;
    Sensor sensor;
    Effector effector;
    int explorationFreq;
    
    /**
     * This function...
     */
    public void explore(){
        
    }
    
    /**
     * This function...
     */
    public void act(){
        effector.doAction(intentions, position, intentions.getFirst());
    }
    
}
