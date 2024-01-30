package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.HomePage;
import view.optionsView.librarianOptionsPages.LibrarianOptionPage;

import java.awt.*;
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
            this.navigateToPage(this, HomePage.getInstance());
        }
        if (source.equals(this.getAuthenticationButton())) {
            String password = new String(this.getPasswordField().getPassword());
            authentication.login(this.getNameField().getText(), password, "librarians");
            this.dispose();

            if (AuthenticationImpl.getLoggedUser() == null
                    || AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED) {
                this.showMessage(new LoginLibrarianPage(), "Login failed. Please try again", Color.RED, 250);
            } else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED) {
                this.showMessage(this, "Login successful", Color.GREEN, 200);
                BookController bookController = new BookControllerImpl();
                //this.navigateToPage(this, new LibrarianOptionPage(bookController));
                this.navigateToPage(this, new LibrarianOptionPage(bookController));
            }
        }
    }
}
