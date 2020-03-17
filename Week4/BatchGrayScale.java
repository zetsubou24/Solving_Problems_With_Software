import edu.duke.*;

import java.io.File;

public class BatchGrayScale {
    public ImageResource makeGrayScale(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3;
            pixel.setBlue(average);
            pixel.setGreen(average);
            pixel.setRed(average);
        }
        return outImage;
    }

    public void batchConversion(){
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()){
            ImageResource inImage = new ImageResource(file);
            ImageResource outImage = makeGrayScale(inImage);
            String path = "TestingFiles/Week4/output/";
            outImage.setFileName(path + "gray-" + file.getName());
            outImage.draw();
            outImage.save();
        }
    }

    public static void main(String[] args) {
        BatchGrayScale batchGrayScale = new BatchGrayScale();
        batchGrayScale.batchConversion();
    }
}
