package org.example;

import java.util.Scanner;

public class Main {

    public static int MATRIX_HEIGHT = 6;
    public static int MATRIX_WIDTH = 6;

    public static char[][] wordPuzzle = new char[][]{{'A','B','C','D','V','D'},{'M','X','Y','J','K','A'},{'J','Z','L','Y','O','M'},{'L','P','R','O','S','E'},{'N','D','S','P','U','R'},{'F','I','R','E','N','N'}};

    public static boolean leftToRight(String word){
        if(word.length()>MATRIX_WIDTH || word.length()==0){
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

        if(word.length()>MATRIX_HEIGHT || word.length()==0){
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


    public static int[] createLongestPrefixSuffixArray(String word){
        int[] lpsArray = new int[word.length()];

        lpsArray[0] = 0;
        int i = 1;
        int prefixLength = 0;

        while(i<word.length()){
            if (word.charAt(i) == word.charAt(prefixLength)) {
                prefixLength++;
                lpsArray[i] = prefixLength;
                i++;
            } else if (prefixLength!=0) {
                prefixLength = lpsArray[prefixLength-1];
            }
            else{
                lpsArray[i] = 0;
                i++;
            }
        }


        return lpsArray;
    }
    public static boolean leftToRightKMP(String word){

        if(word.length()>MATRIX_WIDTH || word.length()==0){
            return false;
        }else{

            int[] lps = createLongestPrefixSuffixArray(word);
            for (int k = 0; k < MATRIX_HEIGHT; k++) {
                int i = 0;
                int j = 0;
                while(i < MATRIX_WIDTH){
                    if(word.charAt(j) == wordPuzzle[k][i]){
                        j++;
                        i++;
                    }

                    if(j == word.length()){
                        return true;
                    } else if (i < MATRIX_WIDTH && word.charAt(j)!=wordPuzzle[k][i]) {
                        if(j!=0){
                            j = lps[j-1];
                        }
                        else{
                            i++;
                        }
                    }

                }
            }


        }

        return false;
    }

    public static boolean topToBottomKMP(String word){

        if(word.length()>MATRIX_HEIGHT){
            return false;
        }else{
            int[] lps = createLongestPrefixSuffixArray(word);
            for (int k = 0; k < MATRIX_WIDTH; k++) {
                int i = 0;
                int j = 0;
                while(i < MATRIX_HEIGHT){
                    if(word.charAt(j) == wordPuzzle[i][k]){
                        j++;
                        i++;
                    }

                    if(j == word.length()){
                        return true;
                    } else if (i < MATRIX_WIDTH && word.charAt(j)!=wordPuzzle[i][k]) {
                        if(j!=0){
                            j = lps[j-1];
                        }
                        else{
                            i++;
                        }
                    }

                }
            }
        }

        return false;
    }

    public static boolean searchForWordKMP(String word){
        return topToBottomKMP(word) || leftToRightKMP(word);
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        for (int i = 0; i < MATRIX_HEIGHT; i++) {
            for (int j = 0; j < MATRIX_WIDTH; j++) {
                System.out.print(wordPuzzle[i][j] + " ");
            }
            System.out.println();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a word to be searched: ");
        String input = scanner.next();

        System.out.println(searchForWord(input));
        System.out.println(searchForWordKMP(input));

//        System.out.println(leftToRight("LYOM"));
//        System.out.println(topToBottom("LYOM"));
//
//        System.out.println("Check for searchForWord method");
//
//        System.out.println(searchForWord("LYOM"));
//        System.out.println(searchForWord("LNE"));
//        System.out.println(searchForWord("XYZ"));


//        for (int a: createLongestPrefixSuffixArray("ABXAB")) {
//            System.out.print(a + " ");
//        }

//        System.out.println(leftToRightKMP("FIRE"));
//        System.out.println(leftToRightKMP("FIE"));
//        System.out.println(topToBottomKMP("MERN"));
//        System.out.println(topToBottomKMP("MEN"));




    }
}

