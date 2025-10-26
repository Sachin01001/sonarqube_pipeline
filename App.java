import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws IOException {
        int port = 2000; // Hardcoded port (SonarQube: code smell)
        String unused = "unused variable"; // SonarQube: unused local variable

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null);
        System.out.println("Server started at http://localhost:" + port + "/");
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello, Jenkins Pipeline!";

            // Empty catch block (SonarQube: should handle exception)
            try {
                exchange.sendResponseHeaders(200, response.length());
            } catch (IOException e) {
                // intentionally empty
            }

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            // Missing os.close() (SonarQube: resource leak)
        }
    }
}
