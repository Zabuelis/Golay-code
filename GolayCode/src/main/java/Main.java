// This project was made by Karolis Zabulis.
// Vilnius University Mathematics and Informatics faculty.
// Contact: karolis.zabulis@mif.stud.vu.lt
// Description of the program: Encryption and decryption using the shorter Golay code (C23) and a channel simulation.
// The program is able to encrypt a provided vector or some text.
// Version: 1.0
// Tested on IntelliJ IDEA, Windows 11 64bit OS
// To Do: 3rd scenario.

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Utilities utilities = new Utilities();
        Encryption encryption = new Encryption();
        Channel channel = new Channel();
        String userInput = "";
        int[][] vector;
        double errorProbability;
        Random rand = new Random();
        Decryption decryption = new Decryption();

        System.out.println("Please insert the probability of an error [0 <= n <= 1]");
        do {
            errorProbability = utilities.readFloat(scanner);
        } while (errorProbability < 0 || errorProbability > 1);

        System.out.println("Please select an option");
        System.out.println("[1] Vector encryption and decryption.");
        System.out.println("[2] Word encryption and decryption.");

        int userChoice = 0;
        boolean isChoiceValid = false;

        while (!isChoiceValid){
            System.out.println("Enter your choice.");
            userChoice = utilities.readNum(scanner);
            if(userChoice == 1 || userChoice == 2){
                isChoiceValid = true;
            } else{
                System.out.println("Invalid option. Please insert 1 or 2");
            }
        }

        if(userChoice == 1) {
            System.out.println("Please insert a vector with binary values [length = 12]");
            do {
                userInput = utilities.readLine(scanner);
                if (utilities.containsPattern(userInput)) {
                    userInput = utilities.formatVector(userInput);
                }
            } while (userInput.length() != 12);

            vector = utilities.stringToInt(userInput);
            vector = encryption.encryption(vector);
            int[][] vectorBeforeChannel = new int[1][23];
            System.arraycopy(vector[0], 0, vectorBeforeChannel[0], 0, 23);
            vector = channel.channel(vector, errorProbability, rand);
            System.out.println("Vector that came out of the channel:\n" + utilities.intToString(vector));
            System.out.println("Channel made " + utilities.calculateMistakes(vector, vectorBeforeChannel) + " mistake(s).");

            if(utilities.calculateMistakes(vector, vectorBeforeChannel) > 0){
                System.out.println("Locations that that mistakes were found (starting from 0) in are:");
                utilities.printLocationsOfMistakes(vector, vectorBeforeChannel);
                System.out.println();
            }

            System.out.println("You can edit the vector now (length has to be 23) if input is left empty the " +
                    "vector that returned from the channel will be used.");

            do {
                userInput = utilities.readLine(scanner);
                if (utilities.containsPattern(userInput)) {
                    userInput = utilities.formatVector(userInput);
                }
            } while (userInput.length() != 23 && !userInput.isEmpty());

            if(!userInput.isEmpty()){
                vector = utilities.stringToInt(userInput);
            }

            vector = decryption.decryption(vector);
            System.out.println("Decrypted vector:");
            System.out.println(Arrays.toString(vector[0]));

        } else if(userChoice == 2){
            System.out.println("Please insert the text that you want to send to the channel");
            userInput = utilities.readMultiLines(scanner);

            int bitLength = utilities.textToBinary(userInput).length();
            String[] vectorsOf12Bits = utilities.splitInto12Length(utilities.textToBinary(userInput));
            String[] vectorsAfterChannel = new String[vectorsOf12Bits.length];

            System.out.println("Text after only using channel.");
            for(int i = 0; i < vectorsOf12Bits.length; i++){
                int[][] letterVector = utilities.stringToInt(vectorsOf12Bits[i]);
                letterVector = channel.channel(letterVector, errorProbability, rand);
                vectorsAfterChannel[i] = utilities.intToString(letterVector);
            }
            String text = utilities.stringJoin(vectorsAfterChannel);
            text = utilities.convertBinaryToText(text, bitLength);
            System.out.println(text);

            System.out.println("Text after encryption, channel and decryption.");
            for(int i = 0; i < vectorsOf12Bits.length; i++){
                int[][] letterVector = utilities.stringToInt(vectorsOf12Bits[i]);
                letterVector = encryption.encryption(letterVector);
                letterVector = channel.channel(letterVector, errorProbability, rand);
                letterVector = decryption.decryption(letterVector);
                vectorsOf12Bits[i] = utilities.intToString(letterVector);
            }
            text = utilities.stringJoin(vectorsOf12Bits);
            text = utilities.convertBinaryToText(text, bitLength);
            System.out.println(text);
        }

        scanner.close();
    }
}
