import java.util.ArrayList;

public class Robot
{
    // Couleurs 
    public static final String ROUGE = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String VERT  = "\u001B[32m";
    public static final String CYAN  = "\u001B[36m";
    public static final String JAUNE = "\u001B[33m";
    public static final String GRAS         = "\u001B[1m" ;  
    public static final String SOULIGNE     = "\u001B[4m" ; 

    private String             nom;
    private int                pv;
    private int                vitesse;
    private ArrayList<Attaque> ensAttaque;

    public Robot(String nom, int pv, int vitesse)
    {
        this.nom        = nom;
        this.pv         = pv;
        this.vitesse    = vitesse;
        this.ensAttaque = new ArrayList<>();
    }

    /* ---------------------- */
    /*         Getteur         */
    /* ---------------------- */
    public ArrayList<Attaque> getAttaques()   { return this.ensAttaque; }
    public Attaque getAttaque (int index)     { return this.ensAttaque.get(index); }
    public int     getPv ()                   { return this.pv; }
    public int     getVit()                   { return this.vitesse; }
    public String  getNom()                   { return this.nom; }

    /* ---------------------- */
    /*     Autres méthodes     */
    /* ---------------------- */
    public void addAttaque(Attaque attaque) { this.ensAttaque.add(attaque); }

    public boolean infligerAttaque(Attaque attaque, Robot ennemi)
    {
        int precision, hasard;

        hasard = (int) (Math.random() * 100) + 1;

        if ( hasard > attaque.getPrecison())
        {
            return false;
        }
        else
        {
            ennemi.pv -= attaque.getDegat();
            return true;
        }
    }

    public String toString()
    {
        String sRet = "";

        sRet = VERT + SOULIGNE + "Robot :" + RESET + "\n" +
              " " + CYAN + "- Nom   : " + ROUGE + this.nom + RESET + "\n" +
              " " + CYAN + "- Pv    : " + RESET + this.pv + "\n" +
              " " + CYAN + "- Speed : " + RESET + this.vitesse + "\n" +
              JAUNE + SOULIGNE + "   Attaques du Robot : " + RESET + "\n";

        for (int cpt = 0; cpt < this.ensAttaque.size(); cpt++)
            sRet += "    # " + cpt + " : " + this.ensAttaque.get(cpt) + "\n";

        return sRet;
    }
}