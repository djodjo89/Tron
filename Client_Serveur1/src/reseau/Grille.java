package reseau;

public class Grille {
	
	private int[] dimensions ;
	private boolean[][] casesVides ;
	
	public Grille () {
		
		this.dimensions = new int[2] ;
		
	}
	
	public void setTaille (String tailleE) {
		
		this.dimensions[0] = Integer.parseInt((tailleE.split(":")[0])) ;
		this.dimensions[1] = Integer.parseInt((tailleE.split(":")[1])) ;
		this.casesVides = new boolean[this.dimensions[0]][this.dimensions[1]] ;
		
	}
	
	public int[] getDimensions () {
		
		return this.dimensions ;
		
	}
	
	public int getHauteur () {
		
		return this.dimensions[0] ;
		
	}
	
	public void initialiserGrille () {
		
		int i, j ;
		
		for (i = 0 ; i < this.dimensions.length ; i ++) {
			
			for (j = 0 ; j < this.dimensions.length ; j ++) {
				
				this.casesVides[i][j] = true ;
				
			}
			
		}
		
	}
	
	public String toString () {
		
		return "La grille est de " + this.getHauteur () + " cases sur " + this.getHauteur () ;
		
	}
	
	public void affichageGrille () {
		
		for (boolean[] i : this.casesVides) {
			
			for (boolean j : i) {
				
				System.out.println(" " + j);
				
			}
			
			System.out.println();
			
		}
		
	}
	
}
