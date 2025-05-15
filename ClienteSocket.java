
import java.io.*;
import java.net.*;

public class ClienteSocket {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    public ClienteSocket(String endereco, int porta) throws IOException {
        socket = new Socket(endereco, porta);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
    }

    public void enviarMensagem(String mensagem) {
        out.println(mensagem);
    }

    public String receberMensagem() throws IOException {
        return in.readLine();
    }

    public void fechar() throws IOException {
        socket.close();
    }
}
