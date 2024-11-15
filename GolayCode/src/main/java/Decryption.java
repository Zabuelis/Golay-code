public class Decryption {

    private int[][] H;  // B x I matrix
    private int[] w = new int[24]; // New code word with appended value
    private int [] sH = new int[12]; // Syndrome calculated with matrix H
    private final int[][] B = {     // B matrix
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
    private int[] sB;

    Decryption(int[][] I){
        setH(I);
    }

    public int[] decryption(int[] vector) {
        if(isAppendZero(vector)){
            appendZero(vector, w);
        } else {
            appendOne(vector, w);
        }
        sH = computeSyndrome(H, w);
        if(countWeightOfSyndrome(sH) <= 3){
            calculateWordStep2(vector, w, sH);
            return vector;
        } else if(countWeightOfSyndromePlusB(sH) <= 2){
            calculateWordStep3(vector, w, sH);
            return vector;
        } else {
            sB = computeSyndrome(B, sH);
            if(countWeightOfSyndrome(sB) <= 3){
                calculateWordStep4(vector, w, sB);
                return vector;
            } else if(countWeightOfSyndromePlusB(sB) <= 2){
                calculateWordStep6(vector, w, sB);
                return vector;
            }
        }
        return new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
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
    private int[] computeSyndrome(int [][] matrix, int[] vector){
        int[] syndrome = new int[matrix[0].length];
        for(int row = 0; row < matrix.length; row++){
            for(int column = 0; column < matrix[0].length; column++){
                syndrome[column] = (syndrome[column] + (vector[row] * H[row][column])) % 2;
            }
        }

        return syndrome;
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

    private int findLinePosition(int[] vector){
        int[] line = new int[12];
        int count = 0;
        int position = 0;
        for(int i = 0; i < B.length; i++){
            count = 0;
            for(int j = 0; j < vector.length; j++){
                line[j] = (vector[j] + B[i][j]) % 2;
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

    private int[] calculateWordStep2(int[] vector, int[] w, int[] sH){
        int[] decryptedVector = new int[24];
        int[] u = new int[24];
        System.arraycopy(sH, 0, u,0, sH.length);
        for(int i = 11; i < 24; i++){
                u[i] = 0;
        }
        for(int i = 0; i < u.length; i++){
            decryptedVector[i] = (u[i] + w[i]) % 2;
        }
        System.arraycopy(decryptedVector, 0, vector, 0, vector.length);
        return vector;
    }

    private int[] calculateWordStep3(int[] vector, int[] w, int[] sH){
        int position = findLinePosition(sH);
        int[] e = new int[12];
        int[] v = new int[24];
        int[] u = new int[24];
        for(int i = 0; i < sH.length; i++){
            u[i] = (sH[i] + B[position][i]) % 2;
            if(i == position){
                e[i] = 1;
            } else {
                e[i] = 0;
            }
        }
        System.arraycopy(e, 0,u, 12, e.length);
        for (int i = 0; i < u.length; i++){
            v[i] = (u[i] + w[i]) % 2;
        }
        System.arraycopy(v, 0, vector, 0, vector.length);
        return vector;
    }

    private int[] calculateWordStep4(int[] vector, int[] w, int[] sB){
        int[] u = new int[24];
        int[] v = new int[24];
        for(int i = 0; i < 12; i++){
            u[i] = 0;
        }
        System.arraycopy(sB, 0, u, 11, 12);
        for(int i = 0; i < u.length; i++){
            v[i] = (u[i] + w[i]) % 2;
        }
        System.arraycopy(v, 0, vector, 0, vector.length);
        return vector;
    }

    private int[] calculateWordStep6(int[] vector, int[] w, int[] sB){
        int[] u = new int[24];
        int[] v = new int[24];
        int[] e = new int[12];
        int position = findLinePosition(sB);

        for(int i = 0; i < e.length; i++){
            if(i == position){
                e[i] = 1;
            } else {
                e[i] = 0;
            }
            u[i+e.length] = (sB[i] + B[position][i]) % 2;
        }
        System.arraycopy(e, 0,u, 0, e.length);

        for(int i = 0; i < v.length; i++){
            v[i] = (u[i] + w[i]) % 2;
        }
        System.arraycopy(v, 0, vector, 0, vector.length);
        return vector;
    }
}

