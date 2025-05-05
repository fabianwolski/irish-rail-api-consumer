import java.util.Scanner;
import java.io.File;
import java.util.List;
import java.awt.Desktop;
public class Main {

    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter station code-defaults to ENFLD: ");
            String code = scanner.nextLine().trim();

            if (code.isEmpty()) {
                code = "ENFLD";
            }

            System.out.print("Enter time window in minutes-defaults to 90(90 is max): ");
            String mins = scanner.nextLine().trim();
            if (mins.isEmpty()) {
                mins = "90";
            }
            scanner.close();

            //fetch data
            IrishRailAPI api = new IrishRailAPI();
            String data = api.getData(code, mins);
            //System.out.println(data);
            //parsing
            ParseXML parser = new ParseXML();
            List<TrainDetails> trains = parser.parseTrainData(data);

            System.out.println("trains: " + trains.size());
            for (TrainDetails train : trains) {
                System.out.println(train);
            }


            //create HTML+save file
            GenerateHTML htmlGenerator = new GenerateHTML();
            String pathOnSave = htmlGenerator.generateTimetable(trains, code);
            System.out.println("see file path for save: " + pathOnSave);

            //open xml auto in browser
            File htmlFile = new File(pathOnSave);
            if(htmlFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            }else {
                System.out.println("Auto open fail, open manually instead");
            }

        } catch (Exception e) {
            System.err.println("error: " + e.getMessage());
        }
    }
}