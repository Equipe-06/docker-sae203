
public class Robot
{
    private String              nom;
    private double              pv;
    private int                 vitesse;
   

    public Robot( String nom, double pv, int vitesse  )
    {
        this.nom        = nom;
        this.pv         = pv;
        this.vitesse    = vitesse;
    }
    
    public void recevoirAttaque( Attaque attaque )
    {
        this.pv -= attaque.getDegat();
    }

    public void infligerAttaque( Attaque attaque, Robot ennemi )
    {
        ennemi.pv -= attaque.getDegat();
    }    

    public double getPv()  { return this.pv;     }

    public int getVit()    { return this.vitesse;}

    public String getNom() { return this.nom;    }

     

}