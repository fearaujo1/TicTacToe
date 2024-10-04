import java.util.Scanner;

public class TicTacToe {
    // Tabuleiro do Jogo
    private char[][] board;

    // Indica o jogador atual ('X' ou 'O')
    private char player;

    public TicTacToe() {
        board = new char[3][3];
        player = 'X';
        initializeBoard();
    }

    // Inicia o tabuleiro com espaços vazios
    public void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Exibe o tabuleiro
    public void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // Verifica se o tabuleiro está cheio
    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    // Alterna o jogador atual
    public void changePlayer() {
        player = (player == 'X') ? 'O' : 'X';
    }

    // Verifica se o jogador atual venceu

    public boolean checkForWin() {
        return (checkRows() || checkColumns() || checkDiagonals());
    }

    // Verifica as linhas para a vitória
    private boolean checkRows() {
        for (int i = 0; i < 3; i++) {
            if (    (board[i][0] == player) &&
                    (board[i][1] == player) &&
                    (board[i][2] == player)) {
                return true;
            }
        }
        return false;
    }

    // Verifica as colunas para a vitória
    private boolean checkColumns() {
        for (int j = 0; j < 3; j++) {
            if (    (board[0][j] == player) &&
                    (board[1][j] == player) &&
                    (board[2][j] == player)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        if (    (board[0][0] == player) &&
                (board[1][1] == player) &&
                (board[2][2] == player)) {
            return true;
        }
        if (    (board[0][2] == player) &&
                (board[1][1] == player) &&
                (board[2][0] == player)) {
            return true;
        }
        return false;
    }

    // Coloca o marcador no tabuleiro
    public boolean placeMark(int row, int col) {
        //Verifica se a posição está dentro dos limites
        if ((row >= 0) && (row < 3) && (col >= 0) && (col < 3)) {
            if (board[row][col] == ' ') {
                board[row][col] = player;
                return true;
            }
        }
        return false;
    }

    // Inicia o jogo
    public void playGame() {
        Scanner sc = new Scanner(System.in);
        boolean gameEnded = false;

        System.out.println("Bem vindo ao Jogo da Velha!");
        printBoard();

        while (!gameEnded) {
            int row = -1;
            int col = -1;
            boolean validInput = false;

            while(!validInput) {
                System.out.println("Jogador " + player + ", insira a linha (1-3) e a coluna (1-3) para jogar: ");
                System.out.println("Linha: ");
                if (sc.hasNextInt()) {
                    row = sc.nextInt() - 1;
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    sc.next(); // Limpa a entrada inválida
                    continue;
                }

                System.out.println("Coluna: ");
                if (sc.hasNextInt()) {
                    col = sc.nextInt() - 1;
                } else {
                    System.out.println("Entrada inválida. Por favor, insira um número.");
                    sc.next();
                    continue;
                }

                if (placeMark(row, col)) {
                    validInput = true;
                } else {
                    System.out.println("Posição inválida ou já ocupada. Tente Novamente.");
                }
            }

            printBoard();

            if (checkForWin()) {
                System.out.println("Parabéns! Jogador " + player + " venceu!");
                gameEnded = true;
            } else if (isBoardFull()) {
                System.out.println("Empate! O tabuleiro está cheio.");
                gameEnded = true;
            } else {
                changePlayer();
            }
        }

        sc.close();
    }

    // Método principal para executar o jogo
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        game.playGame();
    }
}