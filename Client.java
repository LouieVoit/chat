import java.io.*;
import java.net.*;
import java.util.Scanner;
/** Le processus client se connecte au site fourni dans la commande
 *   d'appel en premier argument et utilise le port distant 8080.
 */
public class Client {
	static final int port = 8080;

	public static void main(String[] args) throws Exception {
		Socket socket = new Socket(args[0], port);
		System.out.println("SOCKET = " + socket);

		BufferedReader plec = new BufferedReader(
							 new InputStreamReader(socket.getInputStream())
							 );

		PrintWriter pred = new PrintWriter(
						   new BufferedWriter(
								      new OutputStreamWriter(socket.getOutputStream())),
						   true);
		
		Redacteur red = new Redacteur(pred);
		Lecteur lec = new Lecteur(plec);
		
		red.start();
		lec.start();
		
		red.join();		
		
		System.out.println("END");     // message de terminaison
		pred.println("END") ;
		plec.close();
		pred.close();
		socket.close();
	}
}
