import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDecryption {
    Encryption encryption = new Encryption();
    Decryption decryption = new Decryption();
    Channel channel = new Channel();

    @Test
    void testSyndromeH(){
        int[][] input = {{1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0}};

        try {
            decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int[][] sH = decryption.getSH();

        assertArrayEquals(new int[][]{{1,1,1,1,1,1,1,1,1,1,1,0}}, sH);

    }

    @Test
    void testSyndromeHWithMistakes(){
        int[][] input = {{1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0}};

        try {
            decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int[][] sH = decryption.getSH();

        assertArrayEquals(new int[][]{{0,1,0,1,0,1,0,0,0,0,0,0}}, sH);
    }

    @Test
    void decryptedVector(){
        int[][] input = {{1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0}};
        int[][] result;
        try {
            result = decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertArrayEquals(new int[][]{{1,0,1,0,1,0,1,0,1,0,1,0}}, result);
    }

    @Test
    void decryptVectorWithAppendOne(){
        int[][] input = {{1,1,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,1}};

        int[][] result = null;
        try {
            result = decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        assertArrayEquals(new int[][]{{1,0,1,0,1,0,1,0,1,0,1,0}},result);
    }

    @Test
    void decryptVectorWith3Mistakes(){
        int[][] input = {{1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0}};
        int[][] result;
        result = decryption.decryption(input);

        assertArrayEquals(new int[][]{{1,0,1,0,1,0,1,0,1,0,1,0}}, result);
    }


//    @Test
//    void experiment(){
//        double errorProbability = 0.50;
//        Random rand = new Random();
//        int[][] vector = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
//        vector = encryption.encryption(vector);
//        vector = channel.channel(vector, errorProbability, rand);
//        vector = decryption.decryption(vector);
//        assertArrayEquals(new int[][]{{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}, vector);
//    }
}
