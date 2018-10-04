package work1;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @authors Claire, Esther & Orann
 *
 */
public class main {
    
    final static int explorationAlgorithm = 1; //0 for uninformed or 1 for informed

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int x = (int)(Math.random() * 10);
        int y = (int)(Math.random() * 10);
        Environment environment = new Environment(10, new Position(x, y));
        
        Sensor sensor = new Sensor(environment);
        Effector effector = new Effector(environment);       
        Agent agent = new Agent(new Position(x, y), sensor, effector, 10);       
        
        Runnable run_environment = new Runnable() {
            public void run() {
                System.out.println("Environment is running...");
                System.out.println(environment);
                int freqDust = environment.getGenerationFreqDust();
                while(true){
                    for(int i = 0 ; i < 3 ; i++){
                        environment.generateDust();
                        if(i == 2) environment.genereateJewels();
                        try {
                            TimeUnit.SECONDS.sleep(freqDust);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        };

        Runnable run_agent = new Runnable() {
            public void run() {
                System.out.println("Agent is running...");
                while(true){
                    agent.updateAgentState();
                    if(!agent.goal()){ 
                        //if the agent hasn't accomplished its goals, it has to explore the environment and act
                    if(explorationAlgorithm == 0) agent.exploreUninformed();
                    else agent.exploreInformed();
                    } else {
                        //if the agent has accomplished its goals, it waits for 30 seconds before starting its work again
                        try {
                            TimeUnit.SECONDS.sleep(30);
                            } catch (InterruptedException ex) {
                             Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                            }
                    }
                    
                    agent.act();
                }
            }
        };

        Thread thread_environment = new Thread(run_environment);
        Thread thread_agent = new Thread(run_agent);

        thread_environment.start();
        thread_agent.start();
    }

}
