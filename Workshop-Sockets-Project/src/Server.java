import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
                	Map<String, String> map = new HashMap<String, String>();

                    String[] partesDaMensagem = inputLine.split(";");
                    System.out.println("Partes da mensagem:");
                    for (int i = 0; i < partesDaMensagem.length; i++) {
                    	System.out.println(partesDaMensagem[i]);
                        String data = partesDaMensagem[i];

                        String[] keyValue = data.split("=");
                        
                        if(keyValue.length == 2) {
                            map.put(keyValue[0], keyValue[1]);
                        }
                    }
              
                    if(map.get("bebida_no_copo").equals("sepsi")){
                        socketOut.println("Enchi o copo com sepsi"); 
                    }
                    else if(map.get("bebida_no_copo").equals("nenhuma")) {
                    	if(map.get("bebida_desejada") == null) {
                            socketOut.println("Erro, especifique uma bebida"); 
                    	}
                    	else if(map.get("bebida_desejada").equals("sepsi")) {
                            socketOut.println("Enchi o copo com sepsi"); 
                    	}
                    }
                    else {
                        socketOut.println("Erro no protocolo"); 
                    }
                }
                clientSocket.close();
                serverSocket.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
	}
}
