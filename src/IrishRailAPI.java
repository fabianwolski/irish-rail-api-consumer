import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class IrishRailAPI {

    private static final String DATA = "http://api.irishrail.ie/realtime/realtime.asmx/getStationDataByCodeXML";
    private static final String STATIONDATA = "http://api.irishrail.ie/realtime/realtime.asmx/getAllStationsXML";

    private final HttpClient client;

    //constructor - initialize the HTTP client
    public IrishRailAPI() {
        this.client = HttpClient.newHttpClient();
    }

    public String getData(String code, String mins) throws IOException, InterruptedException {
        String url = DATA + "?StationCode=" + code + "&NumMins=" + mins;

        //System.out.println("fetch:"+ url);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to fetch data: HTTP " + response.statusCode());
        }

        return response.body();
    }

    public String getStations() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(STATIONDATA))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IOException("Failed to fetch stations: HTTP " + response.statusCode());
        }

        return response.body();
    }
}