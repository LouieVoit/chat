import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class Serveur {
	static final int port = 8080;
	
	public static ArrayList<PrintWriter> redacteurs = new ArrayList<PrintWriter>();

	public static void main(String[] args) throws Exception {
		ServerSocket s = new ServerSocket(port);
		
		ArrayList<PrintWriter>listeGest=new ArrayList<PrintWriter>();
		
		while(true){
			Socket soc = s.accept();
			GestionnaireClient leNouveauGestionnaire=new GestionnaireClient(soc);
			leNouveauGestionnaire.start();
		}
	}
}
