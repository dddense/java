import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Players: ");
        Random random = new Random();
        String names = in.nextLine();
        String[] playersNames = names.split(" ");
        int n = playersNames.length;
        System.out.print("Mafia: ");
        int mafia = in.nextInt();
        System.out.print("Doc: ");
        int doc = in.nextInt();
        System.out.print("Cop: ");
        int cop = in.nextInt();
        System.out.println();
        int[] players = new int[n];
        String[] role = {" citizen", " mafia", " cop", " doctor"};
        int count = mafia + cop + doc;
        int roles = 0;
        while (roles < count) {
            int k = random.nextInt(n) + 1;
            if (players[k - 1] == 0) {
                if (mafia != 0) {
                    mafia--;
                    players[k - 1] = 1;
                    roles++;
                } else if (cop != 0) {
                    cop--;
                    players[k - 1] = 2;
                    roles++;
                } else if (doc != 0) {
                    doc--;
                    players[k - 1] = 3;
                    roles++;
                }
            }
        }

        for (int i = 0; i < players.length; i++)
            System.out.println(playersNames[i] + role[players[i]]);

        System.out.println("Enter space to quit");
        String s = in.nextLine();
    }
}
