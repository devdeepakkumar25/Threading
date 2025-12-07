import java.net.*;
import java.io.*;

public class DatagramClient {

    public static void main(String[] args) {

        try {
            DatagramSocket ds = new DatagramSocket();

            String msg = "Hello World";

            // Send packet to server
            DatagramPacket dp = new DatagramPacket(
                    msg.getBytes(),
                    msg.length(),
                    InetAddress.getByName("localhost"),
                    2000);

            ds.send(dp);

            // Buffer for receiving data
            byte[] b = new byte[1024];

            dp = new DatagramPacket(b, b.length);
            ds.receive(dp);

            msg = new String(dp.getData(), 0, dp.getLength()).trim();

            System.out.println("From Server : " + msg);

            ds.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
