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
    int generationFreqJewels = generationFreqDust * 3;
    int dimension = 10; // size of a row of the matrix   

    /**
     * Constructor
     *
     * @param dim size of one row or column of the environment
     * @param positionAgent
     */
    public Environment(int dim, Position positionAgent) {
        this.dimension = dim;
        this.positionAgent = new Position(positionAgent);
        performance = 0;
        rooms = new RoomState[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                rooms[i][j] = RoomState.EMPTY;
            }
        }

        //randomly generates 10 dusts and 5 jewels
        for (int i = 0; i < 10; i++) {
            this.generateDust();
            if (i % 2 == 0) {
                this.genereateJewels();
            }
        }
    }

    /**
     * This function is called with a defined interval in the variable
     * generationFreqDust and generates dust in the environment.
     */
    public void generateDust() {
        int i = (int) (Math.random() * (dimension));
        int j = (int) (Math.random() * (dimension));
        switch (rooms[i][j]) {
            case EMPTY:
                rooms[i][j] = RoomState.DUST;
                break;
            case JEWEL:
                rooms[i][j] = RoomState.DUSTJEWEL;
                break;
            default:
                break;
        };
    }

    /**
     * this function is called with a defined interval in the variable
     * generationFreqJewels and generates jewel in the environment.
     */
    public void genereateJewels() {
        int i = (int) (Math.random() * (dimension));
        int j = (int) (Math.random() * (dimension));
        switch (rooms[i][j]) {
            case EMPTY:
                rooms[i][j] = RoomState.JEWEL;
                break;
            case DUST:
                rooms[i][j] = RoomState.DUSTJEWEL;
                break;
            default:
                break;
        };
    }

    /**
     * This function is called when the agent vaccum something
     *
     * @param i
     * @param j
     */
    private void removeDust(int i, int j) {
        switch (rooms[i][j]) {
            case DUST:
                rooms[i][j] = RoomState.EMPTY;
                break;
            case DUSTJEWEL:
                rooms[i][j] = RoomState.JEWEL;
            default:
                break;
        }
    }

    /**
     * the funciton is called when the agent grab something
     *
     * @param i
     * @param j
     */
    private void removeJewel(int i, int j) {
        switch (rooms[i][j]) {
            case JEWEL:
                rooms[i][j] = RoomState.EMPTY;
                break;
            case DUSTJEWEL:
                rooms[i][j] = RoomState.DUST;
            default:
                break;
        }
    }

    /**
     * Getter
     *
     * @return the performance of the vacuum cleaner in the environment.
     */
    public int getPerformance() {
        return performance;
    }

    /**
     * Getter
     *
     * @return the rooms states of the environment
     */
    public RoomState[][] getRooms() {
        return rooms;
    }

    /**
     * Getter
     *
     * @return the frequence of the generation of jewels
     */
    public int getGenerationFreqJewels() {
        return generationFreqJewels;
    }

    /**
     * Getter
     *
     * @return the frequence of the generation of dust
     */
    public int getGenerationFreqDust() {
        return generationFreqDust;
    }

    /**
     * Calculates the agent's performance after he has performed an action (this
     * function is called in the update() function).
     */
    private void calculatePerformance(Position position, Action action) {
        int perf = performance;
        RoomState currentState = rooms[position.getX()][position.getY()];
        switch (action) {
            case GRABVACCUM:
                perf += 32;
                break;
            case GRAB:
                perf += 17;
                break;
            case VACCUM:
                if (currentState == RoomState.DUSTJEWEL) {
                    perf -= 40;
                }
                perf += 15;
                break;
            case NONE:
                break;
            default:
                perf -= 5;
        }
        this.performance = perf;
    }

    /**
     * this function updates the environment after the agent has performed an
     * action
     *
     * @param position
     * @param action
     */
    public void update(Position position, Action action) {
        positionAgent = position.nextPosition(action);
        switch (action) {
            case VACCUM:
                removeDust(position.getX(), position.getY());
                break;
            case GRAB:
                removeJewel(position.getX(), position.getY());
                break;
            case GRABVACCUM:
                removeJewel(position.getX(), position.getY());
                removeDust(position.getX(), position.getY());
                break;
            default:
                break;
        }
        calculatePerformance(position, action);
        System.out.println(this);

    }

    /**
     * Display the environment
     *
     * @return a String
     */
    @Override
    public String toString() {
        String ret = new String();
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (positionAgent.getX() == i && positionAgent.getY() == j) {
                    ret = ret.concat("A");
                } else {
                    ret = ret.concat(" ");
                }
                switch (this.rooms[i][j]) {
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
        ret = ret.concat("Agent's performance :" + this.performance + "\n");
        return (ret);
    }
}