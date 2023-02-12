package shop.mtcoding.blogv1_2.util;

public class CustomFileReturn {

    public static String Script(String msg) {
        StringBuilder sb = new StringBuilder();
        sb.append("<script>");
        sb.append("alert('" + msg + "');");
        sb.append("history.back();");
        sb.append("</script>");

        return sb.toString();
    }
}
