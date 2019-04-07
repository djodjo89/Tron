package reseau;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class IA_Tron {
	
	int direction ;
	private int[] posTemp ;
	
	private String[] infosPartie ;
	private final static String[] DIRECTIONS = {"UP", "DOWN", "LEFT", "RIGHT"} ;
	
	private Connexion connexion ;
	private Grille grille ;
	private Flynn joueur ;
	
	private ArrayList<Joueur> ennemis ;
	
	public IA_Tron (String nomE, String ip, int port) throws UnknownHostException, IOException {
		
		this.posTemp = new int[2] ;
		this.joueur = new Flynn (nomE) ;
		this.connexion = new Connexion (ip, port) ;
		this.grille = new Grille () ;
		this.ennemis = new ArrayList<Joueur> () ;

	}

	public void jeu() throws IOException, InterruptedException {

		this.envoiNom () ;
		this.receptNum () ;
		System.out.println(this.joueur) ;	
		this.initCoor () ;	
		System.out.println(this.affichageGrille ());
		this.initEnnemis () ;
		this.actuTours () ;
		
        // "The grid... A digital frontier... I tried to picture
        // bytes of information as they moved through the compiler...
        // What did they look like... Strings? BufferedReaders ?...
        // With sockets like freeways ?... I kept dreaming of a server I
        // thought I’d never see... And then... One day... I got in.﻿"
        // Moi, 2019

	}
	
	public void initEnnemis () {
		
		int i ;
		
		for (i = 1 ; i < 4 ; i ++) {
			
			this.ennemis.add(new Joueur ()) ;
			
		}
		
	}
	
	public void actuTours () throws IOException, InterruptedException {
		
		while (true) {
			
			this.connexion.envoi(this.calculDir ());
			this.actuPosJoueurs() ;

		}
		
	}
	
	public void envoiNom () throws IOException {
		
		this.connexion.envoi (this.joueur.getNom()) ;
		
	}
	
	public void receptNum () throws IOException {
		
		this.joueur.setNum(this.connexion.conversionIntReception ()) ;
		
	}
	
	public String affichageGrille () {
		
		return this.grille.toString() + " et je suis en " + infosPartie[1] ;
		
	}

	public String calculDir () throws InterruptedException {

		do {
			
			this.direction = (int)(Math.random() * 4) ;

			switch (this.direction) {

			case 0 : this.monter() ;
			break ;

			case 1 : descendre () ;
			break ;

			case 2 : allerAGauche () ;
			break ;

			case 3 : allerADroite () ;
			break ;

			}

		} while (!deplacementPossible ()) ;

		return DIRECTIONS[direction] ;

	}
	
	public boolean deplacementPossible () {
		
		return this.posTemp[0] > 0 && this.posTemp[0] < this.grille.getHauteur() && this.posTemp[1] > 0 && this.posTemp[1] < this.grille.getHauteur() ;
		
	}
	
	public void monter () {
		
		this.posTemp[0] = this.joueur.getPosition()[0] - 1 ; this.posTemp[1] = this.joueur.getPosition()[1] ;
		
	}
	
	public void descendre () {
		
		posTemp[0] = this.joueur.getPosition()[0] + 1 ; posTemp[1] = this.joueur.getPosition()[1] ;
		
	}
	
	public void allerAGauche () {
		
		posTemp[0] = this.joueur.getPosition()[0] ; posTemp[1] = this.joueur.getPosition()[1] - 1 ;
		
	}
	
	public void allerADroite () {
		
		posTemp[0] = this.joueur.getPosition()[0] ; posTemp[1] = this.joueur.getPosition()[1] + 1 ;
		
	}

	public void initCoor () throws IOException {

		this.actuInfos () ;
		this.grille.setTaille(this.infosPartie[0]);
		this.joueur.setPosition(this.infosPartie[1]);

	}

	public void actuPosJoueurs () throws IOException {

		int i ;
		
		this.actuInfos () ;

		for (i = 0 ; i < this.ennemis.size() ; i ++) {
			
			this.ennemis.get(i).setPosition(this.infosPartie[i]);

		}

	}
	
	public void actuInfos () throws IOException {
		
		this.infosPartie = this.connexion.reception().split(";") ;
		
	}

 	public void fermerConnexion () throws IOException {
		
		this.connexion.fermerSocket() ;
		
	}

}
