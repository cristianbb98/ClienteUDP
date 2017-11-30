package clienteudp;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JOptionPane;

public class ClienteUDP {
//192.168.1.104
    private static final int SERVE_PORT = 4501;
    
    public static void main(String []args) throws IOException{
        String serverAddress = JOptionPane.showInputDialog("Enter IP Adress of a machine that is\n"+
                "running the date service on port "+SERVE_PORT+":");
        
        //Send packet (request)
        DatagramSocket clientSocket = new DatagramSocket();
        byte bufferSend[] = serverAddress.getBytes();  // direccion del servidor (IP -> bytes)
        DatagramPacket sendPacket = new DatagramPacket(bufferSend, bufferSend.length,InetAddress.getByName(serverAddress),
                SERVE_PORT);
        clientSocket.send(sendPacket);
        
        //Receive packet
      byte bufferReceive[] = new byte[128];
      DatagramPacket receivePacket = new DatagramPacket(bufferReceive, bufferReceive.length);
      clientSocket.receive(receivePacket);
      
      // transformar byte a String
        InputStream myInputStream = new ByteArrayInputStream(receivePacket.getData());
        BufferedReader input = new BufferedReader(new InputStreamReader(myInputStream));
        String answer = input.readLine();
        
        //Despliega mensaje
        JOptionPane.showMessageDialog(null, answer);
        clientSocket.close();
        System.exit(0);
    }
    
}

