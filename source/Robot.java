import java.util.ArrayList;

public class Robot
{
    // Couleurs
    public static final String ROUGE = "\u001B[31m";
    public static final String RESET = "\u001B[0m";
    public static final String VERT  = "\u001B[32m";
    public static final String CYAN  = "\u001B[36m";
    public static final String JAUNE = "\u001B[33m";
    public static final String GRAS  = "\u001B[1m";
    public static final String SOULIGNE = "\u001B[4m";

    private String nom;
    private int pv;
    private int vitesse;
    private int deplacement;
    private int position;

    private ArrayList<Attaque> ensAttaque;

    // Constructeur corrigé (ajout déplacement)
    public Robot(String nom, int pv, int vitesse, int deplacement)
    {
        this.nom         = nom;
        this.pv          = pv;
        this.vitesse     = vitesse;
        this.deplacement = deplacement;
        this.position    = 0; // Position de départ fixée à 0
        this.ensAttaque  = new ArrayList<>();
    }

    /* ---------------------- */
    /*         Getteurs        */
    /* ---------------------- */
    public ArrayList<Attaque> getAttaques()   { return this.ensAttaque; }
    public Attaque getAttaque(int index)      { return this.ensAttaque.get(index); }
    public int getPv()                        { return this.pv; }
    public int getVit()                       { return this.vitesse; }
    public String getNom()                    { return this.nom; }
    public int getDeplacement()               { return this.deplacement; }
    public int getPosition()                  { return this.position; }

    /* ---------------------- */
    /*         Setteurs        */
    /* ---------------------- */
    public void setPosition(int position) 
    { 
        this.position = position; 
    }

    /* ---------------------- */
    /*     Autres méthodes     */
    /* ---------------------- */
    public void addAttaque(Attaque attaque) 
    { 
        this.ensAttaque.add(attaque); 
    }

    public boolean infligerAttaqueDistance(Attaque attaque, Robot cible, int distance)
    {
        int degatsInfliges;
        int precisionTir;

        if (!(attaque.getPortee() == 0 && attaque.getPorteeMax() == 0))
        {
            if (distance > attaque.getPorteeMax())
                return false;
        }

        if (distance <= attaque.getPortee()) 
        {
            degatsInfliges = attaque.getDegatMax();
            precisionTir = attaque.getPrecisionMax();
        } 
        else 
        {
            degatsInfliges = attaque.getDegatMin();
            precisionTir = attaque.getPrecisionMin();
        }

        boolean auMoinsUnTouche = false;

        // Pour stocker les messages de tir (qu'on renverra à l'afficheur plus tard)
        StringBuilder tirFeedback = new StringBuilder();

        // Plusieurs tirs
        for (int i = 0; i < attaque.getNbTirs(); i++)
        {
            int random = (int) (Math.random() * 100) + 1;

            if (random <= precisionTir)
            {
                cible.subirDegats(degatsInfliges);
                tirFeedback.append(VERT + "BANG! Tir " + (i+1) + " réussi !" + RESET + "\n");
                auMoinsUnTouche = true;
            }
            else
            {
                tirFeedback.append(ROUGE + "Miss... Tir " + (i+1) + " raté." + RESET + "\n");
            }
        }

        // Afficher le feedback pour l'attaquant
        System.out.print(tirFeedback.toString()); // ← pour console serveur (DEBUG)

        return auMoinsUnTouche;
    }

    public void subirDegats(int degats)
    {
        this.pv -= degats;
        if (this.pv < 0)
            this.pv = 0;
    }



    public String toString()
    {
        String sRet = "";
        sRet = VERT + SOULIGNE + "Robot :" + RESET + "\n" +
               " " + CYAN + "- Nom         : " + ROUGE + this.nom + RESET + "\n" +
               " " + CYAN + "- Pv          : " + RESET + this.pv + "\n" +
               " " + CYAN + "- Vitesse     : " + RESET + this.vitesse + "\n" +
               " " + CYAN + "- Déplacement : " + RESET + this.deplacement + "\n" +
               " " + CYAN + "- Position    : " + RESET + this.position + "\n\n" +
               JAUNE + SOULIGNE + "   Attaques du Robot : " + RESET + "\n";

        for (int cpt = 0; cpt < this.ensAttaque.size(); cpt++)
            sRet += "    # " + cpt + " : " + this.ensAttaque.get(cpt) + "\n";

        return sRet;
    }
}
