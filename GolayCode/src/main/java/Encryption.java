import java.lang.reflect.Array;

public class Encryption {
    // Basic Golay's code matrix B (12x11)
    private static final int[][] B = {
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
    private static final int[][] I = {
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

    public int[] encryption(int[] vector){
        int[][] G = generatingMatrix();
        int[] encryptedVector = new int[23];

        // Multiplying vector with generating matrix (1x23)
        for(int i = 0; i < G.length; i++){
            for(int j = 0; j < G[0].length; j++){
                encryptedVector[j] = (encryptedVector[j] + (vector[i] * G[i][j])) % 2;
            }
        }

        return encryptedVector;
    }

    // Build a generating matrix (12x23)
    private int[][] generatingMatrix(){
        int k = I.length;
        int n = B[0].length;
        int[][] G = new int[k][n+k];

        for(int i = 0; i < k; i++){
            for(int j = 0; j < k; j++){
                G[i][j] = I[i][j];
            }
            for(int j = 0; j < n; j++){
                G[i][k+j] = B[i][j];
            }
        }

        return G;
    }
}
