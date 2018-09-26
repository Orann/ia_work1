package work1;

/**
 *
 * @author Claire, Esther, Orann
 */
public class Node {
    Action action;
    Node parent;
    Position position;

    public Node(Action action, Node parent, Position position) {
        this.action = action;
        this.parent = parent;
        this.position = position;
    }

    public Action getAction() {
        return action;
    }

    public Node getParent() {
        return parent;
    }

    public Position getPosition() {
        return position;
    }
    
    /**
     * This function teste la condition d'arrêt.
     * @param roomState
     * @return 
     */
    public boolean isSuccess(RoomState roomState){
        boolean result = false;
        switch(roomState){
            case DUST :
                if(action.equals(Action.VACCUM)){
                    result = true;
                }
                break;
            case DUSTJEWEL :
                if(action.equals(Action.GRABVACCUM)){
                    result = true;
                }
                break;
            case JEWEL :
                if(action.equals(Action.GRAB)){
                    result = true;
                }
                break;
            default:
                break;
        }
        return result;
    }  
}
