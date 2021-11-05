import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static char [][] field;
    private static final char DOT_X='X';
    private static final char DOT_0='0';
    private static final char DOT_EMPTY='.';
    private static int fieldSizeX;
    private static int fieldSizeY;
    private static int scoreHuman;
    private static int scorePC;
    private static int round;
    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();
    private static int roundScore;
    private static int line;
    private static int diag;

    public static void main(String[] args) {
        play();
    }
    private static void play(){
        while (true){
            playRound();
            System.out.printf("Счёт: Вы - %d - комп - %d ", scoreHuman, scorePC);
            System.out.printf("Играем ещё? Y - да, N - нет.");
            if(!scanner.next().toLowerCase().equals("y")){
                System.out.println("Пока.");
                break;
            }
        }
    }
    private static void playRound() {
        System.out.printf("Раунд №%d\n", roundScore++);
        initField(5, 5);
        printField();
        while (true) {
            humanTurn();
            printField();
            if(checkAll(DOT_X)) break;
            pcTurn();
            printField();
            if(checkAll(DOT_0)) break;
        }
    }
    private static void initField(int sizeX, int sizeY){
        fieldSizeX = sizeX;
        fieldSizeY = sizeY;
        field = new char[sizeX][sizeY];
        for(int i=0; i<fieldSizeY; i++){
            for(int j=0; j<fieldSizeX; j++){
                field[i][j] = DOT_EMPTY;
            }
        }
    }
    private static void printField(){
        System.out.print("@|");
        for(int i=1; i<=fieldSizeX; i++){
            System.out.print(i+"|");
        }
        System.out.println();
        for(int i=0; i<fieldSizeY; i++){
            System.out.print(i+1 + "|");
            for(int j=0; j<fieldSizeX; j++){
                System.out.print(field[i][j] + "|");
            }
            System.out.println();
        }
        System.out.print("@|");
        for(int i=1; i<=fieldSizeX; i++){
            System.out.print(i+"|");
        }
        System.out.println();
    }
    private static void humanTurn(){
        int x;
        int y;
    do {
        System.out.println("Введите координаты x и y");
        x = scanner.nextInt() - 1;
        y = scanner.nextInt() - 1;
    }while(!isInField(y,x)||!isEmpty(y,x));
        field[y][x]= DOT_X;
    }
    private static void pcTurn(){
        int x;
        int y;
        do {
            x = random.nextInt(fieldSizeX);
            y = random.nextInt(fieldSizeY);
        }while(!isEmpty(y,x));
        field[y][x]= DOT_0;
    }
    private static boolean isInField(int y, int x){
        return y>=0 && x>=0 && y<fieldSizeY && x<fieldSizeX;
    }
    private static boolean isEmpty(int y, int x){
        return field[y][x] == DOT_EMPTY;
    }
    private static boolean checkWin(char dot){
        for (int i = 0; i < field.length; i++) {
            int sum1 = 0;
            for (int j = 0; j < field.length; j++) {
                if(field[j][i] == dot)  sum1++;
            }
            if(sum1==3) return true;}
        for (int j = 0; j < field.length; j++) {
            int sum2 = 0;
            for (int i = 0; i< field.length; i++) {
                    if(field[j][i] == dot)  sum2++;
            }
            if(sum2==3) return true;
        } return false;
    }
    private static boolean checkDraw(){
        for (int y = 0; y < fieldSizeY; y++) {
            for (int x = 0; x < fieldSizeX; x++) {
                if (isEmpty(y,x)) return false;
            }
        }
        System.out.println("Ничья");
        return true;
    }
    private static boolean checkAll(char dot){
        if(checkDraw()) return  true;
        if(checkWin(dot)){
            if(dot == DOT_X){
                System.out.println("Вы выиграли!!!");
                scoreHuman++;
            }else {
                System.out.println("Вы проиграли.");
                scorePC++;
            } return true;
        }return false;
    }
}
