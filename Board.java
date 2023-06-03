import java.util.Scanner;

class Board {
    int[][] matrixI;
    int[][] matrixF;
    int xI, xF, yI, yF;
    String gps;

    Board() {
        matrixI = new int[4][4];
        matrixF = new int[4][4];
        gps = "";
    }

    public void read(Scanner in) {
        int temp;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp = in.nextInt();
                if (temp == 0) {
                    yI = i;
                    xI = j;
                }
                matrixI[i][j] = temp;
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp = in.nextInt();
                if (temp == 0) {
                    yF = i;
                    xF = j;
                }
                matrixF[i][j] = temp;
            }
        }
    }

    public Board direction(int flag) {
        Board tempmat = new Board();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempmat.matrixI[i][j] = matrixI[i][j];
            }
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                tempmat.matrixF[i][j] = matrixF[i][j];
            }
        }
        tempmat.xI = this.xI;
        tempmat.yI = this.yI;
        int[][] sum = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        String[] dir = { "cima ", "baixo ", "direita ", "esquerda " };
        if (tempmat.xI + (sum[flag][1]) > 3 || tempmat.xI + (sum[flag][1]) < 0 || tempmat.yI + (sum[flag][0]) > 3
                || tempmat.yI + (sum[flag][0]) < 0)
            return this;
        int temp;
        temp = tempmat.matrixI[yI + (sum[flag][0])][xI + (sum[flag][1])];
        tempmat.matrixI[yI + (sum[flag][0])][xI + (sum[flag][1])] = tempmat.matrixI[yI][xI];
        tempmat.matrixI[yI][xI] = temp;
        tempmat.xI += (sum[flag][1]);
        tempmat.yI += (sum[flag][0]);
        tempmat.gps += gps + dir[flag];
        return tempmat;
    }

    public void show() {
        System.out.println("Matrix Inicial:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrixI[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("zero no " + xI + "|" + yI);

        System.out.println("Matrix Final:");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(matrixF[i][j] + "|");
            }
            System.out.println();
        }
        System.out.println("zero no " + xF + "|" + yF);

    }

    public int inversion(int[][] Tabela) { // calcular a inversao
        int count = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i == 3 && j == 3 || Tabela[i][j] > 0) {
                    for (int k = 0; k < 4; k++) {
                        for (int l = 0; l < 4; l++) {
                            if (k < i)
                                continue;
                            if (l <= j && k == i)
                                continue;
                            if ((Tabela[i][j] > Tabela[k][l]) && (Tabela[k][l] != 0)) {
                                // System.out.println(Tabela[k][l] +"|" + Tabela[i][j]);
                                count++;
                            }
                        }
                    }
                }

            }
        }

        return count;
    }

    public boolean temSolucao() {
        int invI = inversion(matrixI);
        int invF = inversion(matrixF);
        if (((invI % 2 == 0) == (yI % 2 == 1)) == ((invF % 2 == 0) == (yF % 2 == 1))) {
            return true;
        }
        return false;
    }
}
