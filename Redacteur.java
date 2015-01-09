
import java.io.PrintWriter;
import java.util.Scanner;

public class Redacteur extends Thread {

		private PrintWriter red;
		
		public Redacteur(PrintWriter red) {
			this.red = red;
		}
		
		public void run () {
			boolean continuer = true;
			Scanner scanner=new Scanner(System.in);
			while(continuer){
				String str = scanner.nextLine();
				if(str.equals("/quit")){
					continuer=false;
				}
				else{
					red.println(str);          // envoi d'un message vers le gestionnaire.
					//str=scanner.nextLine();
				}
			}
		}
}
