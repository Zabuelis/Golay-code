public class Encryption {

    public int[][] encryption(int[][] vector){
        Utilities utilities = new Utilities();
        return utilities.matrixMultiplication(vector, Matrices.G);
    }

}
