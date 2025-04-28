public class Attaque
{
    private String nom;
    private double degat;
    private double precision;

    public Attaque(String nom, double degat, double precision)
    {  
        this.nom            = nom;
        this.degat          = degat;
        this.precision      = precision;
    }

    /* ---------------------- */
    /*         Getteur        */
    /* ---------------------- */

    public String getNom()     { return this.nom;   }
    public double getDegat()   { return this.degat; }
    public double getPrecison(){ return this.precision; }


    /* ---------------------- */
    /*    Autres méthodes     */
    /* ---------------------- */
    public String toString()
    {
        return "Attaque: " + this.nom + " Dégât: " + this.degat;
    }

}