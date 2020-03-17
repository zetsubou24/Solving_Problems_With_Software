package StringsFirstAssignment;

public class Part3 {
    public boolean twoOccurrences(String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        int count = 0;
        while(index != -1){
            count += 1;
            index = stringb.indexOf(stringa,index + stringa.length());
        }
        if(count == 2) return true;
        else return false;
    }

    public String lastPart(String stringa, String stringb){
        int index = stringb.indexOf(stringa);
        if(index == -1) return stringb;
        else return stringb.substring(index + stringa.length());
    }

    public void testing() {
        System.out.println(twoOccurrences("by", "A story by Abby Long"));
        System.out.println(twoOccurrences("atg", "ctgtatgta"));
        System.out.println(lastPart("an", "banana"));
        System.out.println(lastPart("zoo", "forest"));
    }

    public static void main(String[] args) {
        Part3 part3 = new Part3();
        part3.testing();
    }
}
