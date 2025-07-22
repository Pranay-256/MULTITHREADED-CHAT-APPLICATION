import java.io.*; //importing everything from java io packkage
import java.net.*;//importing everything from the networking library
import java.util.*;// importing everything from the utility package

public class Server {
    // List to keep track of connected clients
    private static List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {// main method which is the entry point of the program
                                                               // and could throw an IOException
        // Create server socket to listen on port 1234
        ServerSocket serverSocket = new ServerSocket(1234);// any number can be used but 1234 is a common choice but
                                                           // avoid using ports like 80 or 443
        System.out.println("Server started. Waiting for clients...");// prints status message

        while (true) { // infinite loop to keep the server running
            // Accept incoming client connections
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());// prints the IP address of the
                                                                                     // connected client

            // Create and start a new ClientHandler thread
            ClientHandler clientThread = new ClientHandler(clientSocket, clients);
            clients.add(clientThread); // adds the new ClientHandler object
            clientThread.start(); // starts the new ClientHandler thread
        }
    }
}
