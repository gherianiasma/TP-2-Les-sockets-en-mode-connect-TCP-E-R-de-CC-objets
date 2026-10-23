
import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serveur = new ServerSocket(1234);
            System.out.println("Serveur en attente...");

            while (true) {
                Socket socket = serveur.accept();

                BufferedReader br = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));

                PrintWriter pw = new PrintWriter(
                        socket.getOutputStream(), true);

                String operation = br.readLine();
                String[] parties = operation.split(" ");

                String reponse;

                if (parties.length != 3) {
                    reponse = "Erreur : operation invalide";
                } else {
                    try {
                        double a = Double.parseDouble(parties[0]);
                        String op = parties[1];
                        double b = Double.parseDouble(parties[2]);
                        double resultat = 0;

                        switch (op) {
                            case "+":
                                resultat = a + b;
                                break;
                            case "-":
                                resultat = a - b;
                                break;
                            case "*":
                                resultat = a * b;
                                break;
                            case "/":
                                if (b == 0) {
                                    reponse = "Erreur : division par zero";
                                    pw.println(reponse);
                                    socket.close();
                                    continue;
                                }
                                resultat = a / b;
                                break;
                            default:
                                reponse = "Erreur : operateur invalide";
                                pw.println(reponse);
                                socket.close();
                                continue;
                        }

                        reponse = "Resultat : " + resultat;

                    } catch (NumberFormatException e) {
                        reponse = "Erreur : nombres invalides";
                    }
                }

                pw.println(reponse);
                socket.close();
            }

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
