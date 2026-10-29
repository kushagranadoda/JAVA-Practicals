public class SignupForm {
    @NotBlank
    @MaxLength(10)
    private String name;

    @NotBlank
    private String email;

    @MaxLength(8)
    private String pass;

    public SignupForm(String name, String email, String pass) {
        this.name = name;
        this.email = email;
        this.pass = pass;
    }
}