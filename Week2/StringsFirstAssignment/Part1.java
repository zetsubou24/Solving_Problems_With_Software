package StringsFirstAssignment;

public class Part1 {

    public String findSimpleGene(String dna){
        int startIndex = dna.indexOf("ATG");
        if(startIndex == -1) return "";
        int stopIndex = dna.indexOf(("TAA"),startIndex + 3);
        if(stopIndex == -1) return "";
        int length = stopIndex - startIndex - 3;
        if((length % 3) != 0) return "";
        return dna.substring(startIndex,stopIndex+3);
    }

    public void testSimpleGene(){
        System.out.println(findSimpleGene("ATGGAATTATAA"));
        System.out.println(findSimpleGene(""));
        System.out.println(findSimpleGene("ATGTTT"));
        System.out.println(findSimpleGene("TTGTAA"));
        System.out.println(findSimpleGene("ATGGAATATAA"));
    }

    public static void main(String[] args) {
        Part1 part1 = new Part1();
        part1.testSimpleGene();
    }
}

