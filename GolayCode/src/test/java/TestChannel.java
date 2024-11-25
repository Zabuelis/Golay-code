import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class TestChannel {
    Channel channel = new Channel();

    @Test
    void testChannelLowProbability(){
        long seed = 12345L;
        Random rand = new Random(seed);

        int[][] input = {{1,0,1,0,1,0,1,0,1,0,1,0,0,1,0,0,1,0,1,1,1,1,0}};
        double errorProbability = 0.5;

        int[][] result = channel.channel(input, errorProbability, rand);

        assertArrayEquals(new int[][]{{0,0,1,1,0,1,0,0,0,0,1,1,0,0,1,0,1,1,0,1,0,1,1}}, result);
    }
}
