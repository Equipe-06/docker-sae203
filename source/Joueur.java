

public class Joueur
{

    private String nom;
    private Robot robot;

    public Joueur(String nom, Robot robot)
    {
        this.nom = nom;
        this.robot = robot;
    }
    
    public String toString()
    {
        return "Joueur :\n" +
                " - nom: " + this.nom + "\n" +
                "   Robot : \n" +
                    " - nom :" + this.robot.getNom() + "\n" +
                    " - vie :" + this.robot.getPv();
    }

    public void setNom( String nom )
    {
        this.nom = nom;
    }

    public Robot getRobot() { return this.robot; }

    public String afficherToutesAttaques()
    {
        String sRet= "";
        for(int cpt=0; cpt < this.robot.getAllAttaque().size(); cpt++)
        {
            sRet += "#" + cpt + " - " + this.robot.getAllAttaque().get(cpt).toString() + "\n";
        }
        return sRet;
    }

    public void choixAttaque(Joueur victim, String input)
    {        
        int choix = Integer.parseInt(input);

        // Affichage de l'attaque    ( ENLEVER LE SOP PLUS TARD )
        System.out.println(robot.getAttaque( choix ));

        // Enlever la vie par rapport à l'attaque
        victim.getRobot().recevoirAttaque( this.robot.getAttaque( choix ) );
    }


    public String getNom() { return this.nom; }
}