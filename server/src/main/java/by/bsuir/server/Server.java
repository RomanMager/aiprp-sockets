package by.bsuir.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        ServerSocket server;
        Socket socket;
        BufferedWriter out;
        BufferedReader in;

        try {
            try {
                server = new ServerSocket(4004);
                System.out.println("Server successfully started");
            } catch (IOException e) {
                System.out.println("Failed to start server: " + e);
                return;
            }
            socket = server.accept();
            System.out.println(socket.getInetAddress());
            InputThread inputThread = new InputThread(socket);
            inputThread.start();
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (server.isBound()) {
                String message = in.readLine();
                System.out.println("Message from client: " + message);
            }
            System.out.println("server closed");
            out.close();
            in.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Something went wrong..." + e);
        }
    }
}
