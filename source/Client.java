import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    private static final String SERVER_IP = "127.0.0.1";
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
                String userInput = scanner.nextLine();

                if (userInput != null && !userInput.isEmpty()) 
                
                    out.println(userInput);

                if (in.ready()) 
                {
                    String serverResponse = in.readLine();
                    System.out.println(serverResponse);
                }

                Thread.sleep(50);
            }

        } 
        catch (IOException | InterruptedException e) 
        {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}