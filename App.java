import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {

    public static void main(String[] args) throws IOException {
        // Create HTTP server on port 2000
        HttpServer server = HttpServer.create(new InetSocketAddress(2000), 0);
        server.createContext("/", new MyHandler());
        server.setExecutor(null); // creates a default executor
        System.out.println("Server started at http://localhost:2000/");
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String response = "Hello, Jenkins Pipeline!";
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

// package com.example;

// public class Sample {
//     public static void main(String[] args) {
//         System.out.println("Hello"); // Non-compliant — should use a logger instead
//         int x = 10 / 0;              // Non-compliant — possible division by zero
//     }
// }

    
// // }
