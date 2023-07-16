package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.optionsView.UserOptionPage;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginPage extends AuthenticationPage{
    private final Authentication authentication;

    public LoginPage() {
        super("Login", "Login");
        this.authentication = AuthenticationImpl.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(this.getReturnButton())){
            this.navigateToPage(HomePage.getInstance());
        }
        if (source.equals(this.getAuthenticationButton())) {
            String password = new String(this.getPasswordField().getPassword());
            authentication.login(this.getNameField().getText(), password, "users");
            this.dispose();

            if (AuthenticationImpl.getLoggedUser() == null
                    || AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again");
                new LoginPage();
            } else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Login successful");
                BookController bookController = new BookControllerImpl();
                this.navigateToPage(new UserOptionPage(bookController));
            }
        }
    }
}
