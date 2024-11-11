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

    public int[] stringToInt(String vector){
        int[] numb = new int[vector.length()];
        for(int i = 0; i < vector.length(); i++){
            numb[i] = Character.getNumericValue(vector.charAt(i));
        }
        return numb;
    }

    public String intToString(int[] vector){
        StringBuilder line = new StringBuilder();
        for(int i = 0; i < vector.length; i++){
            line.append(String.valueOf(vector[i]));
        }
        return line.toString();
    }

}
