import org.junit.jupiter.api.Test;

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

        decryption.decryption(input);
        int[] result = decryption.getW();

        assertArrayEquals(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,1,1}, result);
    }

    @Test
    void testAppendingZero(){
        int[] input = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0};

        decryption.decryption(input);
        int[] result = decryption.getW();

        assertArrayEquals(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,0,1,1,0,0}, result);
    }

    @Test
    void testSyndromeH(){
        int[] input = {1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0};

        decryption.decryption(input);
        int[] sH = decryption.getSH();

        assertArrayEquals(new int[]{1,1,1,1,1,1,1,1,1,1,1,0}, sH);

    }

    @Test
    void testSyndromeHWithMistakes(){
        int[] input = {1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0};

        decryption.decryption(input);

        int[] sH = decryption.getSH();

        assertArrayEquals(new int[]{0,1,0,1,0,1,0,0,0,0,0,0}, sH);
    }

    @Test
    void decryptedVector(){
        int[] input = {1,1,1,1,1,1,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0};

        int[] result = decryption.decryption(input);

        assertArrayEquals(new int[]{1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0},result);
    }

}
