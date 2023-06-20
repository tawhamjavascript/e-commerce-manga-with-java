package br.edu.ifpb.tawham.ecommerce.model;

public class Login {
    private String email;
    private String password;

    public Login() {
        // implements
    }

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
        // implements
    }

    public void setEmail(String email) {
        this.email = email;
        // implements
    }

    public String getPassword() {
        return this.password;
        // implements
    }

    public void setPassword(String password) {
        this.password = password;
        // implements
    }
    
}
