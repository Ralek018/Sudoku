public class Main {
    public static void main(String[] args) {
        char[][] board1 = new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        System.out.println(isValidSudoku(board1));

        char[][] board2 = {
                  {'8', '3', '.', '.', '7', '.', '.', '.', '.'}
                , {'6', '.', '.', '1', '9', '5', '.', '.', '.'}
                , {'.', '9', '8', '.', '.', '.', '.', '6', '.'}
                , {'8', '.', '.', '.', '6', '.', '.', '.', '3'}
                , {'4', '.', '.', '8', '.', '3', '.', '.', '1'}
                , {'7', '.', '.', '.', '2', '.', '.', '.', '6'}
                , {'.', '6', '.', '.', '.', '.', '2', '8', '.'}
                , {'.', '.', '.', '4', '1', '9', '.', '.', '5'}
                , {'.', '.', '.', '.', '8', '.', '.', '7', '9'}};

        System.out.println(isValidSudoku(board2));


    }
    //validation(board) - proverava da li su u poljima samo brojevi od 0-9 ili .
    //prvi uslov - podelio sam matricu na 9 boxeva, odnosno 9 matrice 3x3 i proverio duple brojeve u njima
    //drugi uslov - provera da li ima istih vrednosti u kolonama i redovima

    public static boolean isValidSudoku(char[][] board) {
        if(validation(board)){
            if(prviUslov(board)) {
                for (int i = 0; i < 9; i++) {
                    if (!drugiUslov(board,i)) {
                        return false;
                    }
                }
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    private static boolean isNumber(char c){
        if(c=='.'){
            return true;
        }else {
            return c >= '1' & c <= '9';
        }
    }
    private static boolean validation(char[][] board){
        if(board!=null){
            for(int i = 0;i<9;i++){
                for(int j = 0;j<9;j++){
                    if(!isNumber(board[i][j])){
                        return false;
                    }
                }
            }

        }else {
            return false;
        }
        return true;
    }
    private static boolean drugiUslov(char[][] board, int j){
        char[] niz = new char[10];
        char[] niz1 = new char[10];
        int br = 0;
        int br1 = 0;
        for(int i = 0; i<9 ; i++){
            if(board[i][j]!='.'){
                niz[br]=board[i][j];
                br++;
            }
            if(board[j][i]!='.'){
                niz1[br1]=board[j][i];
                br1++;
            }
        }
        char[] nizPoI = new char[br];
        char[] nizPoJ = new char[br1];

        if (br >= 0) System.arraycopy(niz, 0, nizPoI, 0, br);
        if (br1 >= 0) System.arraycopy(niz1, 0, nizPoJ, 0, br1);

        return proveraNiza(nizPoI) && proveraNiza(nizPoJ);
    }

    private static boolean proveraNiza(char[] niz){
        for(int i = 0; i<niz.length-1;i++) {
            for (int k = i + 1; k <niz.length; k++) {
                if (niz[i] == niz[k]) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean prviUslov(char[][] board){
        boolean box012,box345,box678;
        box012 = box(board,0,3);
        box345 = box(board,3,6);
        box678 = box(board,6,9);
        return box012 && box345 && box678;
    }

    private static boolean box(char[][] board,int i_pocetno, int velicina_matrice) {
        char[] niz = new char[10];
        char[] niz1 = new char[10];
        char[] niz2 = new char[10];
        int br = 0;
        int br1 = 0;
        int br2 = 0;
        boolean box1,box2,box3;

            for (int i = i_pocetno; i < velicina_matrice; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] != '.') {
                        niz[br] = board[i][j];
                        br++;
                    }
                }
            }
            char[] noviNiz = new char[br];
            if (br >= 0) System.arraycopy(niz, 0, noviNiz, 0, br);

            box1 =  proveraNiza(noviNiz);

        for (int i = i_pocetno; i < velicina_matrice; i++) {
            for (int j = 3; j < 6; j++) {
                if (board[i][j] != '.') {
                    niz1[br1] = board[i][j];
                    br1++;
                }
            }
        }
        char[] noviNiz1 = new char[br1];
        if (br1 >= 0) System.arraycopy(niz1, 0, noviNiz1, 0, br1);

        box2 =  proveraNiza(noviNiz1);

        for (int i = i_pocetno; i < velicina_matrice; i++) {
            for (int j = 6; j < 9; j++) {
                if (board[i][j] != '.') {
                    niz2[br2] = board[i][j];
                    br2++;
                }
            }
        }
        char[] noviNiz2 = new char[br2];
        if (br2 >= 0) System.arraycopy(niz2, 0, noviNiz2, 0, br2);

        box3 =  proveraNiza(noviNiz2);

        return box1 && box2 && box3;

    }
}
