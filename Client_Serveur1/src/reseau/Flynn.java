package reseau;

public class Flynn extends Joueur {
	
	public int num ;
	private String nom ;
	
	public Flynn (String nomE) {
		
		super() ;
		this.nom = nomE ;
		
	}
	
	public String getNom () {
		
		return this.nom ;
		
	}
	
	public void setNum (int numE) {
		
		this.num = numE ;
		
	}
	
	public int getNum () {
		
		return this.num ;
		
	}
	
	public String toString () {
		
		return "Je suis le joueur : " + this.num ;
		
	}

}
