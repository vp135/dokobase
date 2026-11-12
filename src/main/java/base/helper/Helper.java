package base.helper;

import base.Constants;
public class Helper {

    public static String removeJsonChars(String s){
        return (s.replace("{",Constants.EMPTY_STRING)
                .replace("}",Constants.EMPTY_STRING)
                .replace("\"",Constants.EMPTY_STRING)
                .replace(",",Constants.EMPTY_STRING)
                .trim());
    }
}
