// Import necessary classes for UDP communication
import java.net.DatagramPacket; // For handling data packets
import java.net.DatagramSocket; // For creating the socket for communication
import java.net.InetAddress; // For handling IP addresses

// Define the main class for the UDP Server
public class UDPServerCOM {
    @SuppressWarnings("resource") // Suppress warnings about not explicitly closing the DatagramSocket
    public static void main(String[] args) {
        try {
            // Step 1: Create a DatagramSocket to listen on a specific port (55555)
            DatagramSocket serverSocket = new DatagramSocket(55555); 
            System.out.println("Server is listening on port 55555...");

            // Step 2: Create buffers for receiving and sending data
            byte[] receiveBuffer = new byte[1024]; // Buffer to store data received from clients
            byte[] sendBuffer; // Buffer to store the server's response

            // Step 3: Create a DatagramPacket to receive incoming data
            DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);

            // Step 4: Enter a loop to handle multiple client requests
            while (true) {
                // Step 4.1: Receive a packet from the client
                serverSocket.receive(receivePacket); // Blocks until a packet is received
                String clientMessage = new String(
                    receivePacket.getData(), // Extract the message bytes
                    0, // Start from the first byte
                    receivePacket.getLength() // Use the actual length of the message
                );
                System.out.println("Received from client: " + clientMessage); // Log the client's message

                // Step 4.2: Extract the client's address and port from the received packet
                InetAddress clientAddress = receivePacket.getAddress(); // IP address of the client
                int clientPort = receivePacket.getPort(); // Port number of the client

                // Step 4.3: Prepare a response message to send back to the client
                String response = "Server acknowledged: " + clientMessage; // Response text
                sendBuffer = response.getBytes(); // Convert the response to bytes

                // Step 4.4: Create a DatagramPacket to send the response
                DatagramPacket sendPacket = new DatagramPacket(
                    sendBuffer, // The response in bytes
                    sendBuffer.length, // The length of the response
                    clientAddress, // The client's IP address
                    clientPort // The client's port number
                );

                // Step 4.5: Send the response to the client
                serverSocket.send(sendPacket); // Transmit the packet back to the client
            }

            // Step 5: Close the DatagramSocket (unreachable in this loop unless explicitly exited)
            // serverSocket.close();
        } catch (Exception e) { // Catch any exceptions that may occur
            e.printStackTrace(); // Print the stack trace for debugging purposes
        }
    }
}
