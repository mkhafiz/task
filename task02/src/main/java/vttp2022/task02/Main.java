package vttp2022.task02;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws UnknownHostException, IOException {

        int sum = 0;
        float avgScore;

        final long NANOSEC_PER_SEC = 1000l * 1000 * 1000;
        long startTime = System.nanoTime();

        // Connect to the server
        System.out.println("Connecting to Chuk server on port 80");
        // Socket sock = new Socket("task02.chuklee.com", 80);
        Socket sock = new Socket("68.183.239.26", 80);
        System.out.println("Connected...\n");

        while ((System.nanoTime() - startTime) < 15 * NANOSEC_PER_SEC) {

            // Get the output stream
            OutputStream os = sock.getOutputStream();
            ObjectOutputStream dos = new ObjectOutputStream(os);

            // Get the input stream
            InputStream is = sock.getInputStream();
            ObjectInputStream dis = new ObjectInputStream(is);

            // Write to server
            String input = ("");
            dos.writeUTF(input);
            dos.flush();

            String request = dis.readUTF();
            System.out.printf("Received request: %s\n", request);

            // Get avg
            String[] terms = request.split(" ");
            String[] numbers = (terms[1].split(","));
            // System.out.println("length = " + numbers.length); // test

            for (int i = 0; i < numbers.length; i++) {
                int a = Integer.parseInt(numbers[i]);
                sum += a;
            }
            avgScore = (float) sum / numbers.length;
            // System.out.println("avg is = " + avgScore + "\n"); // test

            // Send output
            dos.writeUTF(numbers[0]);
            dos.writeUTF("Muhammad Khairul Hafiz Bin Ridzwan");
            dos.writeUTF("mkhafiz27@gmail.com");
            dos.writeFloat(avgScore);
            dos.flush();
            dis.readUTF();

            boolean status = dis.readBoolean();
            if (status) {
                System.out.println("SUCCESS");
                sock.close();

            } else {
                System.out.println("FAILED");
                System.err.println(dis.readUTF());
                sock.close();
            }
        }
        sock.close();
    }
}