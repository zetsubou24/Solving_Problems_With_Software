import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        double totalPerim = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
            totalPerim = totalPerim + currDist;
            prevPt = currPt;
        }
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        int count = 0;
        for(Point point : s.getPoints()){
            count += 1;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        double total = 0.0;
        Point prevPoint = s.getLastPoint();
        for(Point point: s.getPoints()){
            double length = point.distance(prevPoint);
            total += length;
            prevPoint = point;
        }
        return total / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        double max = 0.0;
        Point prevPoint = s.getLastPoint();
        for(Point point: s.getPoints()){
            double length = point.distance(prevPoint);
            if(length > max) max = length;
            prevPoint = point;
        }
        return max;
    }

    public double getLargestX(Shape s) {
        double max = s.getLastPoint().getX();
        for(Point point:s.getPoints() ){
            if(point.getX() > max){
                max = point.getX();
            }
        }
        return max;
    }

    public double getLargestPerimeterMultipleFiles() {
        double maxPerimeter = 0;
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()){
            FileResource fileResource = new FileResource(file);
            Shape shape = new Shape(fileResource);
            double perimeter = getPerimeter(shape);
            if(perimeter > maxPerimeter){
                maxPerimeter = perimeter;
            }
        }
        return maxPerimeter;
    }

    public String getFileWithLargestPerimeter() {
        double maxPerimeter = 0;
        File maxFile = null;
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()) {
            FileResource fileResource = new FileResource(file);
            Shape shape = new Shape(fileResource);
            double perimeter = getPerimeter(shape);
            if(perimeter > maxPerimeter){
                maxPerimeter = perimeter;
                maxFile = file;
            }
        }
        return maxFile == null ? " " : maxFile.getName();
    }

    public void testPerimeter () {
        FileResource fileResource = new FileResource();
        Shape shape = new Shape(fileResource);
        double perimeter = getPerimeter(shape);
        double noOfPoints = getNumPoints(shape);
        double averageLength = getAverageLength(shape);
        double largestSide = getLargestSide(shape);
        double largestX = getLargestX(shape);
        System.out.println("Perimeter = " + perimeter);
        System.out.println("No. of points = " + noOfPoints);
        System.out.println("Average length = " + averageLength);
        System.out.println("Largest side = " + largestSide);
        System.out.println("Largest X = " + largestX);
    }

    public void testPerimeterMultipleFiles() {
        double maxPerimeter = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter = " + maxPerimeter);
    }

    public void testFileWithLargestPerimeter() {
        String maxFileName = getFileWithLargestPerimeter();
        System.out.println("The file with largest perimeter = " + maxFileName);
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f.getName());
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner perimeterAssignmentRunner = new PerimeterAssignmentRunner();
        perimeterAssignmentRunner.testPerimeter();
        perimeterAssignmentRunner.testPerimeterMultipleFiles();
        perimeterAssignmentRunner.testFileWithLargestPerimeter();
    }
}
