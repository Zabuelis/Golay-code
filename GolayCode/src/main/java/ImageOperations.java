import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class ImageOperations {
    // Converts image to bit string
    public static String imageToBitString(BufferedImage image, int height, int width) throws IOException {
        StringBuilder bits = new StringBuilder();

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int pixel = image.getRGB(j, i);

                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = pixel & 0xFF;

                bits.append(String.format("%8s", Integer.toBinaryString(red)).replace(' ', '0'));
                bits.append(String.format("%8s", Integer.toBinaryString(green)).replace(' ', '0'));
                bits.append(String.format("%8s", Integer.toBinaryString(blue)).replace(' ', '0'));
            }
        }
        return bits.toString();
    }

    // Convert bitString back to the image
    public static BufferedImage bitStringToImage(String imageAsString, int height, int width){
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        int index = 0;

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(index + 24 <= imageAsString.length()){
                    int red = (Integer.parseInt(imageAsString.substring(index, index + 8), 2));
                    int green = (Integer.parseInt(imageAsString.substring(index + 8, index + 16), 2));
                    int blue = (Integer.parseInt(imageAsString.substring(index + 16, index + 24), 2));
                    index += 24;
                    int pixel = (red << 16) | (green << 8) | blue;
                    image.setRGB(j, i, pixel);
                } else {
                    image.setRGB(j, i, 0);
                }
            }
        }

        return image;
    }

    // Validates if a file uses .bmp extension and if it is a file
    public static boolean bmpFileValidator (String path){
        File file = Path.of(path).toFile();
        return file.isFile() && path.endsWith(".bmp");
    }
}
