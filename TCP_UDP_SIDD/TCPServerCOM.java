// Import necessary classes for handling I/O and networking
import java.io.*; // For input and output streams
import java.net.*; // For networking classes like ServerSocket and Socket

// Define the main class for the TCP Server
public class TCPServerCOM {
    public static void main(String[] args) {
        try {
            // Step 1: Create a server socket that listens on port 65433
            ServerSocket serverSocket = new ServerSocket(65433); 
            System.out.println("Server listening on port 65433..."); // Inform user that the server is active

            // Step 2: Accept an incoming connection from a client
            Socket clientSocket = serverSocket.accept(); 
            System.out.println("Connected to client: " + clientSocket.getInetAddress()); 
            // Display the IP address of the connected client

            // Step 3: Set up input and output streams for communication
            BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream())
            ); 
            // 'in' reads messages sent by the client

            PrintWriter out = new PrintWriter(
                clientSocket.getOutputStream(), 
                true
            ); 
            // 'out' is used to send messages to the client. The 'true' argument enables auto-flushing.

            // Step 4: Read the message sent by the client
            String clientMessage = in.readLine(); 
            System.out.println("Received from client: " + clientMessage); 
            // Print the client's message to the server's console

            // Step 5: Prepare a response message to send back to the client
            String response = "Server says: Message received - " + clientMessage; 
            out.println(response); 
            // Send the response back to the client

            // Step 6: Close the connection to release resources
            clientSocket.close(); // Close the client's socket connection
            serverSocket.close(); // Close the server socket
        } catch (IOException e) { // Handle potential input/output exceptions
            e.printStackTrace(); // Print the exception details for debugging
        }
    }
}
