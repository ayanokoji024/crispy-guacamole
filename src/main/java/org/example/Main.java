package org.example;

import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Main {

    // Declaring puzzle matrix and its height and width as static members of the class
    public static int MATRIX_HEIGHT;
    public static int MATRIX_WIDTH;
    public static char[][] wordPuzzle;
    static char[] vowels = {'A', 'E', 'I', 'O', 'U'};

    // Initializing static members using static block
    static{
        MATRIX_HEIGHT = ThreadLocalRandom.current().nextInt(6,11);
        MATRIX_WIDTH = MATRIX_HEIGHT;
        wordPuzzle = new char[MATRIX_HEIGHT][MATRIX_WIDTH];
        for(int i=0 ; i<MATRIX_HEIGHT ; i++){
            for(int j=0 ; j<MATRIX_WIDTH ; j++){
                if((i+j)%2==1) wordPuzzle[i][j] = vowels[ThreadLocalRandom.current().nextInt(0, 5)];
                else wordPuzzle[i][j] = (char) ThreadLocalRandom.current().nextInt(65, 91);
            }
        }
    }

    // Search for word left to right using brute force
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

    // Search for word top to bottom using brute force
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

    // Search for word using brute force. 
    // Calls the search for top-to-bottom and left-to-right and returns true if the word is found by any one of them
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

    // Search for word left-to-right using KMP Algorithm
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

    // Search for word top-to-bottom using KMP Algorithm
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

    // Search for word using KMP Algorithm
    // Calls for search top-to-bototm and left-to-right
    public static boolean searchForWordKMP(String word){
        return topToBottomKMP(word) || leftToRightKMP(word);
    }

    public static void main(String[] args) {        
        System.out.println("Word Puzzle:");
        for (int i = 0; i < MATRIX_HEIGHT; i++) {
            for (int j = 0; j < MATRIX_WIDTH; j++) {
                System.out.print(wordPuzzle[i][j] + " ");
            }
            System.out.println();
        }
        
        Scanner scanner = new Scanner(System.in);
        char loop = 'y';
        while(loop=='y' || loop=='Y'){
            System.out.print("\nEnter a string to be searched: ");
            String input = scanner.next();
            boolean result;
            System.out.print("Using brute force");
            long start = System.currentTimeMillis();
            result = searchForWord(input);
            long end = System.currentTimeMillis();
            for(int i=0 ; i<3 ; i++){
                try{Thread.sleep((end-start)*100);} catch(Exception e){}
                System.out.print(".");
            }
            System.out.println(result ? " String Found" : " String Not Found");
    
            System.out.print("Using KMP Algorithm");
            start = System.currentTimeMillis();
            result = searchForWordKMP(input);
            end = System.currentTimeMillis();
            for(int i=0 ; i<3 ; i++){
                try{Thread.sleep((end-start)*100);} catch(Exception e){}
                System.out.print(".");
            }
            System.out.println(result ? " String Found" : " String Not Found");
            System.out.print("Search again? [y/n]: "); loop = scanner.next().charAt(0);
        }
        
        scanner.close();
    }
}

