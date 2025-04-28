import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;


public class Controleur 
{   
    // COULEUR 
    public static final String RESET = "\u001B[0m";
    public static final String ROUGE = "\u001B[31m";
    public static final String VERT  = "\u001B[32m";
    public static final String JAUNE = "\u001B[33m";
    public static final String BLEU  = "\u001B[34m";
    public static final String CYAN  = "\u001B[36m";;

    // Attributs
    private Joueur  joueur1;
    private Joueur  joueurServeur;

    private Serveur serveur;

    private ArrayList<Attaque>  ensAttaque;
   	private ArrayList<Robot>    ensRobot;

    public Controleur()
    {
        this.serveur    = new Serveur(this);
        
        this.ensAttaque = new ArrayList<>();
        this.ensRobot   = new ArrayList<>();

        this.startGame();
    }

    /* ---------------------- */
    /* Afficher Robot/Attaque */
    /* ---------------------- */
    private void afficherRobots()
    {
        for (Robot robot : this.ensRobot ) 
            System.out.println( robot );
    }
    
    /* ---------------------- */
    /*      Start Game        */
    /* ---------------------- */

    private void startGame()
{   
    this.initRobot();
    this.initAttaque();
    
    Joueur serv = new Joueur("Boss - Philippe LePivert", this);
    Joueur j1 = null;
    
    int tours = 1;    

    // J1 fournit son nom
    System.out.print(BLEU + "Veuillez choisir votre prénom : " + RESET);
    Scanner scanner = new Scanner(System.in);
    String nom = scanner.nextLine();
    j1 = new Joueur(nom, this);

    // J1 choisit son robot
    System.out.print(BLEU + "Veuillez choisir votre robot : " + RESET);
    this.afficherRobots();
    scanner = new Scanner(System.in);
    String nomRobot = scanner.nextLine();
    j1.choixRobot(nomRobot);

    // Serveur choisit son robot aléatoirement
    serv.choixRobot("" + (int) (Math.random() * 6));

    // Boucle du jeu
    while (j1.getRobotJoueur().getPv() > 0 && serv.getRobotJoueur().getPv() > 0)
    {
        System.out.println(JAUNE + "\n********     Tours : " + tours + "     *******" + RESET);
        System.out.println(CYAN + serv + RESET);
        System.out.println(VERT + j1 + RESET);

        if (j1.getRobotJoueur().getVit() < serv.getRobotJoueur().getVit())
        {
            // Serveur attaque
            serv.choixAttaque(j1, "" + (int) (Math.random() * 3));

            if (j1.getRobotJoueur().getPv() <= 0)
                break;

            // Joueur attaque
            System.out.print(BLEU + "Votre tour d'attaquer ! Choisissez une attaque : " + RESET);
            String input = scanner.nextLine();
            j1.choixAttaque(serv, input);
        }
        else
        {
            // Joueur attaque
            System.out.print(BLEU + "Votre tour d'attaquer ! Choisissez une attaque : " + RESET);
            String input = scanner.nextLine();
            j1.choixAttaque(serv, input);

            if (serv.getRobotJoueur().getPv() <= 0)
                break;

            // Serveur attaque
            serv.choixAttaque(j1, "" + (int) (Math.random() * 3));
        }
        tours++;
    }
    
    // Fin de la partie
    if (serv.getRobotJoueur().getPv() <= 0)
    {
        System.out.println(
            VERT +
            "╔══════════════════════════════════╗\n" +
            "║                                  ║\n" +
            "║         🎉  VICTOIRE !!!  🎉    ║\n" +
            "║                                  ║\n" +
            "╚══════════════════════════════════╝" +
            RESET
        );
        System.out.println(VERT + "Bravo, vous avez gagné la partie !" + RESET);
    } 
    else 
    {
        System.out.println(
            ROUGE +
            "╔══════════════════════════════════╗\n" +
            "║                                  ║\n" +
            "║         💀  DÉFAITE...  💀      ║\n" +
            "║                                  ║\n" +
            "╚══════════════════════════════════╝" +
            RESET
        );
        System.out.println(ROUGE + "Ne baissez pas les bras, réessayez !" + RESET);
    }
}


    /* ---------------------- */
    /*   Init  Robot/Attaque  */
    /* ---------------------- */

    private void initRobot()
	{
		
        String nom, ligneRobot ;
		int vitesse, indice;
        double pv; 

		try
		{
			Scanner sc 		= new Scanner ( new FileInputStream ( "robot.data" ), "UTF8" );


            while ( sc.hasNextLine() )
			{
				ligneRobot = sc.nextLine();

				nom        = ligneRobot  .substring  (0 , 18                      );
                pv         = Double      .parseDouble(ligneRobot.substring(18, 24).trim());
				vitesse    = Integer     .parseInt   (ligneRobot.substring(25   ).trim());
                
				this.ensRobot.add( new Robot( nom.trim(), pv, vitesse )) ;
			}

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }

	}
    
    
    private void initAttaque()
    {
        
        String nom, ligneAttaque ;
        double degat;
        int    indiceRobot;

		try
		{
			Scanner sc 		= new Scanner ( new FileInputStream ( "attaque.data" ), "UTF8" );


            while ( sc.hasNextLine() )
			{
				ligneAttaque    = sc.nextLine();
                nom             = ligneAttaque.substring(0,30 );
                degat           = Double.parseDouble(ligneAttaque.substring(30, 36).trim());
                indiceRobot     = Integer.parseInt(ligneAttaque.substring(37, 38));
				this.ensAttaque           .add       (new Attaque( nom.trim(), degat, 1));
                this.getRobot(indiceRobot).addAttaque(new Attaque( nom.trim(), degat, 1));
			}

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
    }

    /* ---------------------- */
    /*         Getteur        */
    /* ---------------------- */
    public Joueur    getJoueur1      (           ) { return this.joueur1;               }
    public Joueur    getJoueurServeur(           ) { return this.joueurServeur;         }
    public Robot     getRobot        ( int index ) { return this.ensRobot.get( index ); }
    public ArrayList<Robot> getEnsRobot (        ) { return this.ensRobot;              }  

    public static void main(String[] args) 
    {
        new Controleur();
    }
}
