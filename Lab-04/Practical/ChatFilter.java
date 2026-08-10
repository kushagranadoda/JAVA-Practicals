public class ChatFilter {

    public static boolean has(String m,String kw){
        return m.toLowerCase().contains(kw.toLowerCase());
    }

    public static String fmt(String t,String u,String m){
        return t + " " + u + ": " + m;
    }
}