package by.bsuir.client;


import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("address: ");
        String address = reader.readLine();
        System.out.print("port: ");
        String port = reader.readLine();

        try (Socket socket = new Socket(address, Integer.parseInt(port))) {
            InputThread inputThread = new InputThread(socket);
            inputThread.start();
            socket.setSoTimeout(600000000);
            System.out.println("Connected to server (" + address + ":" + port + ")");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (socket.isBound()) {
                String response = in.readLine();
                System.out.println("server: " + response);
            }
            in.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
