import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class CSVWeatherRunner {
    public double findHottestTempInADay(CSVParser csvParser){
        double maxTemp = 0;
        for(CSVRecord csvRecord: csvParser){
            double currTemp = Double.parseDouble(csvRecord.get("TemperatureF"));
            maxTemp = Math.max(currTemp, maxTemp);
        }
        return maxTemp;
    }
    public String findHottestDay(){
        double maxTemp = 0;
        File maxFile = null;
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()){
            FileResource fileResource = new FileResource(file);
            CSVParser csvParser = fileResource.getCSVParser();
            double currTemp = findHottestTempInADay(csvParser);
            if(currTemp > maxTemp){
                maxTemp = currTemp;
                maxFile = file;
            };
        }
        return maxFile == null ? "" : maxFile.getName();
    }

    public CSVRecord coldestHourInFile(CSVParser csvParser){
        double minTemp = 9999;
        CSVRecord minRecord = null;
        for(CSVRecord csvRecord: csvParser){
            double currTemp = Double.parseDouble(csvRecord.get("TemperatureF"));
            if(currTemp != -9999 && currTemp < minTemp){
                minTemp = currTemp;
                minRecord = csvRecord;
            }
        }
        return minRecord;
    }

    public void fileWithColdestTemperature(){
        DirectoryResource directoryResource = new DirectoryResource();
        File minFile = null;
        double minTemp = 9999;
        for(File file: directoryResource.selectedFiles()){
            FileResource fileResource = new FileResource(file);
            CSVParser csvParser = fileResource.getCSVParser();
            CSVRecord csvRecord = coldestHourInFile(csvParser);
            double currTemp = Double.parseDouble(csvRecord.get("TemperatureF"));
            if(currTemp < minTemp){
                minTemp = currTemp;
                minFile = file;
            }
        }
        FileResource fileResource = new FileResource(minFile);
        CSVParser csvParser = fileResource.getCSVParser();
        for(CSVRecord csvRecord: csvParser){
            System.out.println(csvRecord.get("DateUTC") + " " + csvRecord.get("TemperatureF"));
        }
    }

    public CSVRecord lowestHumidityInFile(CSVParser csvParser){
        double lowestHumidity = 9999;
        CSVRecord lowestRecord = null;
        for(CSVRecord csvRecord: csvParser){
            String temp = csvRecord.get("Humidity");
            if(temp == "N/A") continue;
            double currHumidity = Double.parseDouble(temp);
            if(currHumidity < lowestHumidity){
                lowestHumidity = currHumidity;
                lowestRecord = csvRecord;
            }
        }
        return lowestRecord;
    }

    public CSVRecord lowestHumidityInManyFiles(){
        double lowestHumidity = 9999;
        CSVRecord lowestRecord = null;
        DirectoryResource directoryResource = new DirectoryResource();
        for(File file: directoryResource.selectedFiles()){
            FileResource fileResource = new FileResource(file);
            CSVParser csvParser = fileResource.getCSVParser();
            CSVRecord currRecord = lowestHumidityInFile(csvParser);
            if(currRecord == null) continue;
            String temp = currRecord.get("Humidity");
            if(temp == "N/A") continue;
            double currHumidity = Double.parseDouble(temp);
            if(currHumidity < lowestHumidity){
                lowestHumidity = currHumidity;
                lowestRecord = currRecord;
            }
        }
        return lowestRecord;
    }

    public double averageTemperatureInFile(CSVParser csvParser){
        double total = 0;
        int count = 0;
        for(CSVRecord csvRecord: csvParser){
            double currTemp = Double.parseDouble(csvRecord.get("TemperatureF"));
            count += 1;
            total += currTemp;
        }
        return total/count;
    }

    public double averageTemperatureWithHighHumidityInFile(CSVParser csvParser, int value){
        double total = 0;
        int count = 0;
        for(CSVRecord csvRecord: csvParser){
            double currTemp = Double.parseDouble(csvRecord.get("TemperatureF"));
            String temp = csvRecord.get("Humidity");
            if(temp == "N/A") continue;
            double currHumidity = Double.parseDouble(csvRecord.get("Humidity"));
            if(currHumidity < value) continue;
            count += 1;
            total += currTemp;
        }
        return count != 0 ? total/count : 0;
    }

    public void testFindHottestTempInADay(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        System.out.println(findHottestTempInADay(csvParser));
    }

    public void testFindHottestDay(){
        System.out.println(findHottestDay());
    }

    public void testColdestHourInFile(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        CSVRecord csvRecord = coldestHourInFile(csvParser);
        System.out.println("Coldest temperature = " + csvRecord.get("TemperatureF") + " at " + csvRecord.get("TimeEST"));
    }

    public void testFileWithColdestTemperature(){
        fileWithColdestTemperature();
    }

    public void testLowestHumidityInFile(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser =  fileResource.getCSVParser();
        CSVRecord csvRecord = lowestHumidityInFile(csvParser);
        System.out.println("Lowest humidity was " + csvRecord.get("Humidity") + " at " + csvRecord.get("DateUTC"));
    }

    public void testLowestHumidityInManyFiles(){
        CSVRecord csvRecord = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity was " + csvRecord.get("Humidity") + " at " + csvRecord.get("DateUTC"));
    }

    public void testAverageTemperatureInFile(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        System.out.println("Average temperature in file is " + averageTemperatureInFile(csvParser));
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fileResource =  new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        double avgTemp = averageTemperatureWithHighHumidityInFile(csvParser, 80);
        if(avgTemp == 0) System.out.println("No temperatures with that humidity");
        else System.out.println("Average Temp when high Humidity is " + avgTemp);
    }

    public static void main(String[] args) {
        CSVWeatherRunner csvWeatherRunner = new CSVWeatherRunner();
//        csvWeatherRunner.testFindHottestTempInADay();
//        csvWeatherRunner.testFindHottestDay();
//        csvWeatherRunner.testColdestHourInFile();
//        csvWeatherRunner.fileWithColdestTemperature();
//        csvWeatherRunner.testLowestHumidityInFile();
//        csvWeatherRunner.testLowestHumidityInManyFiles();
//        csvWeatherRunner.testAverageTemperatureInFile();
        csvWeatherRunner.testAverageTemperatureWithHighHumidityInFile();
    }
}
