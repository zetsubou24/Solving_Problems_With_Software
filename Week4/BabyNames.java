import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class BabyNames {
    public void totalBirths(FileResource fileResource){
        int totalBirths = 0;
        int totalBoys = 0;
        int totalGirls = 0;
        for(CSVRecord csvRecord: fileResource.getCSVParser(false)){
            if(csvRecord.get(1).equals("M")) totalBoys += 1;
            if(csvRecord.get(1).equals("F")) totalGirls += 1;
            totalBirths += 1;
            System.out.println(csvRecord.get(0));
        }
        System.out.println("Total no. of births = " + totalBirths);
        System.out.println("Total no. of boys = " + totalBoys);
        System.out.println("Total no. of girls = " + totalGirls);
    }

    public int getRank(int year, String name, String gender){
        int rank = 1;
        FileResource fileResource = new FileResource("TestingFiles/Week4/us_babynames/us_babynames_test/yob" + year + "short.csv");
        for(CSVRecord csvRecord: fileResource.getCSVParser()){
            if(csvRecord.get(1).equals(gender)) {
                if (csvRecord.get(0).equals(name)) {
                    return rank;
                }
                rank += 1;
            }
        }
        return -1;
    }

    public void whatIsNameInYear(String name,int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        int currRank = 1;
        FileResource fileResource = new FileResource("TestingFiles/Week4/us_babynames/us_babynames_test/yob" + newYear + "short.csv");
        for(CSVRecord csvRecord: fileResource.getCSVParser()){
            if(csvRecord.get(1).equals(gender)) {
                if(rank == currRank){
                    System.out.println(name + " born in " + year + " would be " + csvRecord.get(0) + " if she was born in " + newYear);
                }
                currRank += 1;
            }
        }
    }

    public int yearOfHighestRank(String name, String gender){
        int maxRank = 9999;
        int maxYear = -1;
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()){
            int year = Integer.parseInt(file.getName().substring(3,7));
            int currRank = getRank(year, name, gender);
            if(currRank < maxRank){
                maxRank = currRank;
                maxYear = year;
            }
        }
        return  maxYear;
    }

    public double getAverageRank(String name, String gender){
        double totalRank = 0;
        int count = 0;
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()){
            int year = Integer.parseInt(file.getName().substring(3,7));
            int currRank = getRank(year, name, gender);
            totalRank += currRank;
            count += 1;
        }
        return totalRank / count;
    }

    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalCount = 0;
        int maxRank = getRank(year, name, gender);
        FileResource fileResource = new FileResource("TestingFiles/Week4/us_babynames/us_babynames_test/yob" + year + "short.csv");
        CSVParser csvParser = fileResource.getCSVParser();
        for(CSVRecord csvRecord: csvParser){
            int currRank = getRank(2012, csvRecord.get(0), csvRecord.get(1));
            if(csvRecord.get(1).equals(gender) && currRank < maxRank){
                totalCount += Integer.parseInt(csvRecord.get(2));
            }
        }
        return totalCount;
    }

    public void testTotalBirths(){
        FileResource fileResource = new FileResource("TestingFiles/Week4/us_babynames/us_babynames_test/yob2014short.csv");
        totalBirths(fileResource);
    }

    public void testGetRank(){
        System.out.println(getRank(2012,"Mason","F"));
    }

    public void testWhatIsNameInYear(){
        whatIsNameInYear("Isabella", 2012, 2014, "F");
    }

    public void testYearOfHighestRank(){
        System.out.println("Mason had highest rank in " + yearOfHighestRank("Mason", "M"));
    }

    public void testGetAverageRank(){
        System.out.println("Mason's average rank is " + getAverageRank("Jacob","M"));
    }

    public void testGetTotalBirthsRankedHigher(){
        System.out.println(getTotalBirthsRankedHigher(2012, "Ethan", "M"));
    }

    public static void main(String[] args) {
        BabyNames babyNames = new BabyNames();
        babyNames.testTotalBirths();
        babyNames.testGetRank();
        babyNames.testWhatIsNameInYear();
//        babyNames.testYearOfHighestRank();
//        babyNames.testGetAverageRank();
        babyNames.testGetTotalBirthsRankedHigher();
    }
}
