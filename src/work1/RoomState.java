package work1;

/**
 *
 * @authors Claire, Esther & Orann
 */
public enum RoomState {
    EMPTY("the room is empty"),
    DUST("the room contains dust"),
    JEWEL("the room contains jewel"),
    DUSTJEWEL("the room contains dust and jewel");
    
    private String name = "";
    
    //Constructeur
    RoomState(String name){
        this.name = name;
    }
   
    public String toString(){
        return name;
    }
    
}
