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

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msgIn;
            String msgOut;

            while (socket.isBound()) {
                msgIn = dis.readUTF();
                System.out.println(msgIn);
                msgOut = br.readLine();
                dos.writeUTF(msgOut);
                dos.flush();
            }

            dis.close();
            dos.close();
            br.close();
            socket.close();
            server.close();
        } catch (IOException e) {
            System.out.println("Something went wrong..." + e);
        }
    }
}
