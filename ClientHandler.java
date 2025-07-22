import java.io.*;//improrts all classes from the java io package
import java.net.*;//imorts all networking classes
import java.util.*;//imports utility classes

public class ClientHandler extends Thread {// this means ClientHandler is a thread
    private String username;// stores the name of the client
    private Socket socket;// represents the connection between the client and server
    private List<ClientHandler> clients;// shared list of all connected clients
    private PrintWriter out;// sends text data to client
    private BufferedReader in;// reads incoming text data from client

    public ClientHandler(Socket socket, List<ClientHandler> clients) throws IOException {// constructor
        this.socket = socket;// stores the socket passed by server
        this.clients = clients;// stores the shared list of all connected clients
        this.out = new PrintWriter(socket.getOutputStream(), true);// prepares the way to send messages to the client
        this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));// prepares the way to receive
                                                                                     // messages from the client

        // Add this client to the list before broadcasting
        clients.add(this);
    }

    public void run() {// the heart of the chat
        String message; // message to be sent or received
        try {
            username = in.readLine(); // reads the first message from the client
            broadcastMessage("Server: " + username + " has joined the chat!");// sends a message to all other clients

            while ((message = in.readLine()) != null) {// waits for the client to send the message
                if (message.equalsIgnoreCase("/quit")) {// if the client sends /quit, it will leave the chat
                    broadcastMessage("Server: " + username + " has left the chat.");// notifies all other clients
                    break;// exits the loop
                }
                System.out.println(username + " says: " + message);// prints the message to the server console
                broadcastMessage("[" + username + "]: " + message);// sends the message to all other clients
            }
        } catch (IOException e) {// catches any IO exceptions that occur during communication
            System.out.println("Connection with " + username + " closed unexpectedly.");// prints an error message
        } finally {// cleanup code that runs after the loop ends
            try {// closes the socket and removes this client from the list
                socket.close();// closes the connection
                clients.remove(this);// removes this client from the shared list
            } catch (IOException e) {// catches any IO exceptions that occur during cleanup
                e.printStackTrace();// prints the stack trace for debugging
            }
        }
    }

    // Send message to all connected clients
    private void broadcastMessage(String message) {// sends a message to all connected clients except the sender
        for (ClientHandler client : clients) {// iterates through the list of connected clients
            if (client != this) { // 👈 skip the sender
                client.out.println(message);// sends the message to each client
            }
        }

    }
}
