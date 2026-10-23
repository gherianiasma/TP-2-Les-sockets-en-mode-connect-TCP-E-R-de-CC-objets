
import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 1234);

            BufferedReader clavier = new BufferedReader(
                    new InputStreamReader(System.in));

            PrintWriter pw = new PrintWriter(
                    socket.getOutputStream(), true);

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));

            System.out.print("Entrez une operation (ex: 55 * 25) : ");
            String operation = clavier.readLine();

            pw.println(operation);

            String reponse = br.readLine();
            System.out.println(reponse);

            socket.close();

        } catch (IOException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
