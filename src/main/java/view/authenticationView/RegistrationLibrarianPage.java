package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookController;
import controller.BookControllerImpl;
import view.optionsView.AdminOptionPage;

import java.awt.event.ActionEvent;

public class RegistrationLibrarianPage extends AuthenticationPage{
    private final Authentication authentication;

    public RegistrationLibrarianPage(String title, String buttonName) {
        super(title, buttonName);
        this.authentication = AuthenticationImpl.getInstance();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(this.getReturnButton())) {
            BookController bookController = new BookControllerImpl();
            this.navigateToPage(new AdminOptionPage(bookController));
        } else if (source.equals(this.getAuthenticationButton())) {
            String password = new String(this.getPasswordField().getPassword());
            authentication.registerLibrarian(this.getNameField().getText(), password);
            this.dispose();
        }
    }
}
