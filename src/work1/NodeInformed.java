package work1;

/**
 * Node structure used for the informed algorithm Possess an attribute heurisitc
 * which allows to sort nodes by priority
 *
 * @author Claire, Esther & Orann
 */
public class NodeInformed extends Node {

    private int heuristic;
    private NodeInformed parent;

    /**
     * Constructor
     *
     * @param action the action which will be done
     * @param parent the parent node
     * @param position the current position, from which action will be done
     */
    public NodeInformed(Action action, NodeInformed parent, Position position) {
        super(action, (Node) parent, position);
        this.parent = parent;
        switch (action) {
            case GRABVACCUM:
                heuristic = 1;
                break;
            case GRAB:
                heuristic = 2;
                break;
            case VACCUM:
                heuristic = 3;
                break;
            default:
                heuristic = 4;
        }
    }

    /**
     * Override getter
     *
     * @return the parent node informed
     */
    @Override
    public NodeInformed getParent() {
        return parent;
    }

    /**
     * Getter
     *
     * @return the heuristic
     */
    public int getHeuristic() {
        return heuristic;
    }
}