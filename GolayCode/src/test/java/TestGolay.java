import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGolay {
    Encryption encryption = new Encryption();
    Utilities utilities = new Utilities();
    @Test
    void testMatrixCombination(){
        int[][] G = {
                {1,0,0,0,0,0,0,0,0,0,0,0,1,1,0,1,1,1,0,0,0,1,0},
                {0,1,0,0,0,0,0,0,0,0,0,0,1,0,1,1,1,0,0,0,1,0,1},
                {0,0,1,0,0,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,1,1},
                {0,0,0,1,0,0,0,0,0,0,0,0,1,1,1,0,0,0,1,0,1,1,0},
                {0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,1,0,1,1,0,1},
                {0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,0,1,1,0,1,1},
                {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0,1,0,1,1,0,1,1,1},
                {0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,1,1,0,1,1,1,0},
                {0,0,0,0,0,0,0,0,1,0,0,0,0,1,0,1,1,0,1,1,1,0,0},
                {0,0,0,0,0,0,0,0,0,1,0,0,1,0,1,1,0,1,1,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1,0,0,1,1,0,1,1,1,0,0,0,1},
                {0,0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1,1,1},
        };

        int[][] result = encryption.generatingMatrix();

        assertArrayEquals(G, result);
    }

    @Test
    void testUtilitiesStringToInt(){
        String input = "{1, 2, 3, 4, 5, 6, 7, 8}";

        input = utilities.formatVector(input);
        int[] result = utilities.stringToInt(input);

        assertArrayEquals(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, result);
    }

    @Test
    void testUtilitiesIntToString(){
        int[] numb = {1, 2, 3, 4, 5, 6, 7};

        String result = utilities.intToString(numb);

        assertEquals("1234567", result);
    }
}