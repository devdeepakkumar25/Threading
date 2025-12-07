import java.net.*;
import java.io.*;

public class DatagramServer {

    public static void main(String[] args) {

        try {
            DatagramSocket ds = new DatagramSocket(2000);

            byte[] b = new byte[1024];
            DatagramPacket dp = new DatagramPacket(b, b.length);

            // Receive message from client
            ds.receive(dp);
            String msg = new String(dp.getData(), 0, dp.getLength()).trim();

            System.out.println("From Client : " + msg);

            // Reverse the message
            StringBuilder sb = new StringBuilder(msg);
            sb.reverse();
            msg = sb.toString();

            // Send response back to client using client's address and port
            InetAddress clientAddr = dp.getAddress();
            int clientPort = dp.getPort();

            dp = new DatagramPacket(msg.getBytes(), msg.length(), clientAddr, clientPort);
            ds.send(dp);

            ds.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
