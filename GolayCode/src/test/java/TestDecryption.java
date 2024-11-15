import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDecryption {
    Encryption encryption = new Encryption();
    Decryption decryption = new Decryption(encryption.getI());

    @Test
    void testHMatrix(){
        assertArrayEquals(new int[][]{
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
                {1,1,1,1,1,1,1,1,1,1,1,0},
        }, decryption.getH());
    }

    @Test
    void testAppendingOne(){
        int[] input = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1};

        try {
            decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int[] result = decryption.getW();

        assertArrayEquals(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1}, result);
    }

    @Test
    void testAppendingZero(){
        int[] input = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0};

        try {
            decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        int[] result = decryption.getW();

        assertArrayEquals(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,0}, result);
    }

    @Test
    void testSyndromeH(){
        int[] input = {1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0};

        try {
            decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        System.out.println(Arrays.toString(decryption.getW()));
        int[] sH = decryption.getSH();

        assertArrayEquals(new int[]{1,1,1,1,1,1,1,1,1,1,1,0}, sH);

    }

    @Test
    void testSyndromeHWithMistakes(){
        int[] input = {1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0};

        try {
            decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        int[] sH = decryption.getSH();

        assertArrayEquals(new int[]{0,1,0,1,0,1,0,0,0,0,0,0}, sH);
    }

    @Test
    void decryptedVector(){
        int[] input = {1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0};

        int[] result = null;
        try {
            result = decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        assertArrayEquals(new int[]{1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0},result);
    }

    @Test
    void decryptVectorWithAppendOne(){
        int[] input = {1,1,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,1};

        int[] result = null;
        try {
            result = decryption.decryption(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        assertArrayEquals(new int[]{1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0},result);
    }

}