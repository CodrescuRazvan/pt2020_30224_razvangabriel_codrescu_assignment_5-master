import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Task2 {

    private int c;

    public boolean counted(int a, int b){
        if(a != b){
            c = b;
            return true;
        }
        return false;
    }

    public void dayCounting(ArrayList<MonitoredData> monitoredData){
        long counter = 0;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task_2.txt"));
            c = monitoredData.get(0).getStartTime().getDay();
            counter = monitoredData.stream().filter(d -> counted(c, d.getStartTime().getDay())).count();
            bw.write("Distinct days : " + (int)counter);
            bw.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
