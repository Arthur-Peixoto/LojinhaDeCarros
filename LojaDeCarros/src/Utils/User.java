package Utils;

public class User {
    public String login;
    public String senha;
    public int role;

    public User(String login, String senha, int role) {
        this.login = login;
        this.senha = senha;
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

}
