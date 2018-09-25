package work1;

import java.util.ArrayList;

/**
 *
 * @authors Claire, Esther & Orann
 */
public class Sensor {
    Environment environment;
    
    /**
     * This function...
     * @param oldBeliefs 
     */
    public void updateBeliefs(RoomState[][] oldBeliefs){
        RoomState[][] newBeliefs = environment.getRooms();
        int size = oldBeliefs.length;
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                oldBeliefs[i][j] = newBeliefs[i][j];
            }
        }
    }
    
    /**
     * This function...
     * @return the performance that was perceived 
     */
    public int performancePerception(){
        return environment.getPerformance();
    }    
}
