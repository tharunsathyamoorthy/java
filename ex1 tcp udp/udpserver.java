import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udpserver {
    public static void main(String[] args) {
        int port = 9876;

        try (DatagramSocket socket = new DatagramSocket(port)) {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                System.out.println("Waiting for a client...");

                socket.receive(packet); // Receive a packet
                String received = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received: " + received);

                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                String response = "Echo: " + received;
                byte[] responseBytes = response.getBytes();

                DatagramPacket responsePacket = new DatagramPacket(
                    responseBytes, responseBytes.length, clientAddress, clientPort
                );

                socket.send(responsePacket); // Send a response
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
