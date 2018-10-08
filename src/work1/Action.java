package work1;

/**
 *
 * @authors Claire, Esther & Orann
 */
public enum Action {
    UP("Up"),
    DOWN("Down"),
    LEFT("Left"),
    RIGHT("Right"),
    VACCUM("Vaccum"),
    GRAB("Grab"),
    GRABVACCUM("Grab and vaccum"),
    NONE("No action - Initial state");

    private String name = "";

    Action(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}