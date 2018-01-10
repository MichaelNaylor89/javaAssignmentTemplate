import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ControllerServer {
	
	public static void main(String[] args) throws IOException  {
		String clientSentence;
		String capitalizedSentence;		
		ServerSocket serverSocket=null;
		Socket connectionSocket = null;
		try {
			serverSocket = new ServerSocket(3001);
			System.out.println("Server up and running on port 3001");
			while (true) {
				connectionSocket = serverSocket.accept();
				BufferedReader inFromClient =
						new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
				DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
				clientSentence = inFromClient.readLine();
				System.out.println("Received: " + clientSentence);
				capitalizedSentence = clientSentence.toUpperCase() + '\n';
				outToClient.writeBytes(capitalizedSentence);
			}
		}
		catch(IOException io) {
			System.out.println("Connection problem "+ io);
		}
		finally {
			connectionSocket.close();
			serverSocket.close();
		}
	}
}

