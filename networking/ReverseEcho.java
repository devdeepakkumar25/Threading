import java.net.*;
import java.io.*;

public class ReverseEcho extends Thread {

    private Socket stk;

    // Constructor to receive socket
    public ReverseEcho(Socket stk) {
        this.stk = stk;
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(stk.getInputStream()));

            PrintStream ps = new PrintStream(stk.getOutputStream());

            String msg;

            while (true) {
                msg = br.readLine();

                if (msg == null || msg.equalsIgnoreCase("end")) {
                    System.out.println("Client disconnected.");
                    break;
                }

                // Reverse message
                String reversed = new StringBuilder(msg).reverse().toString();

                ps.println(reversed); // Send reversed back to client
            }

            stk.close(); // Close client connection

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Main server program
    public static void main(String[] args) throws Exception {

        ServerSocket ss = new ServerSocket(2000);
        System.out.println("Server Started... Waiting for Clients...");
        int count = 1;

        while (true) {
            Socket stk = ss.accept();
            System.out.println("Client Connected! : " + count++);

            ReverseEcho re = new ReverseEcho(stk);
            re.start();
        }
    }
}

