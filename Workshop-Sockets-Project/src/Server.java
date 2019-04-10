import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String args[]) {
		int port = 9002;
        try {        	
        		// Criar Server Socket para receber ligacoes de clientes
                ServerSocket serverSocket = new ServerSocket(port);
        		
                
        		System.out.println("Esperando por cliente");
                Socket clientSocket = serverSocket.accept(); 
                System.out.println("Cliente aceite");

                //Ir buscar entrada e saída do socket
                PrintWriter socketOut =
                    new PrintWriter(clientSocket.getOutputStream(), true);                   
                BufferedReader socketIn = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
                
                
                String inputLine;
                // Esperar por mensagem do cliente
                while ((inputLine = socketIn.readLine()) != null) {
                	// Devolver a mensagem recebida
                    socketOut.println(inputLine); 
                }
                clientSocket.close();
                serverSocket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
	}
}
