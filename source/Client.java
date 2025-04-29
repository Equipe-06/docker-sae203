import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;
import java.awt.*;

public class Client 
{
    private static final String SERVER_IP   = "5.tcp.eu.ngrok.io";
    private static final int    SERVER_PORT = 12108;

    private FrameClient frameClient;

    private PrintWriter     out;
    private BufferedReader  in;

    public Client() 
    {
        this.frameClient = new FrameClient( this );
        this.initClient();
    }

    public void initClient()
    {
        try 
        {
            System.out.println("Connexion au serveur " + SERVER_IP + ":" + SERVER_PORT + "...");
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Connecté au serveur!");

            this.out        = new PrintWriter(socket.getOutputStream(), true);
            this.in         = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            Scanner scanner = new Scanner(System.in);

            Thread receiveThread = new Thread(() -> {
                try 
                {
                    String serverResponse;
                    while ( (serverResponse = this.in.readLine() ) != null) 
                    {
                        try { Thread.sleep(50); } catch (Exception e) {}
                        System.out.println(serverResponse);
                        this.findAction( serverResponse );
                    }   
                    
                } 
                catch (IOException e) { System.out.println("Erreur réception: " + e.getMessage()); }
            });
            receiveThread.start();

            while (true) 
            {
                String userInput = scanner.nextLine();
                this.sendResponse(userInput);
            } 

        } 
        catch (IOException e) 
        {
            System.out.println("Erreur: " + e.getMessage());
        }
    }

    public void findAction(String action) 
    {
        String[] parts = action.split(":", 2);
        String   type  = parts[0];
        String   data  = parts.length > 1 ? parts[1] : "";

        switch (type) 
        {
            case "CREATING_PLAYER" -> this.frameClient.openPanelJoueur(data);
        }

        this.frameClient.update();
    }

    public void sendResponse(String response)
    {
        this.out.println(response);
    }

    public static void main(String[] args) 
    {
        new Client();    
    }
}