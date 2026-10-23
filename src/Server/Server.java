
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) {

        try {
            ServerSocket serveur = new ServerSocket(1234);
            System.out.println("Serveur en attente...");

            Socket socket = serveur.accept();
            System.out.println("Client connecté.");

            ObjectOutputStream out = new ObjectOutputStream(
                    socket.getOutputStream());
            out.flush();

            ObjectInputStream in = new ObjectInputStream(
                    socket.getInputStream());

            Operation op = (Operation) in.readObject();

            double a = op.getA();
            String operateur = op.getOperateur();
            double b = op.getB();

            double resultat = 0;
            String reponse;

            switch (operateur) {
                case "+":
                    resultat = a + b;
                    reponse = "Resultat : " + resultat;
                    break;

                case "-":
                    resultat = a - b;
                    reponse = "Resultat : " + resultat;
                    break;

                case "*":
                    resultat = a * b;
                    reponse = "Resultat : " + resultat;
                    break;

                case "/":
                    if (b == 0) {
                        reponse = "Erreur : division par zero";
                    } else {
                        resultat = a / b;
                        reponse = "Resultat : " + resultat;
                    }
                    break;

                default:
                    reponse = "Erreur : operateur invalide";
            }

            out.writeObject(reponse);

            System.out.println("Reponse envoyee : " + reponse);

            in.close();
            out.close();
            socket.close();
            serveur.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
