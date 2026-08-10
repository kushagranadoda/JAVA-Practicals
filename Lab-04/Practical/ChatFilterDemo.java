import java.util.Scanner;

public class ChatFilterDemo {
    public static void main(String[] args){
        String[] log ={
            "10:05 alice Hello there",
            "10:06 bob How are you",
            "10:07 bad"
        };

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String kw = sc.nextLine();

        int n = 0;
        StringBuilder sb = new StringBuilder();

        for (String line : log){
            String[] p = line.split(" ", 3);
            if (p.length < 3) {
                continue;
            }
            String t = p[0], u = p[1], m = p[2];

            if (ChatFilter.has(m, kw)){
                n++;
                sb.append(ChatFilter.fmt(t,u,m)).append("\n");
            }
        }

        System.out.println("Matches: " + n);
        System.out.print(sb);
    }
}