import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Controleur 
{   
    // Constante de victoire / défaite
    private static final String CADRE_VICTOIRE = 
        "╔══════════════════════════════════╗\n" +
        "║                                  ║\n" +
        "║           VICTOIRE !!!           ║\n" +
        "║                                  ║\n" +
        "╚══════════════════════════════════╝";

    private static final String CADRE_DEFAITE = 
        "╔══════════════════════════════════╗\n" +
        "║                                  ║\n" +
        "║           DÉFAITE...             ║\n" +
        "║                                  ║\n" +
        "╚══════════════════════════════════╝";

    // Couleur 
    public static final String RESET        = "\u001B[0m" ;
    public static final String ROUGE        = "\u001B[31m";
    public static final String VERT         = "\u001B[32m";
    public static final String JAUNE        = "\u001B[33m";
    public static final String BLEU         = "\u001B[34m";
    public static final String CYAN         = "\u001B[36m";
    public static final String BLEU_CLAIR   = "\u001B[94m";
    public static final String GRAS         = "\u001B[1m" ;  
    public static final String SOULIGNE     = "\u001B[4m" ;  


    // Port du serveur
    private static final int PORT = 9000;

    // Attributs
    private ArrayList<Attaque>  ensAttaque;
    private ArrayList<Robot>    ensRobot;
    private ServerSocket        serverSocket;
    private int                 nombreJoueurs;

    private Joueur              j1;
    private Joueur              j2;

    public Controleur()
    {
        this.ensAttaque    = new ArrayList<>();
        this.ensRobot      = new ArrayList<>();
        this.nombreJoueurs = 0;
        this.j1            = null;
        this.j2            = null;

        this.initRobot();
        this.initAttaque();

        // Démarrer le serveur
        startServer();
    }

    /**
     * Démarrage du serveur
     */
    private void startServer() 
    {
        try 
        {
            serverSocket = new ServerSocket(PORT);
            System.out.println(VERT + "Serveur démarré sur le port " + PORT + RESET);
            
            System.out.println(JAUNE + "En attente de joueurs (2 nécessaires pour commencer)..." + RESET);
            
            System.out.println(JAUNE + "En attente du premier joueur..." + RESET);
            Socket socketJ1 = serverSocket.accept();
            this.j1 = connecterJoueur(socketJ1, "Joueur 1");
            
            System.out.println(JAUNE + "Premier joueur connecté! En attente du second joueur..." + RESET);
            Socket socketJ2 = serverSocket.accept();
            this.j2 = connecterJoueur(socketJ2, "Joueur 2");
            
            System.out.println(VERT + "Deux joueurs connectés! Démarrage de la partie." + RESET);

            /* Lancment de la partie */
            /* --------------------- */
            jouerPartie(this.j1, this.j2);
            
            // Fin de la partie après
            finaliserPartie(this.j1, this.j2);
            
        } 
        catch (IOException e) 
        {
            System.out.println(ROUGE + "Erreur serveur: " + e.getMessage() + RESET);
        } 
        // Une fois que le try est fini et que tout s'est bien passé, il fais les opérations du 
        finally 
        {
            try 
            {
                if (serverSocket != null && !serverSocket.isClosed()) 
                    serverSocket.close();
            } 
            catch (IOException e) 
            {
                System.out.println(ROUGE + "Erreur lors de la fermeture du serveur: " + e.getMessage() + RESET);
            }
        }
    }
    
    /**
     * Connecte un joueur et configure son robot
     */
    private Joueur connecterJoueur(Socket socket, String defaultName) throws IOException 
    {
        PrintWriter   out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        
        out.println("Bienvenue au jeu de combat de robots!");
        out.println("Veuillez entrer votre nom:");
        
        String nomJoueur = in.readLine();
        if ( nomJoueur == null || nomJoueur.trim().isEmpty() ) 
            nomJoueur = defaultName;
        
        Joueur joueur = new Joueur(nomJoueur, this);
        joueur.setSocket(socket);
        joueur.setWriter(out);
        joueur.setReader(in);
        
        out.println("Bonjour " + nomJoueur + "!");
        
        // Afficher les robots disponibles

        while(joueur.getRobotJoueur() == null)
        {
            out.println("Voici les robots disponibles:");
            out.println(getRobotsAvailableAsString());
            out.println("Choisissez un robot (entrez le nom complet du robot (sans fautes d'orthographes svp) ):");
            
            // On enlève le robot déjà choisit
            this.ensRobot.remove( j1 );

            // Attendre le choix du robot
            String choixRobot = in.readLine();

            joueur.choixRobot(choixRobot);
            
            out.println("Vous avez choisi le robot: " + joueur.getRobotJoueur().getNom());
        }
        return joueur;
    }
    
    /**
     * Gère le déroulement d'une partie entre deux joueurs
     */
    private void jouerPartie(Joueur j1, Joueur j2) 
    {
        
        // On initialise les variables pour leur envoyer des messages
        PrintWriter    out1 = j1.getWriter();
        PrintWriter    out2 = j2.getWriter();
        BufferedReader in1  = j1.getReader();
        BufferedReader in2  = j2.getReader();
        
        out1.println("Début de la partie contre " + j2.getNom());
        out2.println("Début de la partie contre " + j1.getNom());
        
        int tours = 1;
        
        try 
        {
            boolean j2Joue = false;
            if ( j1.getRobotJoueur().getVit() < j2.getRobotJoueur().getVit() )
                j2Joue = true;

            /* ------------------------------ */
            /*           Boucle du jeu        */
            /* ------------------------------ */
            while (j1.getRobotJoueur().getPv() > 0 && j2.getRobotJoueur().getPv() > 0) 
            {
                String infoTour = JAUNE  + GRAS + "\n********     Tour : " + tours + "     *******\n" + RESET;
                infoTour += j1.getNom() + " - " + j1.getRobotJoueur().toString() + "\n";
                infoTour += j2.getNom() + " - " + j2.getRobotJoueur().toString();
                
                out1.println(infoTour);
                out2.println(infoTour);
                
                // Déterminer qui attaque en premier (basé sur la vitesse)
                if ( j2Joue ) 
                {
                    // J2 attaque en premier
                    processAttack( j2, j1 );
                    
                    if (j1.getRobotJoueur().getPv() <= 0)
                        break;
                    
                    // J1 riposte
                    processAttack( j1, j2 );
                    
                    j2Joue = false;
                }
                else 
                {
                    // J1 attaque en premier
                    processAttack( j1, j2 );
                    
                    if (j2.getRobotJoueur().getPv() <= 0)
                        break;
                    
                    // J2 riposte
                    processAttack( j2, j1 );
                    
                    j2Joue = true;
                }
                
                tours++;
            }
            
            /* Fin de la partie du coté Client */
            /* ------------------------------- */
            if (j2.getRobotJoueur().getPv() <= 0) 
            {
                // J1 gagne
                afficherVictoire(j1);
                // J2 perd
                afficherDefaite(j2);
            } 
            else 
            {
                // J2 gagne
                afficherVictoire(j2);
                // J1 perd
                afficherDefaite(j1);
            }
                        
        } 
        catch (IOException e) 
        {
            System.out.println(ROUGE + "Erreur lors de la partie: " + e.getMessage() + RESET);
            out1.println("Une erreur est survenue pendant la partie.");
            out2.println("Une erreur est survenue pendant la partie.");
        }
    }

    private void afficherVictoire(Joueur joueur) 
    {
        joueur.getWriter().println(VERT + CADRE_VICTOIRE + RESET);
        joueur.getWriter().println(VERT + "Bravo, vous avez gagné la partie !" + RESET);
    }

    private void afficherDefaite(Joueur joueur)
    {
        joueur.getWriter().println(ROUGE + CADRE_DEFAITE + RESET);
        joueur.getWriter().println(ROUGE + "Ne baissez pas les bras, réessayez !" + RESET);
    }
    
    private void afficherDegat ( Joueur jAtt, Joueur jDef, int numAttaqueRobot )
    {
        jAtt.getWriter().println(
                    String.format("%-15s",jAtt.getNom())     + " attaque avec      : " + BLEU_CLAIR + jAtt.getRobotJoueur().getAttaque(numAttaqueRobot).getNom()                 + RESET     + "\n" +
                    String.format("%-15s",jDef.getNom())     + " a subi            : " + BLEU_CLAIR + jAtt.getRobotJoueur().getAttaque(numAttaqueRobot).getDegat()  + " dégats " + RESET     + "\n" +
                    "\nLe Robot de : \n" +
                    String.format("%-15s",jDef.getNom()) + " a   : " + BLEU_CLAIR + jDef.getRobotJoueur().getPv()                                                                + RESET     + " PV\n"
                    );

        jDef.getWriter().println(
                    String.format("%-15s",jAtt.getNom())     + " attaque avec      : " + BLEU_CLAIR + jAtt.getRobotJoueur().getAttaque(numAttaqueRobot).getNom()                 + RESET     + "\n" +
                    String.format("%-15s",jDef.getNom())     + " a subi            : " + BLEU_CLAIR + jAtt.getRobotJoueur().getAttaque(numAttaqueRobot).getDegat()  + " dégats " + RESET     + "\n" +
                    "\nLe Robot de : \n" +
                    String.format("%-15s",jDef.getNom()) + " a   : " + BLEU_CLAIR + jDef.getRobotJoueur().getPv()                                                                + RESET     + " PV\n"
                    );

    }
    
    /**
     * Traite une attaque d'un joueur vers un autre
     */
    private void processAttack(Joueur attacker, Joueur defender ) throws IOException 
    {
        // Demander l'attaque
        attacker.getWriter().println("C'est votre tour d'attaquer! Choisissez une attaque (0-3):");
        
        try 
        {
            String choixAttaque = attacker.getReader().readLine();
            System.out.println("DEBUG: Attaque choisie: " + choixAttaque);
            
            // Effectuer l'attaque
            Robot robotAttacker = attacker.getRobotJoueur();
            Robot robotDefender = defender.getRobotJoueur();
            
            // Selectionne les dégât de l'attaque pour l'appliquer
            int indexAttaque = Integer.parseInt(choixAttaque);
            Attaque attaque  = robotAttacker.getAttaque(indexAttaque);
            
            // Appliquer les dégâts
            double  degats   = attaque.getDegat();
            boolean bAttaque = robotAttacker.infligerAttaque(attaque, robotDefender );

            // On vérifie que l'attauqe 
            String msgAttaque = "";
            if ( bAttaque ) 
            {
                msgAttaque = attacker.getNom() + " utilise " + attaque.getNom() + 
                        " et inflige " + degats + " dégâts!";
            }
            else
            {
                msgAttaque = attacker.getNom() + " utilise " + attaque.getNom() + 
                        " mais l'attaque a échoué";
            }
                // Informer les joueurs des nouvelles attaquent
                attacker.getWriter().println(msgAttaque);
                defender.getWriter().println(msgAttaque);
            
                String pv = defender.getNom() + " a maintenant " + robotDefender.getPv() + " PV.";
                attacker.getWriter().println(pv);
                defender.getWriter().println(pv);

                        
            afficherDegat(attacker, defender, Integer.parseInt(choixAttaque));

            String infoApresAttaqueJ = "\nAprès l'attaque de " + attacker.getNom() + ":\n";
            infoApresAttaqueJ += attacker.getNom() + " - " + attacker.getRobotJoueur().toString() + "\n";
            infoApresAttaqueJ += defender.getNom() + " - " + defender.getRobotJoueur().toString();
            
            attacker.getWriter().println(infoApresAttaqueJ);
            defender.getWriter().println(infoApresAttaqueJ);
            
        } 
        // On gère toutes les erreurs possiblent que pourrait faire le joueur
        catch (NumberFormatException e)
        {
            attacker.getWriter().println("Erreur: Veuillez entrer un nombre valide entre 0 et 3");
            processAttack(attacker, defender );
        } 
        catch (IndexOutOfBoundsException e) 
        {
            attacker.getWriter().println("Erreur: Cette attaque n'existe pas");
            processAttack(attacker, defender );
        } 
        catch (IOException e) 
        {
            System.out.println(ROUGE + "Erreur I/O pendant l'attaque: " + e.getMessage() + RESET);
            throw e;
        }
    }
    
    /**
     * Finalise la partie en fermant les connexions
     */
    private void finaliserPartie(Joueur j1, Joueur j2) 
    {
        try 
        {
            // Fermer les connexions du premier joueur
            j1.getWriter().close();
            j1.getReader().close();
            j1.getSocket().close();
            
            // Fermer les connexions du deuxième joueur
            j2.getWriter().close();
            j2.getReader().close();
            j2.getSocket().close();
            
            System.out.println(JAUNE + "Partie terminée, connexions fermées" + RESET);
        } 
        catch (IOException e)
        {
            System.out.println(ROUGE + "Erreur lors de la fermeture des connexions: " + e.getMessage() + RESET);
        }
    }
    
    /**
     * Renvoie la liste des robots au format String
     */
    public String getRobotsAvailableAsString() 
    {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ensRobot.size(); i++) 
        {
            sb.append(i).append(". ").append(ensRobot.get(i)).append("\n");
        }
        return sb.toString();
    }

    /* ---------------------- */
    /*   Init  Robot/Attaque  */
    /* ---------------------- */

    private void initRobot() 
    {
        String nom, ligneRobot;
        int vitesse, indice;
        int pv; 

        try 
        {
            Scanner sc = new Scanner(new FileInputStream("robot.data"), "UTF8");

            while (sc.hasNextLine()) 
            {
                ligneRobot = sc.nextLine();

                nom     = ligneRobot.substring(0, 18);
                pv      = Integer.parseInt(ligneRobot.substring(18, 24).trim());
                vitesse = Integer.parseInt(ligneRobot.substring(25).trim());
                
                this.ensRobot.add(new Robot(nom.trim(), pv, vitesse));
            }

            sc.close();
        } catch (Exception e) { e.printStackTrace(); }
    }
    
    private void initAttaque()
    {
        
        String nom  , ligneAttaque ;
        int    degat, precision,   indiceRobot;

        try
        {
            Scanner sc         = new Scanner ( new FileInputStream ( "attaque.data" ), "UTF8" );


            while ( sc.hasNextLine() )
            {
                ligneAttaque    = sc.nextLine();
                nom             = ligneAttaque.substring(0,30 );
                degat           = Integer.parseInt(ligneAttaque.substring(30, 32).trim());
                precision       = Integer.parseInt(ligneAttaque.substring(34, 36).trim());
                indiceRobot     = Integer.parseInt(ligneAttaque.substring(37).trim());

                this.ensAttaque           .add       (new Attaque( nom.trim(), degat, precision));
                this.getRobot(indiceRobot).addAttaque(new Attaque( nom.trim(), degat, precision));
            }

            sc.close();
        }
        catch (Exception e){ e.printStackTrace(); }
    }

    /* ---------------------- */
    /*         Getteur        */
    /* ---------------------- */
    public Robot getRobot(int index)      { return this.ensRobot.get(index); }
    public ArrayList<Robot> getEnsRobot() { return this.ensRobot;            }

    public static void main(String[] args) 
    {
        // Quand une partie se finit, la boucle permet d'éviter que le server se ferme
        while(true)
            new Controleur();
    }
}