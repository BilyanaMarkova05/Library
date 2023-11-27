package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.HomePage;
import view.optionsView.UserOptionPage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LoginPage extends AuthenticationPage{
    private final Authentication authentication;

    public LoginPage() {
        super("Login", "Login");
        this.authentication = AuthenticationImpl.getInstance();
        this.getContentPane().setBackground(Color.ORANGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(this.getReturnButton())){
            this.navigateToPage(this, HomePage.getInstance());
        }
        if (source.equals(this.getAuthenticationButton())) {
            String password = new String(this.getPasswordField().getPassword());
            authentication.login(this.getNameField().getText(), password, "users");
            this.dispose();

            if (AuthenticationImpl.getLoggedUser() == null
                    || AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED) {
                this.showMessage(new LoginPage(), "Login failed. Please try again", Color.RED, 250);
            } else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED) {
                this.showMessage(this, "Login successful", Color.GREEN, 200);
                BookController bookController = new BookControllerImpl();
                this.navigateToPage(this, new UserOptionPage(bookController));
            }
        }
    }
}
