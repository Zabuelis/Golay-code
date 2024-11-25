import java.util.Scanner;

public class Utilities {
    public String readLine(Scanner scanner){
        String line;
        line = scanner.nextLine();
        return line;
    }

    public float readFloat(Scanner scanner){
        float num = 0;
        num = scanner.nextFloat();
        return num;
    }

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

    public int[][] matrixAddition(int matrixA[][], int matrixB[][]){
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

}
