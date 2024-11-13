public class Decryption {

    private int[][] H;
    private int[] w, sH = new int[24];
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
    private int u;
    private int[] sB;

    Decryption(int[][] I){
        setH(I);
    }

    void decryption(int[] vector){
        if(isAppendZero(vector)){
            appendZero(vector, w);
        } else {
            appendOne(vector, w);
        }

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
    private int[] appendOne(int[] vector, int[] appendedVector){
        System.arraycopy(vector, 0, appendedVector, 0, vector.length);
        appendedVector[23] = 1;
        return appendedVector;
    }
    // Appending zero to the vector to make it (1x24)
    private int[] appendZero(int[] vector, int[] appendedVector){
        System.arraycopy(vector, 0, appendedVector, 0, vector.length);
        appendedVector[23] = 0;
        return appendedVector;
    }

    //Calculating the syndrome with matrix H
    private int[] computeSyndromeH(){
        for(int row = 0; row < H.length; row++){
            for(int column = 0; column < H[0].length; column++){
                sH[column] = (sH[column] + (w[row] * H[row][column])) % 2;
            }
        }
        return sH;
    }
}

