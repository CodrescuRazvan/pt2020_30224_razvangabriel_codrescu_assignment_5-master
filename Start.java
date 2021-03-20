import java.util.ArrayList;

public class Start {

    public static void main(String[] args) {
        ArrayList<MonitoredData> monitoredData = new ArrayList<>();
        Task1 ts= new Task1();
        Task2 rs = new Task2();
        Task3 t3 = new Task3();
        Task4 t4 = new Task4();
        Task5 t5 = new Task5();
        Task6 t6 = new Task6();
        ts.dataRead("Activity.txt", monitoredData);
        rs.dayCounting(monitoredData);
        t3.activityCounter(monitoredData);
        t4.countingEach(monitoredData);
        t5.computeTime(monitoredData);
        t6.activityFilter(monitoredData);
    }
}
