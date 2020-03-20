package StringsSecondAssignment;

public class Part3 {

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

    public int countGenes(String dna){
        int startIndex = dna.indexOf("ATG");
        int count = 0;
        while(startIndex != -1){
            String gene = findGene(dna.substring(startIndex));
            if(gene.equals("")) return 0;
            count += 1;
            startIndex = dna.indexOf("ATG",startIndex + gene.length());
        }
        return count;
    }

    public void testCountGenes(){
        System.out.println(countGenes("ATGTAAGATGCCCTAGT”"));
        System.out.println(countGenes("ATGGAATTATAAATGGAATTATGAATGGAATTATAG"));
        System.out.println(countGenes("ATGFGHTAAUHGATGCHGTAGATGKGHTGA"));
        System.out.println(countGenes("ATGFHTAAUHGATGCHGTAGATGKGHTGA"));
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testCountGenes();
    }
}
