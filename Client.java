import java.io.*;// imports all classes from the java io package
import java.net.*;// imports all networking classes

public class Client {// Client class to connect to the server and send/receive messages
    public static void main(String[] args) throws IOException {//main method which is the entry point of the program and could throw an IOException
        Socket socket = new Socket("localhost", 1234);//Connect to server at localhost:1234
        System.out.println("Connected to server.");// prints status message

        //Set up I/O
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));// prepares the way to receive messages from the server
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);// prepares the way to send messages to the server
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));// prepares the way to read user input from the console
        System.out.print("Enter your username: ");// asks user for their username
        String username = userInput.readLine();// reads the username from the console
        out.println(username); // Send username to the server first
        System.out.println("You are now connected as [" + username + "]. Start messaging!");// prints a message when the user is connected

        //Thread to read messages from server
        Thread readerThread = new Thread(() -> {
            String serverMessage;// message received from the server
            try {
                while ((serverMessage = in.readLine()) != null) {// reads messages from the server
                    System.out.println(serverMessage);// prints the message to the console
                }

            } catch (IOException e) {// catches any IO exceptions that occur during communication
                System.out.println("Disconnected from server.");// prints an error message
            }
        });
        readerThread.start();// starts the thread to read messages from the server

        // 4. Main thread sends messages
        String message;
        while ((message = userInput.readLine()) != null) {// reads messages from the console
            if (message.equalsIgnoreCase("/quit")) {// if the user types /quit, it will leave the chat
                out.println("/quit");// sends the /quit command to the server
                System.out.println("You have left the chat.");// prints a message when the user leaves the chat
                socket.close();// closes the connection
                break;// exits the loop
            }
            out.println(message);// sends the message to the server
        }

        socket.close();// closes the connection to the server
    }
}
// for compiling javac *.java
// for running the server java Server
// for running the client java Client
// sources for making this project : ChatGpt , GeeksForGeeks , youtube