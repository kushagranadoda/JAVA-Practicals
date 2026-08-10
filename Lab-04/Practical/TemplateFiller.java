import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TemplateFiller {

    public static String find(String key, String[] names, String[] values) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(key)) {
                return values[i];
            }
        }
        return "[?]";
    }

    public static String fill(String template, String[] names, String[] values) {
        Pattern p = Pattern.compile("\\{(\\w+)\\}");
        Matcher m = p.matcher(template);
        StringBuilder sb = new StringBuilder();

        while (m.find()) {
            String key = m.group(1);
            String val = find(key, names, values);
            m.appendReplacement(sb, Matcher.quoteReplacement(val));
        }
        m.appendTail(sb);

        return sb.toString();
    }
}