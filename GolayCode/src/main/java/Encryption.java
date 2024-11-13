import java.lang.reflect.Array;

public class Encryption {
    // Basic Golay's code matrix B (12x11)
    private final int[][] B = {
        {1,1,0,1,1,1,0,0,0,1,0},
        {1,0,1,1,1,0,0,0,1,0,1},
        {0,1,1,1,0,0,0,1,0,1,1},
        {1,1,1,0,0,0,1,0,1,1,0},
        {1,1,0,0,0,1,0,1,1,0,1},
        {1,0,0,0,1,0,1,1,0,1,1},
        {0,0,0,1,0,1,1,0,1,1,1},
        {0,0,1,0,1,1,0,1,1,1,0},
        {0,1,0,1,1,0,1,1,1,0,0},
        {1,0,1,1,0,1,1,1,0,0,0},
        {0,1,1,0,1,1,1,0,0,0,1},
        {1,1,1,1,1,1,1,1,1,1,1},
    };
    // Identifying matrix (12x12)
    private final int[][] I = {
        {1,0,0,0,0,0,0,0,0,0,0,0},
        {0,1,0,0,0,0,0,0,0,0,0,0},
        {0,0,1,0,0,0,0,0,0,0,0,0},
        {0,0,0,1,0,0,0,0,0,0,0,0},
        {0,0,0,0,1,0,0,0,0,0,0,0},
        {0,0,0,0,0,1,0,0,0,0,0,0},
        {0,0,0,0,0,0,1,0,0,0,0,0},
        {0,0,0,0,0,0,0,1,0,0,0,0},
        {0,0,0,0,0,0,0,0,1,0,0,0},
        {0,0,0,0,0,0,0,0,0,1,0,0},
        {0,0,0,0,0,0,0,0,0,0,1,0},
        {0,0,0,0,0,0,0,0,0,0,0,1},
    };
    private int[][] G;

    Encryption() {
        setGeneratingMatrix();
    }

    public int[] encryption(int[] vector){
        int[] encryptedVector = new int[23];

        // Multiplying vector with generating matrix
        for(int row = 0; row < G.length; row++){
            for(int column = 0; column < G[0].length; column++){
                encryptedVector[column] = (encryptedVector[column] + (vector[row] * G[row][column])) % 2;
            }
        }

        return encryptedVector;
    }

    public int[][] getG(){
        return this.G;
    }
    public int[][] getI(){
        return this.I;
    }
    public int[][] getB(){
        return this.B;
    }


    // Build a generating matrix (12x23)
    private void setGeneratingMatrix(){
        int k = I.length;
        int n = B[0].length;
        int[][] G = new int[k][n+k];

        for(int i = 0; i < k; i++){
            System.arraycopy(I[i], 0, G[i], 0, k);
            System.arraycopy(B[i], 0, G[i], k, n);
        }

        this.G = G;
    }
}
