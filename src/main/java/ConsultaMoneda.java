import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {
    public String obtenerDatosMoneda(String base) {
        try {
            String apiKey = "00e2780ed30751ae99000a90";
            String direccion = "https://v6.exchangerate-api.com/v6/" + apiKey + "/latest/" + base;

            HttpClient cliente = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(direccion))
                    .build();

            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (Exception e) {
            throw  new RuntimeException("Error al consultar la API: " + e.getMessage());
        }
    }
}
