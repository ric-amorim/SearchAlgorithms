import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class GreedyManhattan {

    public static int[] position(Board game, int numero) {
        int[] res = new int[2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (game.matrixI[i][j] == numero) {
                    res[0] = j;
                    res[1] = i;
                }
            }
        }
        return res;
    }

    public static int ManhattanDist(Board game) {
        int res = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                res += Math.abs(j - position(game, game.matrixF[i][j])[0])
                        + Math.abs(i - position(game, game.matrixF[i][j])[1]);
            }
        }
        return res;
    }

    public static void GM(Board game) {
        PriorityQueue<Board> wcf = new PriorityQueue<>(
                (Board c1, Board c2) -> Integer.compare(ManhattanDist(c1), ManhattanDist(c2)));
        if (!game.temSolucao()) {
            System.out.println("Não tem solução");
            return;
        }
        if (Arrays.deepEquals(game.matrixI, game.matrixF)) {
            System.out.println("Não é preciso fazer nenhum movimento");
            return;
        }
        wcf.add(game);
        Board node;
        node = wcf.remove();
        LinkedList<String> pbp = new LinkedList<>();
        while (true) {
            if (Arrays.deepEquals(node.matrixI, node.matrixF)) {
                //System.out.println(pbp.size());
                System.out.println(node.gps);
                break;
            }
            Board tempo;
            String tmp;
            for (int i = 0; i < 4; i++) {
                tempo = node.direction(i);
                tmp = toInt(tempo.matrixI);
                if (!Arrays.deepEquals(tempo.matrixI, node.matrixI)) {
                    if (!pbp.contains(tmp)) {
                        wcf.add(tempo);
                        pbp.addLast(tmp);
                    }
                }
            }
            if (wcf.isEmpty()) {
                System.out.println("Ficou Preso");
                break;
            }
            node = wcf.remove();
        }
    }

    public static String toInt(int[][] mat) {
        String soma = "";
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                soma += mat[i][j];
                // System.out.println(soma);
            }
        }
        // System.out.println(soma);
        return soma;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Board gaming = new Board();
        gaming.read(in);
        GM(gaming);

    }
}
