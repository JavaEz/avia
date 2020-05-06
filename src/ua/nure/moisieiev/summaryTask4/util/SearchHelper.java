package ua.nure.moisieiev.summaryTask4.util;

public class SearchHelper {

    public static String writeStringToDB (String string){
        string = string.toLowerCase();
        StringBuilder stringBuilder = new StringBuilder(string);
        stringBuilder.setCharAt(0, string.substring(0,1).toUpperCase().charAt(0));
        string = stringBuilder.toString();
        return string;
    }
}
