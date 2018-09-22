package work1;

/**
 *
 * @authors Claire, Esther & Orann
 */
public enum Action {
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right"),
    VACCUM("vaccum"),
    GRAB("grab"),
    GRABVACCUM("grab and vaccum");
    
    private String name = "";
    
    //Constructeur
    Action(String name){
        this.name = name;
    }
   
    public String toString(){
        return name;
    }
    
}