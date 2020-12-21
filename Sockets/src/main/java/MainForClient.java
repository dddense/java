import com.beust.jcommander.JCommander;

import java.util.Scanner;

public class MainForClient {

    public static void main(String[] args) {


        Args argv = new Args();
        JCommander.newBuilder().addObject(argv).build().parse(args);

        SocketClient client = new SocketClient(argv.host, argv.port);
        Scanner in = new Scanner(System.in);
        while (true) {
            String message = in.nextLine();
            client.sendMessage(message);
        }
    }
}
