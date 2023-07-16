package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.optionsView.LibrarianOptionPage;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LoginLibrarianPage extends AuthenticationPage{
    private final Authentication authentication;

    public LoginLibrarianPage() {
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
            authentication.login(this.getNameField().getText(), password, "librarians");
            this.dispose();

            if (AuthenticationImpl.getLoggedUser() == null
                    || AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again");
                new LoginLibrarianPage();
            } else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Login successful");
                BookController bookController = new BookControllerImpl();
                this.navigateToPage(new LibrarianOptionPage(bookController));
            }
        }
    }
}
