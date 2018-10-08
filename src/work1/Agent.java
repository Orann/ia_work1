package work1;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @authors Claire, Esther & Orann
 */
public class Agent {

    RoomState[][] beliefs; //BELIEFS
    Position position;
    LinkedList<Action> intentions; //INTENTIONS
    Sensor sensor;
    Effector effector;
    int explorationFreq;
    HashMap<Integer, Integer> explorationFreqAndPerformance; // Memory (for learning part)

    /**
     * Constructor
     *
     * @param position
     * @param sensor
     * @param effector
     * @param dimension size of one row or column of the environment
     */
    public Agent(Position position, Sensor sensor, Effector effector, int dimension) {
        this.position = new Position(position);
        this.sensor = sensor;
        this.effector = effector;
        this.intentions = new LinkedList<>();
        this.beliefs = new RoomState[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                this.beliefs[i][j] = RoomState.EMPTY;
            }
        }
        this.explorationFreq = 55; // Maximum length of the intention list time 5 seconds
        explorationFreqAndPerformance = new HashMap<Integer, Integer>();
    }

    /**
     * This function expresses the agent's goals/desires
     *
     * @return true if the goal is reached
     */
    public boolean goal() {
        boolean goalSuccess = true;
        // The agent's goal is only accomplished if all the rooms are empty of jewels and dust
        for (int i = 0; i < this.beliefs.length; i++) {
            for (int j = 0; j < this.beliefs.length; j++) {
                if (this.beliefs[i][j] != RoomState.EMPTY) {
                    goalSuccess = false;
                }
            }
        }
        return goalSuccess;
    }

    /**
     * Function of update of its inner state thanks to the sensor's perceptions
     */
    public void updateAgentState() {
        sensor.updateBeliefs(beliefs);
        int performance = sensor.performancePerception();

        //add or update the performance of a exploration frequency :
        if (!explorationFreqAndPerformance.containsKey(explorationFreq)) {
            explorationFreqAndPerformance.put(explorationFreq, performance);
        } else {
            for (HashMap.Entry<Integer, Integer> entry : explorationFreqAndPerformance.entrySet()) {
                if (entry.getKey() == explorationFreq && performance > entry.getValue()) {
                    explorationFreqAndPerformance.put(explorationFreq, performance);
                }
            }
        }

        //Update the frequency exploration randomly in order to discover over exploration frequency and learn :
        if (explorationFreqAndPerformance.size() <= 5) {
            explorationFreq = (int) (Math.random() * 50 + 5);
        } else {
            int maxValue = (Collections.max(explorationFreqAndPerformance.values()));
            int keyOfMaxValue = 0;
            for (HashMap.Entry<Integer, Integer> entry : explorationFreqAndPerformance.entrySet()) {
                if (entry.getValue() == maxValue) {
                    keyOfMaxValue = entry.getKey();
                }
            }
            int random = (int) (Math.random() * 3 + 1);
            if (random <= 2) {
                explorationFreq = keyOfMaxValue - random;
            } else if (random == 3) {
                explorationFreq = keyOfMaxValue + 1;
            } else {
                explorationFreq = keyOfMaxValue + 2;
            }
        }
        System.out.println("The agent explore every " + explorationFreq + "seconds.");
        //System.out.println(explorationFreqAndPerformance);
    }

    /**
     * Function of exploration with a Breadth First Search algorithm
     */
    public void exploreUninformed() {

        //Initialisation of the frontier to explore
        LinkedList<Node> frontier = new LinkedList<>();
        frontier.add(new Node(Action.NONE, null, new Position(this.position)));

        int dim = this.beliefs.length;

        Node currentNode = frontier.pop();
        Position currentPosition = currentNode.getPosition(); // Position of the children of the current node
        Action currentAction = currentNode.getAction();

        boolean stop = currentNode.isSuccess(beliefs[currentPosition.getX()][currentPosition.getY()]);

        // This function can stop if we have explored everything, which is one step more than the number of 
        while (!stop) {

            if (!(currentAction.equals(Action.GRABVACCUM)
                    || currentAction.equals(Action.GRAB)
                    || currentAction.equals(Action.VACCUM))) {
                frontier.addLast(new Node(Action.GRABVACCUM, currentNode, currentPosition.nextPosition(Action.GRABVACCUM)));
                frontier.addLast(new Node(Action.GRAB, currentNode, currentPosition.nextPosition(Action.GRAB)));
                frontier.addLast(new Node(Action.VACCUM, currentNode, currentPosition.nextPosition(Action.VACCUM)));
                if (currentPosition.getY() > 0) {
                    frontier.addLast(new Node(Action.LEFT, currentNode, currentPosition.nextPosition(Action.LEFT)));
                }
                if (currentPosition.getY() < (dim - 1)) {
                    frontier.addLast(new Node(Action.RIGHT, currentNode, currentPosition.nextPosition(Action.RIGHT)));
                }
                if (currentPosition.getX() > 0) {
                    frontier.addLast(new Node(Action.UP, currentNode, currentPosition.nextPosition(Action.UP)));
                }
                if (currentPosition.getX() < (dim - 1)) {
                    frontier.addLast(new Node(Action.DOWN, currentNode, currentPosition.nextPosition(Action.DOWN)));
                }
            }

            currentNode = frontier.pop();
            currentAction = currentNode.getAction();
            currentPosition = currentNode.getPosition();
            stop = currentNode.isSuccess(beliefs[currentPosition.getX()][currentPosition.getY()]);
            /*
            UNCOMMENT FOR MORE DETAILS ABOUT THE EXPLORATION IN THE TERMINAL
            
            System.out.println("Noeud exploré : " + currentNode.getAction() + " et parent : " + currentNode.getParent().getAction());
            System.out.println("Success ? "+ stop);
             */

        }
        //We build the list of intentions thanks to the path to the current node
        if (stop) {
            while (currentNode != null) {
                intentions.addFirst(currentNode.getAction());
                currentNode = currentNode.getParent();
            }
        }

    }

    /**
     * Function of exploration with a Greedy Search algorithm
     */
    public void exploreInformed() {

        //Initialisation of the frontier
        LinkedList<NodeInformed> frontier = new LinkedList<>();
        frontier.add(new NodeInformed(Action.NONE, null, new Position(this.position)));

        int dim = this.beliefs.length;

        NodeInformed currentNode = frontier.pop();
        Position currentPosition = currentNode.getPosition(); // Position of the current node
        Action currentAction = currentNode.getAction(); // Action of the current node

        boolean stop = currentNode.isSuccess(beliefs[currentPosition.getX()][currentPosition.getY()]);

        while (!stop) {

            if (!(currentAction.equals(Action.GRABVACCUM)
                    || currentAction.equals(Action.GRAB)
                    || currentAction.equals(Action.VACCUM))) {
                frontier.addLast(new NodeInformed(Action.GRABVACCUM, currentNode, currentPosition.nextPosition(Action.GRABVACCUM)));
                frontier.addLast(new NodeInformed(Action.GRAB, currentNode, currentPosition.nextPosition(Action.GRAB)));
                frontier.addLast(new NodeInformed(Action.VACCUM, currentNode, currentPosition.nextPosition(Action.VACCUM)));
                if (currentPosition.getY() > 0) {
                    frontier.addLast(new NodeInformed(Action.LEFT, currentNode, currentPosition.nextPosition(Action.LEFT)));
                }
                if (currentPosition.getY() < (dim - 1)) {
                    frontier.addLast(new NodeInformed(Action.RIGHT, currentNode, currentPosition.nextPosition(Action.RIGHT)));
                }
                if (currentPosition.getX() > 0) {
                    frontier.addLast(new NodeInformed(Action.UP, currentNode, currentPosition.nextPosition(Action.UP)));
                }
                if (currentPosition.getX() < (dim - 1)) {
                    frontier.addLast(new NodeInformed(Action.DOWN, currentNode, currentPosition.nextPosition(Action.DOWN)));
                }
            }

            Collections.sort(frontier, new Comparator<NodeInformed>() {
                @Override
                public int compare(NodeInformed o1, NodeInformed o2) {
                    int ret;
                    if (o1.getHeuristic() < o2.getHeuristic()) {
                        ret = -1;
                    } else if (o1.getHeuristic() == o2.getHeuristic()) {
                        ret = 0;
                    } else {
                        ret = 1;
                    }
                    return ret;
                }
            });

            currentNode = frontier.pop();
            currentAction = currentNode.getAction();
            currentPosition = currentNode.getPosition();
            stop = currentNode.isSuccess(beliefs[currentNode.getPosition().getX()][currentNode.getPosition().getY()]);

            /*
            UNCOMMENT FOR MORE DETAILS ABOUT THE EXPLORATION IN THE TERMINAL
            
            System.out.println("Noeud exploré : " + currentNode.getAction() + " et parent : " + currentNode.getParent().getAction());
            System.out.println("Success ? "+ stop);
             */
        }

        //We build the list of intentions thanks to the path to the current node
        if (stop) {
            while (currentNode != null) {
                intentions.addFirst(currentNode.getAction());
                currentNode = currentNode.getParent();
            }
        }
    }

    /**
     * This function executes the list of intentions of the agent
     *
     * @return true if the agent has to do another exploration
     */
    public boolean act() {
        boolean doExploration = effector.doAction(intentions, position, explorationFreq);
        return doExploration;
    }
}