package src.com.VivaJavaka.domain;

import com.VivaJavaka.domain.Client;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Operator implements Runnable{
    BlockingDeque<com.VivaJavaka.domain.Client> queue;
    private static final AtomicInteger COUNTER = new AtomicInteger(1);
    private int id;

    public Operator(BlockingDeque<Client> queue) {
        this.queue = queue;
        this.id = COUNTER.getAndIncrement();;
    }

    public BlockingDeque<Client> getQueue() {
        return queue;
    }

    public int getId() {
        return id;
    }

    public void setQueue(BlockingDeque<Client> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while(true){
                Client client = queue.poll(1, TimeUnit.SECONDS);

                        if (client == null) {
                            System.out.println("опер " + id + " закон раб ");
                            break;
                        }

                        try {
                            Thread.sleep(1000);
                            System.out.println("operator  " + id + " talk to client" + client.getId());
                        } catch (InterruptedException ex) {
                            System.out.println("operator Read Interrrupted");
                        }
            }
        } catch (InterruptedException ex) {
            System.out.println("operator interrupted");
        }
    }

}
