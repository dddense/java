public class MainForServer {

    public static void main(String[] args) {

        EchoServerSocket serverSocket = new EchoServerSocket(1488);
        serverSocket.start();
    }
}
