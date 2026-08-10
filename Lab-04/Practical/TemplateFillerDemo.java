public class TemplateFillerDemo {
    public static void main(String[] args) {
        String t = "Dear {name}, order {id} ships {date}.";
        String[] names = {"name", "id"};
        String[] values = {"Riya", "A07"};

        System.out.println(TemplateFiller.fill(t, names, values));
    }
}