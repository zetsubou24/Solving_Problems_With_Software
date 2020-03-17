package StringsFirstAssignment;

public class Part2 {

    public String findSimpleGene(String dna, String startCodon, String endCodon){
        int startIndex = dna.toLowerCase().indexOf(startCodon.toLowerCase());
        if(startIndex == -1) return "";
        int stopIndex = dna.toLowerCase().indexOf(endCodon.toLowerCase(),startIndex + 3);
        if(stopIndex == -1) return "";
        int length = stopIndex - startIndex - 3;
        if((length % 3) != 0) return "";
        return dna.substring(startIndex,stopIndex+3);
    }

    public void testSimpleGene(){
        System.out.println(findSimpleGene("ATGGAATTATAA","ATG","TAA"));
        System.out.println(findSimpleGene("","ATG","TAA"));
        System.out.println(findSimpleGene("ATGTTT","ATG","TAA"));
        System.out.println(findSimpleGene("TTGTAA","ATG","TAA"));
        System.out.println(findSimpleGene("ATGGAATATAA","ATG","TAA"));
        System.out.println(findSimpleGene("atggaattataa","ATG","TAA"));
    }
    public static void main(String[] args) {
        Part2 part2 = new Part2();
        part2.testSimpleGene();
    }
}

