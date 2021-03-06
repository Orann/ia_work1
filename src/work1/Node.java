package work1;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Node {

    protected Action action;
    private Node parent;
    protected Position position;

    /**
     * Contructor
     *
     * @param action
     * @param parent
     * @param position
     */
    public Node(Action action, Node parent, Position position) {
        this.action = action;
        this.parent = parent;
        this.position = position;
    }

    /**
     * Getter
     *
     * @return the action
     */
    public Action getAction() {
        return action;
    }

    /**
     * Getter
     *
     * @return the parent node
     */
    public Node getParent() {
        return parent;
    }

    /**
     * Getter
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * This function tests the stopping condition of the exploration algorithm
     *
     * @param roomState
     * @return true or false
     */
    public boolean isSuccess(RoomState roomState) {
        boolean result = false;
        switch (roomState) {
            case DUST:
                if (action.equals(Action.VACCUM)) {
                    result = true;
                }
                break;
            case DUSTJEWEL:
                if (action.equals(Action.GRABVACCUM)) {
                    result = true;
                }
                break;
            case JEWEL:
                if (action.equals(Action.GRAB)) {
                    result = true;
                }
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    public String toString() {
        return this.action.toString();
    }
}