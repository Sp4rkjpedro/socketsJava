
import java.io.*;

public class chatCliente {
    public static void main(String[] args) throws IOException {
        ClienteSocket cliente = new ClienteSocket("localhost", 12345);
        BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

        Thread leitor = new Thread(() -> {
            String resposta;
            try {
                while ((resposta = cliente.receberMensagem()) != null) {
                    System.out.println(resposta);
                }
            } catch (IOException e) {
                System.out.println("Desconectado do servidor.");
            }
        });

        leitor.start();

        System.out.println("Digite comandos ou mensagens. Ex: /nick Maria, /join amigos, /msg amigos Olá!");
        String entrada;
        while ((entrada = teclado.readLine()) != null) {
            cliente.enviarMensagem(entrada);
        }

        cliente.fechar();
    }
}
