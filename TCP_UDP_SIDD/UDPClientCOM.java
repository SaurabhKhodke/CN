// Import necessary classes for networking
import java.net.DatagramPacket; // For sending and receiving data packets
import java.net.DatagramSocket; // For creating the socket for UDP communication
import java.net.InetAddress; // For handling IP addresses

// Define the main class for the UDP Client
public class UDPClientCOM {
    public static void main(String[] args) {
        try {
            // Step 1: Create a DatagramSocket to send and receive packets
            DatagramSocket clientSocket = new DatagramSocket();

            // Step 2: Define the server's IP address and port number
            InetAddress serverAddress = InetAddress.getByName("127.0.0.1"); // Loopback address (localhost)
            int serverPort = 55555; // Port on which the server is listening

            // Step 3: Create the message to send to the server
            String message = "Greetings, Server!"; // The message to be sent
            byte[] sendBuffer = message.getBytes(); // Convert the message to bytes for transmission

            // Step 4: Create a DatagramPacket for sending the message
            DatagramPacket sendPacket = new DatagramPacket(
                sendBuffer, // The message in bytes
                sendBuffer.length, // The length of the message
                serverAddress, // The server's IP address
                serverPort // The server's port number
            );

            // Step 5: Send the packet to the server
            clientSocket.send(sendPacket); // Transmit the packet
            System.out.println("Sent to server: " + message); // Log the sent message

            // Step 6: Prepare a buffer to receive a response from the server
            byte[] receiveBuffer = new byte[1024]; // Buffer to store the server's response
            DatagramPacket receivePacket = new DatagramPacket(
                receiveBuffer, // Buffer for the response
                receiveBuffer.length // Size of the buffer
            );

            // Step 7: Receive the response from the server
            clientSocket.receive(receivePacket); // Block until a response is received
            String serverResponse = new String(
                receivePacket.getData(), // Extract the data from the packet
                0, // Start from the first byte
                receivePacket.getLength() // Use the actual length of the received data
            );
            System.out.println("Received from server: " + serverResponse); // Log the server's response

            // Step 8: Close the DatagramSocket to release resources
            clientSocket.close(); // Properly terminate the connection
        } catch (Exception e) { // Catch any exceptions that might occur
            e.printStackTrace(); // Print the stack trace for debugging
        }
    }
}
