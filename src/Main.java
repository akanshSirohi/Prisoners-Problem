import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        ArrayList<Maze> mazes = new ArrayList<>();
        AtomicInteger total_run_saved = new AtomicInteger();
        AtomicInteger total_run_dead = new AtomicInteger();

        for(int i=0; i<100; i++) {
            Maze m = new Maze("log_run_"+(i+1)+".txt");
            mazes.add(m);
        }

        for(int i=1; i<=100; i++) {
            boolean res = mazes.get(i-1).solve();
            System.out.println("RUN "+i+": "+res);
            if(res) {
                total_run_saved.getAndIncrement();
            }else{
                total_run_dead.getAndIncrement();
            }
        }
        System.out.println("");
        System.out.println("Total Run Saved: "+total_run_saved);
        System.out.println("Total Run Dead: "+total_run_dead);
    }
}
