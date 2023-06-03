import java.util.Arrays;
import java.util.Scanner;

public class IDFS {
    public static boolean BP(Board game, int depth, int limit) {
        if (depth >= limit) {
            if (Arrays.deepEquals(game.matrixI, game.matrixF)) {
                System.out.println(game.gps);
                return true;
            } else {
                // game.show();
                // System.out.println(gps);
                return false;
            }
        }

        // System.out.println(depth);

        for (int i = 0; i < 4; i++) {
            // System.out.println("tequila " + i);
            if (!Arrays.deepEquals(game.direction(i).matrixI, game.matrixI)) {
                if (BP(game.direction(i), depth + 1, limit)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void BPI(Board game) {
        int prof = 0;
        if (!game.temSolucao()) {
            System.out.println("Não tem solução");
            return;
        }
        while (true) {
            if (BP(game, 0, prof)) {
                break;
            }
            prof++;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board gaming = new Board();
        gaming.read(in);
        BPI(gaming);
        // gaming.algGeral();
        // System.out.println(gaming.temSolucao());
    }
}
