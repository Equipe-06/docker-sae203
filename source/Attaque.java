public class Attaque
{
    private String nom;

    private int degatMax;
    private int degatMin;
    private int portee;
    private int porteeMax;
    private int precisionMax;
    private int precisionMin;
    private int nbTirs;
    private int chanceMulti;

    // Constructeur
    public Attaque(String nom, int degatMax, int degatMin, int portee, int porteeMax, 
                   int precisionMax, int precisionMin, int nbTirs, int chanceMulti    )
    {
        this.nom           = nom;
        this.degatMax      = degatMax;
        this.degatMin      = degatMin;
        this.portee        = portee;
        this.porteeMax     = porteeMax;
        this.precisionMax  = precisionMax;
        this.precisionMin  = precisionMin;
        this.nbTirs        = nbTirs;
        this.chanceMulti   = chanceMulti;
    }

    /* ---------------------- */
    /*         Getteurs        */
    /* ---------------------- */
    public String getNom      () { return this.nom         ; }
    public int getDegatMax    () { return this.degatMax    ; }
    public int getDegatMin    () { return this.degatMin    ; }
    public int getPortee      () { return this.portee      ; }
    public int getPorteeMax   () { return this.porteeMax   ; }
    public int getPrecisionMax() { return this.precisionMax; }
    public int getPrecisionMin() { return this.precisionMin; }
    public int getNbTirs      () { return nbTirs           ; }
    public int getChanceMulti () { return chanceMulti      ; }

    /* ---------------------- */
    /*    Affichage / Debug    */
    /* ---------------------- */

    public String toString()
    {
        final String RESET      = "\u001B[0m";
        final String TITRE      = "\u001B[1;36m";  // Cyan clair gras
        final String VAL        = "\u001B[1;37m";  // Blanc gras
        final String ACCENT     = "\u001B[1;34m";  // Bleu tactique
        final String SYSTEM     = "\u001B[38;5;244m";  // Gris tech custom
    
        StringBuilder sb = new StringBuilder();
    
        sb.append(ACCENT).append("> ").append(TITRE).append("Attaque: ").append(VAL).append(this.nom).append(RESET).append("\n");
    
        sb.append(SYSTEM)
          .append(String.format("%-10s", "Portée : "))
          .append(VAL).append(String.format("%-5d", this.portee)).append(" km")
          .append(SYSTEM).append(" -> ")
          .append(VAL).append(String.format("%-4d", this.degatMax)).append(" dmg")
          .append(SYSTEM).append(" - ")
          .append(VAL).append(String.format("%-3d", this.precisionMax)).append("% hit chance")
          .append(RESET).append("\n");
    
        sb.append(SYSTEM)
          .append(String.format("%-10s", "Portée : "))
          .append(VAL).append(String.format("%-5d", this.porteeMax)).append(" km")
          .append(SYSTEM).append(" -> ")
          .append(VAL).append(String.format("%-4d", this.degatMin)).append(" dmg")
          .append(SYSTEM).append(" - ")
          .append(VAL).append(String.format("%-3d", this.precisionMin)).append("% hit chance")
          .append(RESET);
    
        if (this.nbTirs > 1)
        {
            sb.append("\n")
              .append(SYSTEM)
              .append(String.format("%-10s", "Tirs : "))
              .append(VAL).append(String.format("%-2d", this.nbTirs))
              .append(SYSTEM).append(" -> ")
              .append(String.format("%-15s", "MultiChance : "))
              .append(VAL).append(this.chanceMulti).append("%")
              .append(RESET);
        }
    
        sb.append("\n");
    
        return sb.toString();
    }
    


}
