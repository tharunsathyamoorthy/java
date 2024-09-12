import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class udpclient {
    public static void main(String[] args) {
        String hostname = "localhost";
        int port = 9876;

        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress address = InetAddress.getByName(hostname);

            byte[] buffer = new byte[1024];
            DatagramPacket packet;
            String message = "Iam THARUNKUMAR";
            
            // Sending message to the server
            byte[] messageBytes = message.getBytes();
            packet = new DatagramPacket(messageBytes, messageBytes.length, address, port);
            socket.send(packet);
            
            // Receiving response from the server
            packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            
            String response = new String(packet.getData(), 0, packet.getLength());
            System.out.println("Server response: " + response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
