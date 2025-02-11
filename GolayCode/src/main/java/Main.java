// This project was made by Karolis Zabulis.
// Vilnius University Mathematics and Informatics faculty.
// Contact: ********
// Description of the program: Encryption and decryption using the shorter Golay code (C23) and a channel simulation.
// The program is able to encrypt a provided vector or some text.
// Version: 1.0
// Tested on IntelliJ IDEA, Windows 11 64bit OS
// To Do:

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Utilities utilities = new Utilities();
        Encryption encryption = new Encryption();
        Decryption decryption = new Decryption();
        Random rand = new Random();
        Channel channel = new Channel();
        String userInput = "";
        double errorProbability;

        System.out.println("Please insert the probability of an error [0 <= n <= 1]");
        do {
            errorProbability = utilities.readFloat(scanner);
        } while (errorProbability < 0 || errorProbability > 1);

        System.out.println("Please select an option");
        System.out.println("[1] Vector encryption and decryption.");
        System.out.println("[2] Text encryption and decryption.");
        System.out.println("[3] Image encryption and decryption.");

        int userChoice = 0;
        boolean isChoiceValid = false;

        while (!isChoiceValid){
            System.out.println("Enter your choice.");
            userChoice = utilities.readNum(scanner);
            if(userChoice == 1 || userChoice == 2 || userChoice == 3){
                isChoiceValid = true;
            } else{
                System.out.println("Invalid option. Please insert 1, 2 or 3");
            }
        }

        // Scenario 1
        if(userChoice == 1) {
            do {
                System.out.println("Please insert a vector with binary values [length = 12]");
                userInput = utilities.readLine(scanner);
                if (utilities.containsPattern(userInput)) {
                    userInput = utilities.formatVector(userInput);
                }
            } while (userInput.length() != 12);

            int[][] vector;
            vector = utilities.stringToInt(userInput);
            vector = encryption.encryption(vector);
            System.out.println("Encrypted vector.\n" + utilities.intToString(vector));
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

            do {
                System.out.println("You can edit the vector now (length has to be 23) if the input is left empty the " +
                        "vector that returned from the channel will be used.");
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
            System.out.println(utilities.intToString(vector));

        // Scenario 2
        } else if(userChoice == 2){
            System.out.println("Please insert the text that you want to send to the channel");
            userInput = utilities.readMultiLines(scanner);

            int bitLength = utilities.textToBinary(userInput).length();
            String[] vectorsOf12Bits = utilities.splitInto12Length(utilities.textToBinary(userInput));
            String[] vectorsAfterChannel = new String[vectorsOf12Bits.length];
            int[][] vectorForParsing;

            for(int i = 0; i < vectorsOf12Bits.length; i++){
                vectorForParsing = utilities.stringToInt(vectorsOf12Bits[i]);
                vectorForParsing = channel.channel(vectorForParsing, errorProbability, rand);
                vectorsAfterChannel[i] = utilities.intToString(vectorForParsing);
            }
            String text = utilities.stringJoin(vectorsAfterChannel);
            text = utilities.convertBinaryToText(text, bitLength);
            System.out.println("Text after only using channel.");
            System.out.println(text);

            for(int i = 0; i < vectorsOf12Bits.length; i++){
                vectorForParsing = utilities.stringToInt(vectorsOf12Bits[i]);
                vectorForParsing = encryption.encryption(vectorForParsing);
                vectorForParsing = channel.channel(vectorForParsing, errorProbability, rand);
                vectorForParsing = decryption.decryption(vectorForParsing);
                vectorsOf12Bits[i] = utilities.intToString(vectorForParsing);
            }
            text = utilities.stringJoin(vectorsOf12Bits);
            text = utilities.convertBinaryToText(text, bitLength);
            System.out.println("Text after encryption, channel and decryption.");
            System.out.println(text);

        // Scenario 3
        } else if(userChoice == 3){
            System.out.println("Please provide a full path of the image with '.bmp' extension.");
            while (true){
                userInput = utilities.readLine(scanner);
                if(ImageOperations.bmpFileValidator(userInput)){
                    break;
                } else {
                    System.out.println("Provided path does not lead to a file or does not have a '.bmp' extension.");
                }
            }
            Path input = Path.of(userInput);
            String output = input.getParent().toString();

            try {
                BufferedImage image = ImageIO.read(input.toFile());
                int height = image.getHeight();
                int width = image.getWidth();
                System.out.println("Performing image transformations.\nPlease wait.");
                String fileInBitString = ImageOperations.imageToBitString(image, height, width);
                int length = fileInBitString.length();
                String[] vectorsOf12Bits = utilities.splitInto12Length(fileInBitString);
                int[][] vectorForParsing;
                String[] vectorsAfterChannel = new String[vectorsOf12Bits.length];

                System.out.println("Sending not encrypted image through the channel.\nPlease wait.");
                for(int i = 0; i < vectorsOf12Bits.length; i++){
                    vectorForParsing = utilities.stringToInt(vectorsOf12Bits[i]);
                    vectorForParsing = channel.channel(vectorForParsing, errorProbability, rand);
                    vectorsAfterChannel[i] = utilities.intToString(vectorForParsing);
                }
                String bitImage = utilities.stringJoin(vectorsAfterChannel);
                bitImage = bitImage.substring(0, length);
                image = ImageOperations.bitStringToImage(bitImage, height, width);
                ImageIO.write(image, "bmp", Path.of(output+"/imageOutOfChannel.bmp").toFile());

                System.out.println("Performing image encryption/decryption and sending through channel.\nPlease wait.");
                for(int i = 0; i < vectorsOf12Bits.length; i++){
                    vectorForParsing = utilities.stringToInt(vectorsOf12Bits[i]);
                    vectorForParsing = encryption.encryption(vectorForParsing);
                    vectorForParsing = channel.channel(vectorForParsing, errorProbability, rand);
                    vectorForParsing = decryption.decryption(vectorForParsing);
                    vectorsOf12Bits[i] = utilities.intToString(vectorForParsing);
                }

                bitImage = utilities.stringJoin(vectorsOf12Bits);
                bitImage = bitImage.substring(0, length);
                image = ImageOperations.bitStringToImage(bitImage, height, width);
                ImageIO.write(image, "bmp", Path.of(output+"/imageAfterEncryptionDecryption.bmp").toFile());
                System.out.println("Images were saved in " + output + " directory.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        scanner.close();
    }
}
