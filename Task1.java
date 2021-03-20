import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task1 {

    public void dataRead(String file, ArrayList<MonitoredData> monitoredData){
        try{
            Stream<String> stream = Files.lines(Paths.get(file));
            BufferedWriter bw = new BufferedWriter(new FileWriter("Task_1.txt"));
            bw.write("Monitored Data content : \n");
            List<String[]> list = stream.map(string -> string.split("\t\t")).collect(Collectors.toList());
            for(int i = 0; i < list.size(); i++){
                 SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                 Date dateStart = parser.parse(list.get(i)[0]);
                 Date dateEnd = parser.parse(list.get(i)[1]);
                 MonitoredData mn = new MonitoredData();
                 mn.setStartTime(dateStart);
                 mn.setEndTime(dateEnd);
                 mn.setActivity(list.get(i)[2]);
                 bw.write(mn.getStartTime() + " " + mn.getEndTime() + " " + mn.getActivity());
                 bw.newLine();
                 monitoredData.add(mn);
            }
            bw.flush();
        }catch(IOException | ParseException e){
            e.printStackTrace();
        }
    }
}
