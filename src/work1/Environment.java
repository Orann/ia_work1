package work1;


/**
 *
 * @authors Claire, Esther & Orann
 */
public class Environment {
    RoomState[][] rooms;
    Position positionAgent;
    int performance;
    int generationFreqDust = 10;
    int generationFreqJewels = generationFreqDust*3;
    int dimension = 10; // size of a row of the matrix   
    
    /**
     * Constructor
     * @param dim size of one row or column of the environment
     * @param positionAgent
     */
    public Environment(int dim, Position positionAgent){
        this.dimension = dim;
        this.positionAgent = new Position(positionAgent);
        performance = 0;
        rooms = new RoomState[dimension][dimension];
        for (int i=0; i<dimension; i++){
            for (int j=0; j<dimension; j++){
                rooms[i][j] = RoomState.EMPTY;
            }
        }
        
        for(int i = 0 ; i < 10 ; i++){
            this.generateDust();
            if(i%2==0) this.genereateJewels();
        }
    }
    
    /**
     * This function is called with a defined interval in the variable generationFreqDust and generates dust in the environment.
     */
    public void generateDust(){
        int i = (int)(Math.random()*(dimension));
        int j = (int)(Math.random()*(dimension));
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
    public void genereateJewels(){
        int i = (int)(Math.random()*(dimension));
        int j = (int)(Math.random()*(dimension));
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

    public int getGenerationFreqJewels() {
        return generationFreqJewels;
    }

    public int getGenerationFreqDust() {
        return generationFreqDust;
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
        System.out.println(this);
        
    }
    
    @Override
    public String toString(){ 
        String ret = new String();
        for (int i=0; i<dimension; i++){
            for(int j=0; j<dimension; j++){
                if (positionAgent.getX() == i && positionAgent.getY() == j){
                    ret = ret.concat("A");
                }
                else{
                    ret = ret.concat(" ");
                }
                switch(this.rooms[i][j]){
                    case DUSTJEWEL:
                        ret = ret.concat("DJ ");
                        break;
                    case JEWEL:
                        ret = ret.concat("J  ");
                        break;
                    case DUST:
                        ret = ret.concat("D  ");
                        break;
                    default:
                        ret = ret.concat("*  ");                        
                }
            }
            ret = ret.concat("\n");
        }
        return (ret);
    }
}
