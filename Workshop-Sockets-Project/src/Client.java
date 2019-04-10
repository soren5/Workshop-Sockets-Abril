import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String args[]) {
		String serverName = "localhost";
		int port = 9002;
		try {
				//Abrir ligação com o servidor
			    Socket socket = new Socket(serverName, port);
			    
			    //Ir buscar entrada e saída do socket
			    PrintWriter socketOut = new PrintWriter(socket.getOutputStream(), true);
			    BufferedReader socketIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			    
			    //Criar um scanner para o utilizador escrever
			    Scanner userIn = new Scanner(System.in);
			    String userInput;
			    
			    //Receber mensagem do utilizador
			    while ((userInput = userIn.nextLine()) != null) {
			    	
			    	//Enviar para o socket
			        socketOut.println(userInput);
			        
			        //Receber resposta
			        System.out.println("echo: " + socketIn.readLine());
			    }
			    userIn.close();
			    socket.close();
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
