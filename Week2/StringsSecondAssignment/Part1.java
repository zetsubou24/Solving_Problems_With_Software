package StringsSecondAssignment;

public class Part1 {

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

    public void testFindStopCodon(){
        System.out.println(findStopCodon("ATGGAATTATAA",0,"TAA"));
        System.out.println(findStopCodon("",0,"TAA"));
        System.out.println(findStopCodon("ATGTTT",0,"TAA"));
        System.out.println(findStopCodon("ATGTAA",0,"TAA"));
        System.out.println(findStopCodon("ATGGAATATAA",0,"ATA"));
    }

    public void testFindGene(){
        System.out.println(findGene("ATGGAATTATAA"));
        System.out.println(findGene("ATGGTAATATAG"));
        System.out.println(findGene("ATGTTT"));
        System.out.println(findGene("ATGTAA"));
        System.out.println(findGene("ATGAATTGAATGG"));
    }

    public void testPrintAllGenes(){
        printAllGenes("ATGTAAGATGCCCTAGT”");
        printAllGenes("ATGGAATTATAAATGGAATTATGAATGGAATTATAG");
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testFindStopCodon();
        part1.testFindGene();
        part1.testPrintAllGenes();
    }
}
