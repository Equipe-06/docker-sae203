import java.util.Scanner;
import java.util.ArrayList;
import java.io.FileInputStream;


public class Controleur 
{   
    private Joueur  joueur1;
    private Joueur  joueurServeur;

    private Serveur serveur;

    private ArrayList<Attaque>  ensAttaque;
   	private ArrayList<Robot>    ensRobot;

    public Controleur()
    {
        this.serveur = new Serveur(this);

        this.startGame();
    }

    /* ---------------------- */
    /*         Getteur        */
    /* ---------------------- */
    public Joueur getJoueur1      () { return this.joueur1;       }
    public Joueur getJoueurServeur() { return this.joueurServeur; }

    /* ---------------------- */
    /* Afficher Robot/Attaque */
    /* ---------------------- */
    private void afficherTousRobot()
    {
        for (Robot robot : this.ensRobot ) 
            System.out.println( robot );
    }

    public String afficherToutesAttaques()
    {
        String sRet= "";
        for (Attaque atta : this.ensAttaque ) 
            sRet += atta.toString();
        return sRet;
    }
    
    /* ---------------------- */
    /*      Start Game        */
    /* ---------------------- */

    private void startGame()
    {   
        // Variables
        // ---------
        Joueur serv   = new Joueur("BOT");
        Joueur j1     = null;
        
        int     tours = 1;    

        // J1 choisit le nom de son robot
        // ------------------------------
        System.out.println("Veuillez choisir votre prénom : ");
        Scanner scanner = new Scanner(System.in);
        String  nom   = scanner.nextLine();
        j1 = new Joueur( nom );

        // J1 choisit son robot
        // --------------------
        System.out.println("Veuillez choisir votre robot : ");
        this.afficherTousRobot();
        scanner = new Scanner(System.in);
        String  numero   = scanner.nextLine();
        j1.choixRobot( numero );

        // Jeu 
        // ----
        while ( j1.getRobot().getPv() > 0 || serv.getRobot().getPv() > 0 )
        {
            System.out.println("********     Tours : " + tours + "     *******");
            System.out.println(serv);
            System.out.println(j1);

            if ( j1.getRobot().getVit() < serv.getRobot().getVit() )
            {
                // Attaque du serveur ( il attaque aleatoirement entre 3 attaque du robot )
                serv.choixAttaque(j1,""+(int) (Math.random() * 3))  ;

                if(j1.getRobot().getPv() <= 0)
                    break;                

                // Attaque du joueur apres le serveur ( il fait son choix lui mm )
                System.out.println( j1.afficherTousAttaques() );
                String  input   = scanner.nextLine();
                j1.choixAttaque(serv, input);
            }
            else
            {
                // Attaque du joueur ( il fait son choix lui mm )
                System.out.println( j1.afficherToutesAttaques() );
                String  input   = scanner.nextLine();
                j1.choixAttaque(serv, input);
                
                if(serv.getRobot().getPv() <= 0)
                    break;
                
                // Attaque du serveur ( il attaque aleatoirement entre 3 attaque du robot )
                serv.choixAttaque(j1, ""+(int) (Math.random() * 3));                     
            }


            tours++;            
        }
        
        if ( serv.getRobot().getPv() > 0 ) 
            System.out.println("GAGNER");
        else 
            System.out.println("PERDU");

    }

    /* ---------------------- */
    /*   Init  Robot/Attaque  */
    /* ---------------------- */

    private void initRobot()
	{
		
        String nom, ligneRobot ;
		int vitesse;
        double pv; 

		this.ensRobot = new ArrayList<Robot>();

		try
		{
			Scanner sc 		= new Scanner ( new FileInputStream ( "robot.data" ) );


            while ( sc.hasNextLine() )
			{
				ligneRobot = sc.nextLine();
				nom = ligneRobot.substring(0,18 );
                pv = Double.parseDouble(ligneRobot.substring(18, 24));
				vitesse = Integer.parseInt(ligneRobot.substring(25));
				this.ensRobot.add( new Robot( nom.trim(),pv, vitesse )) ;
			}

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }

	}
    
    
    private void initAttaque()
    {
        
        String nom, ligneAttaque ;
        double degat; 

		this.ensAttaque = new ArrayList<Attaque>();

		try
		{
			Scanner sc 		= new Scanner ( new FileInputStream ( "attaque.data" ) );


            while ( sc.hasNextLine() )
			{
				ligneAttaque = sc.nextLine();
				nom = ligneAttaque.substring(0,30 );
                degat = Double.parseDouble(ligneAttaque.substring(30));
				this.ensAttaque.add( new Attaque( nom.trim(), degat)) ;
			}

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
        
    }

	public Robot getRobot ( int index){ return this.ensRobot.get( index);}

    public static void main(String[] args) 
    {
        new Controleur();
    }
}
