import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String serverIP = "127.0.0.1";
        int port = 1234;

        try (
            Socket socket = new Socket(serverIP, port);
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected .....");

            while (true) {
                // Receive message from server
                String msg = reader.readLine();

                if (msg == null || msg.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("$ " + msg);

                // Read input from user
                System.out.print(">> ");
                String txt = scanner.nextLine();
                writer.println(txt); // Send to server

                if (txt.equalsIgnoreCase("exit")) {
                    break;
                }
            }

            socket.close();
            System.out.println("Connection closed.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
