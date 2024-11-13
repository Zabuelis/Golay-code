import java.util.Random;

public class Channel {
    // Simulation of the channel noise
    public int[] channel(int[] vector, double errorProbability, Random rand){
        for(int i = 0; i < vector.length; i++){
            double randDub = rand.nextDouble();

            if(errorProbability > randDub){
                vector[i] = (vector[i] == 1) ? 0 : 1;
            }
        }
        return vector;
    }
}
