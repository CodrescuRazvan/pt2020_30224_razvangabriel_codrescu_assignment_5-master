import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;

public class Task4 {

    public Map<Object, Map<Object, Integer>> countingEach(ArrayList<MonitoredData> monitoredData) {
        Map<Object, Map<Object, Integer>> apparitionsPerDay;
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        apparitionsPerDay = monitoredData.stream().collect(groupingBy(d -> {
            try {
                String currDate = date.format(d.getStartTime());
                String[] spliter = currDate.split(" ");
                return date.parse(spliter[0]).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return 0;
        }, groupingBy(d -> d.getActivity(), summingInt(d -> 1))));
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task_4.txt"));
            bw.write("How many times has appeared for each day over the monitoring period: \n");
            for (Map.Entry activ : apparitionsPerDay.entrySet()) {
                //System.out.println(date.format(activ.getKey()));
                bw.write(date.format(activ.getKey()));
                bw.newLine();
                for (Map.Entry activ2 : ((Map<String, Long>) activ.getValue()).entrySet()) {
                    //System.out.println(activ2.getKey() + "->" + activ2.getValue());
                    bw.write(activ2.getKey() + "->" + activ2.getValue());
                    bw.newLine();
                }
                //System.out.println();
                bw.newLine();
            }
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apparitionsPerDay;
    }
}
