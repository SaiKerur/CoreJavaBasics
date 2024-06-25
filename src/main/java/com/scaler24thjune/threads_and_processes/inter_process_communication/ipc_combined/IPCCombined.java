package com.scaler24thjune.threads_and_processes.inter_process_communication.ipc_combined;

import java.io.*;
import java.net.*;

public class IPCCombined {

    public static void main(String[] args) {
        // Start the server thread
        new Thread(new ServerThread()).start();

        // Delay to ensure the server starts before the client
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Start the client thread
        new Thread(new ClientThread()).start();
    }

    // Server Thread
    static class ServerThread implements Runnable {
        @Override
        public void run() {
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

    // Client Thread
    static class ClientThread implements Runnable {
        @Override
        public void run() {
            try (Socket socket = new Socket("localhost", 5000);
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                String userInput;
                while ((userInput = stdIn.readLine()) != null) {
                    out.println(userInput);
                    System.out.println("Echo from server: " + in.readLine());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

