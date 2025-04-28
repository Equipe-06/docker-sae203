public class Attaque
{
    private String nom;
    private double degat;

    public Attaque(String nom, double degat)
    {  
        this.nom   = nom;
        this.degat = degat;
    }

    public String getNom()
    {
        return this.nom;
    }

    public double getDegat()
    {
        return this.degat; 
    }

    public String toString()
    {
        return "Attaque: " + this.nom + " Dégt: " + this.degat;
    }

}