
import java.io.BufferedReader;
import java.io.IOException;

public class Lecteur extends Thread {

		private BufferedReader lec;
		
		public Lecteur(BufferedReader lec) {
			this.lec = lec;
		}
		
		public void run () {

			boolean continuer = true;
			while(continuer){
				try {
					System.out.println(lec.readLine());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
}
