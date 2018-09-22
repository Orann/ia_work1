package work1;

import java.util.ArrayList;

/**
 *
 * @authors Claire, Esther & Orann
 */
public class Environment {
    ArrayList<RoomState> rooms;
    int performance;
    final int generationFreqJewels = 20;
    final int generationFreqDust = 50;
    
    /**
     * This function is called with a defined interval in the variable generationFreqDust and generates dust in the environment.
     */
    private void generateDust(){
        
    }
    
    /**
     * this function is called with a defined interval in the variable generationFreqJewels and generates jewel in the environment.
     */
    private void genereateJewels(){
        
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
    public ArrayList<RoomState> getRooms() {
        return rooms;
    }
    
    /**
     * 
     */
    private void calculatePerformance(){
        
    }
    
    /**
     * 
     * @param positions
     * @param action 
     */
    public void update(ArrayList<Integer> positions, Action action){
        
    }
}
