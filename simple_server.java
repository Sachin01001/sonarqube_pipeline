import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        // Read environment variables
        String portEnv = System.getenv("PORT");
        int port = (portEnv != null) ? Integer.parseInt(portEnv) : 3000;
        String message = System.getenv("MESSAGE");
        if (message == null) {
            message = "Hello World!";
        }

        // Create an HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);

        // Define a simple handler for root path
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String response = message;
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        });

        // Start the server
        server.start();
        System.out.println("✅ Server is running on port " + port);
    }
}
