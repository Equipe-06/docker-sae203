import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client 
{
    private static final String SERVER_IP = "5.tcp.eu.ngrok.io";
    private static final int SERVER_PORT = 12108;

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
            
            Thread receiveThread = new Thread(() -> {
                try 
                {
                    String serverResponse;
                    while ( (serverResponse = in.readLine() ) != null) 
                    {
                        try { Thread.sleep(50); } catch (Exception e) {}
                        System.out.println(serverResponse);
                    }   
                    
                } 
                catch (IOException e) { System.out.println("Erreur réception: " + e.getMessage()); }
            });
            receiveThread.start();

            while (true) 
            {
                String userInput = scanner.nextLine();
                out.println(userInput);
            }

            

        } 
        catch (IOException e) 
        {
            System.out.println("Erreur: " + e.getMessage());
        }
    }
}