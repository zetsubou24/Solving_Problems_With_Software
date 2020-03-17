import edu.duke.*;

import java.io.File;

public class BatchInversion {
    public ImageResource makeInversion(ImageResource inImage){
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        for(Pixel pixel: outImage.pixels()){
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            pixel.setBlue(255 - inPixel.getBlue());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setRed(255 - inPixel.getRed());
        }
        return outImage;
    }

    public void selectAndConvert(){
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()){
            ImageResource inImage = new ImageResource(file);
            ImageResource outImage = makeInversion(inImage);
            String path = "TestingFiles/Week4/output/";
            outImage.setFileName(path + "inverted-" + file.getName());
            outImage.draw();
            outImage.save();
        }
    }

    public static void main(String[] args) {
        BatchInversion batchInversion = new BatchInversion();
        batchInversion.selectAndConvert();
    }
}
