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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Environment environment = new Environment(10);
        
        Sensor sensor = new Sensor(environment);
        Effector effector = new Effector(environment);
        
        int x = (int)(Math.random() * 10);
        int y = (int)(Math.random() * 10);
        Agent agent = new Agent(new Position(x, y), sensor, effector, 10);
        
        for(int i = 0 ; i < 10 ; i++){
            environment.generateDust();
            if(i%2==0) environment.genereateJewels();
        }
        
        Runnable run_environment = new Runnable() {
            public void run() {
                System.out.println("Environment is running...");
                while(true){
                    System.out.println(environment);
                    
                    try {
                        TimeUnit.SECONDS.sleep(30);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        Runnable run_agent = new Runnable() {
            public void run() {
                System.out.println("Agent is running...");
                while(true){
                    System.out.println("Agent : I'm alive");
                    
                    try {
                        TimeUnit.SECONDS.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };

        Thread thread_environment = new Thread(run_environment);
        Thread thread_agent = new Thread(run_agent);

        thread_environment.start();
        thread_agent.start();
    }

}
