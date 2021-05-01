package exctractor;

import java.util.HashMap;
import java.util.regex.Pattern;

public class parser {

    private static final Pattern videoIdPattern = Pattern.compile("^[a-zA-Z0-9]+$");

    private static HashMap<String, String> parseHttpHeaders(String link) {
        HashMap<String, String> result = new HashMap<>();
        if (link.contains("?")) {
            link = link.split("\\?")[1];
            // if more then one header
            if (link.contains("&")) {
                String[] headers = link.split("&");
                for (String header : headers) {
                    String[] keyAndValue = header.split("=");
                    result.put(keyAndValue[0], keyAndValue[1]);
                }
            }
            // if single header
            else {
                String[] keyAndValue = link.split("=");
                result.put(keyAndValue[0], keyAndValue[1]);
            }
        }
        return result;
    }

    private static boolean isShort(String link) {
        return link.contains(".be");
    }

    private static boolean isNormal(String link) {
        return link.contains("youtube.com/watch?v=");
    }

    private static boolean isAlreadyVideoId(String link){
        return videoIdPattern.matcher(link).find();
    }

    private static String extractIdFromShort(String link) {
        return link.split(".be/")[1];
    }

    private static String extractIdFromNormal(String link) {
        return parseHttpHeaders(link).get("v");
    }

    static String getVideoId(String link) throws Exception {
        if (isNormal(link)) {
            return extractIdFromNormal(link);
        } else if (isShort(link)) {
            return extractIdFromShort(link);
        } else if (isAlreadyVideoId(link)){
            return link;
        } else{
            throw new Exception("There is no youtube url");
        }
    }
}
