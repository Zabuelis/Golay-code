public class Decryption {

    private int[][] w = new int[1][24]; // New code word with appended value
    private int [][] sH = new int[1][12]; // Syndrome multiplied with matrix H
    private Utilities utilities = new Utilities();
    private int[][] sB = new int [1][12];   // Code word multiplied with B matrix to get syndrome
    private int[][] u = new int[1][24]; // Code word U
    private int [][] v = new int[1][24]; // Decrypted codeword
    private int [][] decryptedWord = new int[1][12]; // Word to return when it is fully decrypted

    // Decryption according the 3.7.1 and 3.6.1 algorithm
    public int[][] decryption(int[][] vector) {
        // Step 1 of 3.7.1
        System.arraycopy(vector[0], 0, w[0], 0, 23);
        if(weight(vector) % 2 == 0){
            w[0][23] = 1;
        } else {
            w[0][23] = 0;
        }
        // Step 1 of 3.7.1 algorithm
        sH = utilities.matrixMultiplication(w, Matrices.H);
        // Step 2 of 3.7.1 algorithm
        if(weight(sH) <= 3) {
            System.arraycopy(sH[0], 0, u[0], 0, 12);
            for (int i = 0; i < 12; i++) {
                u[0][12 + i] = 0;
            }
            v = utilities.matrixAddition(w, u);
            System.arraycopy(v[0], 0, decryptedWord[0], 0, 12);
            return decryptedWord;
        }
        // Step 3 of 3.7.1 algorithm
        int[][] bi;
        for(int i = 0; i < 12; i++){
            bi = new int[1][12];
            System.arraycopy(Matrices.B[i], 0, bi[0], 0, 12);
            if(weight(utilities.matrixAddition(sH, bi)) <= 2){
                System.arraycopy(utilities.matrixAddition(sH, bi)[0], 0, u[0], 0, 12);
                System.arraycopy(Matrices.I[i], 0, u[0], 12, 12);
                v = utilities.matrixAddition(w, u);
                System.arraycopy(v[0], 0, decryptedWord[0], 0, 12);
                return decryptedWord;
            }
        }
        // Step 4 of 3.7.1 algorithm
        sB = utilities.matrixMultiplication(sH, Matrices.B);
        // Step 5 of 3.7.1 algorithm
        if(weight(sB) <= 3) {
            for(int i = 0; i < 12; i++) {
                u[0][i] = 0;
            }
            System.arraycopy(sB[0], 0, u[0], 12, 12);
            v = utilities.matrixAddition(w, u);
            System.arraycopy(v[0], 0, decryptedWord[0], 0, 12);
            return decryptedWord;
        }
        // Step 6 of 3.7.1 algorithm
        for(int i = 0; i < 12; i++){
            bi = new int[1][12];
            System.arraycopy(Matrices.B[i], 0, bi[0], 0, 12);
            if(weight(utilities.matrixAddition(sB, bi)) <= 2){
                System.arraycopy(Matrices.I[i], 0, u[0], 0, 12);
                System.arraycopy(utilities.matrixAddition(sB, bi)[0], 0, u[0], 12, 12);
                v = utilities.matrixAddition(w, u);
                System.arraycopy(v[0], 0, decryptedWord[0], 0, 12);
                return decryptedWord;
            }
        }
        // Step 7 vector could not be decrypted
        return new int[][]{{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
    }

    public int[][] getW(){
        return this.w;
    }
    public int[][] getSH(){
        return this.sH;
    }

    private int weight(int [][] vector){
        int result = 0;

        for(int i = 0; i < vector.length; i++){
            for(int j = 0; j < vector[0].length; j++){
                if(vector[i][j] != 0){
                    result++;
                }
            }
        }

        return result;
    }
}

