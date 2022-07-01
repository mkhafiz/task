package vttp2022.task02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Session {

    private InputStream is;
    private DataInputStream dis;
    private OutputStream os;
    private DataOutputStream dos;

    public String read() throws IOException {
        return dis.readUTF();
    }

    public void write(String msg) throws IOException {
        dos.writeUTF(msg);
        dos.flush();
    }

    public void close() {
        try {
            dis.close();
            is.close();
            dos.close();
            os.close();
        } catch (IOException ex) {

        }
    }
}
