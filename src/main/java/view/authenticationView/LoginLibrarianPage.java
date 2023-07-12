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
    private BookController bookController;

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
            authentication.loginAsLibrarian(this.getNameField().getText(), password);
            this.dispose();

            if (AuthenticationImpl.getLoggedUser() == null
                    || AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again");
            } else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Login successful");
                bookController = new BookControllerImpl();
                this.navigateToPage(new LibrarianOptionPage(bookController));
            }
        }
    }
}
