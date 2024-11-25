//This project was made by Karolis Zabulis.
//Vilnius University Mathematics and Informatics faculty.
//Contact: karolis.zabulis@mif.stud.vu.lt
//Description of the program:
//Version: 1.0
//Tested on IntelliJ IDEA, Windows 11 64bit OS

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
        // Fix the prompt
        do {
            errorProbability = utilities.readFloat(scanner);
        } while (errorProbability < 0 || errorProbability > 1);

        System.out.println("Please insert a vector with binary values [length = 12]");
        do {
            userInput = utilities.readLine(scanner);
            if(utilities.containsPattern(userInput)){
                userInput = utilities.formatVector(userInput);
            }
        } while (userInput.length() != 12);

        vector = utilities.stringToInt(userInput);
        vector = encryption.encryption(vector);
        System.out.println(utilities.intToString(vector));
        vector = channel.channel(vector, errorProbability, rand);
        System.out.println(utilities.intToString(vector));
        vector = decryption.decryption(vector);
        System.out.println("Decrypted vector:");
        System.out.println(Arrays.toString(vector));


        scanner.close();
    }
}
