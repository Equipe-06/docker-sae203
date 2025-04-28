import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Joueur
{
    // Couleurs ANSI
    public static final String RESET        = "\u001B[0m";
    public static final String VERT         = "\u001B[32m";
    public static final String CYAN         = "\u001B[36m";
    public static final String JAUNE        = "\u001B[33m";
    public static final String ROUGE        = "\u001B[31m";
    public static final String MAGENTA      = "\u001B[35m";
    public static final String BLANC        = "\u001B[37m";
    public static final String GRAS         = "\u001B[1m" ;  
    public static final String SOULIGNE     = "\u001B[4m" ; 

    private String     nom;
    private Robot      robot;
    private Controleur ctrl;

    private Socket         socket;
    private PrintWriter    writer;
    private BufferedReader reader;

    public Joueur(String nom, Controleur ctrl)
    {
        this.ctrl  = ctrl;
        this.nom   = nom;
        this.robot = null;
    }
    
    public String toString()
    {
        return VERT + SOULIGNE + "Joueur :" + RESET + "\n" +
                " " + CYAN + "- Nom: " + RESET + ROUGE + GRAS +  this.nom + RESET + "\n" + 
                this.robot;  // Affiche le robot avec son toString() proprement
    }

    /* ---------------------- */
    /*         Getteur        */
    /* ---------------------- */

    public Robot  getRobotJoueur() { return this.robot; }
    public String getNom()         { return this.nom; }

    public Socket getSocket() 
    { 
        return this.socket; 
    }
    
    public void setSocket(Socket socket) 
    { 
        this.socket = socket; 
    }
    
    public PrintWriter getWriter() 
    { 
        return this.writer; 
    }
    
    public void setWriter(PrintWriter writer) 
    { 
        this.writer = writer; 
    }
    
    public BufferedReader getReader() 
    { 
        return this.reader; 
    }
    
    public void setReader(BufferedReader reader) 
    { 
        this.reader = reader; 
    }

    /* ---------------------- */
    /*  Choix Robot/Attaques  */
    /* ---------------------- */
    public void choixAttaque(Joueur victim, String input)
    {        
        int choix = Integer.parseInt(input);
        this.robot.infligerAttaque(this.robot.getAttaque(choix), victim.getRobotJoueur());
    }

    public void choixRobot(String input)
    {
        int cpt = 0;

        for (Robot robot : this.ctrl.getEnsRobot())
        {
            if (input.equals(robot.getNom()))
                break;
            cpt++;
        }
        if (cpt == this.ctrl.getEnsRobot().size())
            cpt = 0;
        
        // Affiche le robot choisi avec couleur
        System.out.println(CYAN + "Vous avez choisi le robot : " + RESET + this.ctrl.getRobot(cpt));

        this.robot = this.ctrl.getRobot(cpt);
    }
}
