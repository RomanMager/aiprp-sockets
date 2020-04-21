package by.bsuir.client;

import java.io.*;
import java.net.Socket;

public class InputThread extends Thread {

    private Socket socket;

    public InputThread(Socket socket) {
        super();
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            while (socket.isBound()) {
                String message = in.readLine();
                out.write(message + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
