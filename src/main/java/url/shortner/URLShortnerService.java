package main.java.url.shortner;

import java.util.HashMap;
import java.util.Map;

public class URLShortnerService {
    private static final String BASE_URL = "https://short.ly/";
    private static long counter = 1;
    private static Map<String, String> urlMap = new HashMap<>();
    private static Map<String, String> reverseMap = new HashMap<>();

    public static String shortenUrl(String longUrl) {
        if (reverseMap.containsKey(longUrl)) {
            return BASE_URL + reverseMap.get(longUrl);
        }
        String shortKey = encodeBase62(counter++);
        urlMap.put(shortKey, longUrl);
        reverseMap.put(longUrl, shortKey);
        return BASE_URL + shortKey;
    }

    public static String getLongUrl(String shortUrl) {
        String shortKey = shortUrl.replace(BASE_URL, "");
        return urlMap.getOrDefault(shortKey, "URL not found");
    }

    private static String encodeBase62(long number) {
        final String chars = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        while (number > 0) {
            sb.append(chars.charAt((int) (number % chars.length())));
            number /= chars.length();
        }
        return sb.reverse().toString();
    }
}