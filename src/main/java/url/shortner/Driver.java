package main.java.url.shortner;

public class Driver {

    public static void main(String[] args) {

        String shortenUrl1 = URLShortnerService.shortenUrl("https://chatgpt.com/");
        String shortenUrl2 = URLShortnerService.shortenUrl("https://chatgpt.com/");

        System.out.println(shortenUrl1);
        System.out.println(shortenUrl2);

        String longUrl = URLShortnerService.getLongUrl(shortenUrl1);
        System.out.println(longUrl);
    }
}
