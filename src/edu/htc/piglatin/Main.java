package edu.htc.piglatin;

import edu.htc.piglatin.FileCompareUtil;
import edu.htc.piglatin.FileParser;
import edu.htc.piglatin.ListFileWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException, NoSentenceException {

        String current = new File( "." ).getCanonicalPath();

        String origFileEnglish = ClassLoader.getSystemResource("data/sampleText.txt").getFile();
        String outputFileEnglish = current + "/samplePigLatin_English.txt";

        String origFilePigLatin = ClassLoader.getSystemResource("data/samplePigLatin.txt").getFile();
        String outputFilePigLatin = current + "/sampleText_PigLatin.txt";

        try {

            translateFileToPigLatin(origFileEnglish, outputFilePigLatin);
            if (FileCompareUtil.compare(origFilePigLatin, outputFilePigLatin)){
                System.out.println("Translation of file to PigLatin matches expected.");
            } else {
                System.out.println("Translation of file to PigLatin does not match expected.");
            }

            translateFileFromPigLatin(origFilePigLatin, outputFileEnglish);
            if (FileCompareUtil.compare(origFileEnglish, outputFileEnglish)){
                System.out.println("Translation of file to English matches expected.");
            } else {
                System.out.println("Translation of file to English does not match expected.");
            }

        } catch (IOException e) {
            System.out.println("An error occurred comparing the files.");
            System.out.println(e.getMessage());
        }
    }

    public static void translateFileToPigLatin(String inFilePath, String outFilePath) throws NoSentenceException {
        // Read File
        ArrayList<String> sentences = null;
        try {
            sentences = FileParser.parseFile(inFilePath);
        } catch (FileNotFoundException e){
            System.out.println("Could not find file!");
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println("An error occurred reading the file.");
            System.out.println(e.getMessage());
        }

        ArrayList<String> translated = new ArrayList<>();
        for (String sentence : sentences) {
            translated.add(PigLatinTranslator.translateToPigLatin(sentence));
        }

        try {
            ListFileWriter.writeToFile(translated, outFilePath);
        } catch (IOException e){
            System.out.println("An error occurred writing the file.");
            System.out.println(e.getMessage());
        }
    }

    public static void translateFileFromPigLatin(String inFilePath, String outFilePath) throws NoSentenceException {
        // Read File
        ArrayList<String> sentences = null;
        try {
            sentences = FileParser.parseFile(inFilePath);
        } catch (FileNotFoundException e){
            System.out.println("Could not find file!");
            System.out.println(e.getMessage());
        } catch (IOException e){
            System.out.println("An error occurred reading the file.");
            System.out.println(e.getMessage());
        }

        ArrayList<String> translated = new ArrayList<>();
        for (String sentence : sentences) {
            translated.add(PigLatinTranslator.translateFromPigLatin(sentence));
        }

        try {
            ListFileWriter.writeToFile(translated, outFilePath);
        } catch (IOException e){
            System.out.println("An error occurred writing the file.");
            System.out.println(e.getMessage());
        }
    }
}
