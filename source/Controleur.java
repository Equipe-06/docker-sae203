import java.util.Scanner;

public class Controleur 
{
    private Joueur  joueur1;
    private Joueur  joueurServeur;

    private Serveur serveur;

    public Controleur()
    {
        this.joueur1       = new Joueur("joueur1", new Robot("", 10.5, 5));
        this.joueurServeur = new Joueur("serveur", new Robot("", 20.0, 2));

        this.serveur = new Serveur(this);

        this.startGame();
    }

    public Joueur getJoueur1      () { return this.joueur1;       }
    public Joueur getJoueurServeur() { return this.joueurServeur; }

    private void startGame()
    {   
        Joueur  j1   = this.joueur1;
        Joueur serv  = this.joueurServeur;
        
        // Joueur1 choisit le nom de son robot
        // -----------------------------------
        System.out.println("Veuillez choisir le nom de votre Robot : ");
        Scanner scanner = new Scanner(System.in);
        String  nom   = scanner.nextLine();
        j1.setNom( nom );
        
        // Jeu 
        // ----
        while ( j1.getRobot().getPv() > 0 || serv.getRobot().getPv() > 0 )
        {
            System.out.println("==============================================================");
            System.out.println(serv);
            System.out.println(j1);

            if ( j1.getRobot().getVit() < serv.getRobot().getVit() )
            {
                // Attaque du serveur ( il attaque aleatoirement entre 3 attaque du robot )
                serv.choixAttaque(j1,""+(int) (Math.random() * 3))  ;

                // Attaque du joueur apres le serveur ( il fait son choix lui mm )
                System.out.println( j1.afficherToutesAttaques() );
                String  input   = scanner.nextLine();
                j1.choixAttaque(serv, input);
            }
            else
            {
                // Attaque du joueur ( il fait son choix lui mm )
                System.out.println( j1.afficherToutesAttaques() );
                String  input   = scanner.nextLine();
                j1.choixAttaque(serv, input);
                
                // Attaque du serveur ( il attaque aleatoirement entre 3 attaque du robot )
                serv.choixAttaque(j1, ""+(int) (Math.random() * 3));            
            }
            
        }
        if ( j1.getRobot().getPv() < 0 && serv.getRobot().getPv() > 0 )
            System.out.println("EGALITER");
        if ( serv.getRobot().getPv() > 0 ) 
            System.out.println("GAGNER");
        else 
            System.out.println("PERDU");

    }

    public static void main(String[] args) 
    {
        new Controleur();
    }
}
