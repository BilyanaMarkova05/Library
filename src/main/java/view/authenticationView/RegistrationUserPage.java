package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.HomePage;
import view.optionsView.userOptonsPages.UserOptionPage;

import java.awt.*;
import java.awt.event.ActionEvent;

public class RegistrationUserPage extends AuthenticationPage {
    private final Authentication authentication;

    public RegistrationUserPage() {
        super("Registration", "Sign in");
        this.authentication = AuthenticationImpl.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(this.getReturnButton())) {
            this.navigateToPage(this, HomePage.getInstance());

        }

        if (source.equals(this.getAuthenticationButton())) {

            String password = new String(this.getPasswordField().getPassword());
            authentication.register(this.getNameField().getText(), password);
            this.dispose();

            if(AuthenticationImpl.getLoggedUser() == null ||
                    AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED){
                this.showMessage(this, new RegistrationUserPage(), "This account already exist. Please try again.", Color.RED, 350);

            }else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED) {
                BookController bookController = new BookControllerImpl();
                this.navigateToPage(this, new UserOptionPage(bookController));
            }

        }
    }
}

