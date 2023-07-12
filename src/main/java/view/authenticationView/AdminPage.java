package view.authenticationView;

import controller.Authentication;
import controller.AuthenticationImpl;
import controller.BookControllerImpl;
import model.userModel.UserStatus;
import view.optionsView.AdminOptionPage;

import javax.swing.*;
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
            this.navigateToPage(HomePage.getInstance());
        }
        if (source.equals(this.getAuthenticationButton())){
            String password = new String(this.getPasswordField().getPassword());
            authentication.loginAsAdmin(this.getNameField().getText(), password);
            this.dispose();

            if (AuthenticationImpl.getLoggedUser() == null
                    || AuthenticationImpl.getLoggedUser().getUserStatus() != UserStatus.LOGGED) {
                JOptionPane.showMessageDialog(null, "Login failed. Please try again");
            }else if (AuthenticationImpl.getLoggedUser().getUserStatus() == UserStatus.LOGGED){
                JOptionPane.showMessageDialog(null, "Login successful");
                this.navigateToPage(new AdminOptionPage(new BookControllerImpl()));
            }
        }
    }
}
