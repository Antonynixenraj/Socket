import java.net.*;
import java.io.*;

public class Server {
  

    public static void main(String args[])
    {
        int port = 1234;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            
            serverSocket.setReuseAddress(true);

            System.out.println("Socket is listening.....");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Got connection from " + clientSocket.getRemoteSocketAddress());

            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                System.out.print(">> ");
                String toSend = consoleReader.readLine();
                writer.println(toSend); 
                String received = reader.readLine(); 
                if (received == null || toSend.equalsIgnoreCase("exit") || received.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.println("$ " + received);
            }

            clientSocket.close();
            System.out.println("Connection closed.");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
