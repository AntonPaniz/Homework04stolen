package src.com.VivaJavaka.utils;

import com.VivaJavaka.domain.Client;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class QueueGenerator implements Runnable {
    BlockingQueue<com.VivaJavaka.domain.Client> queue;
    private int sizeOfClients;

    public QueueGenerator(BlockingQueue<com.VivaJavaka.domain.Client> queue, int sizeOfClients) {
        this.sizeOfClients = sizeOfClients;
        this.queue = queue;
    }

    public BlockingQueue<Client> getQueue() {
        return queue;
    }

    public void setQueue(BlockingDeque<Client> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < sizeOfClients; i++) {
                Client client = Generators.generateClient();
                queue.put(client);
                System.out.println("client " + client.getId() + " waiting for the answer.");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("client write interrupted");
                }
            }
        } catch (InterruptedException ex) {
            System.out.println("client  interrupted");
        }
    }
}
