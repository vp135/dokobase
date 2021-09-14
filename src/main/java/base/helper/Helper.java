package base.helper;

public class Helper {

    public static String removeJsonChars(String s){
        return (s.replace("{","")
                .replace("}","")
                .replace("\"","")
                .replace(",","")
                .trim());
    }
}
