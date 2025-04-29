# Robot Battle Game - Docker SAE 203

Bienvenue dans **Robot Battle Game** — un projet Java dans lequel deux robots s'affrontent à coups d'attaques stratégiques !

---

## 📚 Description

**Robot Battle Game** est une simulation de combat à deux joueurs :

- Chaque joueur choisit et contrôle un robot.
- Chaque robot dispose :
  - de **points de vie (PV)**,
  - d'une **vitesse**,
  - de **déplacements**, 
  - et d'une liste **d'attaques**.
- Le but est de **réduire à 0 PV** le robot de l'adversaire avant que le sien ne soit détruit.

Les **attaques** varient selon leurs dégâts, leur précision et leur portée. 
La **vitesse** détermine l'ordre des tours.


---

## 🛠️ Structure du Projet

### 🔬 Attaque
- Modélise une attaque :
  - **Nom**
  - **Dégâts max/min**
  - **Portée**
  - **Précision**
  - **Nombre de tirs**
  - **Chance de multiplicateur**

```java
public Attaque(String nom, int degatMax, int degatMin, int portee, int porteeMax, int precisionMax, int precisionMin, int nbTirs, int chanceMulti)
```

### 🛸 Robot
- Représente un robot avec :
  - **Nom**
  - **Points de Vie (PV)**
  - **PV Max**
  - **Vitesse**
  - **Déplacement**
  - **Liste d'attaques**

```java
public Robot(String nom, int pv, int vitesse, int deplacement)
public ArrayList<Attaque> getAttaques()
public Attaque getAttaque(int index)
public int getPv()
public int getPvMax()
public int getVit()
public String getNom()
public void addAttaque(Attaque attaque)
public boolean infligerAttaque(Attaque attaque, Robot ennemi)
```

### 👨‍💻 Joueur
- Modélise un joueur humain.
  - Possède un **nom** et un **robot** associé.

### 📁 Controleur
- Classe principale qui orchestre :
  - La **connexion** des deux joueurs.
  - Le **déroulement du jeu** (à tours alternés).
  - La **gestion des attaques**, déplacements, dégâts et de la **victoire**.


### 🚀 Serveur
- (Note : Cette partie est incluse dans le **Controleur** et permet le jeu en **multijoueur TCP**.)

---

## 🚀 Comment Lancer le Jeu

1. Compiler tous les fichiers Java :
   ```bash
   javac *.java
   ```
2. Lancer le serveur :
   ```bash
   java Controleur
   ```

3. Lancer les clients :   ( revient à lancer 1 joueur )
```bash
   java Controleur
   ``` 

---

## 🧑‍💻 Auteurs

- **Damestoy Ethan**  — [GitHub](#)
- **Leclerc Jonathan** — [GitHub](#)
- **Millereux Bienvault William** — [GitHub](#)
- **Leprevost Lucas** — [GitHub](#)

---

> Ce projet a été réalisé dans le cadre de la SAE 203 en BUT Informatique.

---

# 🎉 Amusez-vous bien sur **Robot Battle Game** ! 🏆
