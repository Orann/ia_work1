package work1;

/**
 *
 * @authors Claire, Esther & Orann
 */
public class Sensor {

    Environment environment;

    /**
     * Constructor
     *
     * @param e environment observed by the sensor
     */
    public Sensor(Environment e) {
        environment = e;
    }

    /**
     * This function updates the agent's beliefs by using its sensors to percept
     * the environment
     *
     * @param oldBeliefs
     */
    public void updateBeliefs(RoomState[][] oldBeliefs) {
        RoomState[][] newBeliefs = environment.getRooms();
        int size = oldBeliefs.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                oldBeliefs[i][j] = newBeliefs[i][j];
            }
        }
    }

    /**
     * This function allows the perception of the performance for the agent for
     * it to be able to improve itself
     *
     * @return the performance that was perceived
     */
    public int performancePerception() {
        return environment.getPerformance();
    }
}