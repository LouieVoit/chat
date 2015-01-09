import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

public class GestionnaireClient extends Thread{
	
	private Socket leSocket;
	private BufferedReader ple;
	private PrintWriter pred;
	
	
	/* La liste est partagée entre tous les gestionnaires,
	   ce qui permet à chacun de connaître tout le monde. */
	private ArrayList<GestionnaireClient>listeGests=null;
	
	public GestionnaireClient(Socket s){
		this.leSocket=s;
		try {
		// Un BufferedReader permet de lire par ligne
		this.ple = new BufferedReader(
				 new InputStreamReader(s.getInputStream())
				 );
		// Un PrintWriter possède toutes les opérations print classiques.
		// En mode auto-flush, le tampon est vidé (flush) à l'appel de println.
		this.pred =  new PrintWriter(new BufferedWriter(new OutputStreamWriter(s.getOutputStream())),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void run(){
		try{
		
		boolean continuer=true;
		
		Serveur.redacteurs.add(pred);
		
		while (continuer) {
			String str = ple.readLine(); 
			if (str.equals("END")){
				continuer=false;
			}
			else{
				/* Envoyer le message à tous les gestionnaires de la liste. */
				this.envoyerMsgTousGests(str);
			}
		}
		ple.close();
		pred.close();
		leSocket.close();
		}
		catch(IOException e){
			
		}
	}

	
	
	private void envoyerMsgTousGests(String msg){
		/* Parcourir la liste et envoyer le message à chacun, sauf à soi-même. */
		
		for(PrintWriter redacteur : Serveur.redacteurs){
			if (!(pred.equals(redacteur))) {
			redacteur.println(msg);
			}
		}
	}
	
}
