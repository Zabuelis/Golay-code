public class Decryption {

    private int[][] H;
    private int[] w = new int[24];
    private int [] sH = new int[12];
    private final int[][] B = {
        {1,1,0,1,1,1,0,0,0,1,0,1},
        {1,0,1,1,1,0,0,0,1,0,1,1},
        {0,1,1,1,0,0,0,1,0,1,1,1},
        {1,1,1,0,0,0,1,0,1,1,0,1},
        {1,1,0,0,0,1,0,1,1,0,1,1},
        {1,0,0,0,1,0,1,1,0,1,1,1},
        {0,0,0,1,0,1,1,0,1,1,1,1},
        {0,0,1,0,1,1,0,1,1,1,0,1},
        {0,1,0,1,1,0,1,1,1,0,0,1},
        {1,0,1,1,0,1,1,1,0,0,0,1},
        {0,1,1,0,1,1,1,0,0,0,1,1},
        {1,1,1,1,1,1,1,1,1,1,1,0}
    };
    private int[] u = new int[24];
    private int[] sB;

    Decryption(int[][] I){
        setH(I);
    }

    public int[] decryption(int[] vector){
        if(isAppendZero(vector)){
            appendZero(vector, w);
        } else {
            appendOne(vector, w);
        }
        computeSyndromeH();
        if(countWeightOfSyndrome(sH) <= 3){
            vector = calculateWordStep2(vector);
        } else if(countWeightOfSyndromePlusB(sH) <= 2){
            vector = calculateWordStep3();
        }

        return vector;
    }

    public int[][] getH(){
        return this.H;
    }
    public int[] getW(){
        return this.w;
    }
    public int[] getSH(){
        return this.sH;
    }

    private void setH(int[][] I){
        int n = I.length + B.length;
        int k = B.length;
        int[][] H = new int[n][k];
        for (int j = 0; j < k; j++) {
            System.arraycopy(I[j], 0, H[j], 0, k);
            System.arraycopy(B[j], 0, H[j + k], 0, k);
        }
        this.H = H;
    }

    // Checking whether a zero or one needs to be appended.
    private boolean isAppendZero(int[] vector){
        int countOnes = 0;
        for (int num : vector) {
            if (num == 1) {
                countOnes++;
            }
        }
        return countOnes % 2 != 0;
    }

    // Appending one to the vector to make it (1x24)
    private void appendOne(int[] vector, int[] w){
        System.arraycopy(vector, 0, w, 0, vector.length);
        w[23] = 1;
        this.w = w;
    }

    // Appending zero to the vector to make it (1x24)
    private void appendZero(int[] vector, int[] w){
        System.arraycopy(vector, 0, w, 0, vector.length);
        w[23] = 0;
        this.w = w;
    }


    //Calculating the syndrome with matrix H
    private void computeSyndromeH(){
        int[] syndrome = new int[H[0].length];
        for(int row = 0; row < H.length; row++){
            for(int column = 0; column < H[0].length; column++){
                syndrome[column] = (syndrome[column] + (w[row] * H[row][column])) % 2;
            }
        }
        this.sH = syndrome;
    }

    private int countWeightOfSyndrome(int[] vector){
        int count = 0;
        for(int i = 0; i < vector.length; i++) {
            if(vector[i] == 1){
                count++;
            }
        }
        return count;
    }

    private int countWeightOfSyndromePlusB(int[] vector){
        int[] line = new int[12];
        int count = 0;
        for(int i = 0; i < B.length; i++){
            count = 0;
            for(int j = 0; j < vector.length; j++){
                line[j] = (vector[j] + B[i][j]) % 2;
            }
            for(int j = 0; j < line.length; j++){
                if(line[j] == 1){
                    count++;
                }
            }
            if(count <= 2){
                return count;
            }
        }
        return count;
    }

    private int findLinePosition(int[] sH){
        int[] line = new int[12];
        int count = 0;
        int position = 0;
        for(int i = 0; i < B.length; i++){
            count = 0;
            for(int j = 0; j < sH.length; j++){
                line[j] = (sH[j] + B[i][j]) % 2;
            }
            for(int j = 0; j < line.length; j++){
                if(line[j] == 1){
                    count++;
                }
            }
            if(count <= 2){
                position = i;
            }
        }
        return position;
    }

    private int[] calculateWordStep2(int[] vector){
        int[] decryptedVector = new int[24];
        System.arraycopy(sH, 0, u,0, sH.length);
        for(int i = sH.length; i < 24; i++){
                u[i] = 0;
        }
        for(int i = 0; i < u.length; i++){
            decryptedVector[i] = (u[i] + w[i]) % 2;
        }
        System.arraycopy(decryptedVector, 0, vector, 0, vector.length);
        return vector;
    }

    private int[] calculateWordStep3(){
        int position = findLinePosition(sH);
        int[] e = new int[12];
        int[] decryptedVector = new int[23];
        for(int i = 0; i < sH.length; i++){
            decryptedVector[i] = (sH[i] + B[position][i]) % 2;
            if(i == position){
                e[i] = 1;
            } else {
                e[i] = 0;
            }
        }
        System.arraycopy(e, 0,decryptedVector, 12, e.length-1);
        return decryptedVector;
    }
}

