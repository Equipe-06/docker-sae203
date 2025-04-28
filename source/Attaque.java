public class Attaque
{
    private String nom;
    private int    degat;
    private int    precision;

    public Attaque(String nom, int degat, int precision)
    {  
        this.nom       = nom;
        this.degat     = degat;
        this.precision = precision;
    }

    /* ---------------------- */
    /*         Getteur        */
    /* ---------------------- */

    public String getNom      (){ return this.nom;       }
    public int    getDegat    (){ return this.degat;     }
    public int    getPrecison (){ return this.precision; }


    /* ---------------------- */
    /*    Autres méthodes     */
    /* ---------------------- */
    public String toString()
    {
        return "Attaque: " + this.nom + " Dégât: " + this.degat;
    }

}