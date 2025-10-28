// package com.example;

// import java.io.*;
// import java.net.*;
// import java.util.*;

// public class SimpleHttpServer {

//     private static String PASSWORD = "admin123"; // Hardcoded password (Security Hotspot)

//     public static void main(String[] args) {
//         new SimpleHttpServer().startServer(8080);
//     }

//     // Unused private method (Code Smell)
//     private void unusedMethod() {
//         System.out.println("This method is never used!");
//     }

//     public void startServer(int port) {
//         try {
//             ServerSocket serverSocket = new ServerSocket(port);
//             System.out.println("Server started on port " + port);

//             while (true) {
//                 Socket clientSocket = serverSocket.accept();
//                 handleClient(clientSocket);
//             }

//         } catch (IOException e) {
//             e.printStackTrace(); // Bad practice: printing stack trace directly
//         }
//     }

//     private void handleClient(Socket clientSocket) {
//         try (BufferedReader in = new BufferedReader(
//                 new InputStreamReader(clientSocket.getInputStream()));
//              PrintWriter out = new PrintWriter(clientSocket.getOutputStream())) {

//             String line;
//             StringBuilder request = new StringBuilder();
//             while ((line = in.readLine()) != null && !line.isEmpty()) {
//                 request.append(line).append("\n");
//             }

//             // Poor logging (Code Smell)
//             System.out.println("Received request: " + request);

//             // Potential vulnerability: not sanitizing user input
//             out.println("HTTP/1.1 200 OK");
//             out.println("Content-Type: text/html");
//             out.println();
//             out.println("<h1>Hello World!</h1>");
//             out.flush();

//         } catch (Exception e) {
//             // Catching generic Exception (Code Smell)
//             System.out.println("Error: " + e.getMessage());
//         } finally {
//             try {
//                 clientSocket.close();
//             } catch (IOException ignored) {
//             }
//         }
//     }
// }

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
    
}

