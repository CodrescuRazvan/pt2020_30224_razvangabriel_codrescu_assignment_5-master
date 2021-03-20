import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

public class Task6 {

    public List<String> activityFilter(ArrayList<MonitoredData> monitoredData){
        List<String> activities;
        Map<String, Integer> numberOfActivities = monitoredData.stream().collect(groupingBy(d -> d.getActivity(), Collectors.summingInt(d -> 1)));
        Map<String, Integer> timeForEach = monitoredData.stream().filter(d -> {
        int seconds = Math.abs((d.getStartTime().getSeconds() + d.getStartTime().getMinutes() * 60 + d.getStartTime().getHours() * 3600) -
                (d.getEndTime().getSeconds() + d.getEndTime().getMinutes() * 60 + d.getEndTime().getHours() * 3600));
        if(seconds < 300)
            return true;
        return false;}).collect(groupingBy(d -> d.getActivity(),summingInt(d -> 1)));
        activities = timeForEach.entrySet().stream().filter(d -> numberOfActivities.get(d.getKey()) * 0.9 <= d.getValue()).map(d -> d.getKey()).collect(Collectors.toList());
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task_6.txt"));
            bw.write("Activities that have more than 90% of the monitoring records with duration less than 5 minutes:\n");
            for (String s : activities) {
                bw.write(s);
                bw.newLine();
            }
            bw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
        return activities;
    }
}
