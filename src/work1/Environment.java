package work1;


/**
 *
 * @authors Claire, Esther & Orann
 */
public class Environment {
    RoomState[][] rooms;
    int performance;
    final int generationFreqJewels = 20;
    final int generationFreqDust = 50;
    final int dimension = 10; // size of a row of the matrix
    
    public void Environment(){
        performance = 0;
        rooms = new RoomState[dimension][dimension];
        for (int i=0; i<dimension; i++){
            for (int j=0; j<dimension; j++){
                rooms[i][j] = RoomState.EMPTY;
            }
        }
    }
    
    /**
     * This function is called with a defined interval in the variable generationFreqDust and generates dust in the environment.
     */
    private void generateDust(){
        int i = (int)(Math.random()*(dimension+1));
        int j = (int)(Math.random()*(dimension+1));
        switch(rooms[i][j]){
            case EMPTY :
                rooms[i][j] = RoomState.DUST;
                break;
            case JEWEL : 
                rooms[i][j] = RoomState.DUSTJEWEL;
                break;
            default:
                break;
        };
    }
    
    /**
     * this function is called with a defined interval in the variable generationFreqJewels and generates jewel in the environment.
     */
    private void genereateJewels(){
        int i = (int)(Math.random()*(dimension+1));
        int j = (int)(Math.random()*(dimension+1));
        switch(rooms[i][j]){
            case EMPTY :
                rooms[i][j] = RoomState.JEWEL;
                break;
            case DUST : 
                rooms[i][j] = RoomState.DUSTJEWEL;
                break;
            default:
                break;
        };        
    }
    
    private void removeDust(int i, int j){
        switch(rooms[i][j]){
            case DUST : 
                rooms[i][j] = RoomState.EMPTY;
                break;
            case DUSTJEWEL :
                rooms[i][j] = RoomState.JEWEL;
            default:
                break;
        }
    }
    
    private void removeJewel(int i, int j){
        switch(rooms[i][j]){
            case JEWEL : 
                rooms[i][j] = RoomState.EMPTY;
                break;
            case DUSTJEWEL :
                rooms[i][j] = RoomState.DUST;
            default:
                break;
        }
    }
    
    /**
     * 
     * @return the performance of the vacuum cleaner in the environment.
     */
    public int getPerformance() {
        return performance;
    }
    
    /**
     * 
     * @return 
     */
    public RoomState[][] getRooms() {
        return rooms;
    }
    
    /**
     * 
     */
    private void calculatePerformance(){
        
    }
    
    /**
     * 
     * @param position
     * @param action 
     */
    public void update(Position position, Action action){
        switch(action){
            case VACCUM : 
                removeDust(position.getX(), position.getY());
                break;
            case GRAB :
                removeJewel(position.getX(), position.getY());
                break;
            case GRABVACCUM :
                removeJewel(position.getX(), position.getY());
                removeDust(position.getX(), position.getY());
                break;
            default:
                break;
        }
        calculatePerformance();
        
    }
    
    @Override
    public String toString(){
        String ret = new String();
        return ret;
    }
}
