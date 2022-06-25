package src.com.VivaJavaka;

import src.com.VivaJavaka.domain.Operator;
import src.com.VivaJavaka.utils.QueueGenerator;

import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
       int numClients = 10;
       int numOperators = 3;
        ExecutorService executorClient = Executors.newFixedThreadPool(1);
        ExecutorService executorOperators = Executors.newFixedThreadPool(numOperators);

        BlockingQueue < com.VivaJavaka.domain.Client> queue = new PriorityBlockingQueue<>(5);
        QueueGenerator queueGenerator = new QueueGenerator(queue, numClients);

        System.out.println("call center start to work");

        executorClient.submit(new Thread(queueGenerator));

        for (int i = 0; i < numOperators; i++) {
            executorOperators.submit(new Thread(new Operator(queue)));

            executorClient.shutdown();
            executorOperators.shutdown();

            while (true){
                if (executorOperators.isTerminated()){
                    System.out.println("call center end to work");
                    break;
                }
            }

        }

    }
}
