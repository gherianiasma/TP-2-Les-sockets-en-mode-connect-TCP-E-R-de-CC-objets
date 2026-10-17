
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 1234);

        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);

        InputStream is = socket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        BufferedReader clavier = new BufferedReader(
                new InputStreamReader(System.in)
        );

        System.out.println("Connecté au serveur.");

        System.out.print("Entrez une opération (ex: 55 * 25) : ");
        String operation = clavier.readLine();

        pw.println(operation);

        String reponse = br.readLine();
        System.out.println(reponse);

        socket.close();
    }
}
