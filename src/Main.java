import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) {
        ArrayList<Maze> mazes = new ArrayList<>();
        AtomicInteger total_run_saved = new AtomicInteger();
        AtomicInteger total_run_dead = new AtomicInteger();

        int TOTAL_RUNS = 100; // This number can be changed for more or less runs

        for(int i = 0; i < TOTAL_RUNS; i++) {
            Maze m = new Maze("log_run_"+(i+1)+".txt");
            mazes.add(m);
        }

        for(int i = 0; i < TOTAL_RUNS; i++) {
            boolean res = mazes.get(i).solve();
            System.out.println("RUN "+(i+1)+": "+res);
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
