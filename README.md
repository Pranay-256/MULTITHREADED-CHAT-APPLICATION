# MULTITHREADED-CHAT-APPLICATION

*COMPANY*: CODTECH IT SOLUTIONS

*NAME*: Pranay Jha 

*INTERN ID*: CT04DH539

*DOMAIN*: Java Programming 

*DURATION*: 4 WEEKS

*MENTOR*: NEELA SANTOSH

DESCRIPTION:

This project is a multithreaded console-based chat application built in Java, designed to facilitate real-time text communication between multiple users within a local network environment. The application is based on the classic client-server architecture, where one central server is responsible for accepting, managing, and maintaining all the active client connections. Clients can connect to the server through a specific port, and upon successful connection, they are prompted to enter a unique username. This username is used throughout the chat session to identify users and associate messages with their respective senders. Once connected, each client is able to send and receive messages from others who are concurrently connected to the server.

The server component of the application listens for client connections using a ServerSocket, and every time a new client connects, a separate thread is spawned to handle that particular client’s communication. This is achieved using Java's built-in multithreading capabilities by extending the Thread class in a custom class named ClientHandler. Each ClientHandler thread is responsible for managing its own client’s input and output streams, receiving messages, and broadcasting them to all other connected clients. This multithreaded design ensures that multiple clients can interact simultaneously without blocking or delaying each other, which is a fundamental concept in real-world networked applications.

Communication between the client and server is established using Java’s Socket programming API. On the server side, a ServerSocket waits for incoming connections, and once a client connects, a new Socket object is created to represent that client’s connection. On the client side, a Socket object is used to initiate a connection with the server using the server’s IP address (localhost in this case) and port number. Input and output between the client and server are handled using BufferedReader for reading incoming data and PrintWriter for sending messages. These classes provide efficient text-based communication over the network stream.

A key feature of the application is the broadcast mechanism. When a client sends a message, the server relays it to all other connected clients except the sender, using a shared list of active ClientHandler instances. This helps simulate a group chat environment. Additionally, the application supports a special command, /quit, which allows a user to leave the chat gracefully. When this command is issued, the server notifies other users that the particular client has disconnected, and it properly removes the client from the active list and releases any associated resources, such as open sockets and threads.

From the client side, a secondary thread is created specifically to listen for messages from the server, ensuring that incoming messages can be received asynchronously while the main thread remains free to capture user input. This dual-threaded approach on the client side enhances responsiveness and user experience by preventing input/output blocking.

#OUTPUT:

<img width="688" height="345" alt="Image" src="https://github.com/user-attachments/assets/d5e7665d-4534-4240-8ccf-f7c678b6f7c9" />

<img width="695" height="326" alt="Image" src="https://github.com/user-attachments/assets/f752bc3d-6dc2-4e7c-afeb-8b3d3820a0d2" />

<img width="674" height="292" alt="Image" src="https://github.com/user-attachments/assets/fe6d9a13-d3b1-4737-a09b-065e224df5d9" />





