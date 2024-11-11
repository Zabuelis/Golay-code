import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGolay {
    @Test
    void testMatrixCombination(){
        Encryption encryption = new Encryption();
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
}
