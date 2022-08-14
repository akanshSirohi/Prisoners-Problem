import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

public class Maze {
    private final int T_CONST = 100;

    private final ArrayList<Box> boxes = new ArrayList<>();
    private final ArrayList<Prisoner> prisoners = new ArrayList<>();

    private FileWriter fileWriter = null;

    public Maze(String log_file_name) {
        // Populating Prisoners and their boxes
        ArrayList<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= T_CONST; i++) {
            boxes.add(new Box(i));
            prisoners.add(new Prisoner(i));
            nums.add(i);
        }
        Collections.shuffle(nums);
        Collections.shuffle(prisoners);

        // Initializing room for prisoners
        for (int i = 0; i < T_CONST; i++) {
            int x = nums.get(i);
            Box bx = boxes.get(i);
            bx.setValue(x);
            bx.setNextIndex(x - 1);
        }
        try {
            File logFile = new File("logs\\" + log_file_name);
            fileWriter = new FileWriter(logFile);
        }catch (Exception e){}
    }

    public boolean solve() {
        boolean solved = true;
        int allowedBoxesToOpen = 50;
        for (int i=0; i < T_CONST; i++) {
            Prisoner prisoner = prisoners.get(i);
            writeLog("Prisoner Number: "+prisoner.getNum()+", started solving!");
            int prisonerVisitedBoxes = 1;
            int prisonerAt = prisoner.getNum() - 1;
            boolean isPrisonerSuccessful = false;
            writeLog("Prisoner "+prisoner.getNum()+" Initially At Pos: "+prisonerAt);
            writeLog("\n");
            while (prisonerVisitedBoxes != allowedBoxesToOpen) {
                Box bx = boxes.get(prisonerAt);
                writeLog("Prisoner "+prisoner.getNum()+" Go To Box: "+bx.getLabel());
                writeLog("Box has number: "+bx.getValue());
                writeLog("Next box is at: "+bx.getNextIndex());
                writeLog("");
                if (bx.getValue() == prisoner.getNum()) {
                    writeLog((i+1)+": Prisoner "+ prisoner.getNum() +" reached to his box in steps: " + (prisonerVisitedBoxes + 1));
                    isPrisonerSuccessful = true;
                    break;
                }
                prisonerAt = bx.getNextIndex();
                prisonerVisitedBoxes++;
            }

            if (!isPrisonerSuccessful) {
                writeLog((i+1)+": Prisoner "+ prisoner.getNum() +" failed and now all prisoners will die!");
                solved = false;
                break;
            }
            writeLog("Prisoner Number: "+prisoner.getNum()+", end solving!");

            writeLog("\n==================================================================\n");
        }
        if(solved) {
            writeLog("All prisoners are saved in this run!");
        }else{
            writeLog("All prisoners will die in this run!");
        }
        // Close Log File
        try {
            fileWriter.close();
        }catch (Exception e){}
        return solved;
    }

    private void writeLog(String str) {
        try {
            fileWriter.write(str+"\n");
        }catch (Exception e){}
    }
}
