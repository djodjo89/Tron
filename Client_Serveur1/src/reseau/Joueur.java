package reseau;

public class Joueur {
	
	private int[] position ;
	
	public Joueur () {
		
		this.position = new int[2] ;
		
	}
	
	public int[] getPosition () {
		
		return this.position ;
		
	}
	
	public void setPosition (String positionE) {
		
		this.position[0] = Integer.parseInt((positionE.split(":")[0])) ;
		this.position[1] = Integer.parseInt((positionE.split(":")[1])) ;
		
	}

}
