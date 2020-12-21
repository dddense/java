import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class EchoServerSocket {

    private ServerSocket server;
    private List<Socket> clients;

    public EchoServerSocket(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        clients = new LinkedList<>();
    }

    public void start() {

        System.out.println("Running");
        new Thread(getClients).start();
    }

    private final Runnable getClients = () -> {

        while (true) {
            try {
                Socket client = server.accept();
                clients.add(client);
                new Worker(client);
                System.out.println("New client connected, amount: " + clients.size());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    };

    public class Worker extends Thread {

        private Socket socket;
        private BufferedReader fromClient;
        private PrintWriter toClient;

        public Worker(Socket socket) {

            this.socket = socket;
            try {
                fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
            this.start();
        }

        @Override
        public void run() {

            while (true) {
                try {
                    String message = fromClient.readLine();
                    System.out.println(message + " " + socket);
                    for (Socket client : clients) {
                        if (client != socket) {
                            toClient = new PrintWriter(client.getOutputStream(),true);
                            toClient.println(message);
                        }
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    }
}