# Robot Battle Game - Docker Sae 203
Bienvenue dans **Robot Battle Game** — un petit jeu Java dans lequel deux robots s'affrontent avec différentes attaques !

## 📚 Description

Ce projet est une simulation de combat entre deux joueurs contrôlant chacun un robot.  
Chaque robot dispose de points de vie (PV), d'une vitesse et de plusieurs attaques pré-définies.  
Le but du jeu est de réduire les PV de l'adversaire à zéro avant de perdre les siens.

- Le joueur choisit le nom de son robot.
- Les attaques infligent des dégâts différents.
- La vitesse du robot détermine qui attaque en premier.

---

## 🛠️ Structure du Projet

- **Attaque**  
  Modélise une attaque (nom + dégâts).

- **Robot**  
  Modélise un robot (nom, PV, vitesse, et liste d'attaques).

- **Joueur**  
  Modélise un joueur (nom + robot associé).

- **Controleur**  
  Classe principale qui gère le déroulement du jeu :
  - Création des joueurs.
  - Tour par tour : chaque robot attaque à son tour.
  - Fin du jeu : victoire, égalité ou défaite.

- **Serveur**  
  (Note : Cette classe est appelée dans `Controleur`, mais n'est pas encore fournie dans votre code.)

---

## 🚀 Lancer le Jeu

1. **Compiler** tous les fichiers `.java` :
   ```bash
   javac *.java
   ```

2. **Exécuter** la classe principale `Controleur` :
   ```bash
   java Controleur
   ```

---

## 📷 Exemple de Fonctionnement

```bash
Veuillez choisir le nom de votre Robot :
> TitanX

==============================================================
Joueur :
 - nom: serveur
   Robot :
    - nom :
    - vie :20.0
Joueur :
 - nom: joueur1
   Robot :
    - nom :TitanX
    - vie :10.5

#0 - Attaque: Laser Éclair Dégat: 10.0
#1 - Attaque: Missile Sonic Dégat: 15.0
#2 - Attaque: Coup de Fer Dégat: 8.0

> 1
Attaque: Missile Sonic Dégat: 15.0
...
```

---

## ⚠️ Remarques

- Pour l'instant, les attaques sont **codées en dur** dans `Robot.java`.
- Le système de fichier pour charger les attaques (`attaque.data`) est présent mais commenté.
- Le projet nécessite une classe **Serveur** non fournie ici.

---

## ✏️ À améliorer

- Ajouter la classe `Serveur`.
- Lire les attaques depuis un fichier externe (`attaque.data`).
- Améliorer l'interface utilisateur.
- Ajouter une gestion d'erreurs sur les entrées utilisateur.

---

## 🧑‍💻 Auteur

- **Damestoy Ethan* – [GitHub](https://github.com/Ethylaa)

- **Leclerc Jonathan* - [GitHub](https://github.com/Nailledo)

- **Millereux Bienvault William* - [GitHub](https://github.com/Falcrom37)

- **Leprevost Lucas* – [GitHub](https://github.com/LucasLeprevost)

 