// Import necessary classes for handling I/O and networking
import java.io.*; // For input and output operations
import java.net.*; // For networking classes like Socket

// Define the main class for the TCP Client
public class TCPClientCOM {
    public static void main(String[] args) {
        try {
            // Step 1: Create a socket to connect to the server
            // The client connects to the server running on localhost (127.0.0.1) and port 65433
            Socket socket = new Socket("127.0.0.1", 65433);

            // Step 2: Set up output and input streams for communication
            PrintWriter out = new PrintWriter(
                socket.getOutputStream(), 
                true
            ); 
            // 'out' is used to send messages to the server. The 'true' argument enables auto-flushing of the stream.

            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream())
            ); 
            // 'in' reads messages sent by the server.

            // Step 3: Send a message to the server
            String message = "Hi, Server! I am the client."; 
            // The message the client sends to the server
            out.println(message); 
            // Send the message to the server
            System.out.println("Sent to server: " + message); 
            // Print the sent message for confirmation

            // Step 4: Receive the server's response
            String serverResponse = in.readLine(); 
            // Read the server's response using the input stream
            System.out.println("Received from server: " + serverResponse); 
            // Print the server's response to the client console

            // Step 5: Close the connection to release resources
            socket.close(); // Close the socket connection to the server
        } catch (IOException e) { // Handle potential input/output exceptions
            e.printStackTrace(); // Print exception details for debugging
        }
    }
}
