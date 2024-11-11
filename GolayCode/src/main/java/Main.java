//This project was made by Karolis Zabulis.
//Vilnius University Mathematics and Informatics faculty.
//Contact: karolis.zabulis@mif.stud.vu.lt
//Description of the program:
//Version: 1.0
//Tested on Visual Studio Code IDE, Windows 64bit OS

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Utilities utilities = new Utilities();
        Encryption encryption = new Encryption();
        String vector;
        float errorProbability;
        System.out.println("Please insert the probability of an error [0 <= n <= 1]");
        // Fix the prompt
        do {
            errorProbability = utilities.readFloat(scanner);
        } while (errorProbability < 0 || errorProbability > 1);
        System.out.println("Please insert a vector with binary values [length = 12]");
        do {
            vector = utilities.readLine(scanner);
            if(utilities.containsPattern(vector)){
                vector = utilities.formatVector(vector);
            }
            System.out.println(vector);
        } while (vector.length() != 12);
        vector = utilities.intToString(encryption.encryption(utilities.stringToInt(vector)));


        scanner.close();
    }
}