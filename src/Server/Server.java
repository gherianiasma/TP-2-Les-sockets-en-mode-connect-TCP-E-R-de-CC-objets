
import java.io.*;
import java.net.*;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket socketServeur = new ServerSocket(1234);

        System.out.println("Le serveur attend un client...");

        while (true) {

            Socket socket = socketServeur.accept();
            System.out.println("Client connecté.");

            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br = new BufferedReader(isr);

                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);

                String operation = br.readLine();
                System.out.println("Opération reçue : " + operation);

                String[] parties = operation.trim().split("\\s+");

                String reponse;

                if (parties.length != 3) {
                    reponse = "Erreur : opération invalide.";
                } else {
                    try {
                        double a = Double.parseDouble(parties[0]);
                        String operateur = parties[1];
                        double b = Double.parseDouble(parties[2]);

                        switch (operateur) {
                            case "+":
                                reponse = "Résultat : " + (a + b);
                                break;

                            case "-":
                                reponse = "Résultat : " + (a - b);
                                break;

                            case "*":
                                reponse = "Résultat : " + (a * b);
                                break;

                            case "/":
                                if (b == 0) {
                                    reponse = "Erreur : division par zéro.";
                                } else {
                                    reponse = "Résultat : " + (a / b);
                                }
                                break;

                            default:
                                reponse = "Erreur : opérateur invalide.";
                        }

                    } catch (NumberFormatException e) {
                        reponse = "Erreur : nombres invalides.";
                    }
                }

                pw.println(reponse);

                br.close();
                pw.close();
                socket.close();

            } catch (IOException e) {
                System.out.println("Erreur : " + e.getMessage());
            }
        }
    }
}
