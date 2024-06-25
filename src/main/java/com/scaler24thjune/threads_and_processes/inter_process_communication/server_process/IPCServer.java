package com.scaler24thjune.threads_and_processes.inter_process_communication.server_process;

import java.io.*;
import java.net.*;

public class IPCServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server started and waiting for clients...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {

                    String message;
                    while ((message = in.readLine()) != null) {
                        System.out.println("Received: " + message);
                        out.println("Echo: " + message);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
