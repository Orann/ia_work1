package work1;

/**
 *
 * @author Claire, Esther, Orann
 */
public class Node {
    Action action;
    Node parent;

    public Node(Action action, Node parent) {
        this.action = action;
        this.parent = parent;
    }

    public Action getAction() {
        return action;
    }

    public Node getParent() {
        return parent;
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
