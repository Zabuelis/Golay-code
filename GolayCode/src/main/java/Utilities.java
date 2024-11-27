import java.util.Scanner;


public class Utilities {
    // Used for reading lines
    public String readLine(Scanner scanner){
        String line;

        line = scanner.nextLine();

        return line;
    }

    public float readFloat(Scanner scanner){
        float num = 0;

        num = scanner.nextFloat();
        scanner.nextLine();

        return num;
    }

    public String readMultiLines(Scanner scanner){
        System.out.println("This is a multi-line read to save your input type 'END' and press 'enter'.");
        StringBuilder str = new StringBuilder();
        String line;
        scanner.useDelimiter("\\t");
        while (true){
            line = scanner.nextLine();
            if (line.equals("END")) {
                break;
            }
            str.append(line).append("\n");
        }
        return str.toString();
    }

    public int readNum(Scanner scanner){
        int number = 0;

        number = scanner.nextInt();
        scanner.nextLine();

        return number;
    }

    // Checks if a string contains patterns
    public boolean containsPattern(String vector){
        String patterns = "[],{} ";

        for(int i = 0; i < vector.length(); i++){
            for(int j = 0; j < patterns.length(); j++){
                if(vector.charAt(i) == patterns.charAt(j)){
                    return true;
                }
            }
        }

        return false;
    }

    // Removes unwanted expressions from a string
    public String formatVector(String vector){
        String patterns = "[\\[\\], {}]";
        return vector.replaceAll(patterns, "");
    }

    public int[][] stringToInt(String vector){
        int[][] numb = new int[1][vector.length()];

        for(int i = 0; i < vector.length(); i++){
            numb[0][i] = Character.getNumericValue(vector.charAt(i));
        }

        return numb;
    }

    // Transforms 2D integer array into a string
    public String intToString(int[][] vector){
        StringBuilder line = new StringBuilder();

        for(int i = 0; i < vector.length; i++){
            for(int j = 0; j < vector[0].length; j++){
                line.append(String.valueOf(vector[i][j]));
            }
            if(i != vector.length-1){
                line.append("\n");
            }
        }

        return line.toString();
    }


    // Multiplies matrices
    public int[][] matrixMultiplication(int[][] matrixA, int[][] matrixB){
        int aRows = matrixA.length, aColumns = matrixA[0].length;
        int bRows = matrixB.length, bColumns = matrixB[0].length;

        int[][] resultMatrix = new int[aRows][bColumns];

        for(int i = 0; i < aRows; i++){
            for(int j = 0; j < bColumns; j++){
                for(int k = 0; k < aColumns; k++){
                    resultMatrix[i][j] += matrixA[i][k] * matrixB[k][j];
                }
                resultMatrix[i][j] %= 2;
            }
        }

        return resultMatrix;
    }

    // Addition of matrices
    public int[][] matrixAddition(int[][] matrixA, int[][] matrixB){
        int aRows = matrixA.length, aColumns = matrixA[0].length;
        int bRows = matrixB.length, bColumns = matrixB[0].length;

        int[][] result = new int[aRows][aColumns];

        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < aColumns; j++) {
                result[i][j] = (matrixA[i][j] + matrixB[i][j]) % 2;
            }
        }

        return result;
    }

    // Calculates mismatches in 2 2D integer arrays
    public int calculateMistakes(int[][] vector, int[][] vectorBeforeChannel){
        int count = 0;

        for(int i = 0; i < vector.length; i++){
            for(int j = 0; j < vector[0].length; j++) {
                if (vector[i][j] != vectorBeforeChannel[i][j]) {
                    count++;
                }
            }
        }

        return count;
    }

    // Finds locations of mismatches in 2 2D integer arrays
    public void printLocationsOfMistakes(int[][] vector, int[][] vectorBeforeChannel){
        for(int i = 0; i < vector[0].length; i++){
            if(vector[0][i] != vectorBeforeChannel[0][i]){
                System.out.print(i + " ");
            }
        }
    }

    // Converts string into binary values appending 0 where they are needed to ensure 8 bit length
    public String textToBinary(String text) {
        StringBuilder binaryText = new StringBuilder();

        for (char c : text.toCharArray()) {
            String binaryString = Integer.toBinaryString(c);
            binaryText.append(String.format("%8s", binaryString).replace(' ', '0')); // Ensure 8-bit binary
        }

        return binaryText.toString();
    }

    // Splits binary representation into vectors of 12 length
    public String[] splitInto12Length(String binaryLine){
        int vectorLength = 12;
        int lineLength = binaryLine.length();
        int numberOfLines = lineLength / vectorLength;;

        if(lineLength % vectorLength != 0){
            numberOfLines += 1;
        }

        String[] letters = new String[numberOfLines];

        for(int i = 0; i < numberOfLines; i++){
            int start = i * vectorLength;
            int end;
            if(start + vectorLength > lineLength){
                end = lineLength;
            } else {
                end = start + vectorLength;
            }
            letters[i] = binaryLine.substring(start, end);
            if(letters[i].length() < lineLength){
                letters[i] = String.format("%-" + vectorLength + "s", letters[i]).replace(' ', '0');
            }
        }

        return letters;
    }

    // Join string array into one string
    public String stringJoin(String[] lines){
        return String.join("", lines);
    }

    // Convert binary string into ASCII representation
    public String convertBinaryToText(String binaryText, int bitLength){
        StringBuilder text = new StringBuilder();
        String trimmedBinaryLine = binaryText.substring(0, bitLength);
        for(int i = 0; i < trimmedBinaryLine.length(); i+=8){
            String letter = trimmedBinaryLine.substring(i, i + 8);
            text.append((char) Integer.parseInt(letter, 2));
        }
        return text.toString();
    }

}
