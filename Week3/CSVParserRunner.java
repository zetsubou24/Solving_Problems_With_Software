import edu.duke.*;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class CSVParserRunner {

    public String countryInfo(CSVParser parser, String country){
        for(CSVRecord csvRecord: parser){
            if(csvRecord.get("Country").equals(country)){
                return country + ": " + csvRecord.get("Exports") + ": " + csvRecord.get("Value (dollars)");
            }
        }
        return "NOT FOUND";
    }

    public void listExportersTwoProducts(CSVParser csvParser, String exportItem1, String exportItem2){
        for(CSVRecord csvRecord: csvParser){
            if(csvRecord.get("Exports").contains(exportItem1) && csvRecord.get("Exports").contains(exportItem2)){
                System.out.println(csvRecord.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser csvParser, String exportItem){
        int count = 0;
        for(CSVRecord csvRecord: csvParser){
            if(csvRecord.get("Exports").contains(exportItem)){
                count += 1;
            }
        }
        return count;
    }

    public void bigExporters(CSVParser csvParser, String amount){
        for(CSVRecord csvRecord: csvParser){
            if(csvRecord.get("Value (dollars)").length() > amount.length()){
                System.out.println(csvRecord.get("Country") + " " + csvRecord.get("Value (dollars)"));
            }
        }
    }

    public void tester(){
        FileResource fileResource = new FileResource();
        CSVParser csvParser = fileResource.getCSVParser();
        System.out.println(countryInfo(csvParser,"South Africa"));
        csvParser = fileResource.getCSVParser();
        listExportersTwoProducts(csvParser, "gold", "diamonds");
        csvParser = fileResource.getCSVParser();
        System.out.println(numberOfExporters(csvParser,"gold"));
        csvParser = fileResource.getCSVParser();
        bigExporters(csvParser,"$999,999,999");
    }

    public static void main(String[] args) {
        CSVParserRunner csvParserRunner = new CSVParserRunner();
        csvParserRunner.tester();
    }
}