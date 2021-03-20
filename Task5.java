import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Task5 {

    public Map<String, Integer> computeTime(ArrayList<MonitoredData> monitoredData){
        Map<String, Integer> count = monitoredData.stream().collect(groupingBy(d -> d.getActivity(),summingInt(d -> {
                int seconds = Math.abs((d.getStartTime().getSeconds() + d.getStartTime().getMinutes() * 60 + d.getStartTime().getHours() * 3600) -
                          (d.getEndTime().getSeconds() + d.getEndTime().getMinutes() * 60 + d.getEndTime().getHours() * 3600));
                return seconds;
        })));
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task_5.txt"));
            bw.write("Duration for each activity over the monitoring period : \n");
            for (Map.Entry activity : count.entrySet()) {
                int seconds = (Integer) (activity.getValue());
                int minutes = seconds / 60;
                seconds = seconds % 60;
                int hours = minutes / 60;
                minutes = minutes % 60;
                bw.write(activity.getKey() + " : " + hours + ":" + minutes + ":" + seconds);
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }
}
