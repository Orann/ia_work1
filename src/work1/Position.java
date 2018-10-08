package work1;

/**
 *
 * @author Claire, Esther & Orann
 */
public class Position {

    private int x;
    private int y;

    /**
     * Constructor with the abscissa and the ordonate
     *
     * @param x
     * @param y
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor with a position
     *
     * @param p
     */
    public Position(Position p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Getter
     *
     * @return the abscissa of the position
     */
    public int getX() {
        return x;
    }

    ;
    
    /**
     * Getter
     * @return the ordinate of the position
     */
    public int getY() {
        return y;
    }

    /**
     * Updates the attribute in function of the action done
     *
     * @param action
     */
    public void move(Action action) {
        switch (action) {
            case UP:
                x = x - 1;
                break;
            case DOWN:
                x = x + 1;
                break;
            case LEFT:
                y = y - 1;
                break;
            case RIGHT:
                y = y + 1;
                break;
            default:
                break;
        }
    }

    /**
     * Returns the position that comes after the action was done
     *
     * @param action
     * @return Position
     */
    public Position nextPosition(Action action) {
        Position nextPos = new Position(this);
        nextPos.move(action);
        return nextPos;
    }
}