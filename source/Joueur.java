public class Joueur
{

    private String nom;
    private Robot robot;

    public Joueur(String nom)
    {
        this.nom = nom;
        this.robot = null;
    }
    
    public String toString()
    {
        return "Joueur :\n" +
                " - nom: " + this.nom + "\n" +
                "   Robot : \n" +
                " - nom :" + this.robot.getNom() + "\n" +
                " - vie :" + this.robot.getPv();
    }

    public Robot getRobot() { return this.robot; }

    public void choixAttaque(Joueur victim, String input)
    {        
        int choix = Integer.parseInt(input);

        // Affichage de l'attaque    ( ENLEVER LE SOP PLUS TARD )
        System.out.println(robot.getAttaque( choix ));

        // Enlever la vie par rapport à l'attaque
        victim.getRobot().recevoirAttaque(robot.getAttaque( choix ));
    }

    public void choixRobot(String input)
    {
        int choix = Integer.parseInt(input);

        System.out.println(getRobot( choix ));

        
        this.robot = new Robot(ctrl.getNomRobot(), pv, vit);
    }




    public String getNom() { return this.nom; }
}