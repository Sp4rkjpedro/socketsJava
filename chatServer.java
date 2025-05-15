
import java.io.*;
import java.net.*;
import java.util.*;

public class chatServer {
    private static Map<String, ClientHandler> clients = new HashMap<>();
    private static Map<String, Set<String>> groups = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(12345);
        System.out.println("Servidor iniciado na porta 12345...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            new Thread(new ClientHandler(clientSocket)).start();
        }
    }

    static class ClientHandler implements Runnable {
        private Socket socket;
        private String nickname = "Anon";
        private PrintWriter out;
        private BufferedReader in;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public String getNickname() {
            return nickname;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                out.println("Bem-vindo! Use /nick <nome> para definir seu apelido.");

                String input;
                while ((input = in.readLine()) != null) {
                    if (input.startsWith("/nick ")) {
                        String newNick = input.substring(6).trim();
                        clients.remove(nickname);
                        nickname = newNick;
                        clients.put(nickname, this);
                        out.println("Apelido alterado para " + nickname);
                    } else if (input.startsWith("/join ")) {
                        String group = input.substring(6).trim();
                        groups.putIfAbsent(group, new HashSet<>());
                        groups.get(group).add(nickname);
                        out.println("Você entrou no grupo: " + group);
                    } else if (input.startsWith("/msg ")) {
                        String[] parts = input.split(" ", 3);
                        if (parts.length < 3) {
                            out.println("Uso: /msg <grupo> <mensagem>");
                            continue;
                        }
                        String group = parts[1];
                        String message = parts[2];
                        if (!groups.containsKey(group) || !groups.get(group).contains(nickname)) {
                            out.println("Você não está no grupo " + group);
                            continue;
                        }
                        for (String member : groups.get(group)) {
                            if (!member.equals(nickname)) {
                                ClientHandler ch = clients.get(member);
                                if (ch != null) {
                                    ch.out.println("[" + group + "] " + nickname + ": " + message);
                                }
                            }
                        }
                    } else {
                        out.println("Comando desconhecido.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Erro com o cliente " + nickname);
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                clients.remove(nickname);
                groups.forEach((g, set) -> set.remove(nickname));
            }
        }
    }
}
