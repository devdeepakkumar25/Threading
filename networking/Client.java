import java.net.*;
import java.io.*;

public class Client {
    public static void main(String[] args) throws Exception {

        Socket stk = new Socket("localhost", 2000);
        System.out.println("Connected to Server!");

        BufferedReader keyb = new BufferedReader(new InputStreamReader(System.in));

        BufferedReader br = new BufferedReader(new InputStreamReader(stk.getInputStream()));

        PrintStream ps = new PrintStream(stk.getOutputStream());

        String msg;

        while (true) {
            System.out.print("Enter message : ");
            msg = keyb.readLine();

            ps.println(msg); // Send to server

            if (msg.equalsIgnoreCase("end")) {
                System.out.println("Closing client...");
                break;
            }

            String reply = br.readLine(); // Receive reversed text
            System.out.println("From Server : " + reply);
        }

        stk.close();
    }
}
