package StringsThirdAssignment;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Part1{

    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int stopIndex = dna.indexOf(stopCodon,startIndex+3);
        while(stopIndex != -1) {
            int length = stopIndex - startIndex - 3;
            if (length % 3 == 0) return stopIndex;
            stopIndex = dna.indexOf(stopCodon,stopIndex+1);
        }
        return dna.length();
    }

    public String findGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1) return "";
        int taaStopIndex = findStopCodon(dna,startIndex,"TAA");
        int tagStopIndex = findStopCodon(dna,startIndex,"TAG");
        int tgaStopIndex = findStopCodon(dna,startIndex,"TGA");

        if(taaStopIndex == -1) taaStopIndex = dna.length();
        if(tagStopIndex == -1) tagStopIndex = dna.length();
        if(tgaStopIndex == -1) tgaStopIndex = dna.length();

        int minIndex = Math.min(taaStopIndex,Math.min(tagStopIndex,tgaStopIndex));
        if(minIndex < dna.length()) return dna.substring(startIndex,minIndex + 3);
        else return "";
    }

    public void printAllGenes(String dna){
        int startIndex = dna.indexOf("ATG");
        while(startIndex != -1){
            String gene = findGene(dna.substring(startIndex));
            System.out.println(gene);
            startIndex = dna.indexOf("ATG",startIndex + gene.length());
        }
    }

    public StorageResource getAllGenes(String dna){
        StorageResource storageResource = new StorageResource();
        int startIndex = dna.indexOf("ATG");
        while(startIndex != -1){
            String gene = findGene(dna.substring(startIndex));
            storageResource.add(gene);
            startIndex = dna.indexOf("ATG",startIndex + gene.length());
        }
        return storageResource;
    }

    public double cgRatio(String dna){
        double count = 0;
        for(int i = 0; i < dna.length(); i++){
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G'){
                count += 1;
            }
        }
        return count/dna.length();
    }

    public int countCTG(String dna){
        int count = 0;
        for(int i = 0; i <= dna.length() - 3; i += 3){
            if(dna.substring(i,i+3).equals("CTG"))
                count += 1;
        }
        return count;
    }

    public void processGenes(StorageResource storageResource){
        int longCount = 0;
        int cgCount = 0;
        String longest = "";
        for(String string: storageResource.data()){
            if(string.length() > longest.length()){
                longest = string;
            }
            if(string.length() > 60) {
                longCount += 1;
                System.out.println("Length greater than requirement:" + string);
            }
            if(cgRatio(string) > 0.35) {
                cgCount += 1;
                System.out.println("CG Ratio greater than 0.35:" + string);
            }
        }
        System.out.println("Number of strings longer than requirement = " + longCount);
        System.out.println("Number of strings having CG ratio greater than required = " + cgCount);
        System.out.println("Longest string is = " + longest);
    }

    public void testGetAllGenes(){
        StorageResource storageResource = new StorageResource();
        storageResource = getAllGenes("ATGTAAGATGCCCTAGT”");
        for(String gene: storageResource.data()){
            System.out.println(gene);
        }
        storageResource = getAllGenes("ATGGAATTATAAATGGAATTATGAATGGAATTATAG");
        for(String gene: storageResource.data()){
            System.out.println(gene);
        }
    }

    public void testCGRatio(){
        System.out.println(cgRatio("ATGCCATAG"));
    }

    public void testCountCTG(){
        System.out.println(countCTG("ATGCTGAGGCTGCTGTAA"));
    }

    public void testProcessGenes(){
        StorageResource storageResource = new StorageResource();
        storageResource.add("ATGTAA");
        storageResource.add("ATGCTGAGGCTGCTGTAA");
        storageResource.add("ATGCCATAG");
        storageResource.add("ATGGAATTATAAATGGAATTATGAATGGAATTATAG");
        storageResource.add("ATGAAAAAATAAA");
        processGenes(storageResource);
    }

    public void testRealGene(){
        FileResource fileResource = new FileResource("TestingFiles/Week2/dna.fa");
        String dna = fileResource.asString();
        StorageResource storageResource = getAllGenes(dna);
        processGenes(storageResource);
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testGetAllGenes();
        part1.testCGRatio();
        part1.testCountCTG();
        part1.testProcessGenes();
        part1.testRealGene();
    }
}

