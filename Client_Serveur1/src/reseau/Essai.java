package reseau;
import java.io.IOException;

public class Essai {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		IA_Tron ia = new IA_Tron("Mathys", "127.0.0.1", 8000);
		
		try {

			ia.jeu() ;
		
		}
		
		catch (Exception e) {
			
			System.out.println("Vous avez été déconnecté du serveur.");
			
		}
		
		finally {
			
			ia.fermerConnexion () ;
			
		}

	}

}
