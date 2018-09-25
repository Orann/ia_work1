package work1;

/**
 *
 * @author clair
 */
public class Position {
    private int x;
    private int y;
    
    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    public Position (Position p){
        this.x = p.getX();
        this.y = p.getY();
    }
    
    /**
     * @return the abscissa of the position
     */
    public int getX(){ return x; };
    
    /**
     * @return the ordinate of the position
     */
    public int getY(){ return y; }
    
    public void move(Action action){
        switch(action){
            case UP :
                x = x + 1 ;
                break;
            case DOWN :
                x = x - 1 ;
                break;
            case LEFT :
                y = y - 1 ;
                break;
            case RIGHT :
                y = y + 1 ;
                break;
            default : 
                break; 
        }        
    }
}