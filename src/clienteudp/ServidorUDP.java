package clienteudp;

import java.io.IOException;
import java.net.*;

public class ServidorUDP {

    private static int PORT = 4501;

    public static void main(String[] args) throws IOException {

        DatagramSocket serverSocket = new DatagramSocket(PORT);
        System.err.println("Server listening on port " + PORT + " using UDP conection\n");
        //medir tiempo del programa
        long initialTime = System.currentTimeMillis();
        System.out.println("Tiempo inicial: " + initialTime + "\n");

        try {
            while (true) {
                byte bufferRecieve[] = new byte[128];
                DatagramPacket receivePacket = new DatagramPacket(bufferRecieve, bufferRecieve.length);
                serverSocket.receive(receivePacket);
                // información del puerto del cliente
                InetAddress clientAdress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();
                System.out.println("Client port: " + clientPort + "\n");

                //Send packet
                String msg = "Message from Server";
                byte bufferSend[] = msg.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(bufferSend, bufferSend.length, clientAdress, PORT);
                serverSocket.send(sendPacket);
                serverSocket.close();
            }
        } finally {
            serverSocket.close();
        }
    }
}
