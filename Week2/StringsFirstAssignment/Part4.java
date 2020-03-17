package StringsFirstAssignment;

import edu.duke.*;

public class Part4 {

    public void parseUrl(String url){
        URLResource urlResource = new URLResource(url);
        String urlResourceString = urlResource.asString();
        String urlResourceLowercaseString = urlResourceString.toLowerCase();
        int index = urlResourceLowercaseString.indexOf("youtube");
        while(index != -1){
            System.out.println(urlResourceString.substring(urlResourceString.lastIndexOf("\"",index) + 1,index) + urlResourceString.substring(index,urlResourceString.indexOf("\"",index)));
            index = urlResourceLowercaseString.indexOf("youtube",index + 1);
        }
    }

    public static void main(String[] args) {
        Part4 part4 = new Part4();
        part4.parseUrl("http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    }
}
