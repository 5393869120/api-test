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
                        "&lat=40.7128&lon=-74.0060" +
                        "&parts=currently"))
                .build();
        try {
            HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject object = new JSONObject(response.body().toString());
//            System.out.println(object.toString(1));
            JSONObject data = object.getJSONObject("data");
            JSONObject currently = data.getJSONObject("currently");
            System.out.println("Current temperature is " + currently.getFloat("temperature") + " degrees");
            System.out.println("Current pressure is " + currently.getFloat("pressure" ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
