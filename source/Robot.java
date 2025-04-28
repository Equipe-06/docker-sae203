import java.util.ArrayList;

public class Robot
{
    private String              nom;
    private double              pv;
    private int                 vitesse;
    private ArrayList<Attaque>  ensAttaque;

    public Robot( String nom, double pv, int vitesse )
    {
        this.nom        = nom;
        this.pv         = pv;
        this.vitesse    = vitesse;
        this.ensAttaque = new ArrayList<>();
        this.initAttaque();
    }
    public void recevoirAttaque( Attaque attaque )
    {
        this.pv -= attaque.getDegat();
    }

    public void infligerAttaque( Attaque attaque, Robot ennemi )
    {
        ennemi.pv -= attaque.getDegat();
    }

    private void initAttaque()
    {
        /*
        String nom, ligneAttaque ;
        double degat; 

		this.ensAttaque = new ArrayList<Attaque>();

		try
		{
			Scanner sc 		= new Scanner ( new FileInputStream ( "attaque.data" ) );


            while ( sc.hasNextLine() )
			{
				ligneAttaque = sc.nextLine();
				nom = ligneAttaque.substring(1,30 );
                degat = ligneAttaque.substring(30);
				this.ensAttaque.add( new Attaque( nom.trim())) ;
				this.nbEditeur ++;
			}

			sc.close();
		}
		catch (Exception e){ e.printStackTrace(); }
        */
        this.ensAttaque.add(new Attaque("Laser Éclair" , 10.0   ));
        this.ensAttaque.add(new Attaque("Missile Sonic", 15.0   ));
        this.ensAttaque.add(new Attaque("Coup de Fer"  , 8.0    ));
    }

    public Attaque getAttaque(int index)
    {
        return this.ensAttaque.get(index);
    }

    public ArrayList<Attaque> getAllAttaque() { return this.ensAttaque; }
    

    public double getPv()  {return this.pv; }

    public int getVit() {return this.vitesse;}

    public String getNom(){ return this.nom;}

     

}