/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package work1;

/**
 * Node structure used for the informed algorithm
 * Possess an attribute heurisitc which allows to sort nodes by priority
 * @author clair
 */
public class NodeInformed extends Node{
    private int heuristic;
    private NodeInformed parent; 
    
    /**
     * Constructor
     * @param action the action which will be done
     * @param parent the parent node
     * @param position the current position, from which action will be done
     */
    public NodeInformed(Action action, NodeInformed parent, Position position) {
        super(action, (Node) parent, position);
        this.parent = parent;
        switch(action){
            case GRABVACCUM :
                heuristic = 1;
            break;
            case GRAB :
                heuristic = 2;
                break;
            case VACCUM :
                heuristic = 3;
                break;
            default:
                heuristic = 4;
        }
    }

    @Override
    public NodeInformed getParent() {
        return parent;
    }       

    public int getHeuristic() {
        return heuristic;
    }
    
}
