import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ApiTest {

    public void method1(){
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.weatherxu.com/v1/weather?" +
                        "api_key=931b36626699d66bae868e85c0fe21cd" +
                        "&lat=48.343084&lon=33.497936" +
                        "&parts=daily"))
                .build();
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject object = new JSONObject(response.body().toString());
//          System.out.println(object.toString(1));
            JSONObject data = object.getJSONObject("data");
            JSONObject daily = data.getJSONObject("daily");
            JSONArray dailyData = daily.getJSONArray("data");
//            System.out.println(dailyData.toString(1) );
            for (int i = 0; i < dailyData.length(); i++) {
                System.out.println(dailyData.getJSONObject(i).getFloat("windGustAvg"));

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
