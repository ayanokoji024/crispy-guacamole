package org.example;

public class Main {

    public static int MATRIX_HEIGHT = 6;
    public static int MATRIX_WIDTH = 6;

    public static char[][] wordPuzzle = new char[][]{{'A','B','C','D','V','D'},{'M','X','Y','J','K','A'},{'J','Z','L','Y','O','M'},{'L','P','R','O','S','E'},{'N','D','S','P','U','R'},{'F','I','R','E','N','N'}};

    public static boolean leftToRight(String word){
        if(word.length()>MATRIX_WIDTH){
            return false;
        }
        else{
            int iterations = MATRIX_WIDTH - word.length() + 1;
            for (int i = 0; i < MATRIX_HEIGHT; i++) {
                StringBuilder stringBuilder;
                for (int j = 0; j < iterations; j++) {
                    stringBuilder = new StringBuilder();
                    for (int k = 0; k < word.length(); k++){
                        stringBuilder.append(wordPuzzle[i][j+k]);
                    }
                    if(word.equals(stringBuilder.toString())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean topToBottom(String word){

        if(word.length()>MATRIX_HEIGHT){
            return false;
        }
        else{
            int iterations = MATRIX_HEIGHT - word.length() + 1;
            for (int i = 0; i < MATRIX_WIDTH; i++) {
                StringBuilder stringBuilder;
                for (int j = 0; j < iterations; j++) {
                    stringBuilder = new StringBuilder();
                    for (int k = 0; k < word.length(); k++){
                        stringBuilder.append(wordPuzzle[j+k][i]);
                    }
                    if(word.equals(stringBuilder.toString())){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean searchForWord(String word){
        return topToBottom(word) || leftToRight(word);
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        for (int i = 0; i < MATRIX_HEIGHT; i++) {
            for (int j = 0; j < MATRIX_WIDTH; j++) {
                System.out.print(wordPuzzle[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println(leftToRight("LYOM"));
        System.out.println(topToBottom("LYOM"));

        System.out.println("Check for searchForWord method");

        System.out.println(searchForWord("LYOM"));
        System.out.println(searchForWord("LNE"));
        System.out.println(searchForWord("XYZ"));
    }
}

