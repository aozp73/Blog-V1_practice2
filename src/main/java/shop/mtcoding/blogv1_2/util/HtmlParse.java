package shop.mtcoding.blogv1_2.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class HtmlParse {

    public static String thumbnail(String msg) {
        String thumbnail = "";
        Document doc = Jsoup.parse(msg);

        Elements els = doc.select("img");

        if (els.size() == 0) {
            thumbnail = "/images/shop.png";
        } else {
            thumbnail = els.get(0).attr("src");
        }

        return thumbnail;
    }
}
