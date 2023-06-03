import java.util.Arrays;
import java.util.Stack;
import java.util.Scanner;
import java.util.LinkedList;

public class DFS {
    public static void BP(Board game) {
        Stack<Board> wcf = new Stack<>();
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
        node = wcf.pop();
        LinkedList<String> pbp = new LinkedList<>();
        while(true){
            System.out.println(node.gps);
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
                        wcf.push(tempo);
                        pbp.addLast(tmp);
                }
            }
        }
        node = wcf.pop();
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
        BP(gaming);
        // gaming.algGeral();
        // System.out.println(gaming.temSolucao());
    }
}
