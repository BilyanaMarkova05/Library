package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.HomePage;
import view.optionsView.AdminOptionPage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class AdminPage extends AuthenticationPage {
    private final Authentication authentication;
    private static AdminPage instance;

    public AdminPage() {
        super("Admin", "login");
        this.authentication = AuthenticationImpl.getInstance();
    }

    public static AdminPage getInstance(){
        if (instance == null){
            instance = new AdminPage();
        }
        return instance;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(this.getReturnButton())){
            this.navigateToPage(this, HomePage.getInstance());
        }
        if (source.equals(this.getAuthenticationButton())){
            String password = new String(this.getPasswordField().getPassword());
            authentication.loginAsAdmin(this.getNameField().getText(), password);
            this.dispose();

            if (AuthenticationImpl.getLoggedUser() == null
                    || AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED) {
                this.showMessage(new AdminPage(), "Login failed. Please try again", Color.RED, 250);
            }else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED){
                this.showMessage(this, "Login successful", Color.GREEN, 200);
                this.navigateToPage(this, new AdminOptionPage(new BookControllerImpl()));
            }
        }
    }
}
