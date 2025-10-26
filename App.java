import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws IOException {
        // Hardcoded port and IP (⚠️ SonarQube: make configurable)
        int port = 2000;
        System.out.println("Starting server on hardcoded port " + port);

        // Unused variable (⚠️ SonarQube: remove unused variable)
        String message = "This variable is never used";

        // Create HTTP server on port 2000
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("Server started at http://localhost:" + port + "/");
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello, Jenkins Pipeline!";

            // Empty catch block (⚠️ SonarQube: do not ignore exceptions)
            try {
                exchange.sendResponseHeaders(200, response.length());
            } catch (IOException e) {
                // intentionally left blank
            }

            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            // Missing close() (⚠️ SonarQube: close resources properly)
            // os.close();
        }
    }
    public class App {

    public static void main(String[] args) throws IOException {
        // Hardcoded port and IP (⚠️ SonarQube: make configurable)
        int port = 2000;
        System.out.println("Starting server on hardcoded port " + port);

        // Unused variable (⚠️ SonarQube: remove unused variable)
        String message = "This variable is never used";

        // Create HTTP server on port 2000
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("Server started at http://localhost:" + port + "/");
        server.start();
    }
}
