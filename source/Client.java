import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 9000;

    public static void main(String[] args) 
    {
        try 
        {
            // Connexion au serveur
            System.out.println("Connexion au serveur " + SERVER_IP + ":" + SERVER_PORT + "...");
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connecté au serveur!");

            // Flux d'entrée/sortie
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            while (true) 
            {
                // Envoyer un message si l'utilisateur a tapé quelque chose
                if (System.in.available() > 0) 
                {
                    String userInput = scanner.nextLine();
                    out.println(userInput);
                }

                // Lire un message du serveur s'il y en a un
                if (in.ready()) 
                {
                    String serverResponse = in.readLine();
                    System.out.println(serverResponse);
                }

                // Petite pause pour éviter de saturer le CPU
                Thread.sleep(50);
            }

        } 
        catch (IOException | InterruptedException e) 
        {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}
