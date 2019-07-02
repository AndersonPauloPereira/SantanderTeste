package pereira.anderson.testeandroidv2.model;

/**
 * Created by Anderson on 13/06/2019.
 */

public class ModeloLogin {

    public String Email, Password;

    public ModeloLogin(String email, String password) {
        this.Email = email;
        this.Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
