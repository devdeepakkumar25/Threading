import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(2000);
        System.out.println("Server Started... Waiting for Client...");

        Socket stk = ss.accept();
        System.out.println("Client Connected!");

        BufferedReader br = new BufferedReader(
                new InputStreamReader(stk.getInputStream()));
        PrintStream ps = new PrintStream(stk.getOutputStream());

        String msg;

        while (true) {
            msg = br.readLine();
            if (msg == null || msg.equalsIgnoreCase("end")) {
                System.out.println("Client ended the chat.");
                break;
            }

            // Reverse message
            StringBuilder sb = new StringBuilder(msg);
            sb.reverse();
            String reversed = sb.toString();

            ps.println(reversed); // Send reversed back to client
        }

        stk.close();
        ss.close();
    }
}
