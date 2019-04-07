'''
Created on 6 avr. 2019

@author: Mathys
'''
#coding: utf-8

import socket
from random import randint

class IA_Tron :
    
    def __init__ (self, nomE, ipE, portE) :
        
        self.joueur = Flynn (nomE)
        self.connexion = Connexion (ipE, portE)
        self.grille = Grille ()
        self.ennemis = []
        self.directions = ["UP", "DOWN", "LEFT", "RIGHT"]
        self.direction = {0 : self.monter,
                          1 : self.descendre,
                          2 : self.allerAGauche,
                          3 : self.allerADroite,
                          }
         
    def jouer (self):

        self.envoiNom ()                
        self.receptNum ()
        print(self.affichageJoueur())
        self.initCoor()
        print(self.affichageGrille())
        self.initEnnemis()
        self.actuTours()
            
    def envoiNom (self) :
            
        self.connexion.envoi(self.joueur.getNom())
        
    def receptNum (self) :
        
        self.joueur.setNum(int(self.connexion.reception()))
        
    def affichageJoueur (self) :
        
        return "Je suis le joueur {}".format(self.joueur.getNom())
    
    def affichageGrille (self) :
        
        return self.grille.toString() + " et je suis en {}".format(self.infosPartie[1])
    
    def initEnnemis (self) :
        
        for i in self.ennemis :
            
            self.ennemis.append(Joueur())
            
    def actuTours (self) :
        
        while (True) :
            self.connexion.envoi(self.calculDir())
            self.actuPosJoueur()
            
    def calculDir (self) :
        
        self.posTemp = [-1, -1]
        
        while not self.deplacementPossible() :
            
            d = randint(0, 3)            
            self.direction[d] ()
            
        return self.directions[d]
    
    def deplacementPossible (self) :
        
        return self.posTemp[0] > 0 and self.posTemp[0] < self.grille.getHauteur() and self.posTemp[1] > 0 and self.posTemp[1] < self.grille.getHauteur()
    
    def monter (self) :
        
        self.posTemp[0] = self.joueur.getPosition()[0] - 1
        self.posTemp[1] = self.joueur.getPosition()[1]
        
    def descendre (self) :
        
        self.posTemp[0] = self.joueur.getPosition()[0] + 1
        self.posTemp[1] = self.joueur.getPosition()[1]
        
    def allerAGauche (self) :
        
        self.posTemp[0] = self.joueur.getPosition()[0]
        self.posTemp[1] = self.joueur.getPosition()[1] - 1
        
    def allerADroite (self) :
        
        self.posTemp[0] = self.joueur.getPosition()[0]
        self.posTemp[1] = self.joueur.getPosition()[1] + 1
      
    def initCoor (self) :
        
        self.actuInfos()
        self.grille.setTaille(self.infosPartie[0])
        self.joueur.setPosition(self.infosPartie[1])
        
    def actuPosJoueur (self) :
        
        self.actuInfos()
        
        for i in self.ennemis :
            
            self.ennemis[i].setPosition(self.infosPartie[i])
            
    def fermerConnexion (self) :
        
        self.connexion.fermerSocket()
        
    def actuInfos (self) :
        
        self.infosPartie = self.connexion.reception().split(";")
        
class Joueur :
    
    def __init__ (self) :
        
        self.position = [-1, -1]
        
    def getPosition (self) :
        
        return self.position
    
    def setPosition (self, positionE) :
        
        self.position = [int(positionE.split(":")[0]), int(positionE.split(":")[1])]
    
    
class Flynn (Joueur) :
    
    def __init__ (self, nomE) :
        
        Joueur.__init__(self)
        self.nom = nomE
        
    def getNom (self) :
        
        return self.nom
    
    def setNum (self, numE) :
        
        self.num = numE
        
    def getNum (self) :
        
        return self.num
    
    def toString (self) :
        
        return "Je suis le joueur : {}".format(self.num)
    
class Grille :
        
    def setTaille (self, tailleE) :
        
        self.dimensions = [int(tailleE.split(":")[0]), int(tailleE.split(":")[1])]
        self.casesVides = [[True in range(self.dimensions[0])] in range (self.dimensions[1])]
        
    def getDimensions (self) :
        
        return self.dimensions
    
    def initialiserGrille (self) :
        
        for i in self.dimensions :
            
            for j in self.dimensions :
                
                self.dimensions[i][j] = True
                
    def getHauteur (self) :
        
        return self.dimensions[0]
                
    def toString (self) :
        
        return "La grille est de {} cases sur {}".format(self.getHauteur(), self.getHauteur())
                
class Connexion :
    
    def __init__ (self, ipE, portE) :
        
        self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.s.connect((ipE, portE))
        
    def envoi (self, donnees) :
        
        self.s.send(donnees.encode("utf-8"))
        
    def reception (self) :
        
        return self.s.recv(64).decode("utf-8")
    
    def fermerSocket (self) :
        
        self.s.close()
                
if __name__ == "__main__" :
    
    ia = IA_Tron('Mathys', '127.0.0.1', 8000)
    
    try :
        
        ia.jouer()
        
    except :

        print("Vous avez été déconnecté du serveur")
        
    finally:
    
        sortie = ''
   
        while (sortie.upper() != 'Q') :
    
            sortie = input("Appuyez sur q pour quitter : ")
            
