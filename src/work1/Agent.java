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
        sensor.updateBeliefs(beliefs);
        
        LinkedList<Node> frontier = new LinkedList<>();
        frontier.add(new Node(null, null));
        boolean stop = false;        
        
        while(!stop){
            frontier.addLast(new Node(Action.GRABVACCUM, frontier.getLast()));
            frontier.addLast(new Node(Action.GRAB, frontier.getLast()));
            frontier.addLast(new Node(Action.VACCUM, frontier.getLast()));
            frontier.addLast(new Node(Action.LEFT, frontier.getLast()));
            frontier.addLast(new Node(Action.RIGHT, frontier.getLast()));
            frontier.addLast(new Node(Action.UP, frontier.getLast()));
            frontier.addLast(new Node(Action.DOWN, frontier.getLast()));
            
            
        }
        
    }
    
    /**
     * This function...
     */
    public void act(){
        effector.doAction(intentions, position, intentions.getFirst());
    }
    
}
