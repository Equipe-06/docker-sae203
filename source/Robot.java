import java.util.ArrayList;

public class Robot
{
    // Couleurs ANSI
    public static final String RESET = "\u001B[0m";
    public static final String VERT  = "\u001B[32m";
    public static final String CYAN  = "\u001B[36m";
    public static final String JAUNE = "\u001B[33m";

    private String             nom;
    private double             pv;
    private int                vitesse;
    private ArrayList<Attaque> ensAttaque;

    public Robot(String nom, double pv, int vitesse)
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
    public double  getPv ()                   { return this.pv; }
    public int     getVit()                   { return this.vitesse; }
    public String  getNom()                   { return this.nom; }

    /* ---------------------- */
    /*     Autres méthodes     */
    /* ---------------------- */
    public void addAttaque(Attaque attaque) { this.ensAttaque.add(attaque); }

    public void infligerAttaque(Attaque attaque, Robot ennemi)
    {
        ennemi.pv -= attaque.getDegat();
    }

    public String toString()
    {
        String sRet = "";

        sRet = VERT + "Robot :" + RESET + "\n" +
              " " + CYAN + "- Nom   : " + RESET + this.nom + "\n" +
              " " + CYAN + "- Pv    : " + RESET + this.pv + "\n" +
              " " + CYAN + "- Speed : " + RESET + this.vitesse + "\n" +
              JAUNE + "   Attaques du Robot : " + RESET + "\n";

        for (int cpt = 0; cpt < this.ensAttaque.size(); cpt++)
            sRet += "    # " + cpt + " : " + this.ensAttaque.get(cpt) + "\n";

        return sRet;
    }
}
