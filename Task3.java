import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Task3 {

    public Map<String, Integer> activityCounter(ArrayList<MonitoredData> monitoredData){
        Map<String, Integer> counter = monitoredData.stream().collect(groupingBy(d -> d.getActivity(), Collectors.summingInt(e -> 1)));
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task_3.txt"));
            bw.write("Apparition of each activity over the entire entire monitoring period: \n");
            for(Map.Entry<String, Integer> obj : counter.entrySet()){
                bw.write(obj.getKey() + ":" + obj.getValue().toString());
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return counter;
    }
}
