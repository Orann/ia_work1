package work1;

import java.util.LinkedList;

/**
 *
 * @authors Claire, Esther & Orann
 */
public class Agent {
    RoomState[][] beliefs;
    Position position;
    LinkedList<Action> intentions;
    Sensor sensor;
    Effector effector;
    int explorationFreq;
    
    
    //Constructor ?
    
    /**
     * Function of exploration with a Breadth First Search algorithm
     */
    public void explore(){
        sensor.updateBeliefs(beliefs);
        
        //Initialisation of the frontier
        LinkedList<Node> frontier = new LinkedList<>();        
        frontier.add(new Node(Action.NONE, null, new Position(this.position)));
        
        
        
        int dim = this.beliefs.length;
        int compt = 0;
        Node currentNode = frontier.pop();
        Position nextPosition = currentNode.getPosition(); // Position of the children of the current node
        Action currentAction = currentNode.getAction();
        
        
        boolean stop = currentNode.isSuccess(beliefs[nextPosition.getX()][nextPosition.getY()]);        
        
        // This function can stop if we have explored everything, which is one step more than the number of ??
        //On peut s'arrêter quand on a tout exploré, ce qui prend une étape de plus que la dimension
        //Au max la dimension pour atteindre toutes les cases
        //Et la dernière étape pour explorer la dernière case 
        //Ex: on est en haut à gauche, et la seule poussière se trouve en bas à droite
        //Il faut dim étapes pour créer le noeud pour ramasser cette poussière
        //Et une de plus pour l'explorer
        while(!stop && (compt <=(dim + 1 ))){
            
            //On n'ajoute des fils que si l'on peut, c'est-à-dire s'il s'agit d'un mouvement
            //et que l'on est pas en dehors des limites de beliefs
            if (!( currentAction.equals(Action.GRABVACCUM) || 
                    currentAction.equals(Action.GRAB) || 
                    currentAction.equals(Action.VACCUM) ||
                    (nextPosition.getX() >= dim) || (nextPosition.getX() <0) ||
                    (nextPosition.getY() >= dim) || (nextPosition.getY() <0) )){
                frontier.addLast(new Node(Action.GRABVACCUM, currentNode, nextPosition));
                frontier.addLast(new Node(Action.GRAB, currentNode, nextPosition));
                frontier.addLast(new Node(Action.VACCUM, currentNode, nextPosition));
                frontier.addLast(new Node(Action.LEFT, currentNode, nextPosition));
                frontier.addLast(new Node(Action.RIGHT, currentNode, nextPosition));
                frontier.addLast(new Node(Action.UP, currentNode, nextPosition));
                frontier.addLast(new Node(Action.DOWN, currentNode, nextPosition));
            }
            
            currentNode = frontier.pop();
            stop = currentNode.isSuccess(beliefs[currentNode.getPosition().getX()][currentNode.getPosition().getY()]);
            currentAction = currentNode.getAction();
            nextPosition = nextPosition.nextPosition(currentAction); 
            
            compt++;
        }
        
        //We build the list of intentions thanks to the path to the current node
        if(stop){
            while(currentNode != null){
                intentions.addFirst(currentNode.getAction());
                currentNode = currentNode.getParent();
            }
        }
        
    }
    
    /**
     * This function...
     */
    public void act(){
        //Si cette fonction est censé vider la liste d'intentions, 
        //il va falloir mettre un while quelque part
        effector.doAction(intentions, position);
    }
    
}
