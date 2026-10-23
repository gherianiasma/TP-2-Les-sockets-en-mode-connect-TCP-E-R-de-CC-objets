
import java.io.*;
import java.net.*;

public class Client {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("localhost", 1234);

            ObjectOutputStream out = new ObjectOutputStream(
                    socket.getOutputStream());
            out.flush();

            ObjectInputStream in = new ObjectInputStream(
                    socket.getInputStream());

            Operation op = new Operation(55, "*", 25);

            out.writeObject(op);

            String reponse = (String) in.readObject();

            System.out.println(reponse);

            in.close();
            out.close();
            socket.close();

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}
