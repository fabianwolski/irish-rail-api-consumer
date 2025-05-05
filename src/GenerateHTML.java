import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Map;

public class GenerateHTML {
    
    private static final String RESOURCES = "/resources";
    private static final String OUTPUT = "output";
    
    public String generateTimetable(List<TrainDetails> trains, String stationCode) throws IOException{
        mkdirOutput();
        saveResourceToOutput("styles.css", OUTPUT + "/css/styles.css");
        saveResourceToOutput("script.js", OUTPUT + "/js/script.js");
        
        String htmlTemplate = readResourceFile("template.html");

        //mapping template changes
        Map<String, String> changes = new HashMap<>();
        changes.put("{{STATION_CODE}}", stationCode);
        changes.put("{{CURRENT_DATETIME}}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        changes.put("{{TRAIN_ROWS}}", generateTrainRows(trains));
        changes.put("{{TRAIN_COUNT}}", String.valueOf(trains.size()));

        //replacing
        for (Map.Entry<String, String> entry : changes.entrySet()) {
            htmlTemplate = htmlTemplate.replace(entry.getKey(), entry.getValue());
        }
        //saving
        String fileName = stationCode + "_timetable.html";
        String path = OUTPUT + File.separator + fileName;
        Files.writeString(Paths.get(path), htmlTemplate);

        return path;
    }

    private String generateTrainRows(List<TrainDetails> trains) {
        if (trains.isEmpty()) {
            return "<tr><td colspan=\"5\">No trains scheduled.</td></tr>";
        }
        StringBuilder rows = new StringBuilder();
        for (TrainDetails train : trains) {
            String statusClass = train.status.toLowerCase().contains("late") ? "late" : "";

            String rowTemplate =
                    "<tr>\n" +
                            "  <td>%s</td>\n" +
                            "  <td>%s</td>\n" +
                            "  <td>%s</td>\n" +
                            "  <td>%s</td>\n" +
                            "  <td class=\"%s\">%s</td>\n" +
                            "</tr>\n";
            String row = String.format(
                    rowTemplate,
                    train.origin,
                    train.destination,
                    train.expectedArrival,
                    train.expectedDeparture,
                    statusClass,
                    train.status
            );
            rows.append(row);}
        return rows.toString();
    }

    private void mkdirOutput() throws IOException {
        Files.createDirectories(Paths.get(OUTPUT));
        Files.createDirectories(Paths.get(OUTPUT, "css"));
        Files.createDirectories(Paths.get(OUTPUT, "js"));
    }
    
    private String readResourceFile(String fileName) throws IOException {
        try (InputStream inputStream = GenerateHTML.class.getResourceAsStream(RESOURCES + "/" + fileName)) {
            if (inputStream == null) {
                throw new IOException("missing/cannot find" + fileName);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
    
    //save directly not just copy
    private void saveResourceToOutput(String resourceName, String path) throws IOException {
        try (InputStream inputStream = GenerateHTML.class.getResourceAsStream(RESOURCES + "/" + resourceName)) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourceName);
            }

            String content = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            SaveFile.saveToFile(path, content);
        }
    }
}