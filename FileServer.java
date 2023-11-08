import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class FileServer {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try {
            ServerSocket serverSocket = new ServerSocket(5555);

            int num;
            System.out.println("Enter number of clients");
            num=sc.nextInt();
            System.out.println("Server is waiting for clients...");
            for (int i = 0; i < num; i++) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());

                // Create a new thread for each client
                Thread clientHandler = new Thread(new ClientHandler(clientSocket));
                clientHandler.start();
            }

            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
