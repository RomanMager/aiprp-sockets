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

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String msgIn = "";
            String msgOut = "";

            while (socket.isBound()) {
                msgOut = br.readLine();
                dos.writeUTF(msgOut);
                msgIn = dis.readUTF();
                System.out.println(msgIn);
            }

            dis.close();
            dos.close();
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
