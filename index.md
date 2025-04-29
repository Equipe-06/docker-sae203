Robot Battle Game - Docker Sae 203
Bienvenue dans Robot Battle Game — un petit jeu Java dans lequel deux robots s'affrontent avec différentes attaques !

📚 Description
Ce projet est une simulation de combat entre deux joueurs contrôlant chacun un robot.
Chaque robot dispose de points de vie (PV), d'une vitesse et de plusieurs attaques pré-définies.
Le but du jeu est de réduire les PV de l'adversaire à zéro avant de perdre les siens.

Le joueur choisit le nom de son robot.
Les attaques infligent des dégâts différents.
La vitesse du robot détermine qui attaque en premier.
🛠️ Structure du Projet
Attaque
Modélise une attaque (nom + dégâts).

Robot
Modélise un robot (nom, PV, vitesse, et liste d'attaques).

Joueur
Modélise un joueur (nom + robot associé).

Controleur
Classe principale qui gère le déroulement du jeu :

Création des joueurs.
Tour par tour : chaque robot attaque à son tour.
Fin du jeu : victoire, égalité ou défaite.
Serveur
(Note : Cette classe est appelée dans Controleur, mais n'est pas encore fournie dans votre code.)

🚀 Lancer le Jeu
Compiler tous les fichiers .java :

javac *.java
Exécuter la classe principale Client :

java Client
🧑‍💻 Auteur
*Damestoy Ethan – GitHub

*Leclerc Jonathan - GitHub

*Millereux Bienvault William - GitHub

*Leprevost Lucas – GitHub
