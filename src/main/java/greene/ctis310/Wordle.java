package greene.ctis310;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author l.greene32 need to change comments in constructor, createWord,
 * {newGame, and verifyInput} (in WordleGUI) when making the JAR file
 */
public class Wordle {

    private String[] correctLetters = new String[5];
    private String correctWord = "";
    private String userWord = "";
    private static final ArrayList<String> fiveLetterWords = new ArrayList<>();
    private ArrayList<String> fiveLetterWordsNoTxt;

    private Dictionary dictionary;

    private static Wordle instance = new Wordle();

    private Wordle() {
        try {
            fiveLetterWordsNoTxt = (ArrayList<String>) getWordsFromFile();
            for (String i : fiveLetterWordsNoTxt) {
                fiveLetterWords.add(i + "\n");
            }
            dictionary = new Dictionary();
        } catch (IOException ex) {
            System.out.println("Error reading file");
        }
    }

    public static Wordle getInstance() {
        return instance;
    }

    private List<String> getWordsFromFile() throws IOException {
        String str;
        ArrayList<String> wordList = new ArrayList<>();
        File input = new File("WordleDictionary.txt");
        BufferedReader br = new BufferedReader(new FileReader(input));
        while ((str = br.readLine()) != null) {
            wordList.add(str);
        }
        br.close();
        return wordList;
    }

    //gets a new word from the dictionary and adds it to the correct letters String
    public void createWord() throws IOException {
        //gets a random word from the word list and assigns it to the corret word
        correctWord = fiveLetterWordsNoTxt.get((int) (Math.floor(Math.random() * (fiveLetterWordsNoTxt.size()))));
//        correctWord = fiveLetterWords.get((int)(Math.floor(Math.random() * (fiveLetterWords.size()))));
        dictionary.setWord(correctWord);
        
        //breaks the word into a string array of the letters so it is easy to compare the user guess
        correctLetters = correctWord.split("");
        for (String i : correctLetters) {
            System.out.print(i);
        }
        System.out.println("");
    }

    //Assigns the user input to a String[] with each letter sperately, which is then compared to the correct letter String[] and assigned a number, which is then returned
    public int[] checkGuess() {
        //0 = incorrect 1 = correct letter, wrong location 2 = correct location
        int[] correctLetterColors = new int[5];
        String[] guessLetters = userWord.split("");
        for (int i = 0; i < guessLetters.length; i++) {
            if (this.correctLetters[i].equalsIgnoreCase(guessLetters[i])) {
                correctLetterColors[i] = 2;
            }
            for (int j = 0; j < guessLetters.length; j++) {
                if (this.correctLetters[j].equalsIgnoreCase(guessLetters[i]) && !this.correctLetters[i].equalsIgnoreCase(guessLetters[i])) {
                    correctLetterColors[i] = 1;
                }
            }
        }
        return correctLetterColors;
    }

    public ArrayList<String> getFiveLetterWordsNoTxt() {
        return fiveLetterWordsNoTxt;
    }

    public ArrayList<String> getFiveLetterWords() {
        return fiveLetterWords;
    }

    public String getCorrectWord() {
        return correctWord;
    }

    public void setCorrectWord(String correctWord) {
        this.correctWord = correctWord;
    }

    public String[] getCorrectLetters() {
        return correctLetters;
    }

    public void setCorrectLetters(String[] correctLetters) {
        this.correctLetters = correctLetters;
    }

    public String getUserWord() {
        return userWord;
    }

    public void setUserWord(String userWord) {
        this.userWord = userWord;
    }

    public Dictionary getDictionary() {
        return dictionary;
    }

}
