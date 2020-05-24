package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.AlreadyUsedUsernameException;
import error.EmptyFieldException;
import error.InexistentUsernameException;
import error.InvalidEmailException;
import error.ShortPasswordException;
import logic.bean.UserBean;
import logic.controller.UserController;
import logic.utilities.PageLoader;
import logic.utilities.Tool;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;


public class LoginUserView implements Initializable {

	@FXML private Button toOwnerLoginButton;
	@FXML private Button homeButton;
	@FXML private Button signInButton;
	@FXML private Button loginButton;
	@FXML private TextField textName6;
	@FXML private TextField textSurname6;
	@FXML private TextField textEmailAddress6;
	@FXML private TextField textPhoneNumber6;
	@FXML private TextField textUsername6;
	@FXML private TextField textPassword6;
	@FXML private TextField textConfirmPassword6;
	@FXML private TextField loginUsername6;
	@FXML private TextField loginPassword6;
	
    private UserController control;
    private UserBean user;
	
    
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage6(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
			pageLoader.homeConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void toOwnerLogin6(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	

	//------------------------------------------------------------------------------
	@FXML
	public void loginUser6(ActionEvent event) throws InexistentUsernameException, EmptyFieldException {
		boolean ok;
		String username = loginUsername6.getText();
		String password = loginPassword6.getText();
		
		user = UserBean.getUserInstance(username);
		user.setUsbPassword(password);
		
		control = new UserController();
		ok = control.login(user);
		Alert alert6 = new Alert(AlertType.ERROR);
		if (!ok) {
			UserBean.setInstance(null);
			alert6.setTitle("Login failed");
			alert6.setHeaderText(null);
			alert6.setContentText("User not registered: incorrect username or password.\nRetry or register!");
			alert6.showAndWait();
			return;
		}
		alertConfig("Welcome Back!", "User verified, login completed.", event);
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void registerUser6(ActionEvent event) throws EmptyFieldException, ShortPasswordException, InvalidEmailException, AlreadyUsedUsernameException {
		boolean ok = true;
		String name = textName6.getText();
		String surname = textSurname6.getText();
		String emailAddress = textEmailAddress6.getText();
		String phoneNumber = textPhoneNumber6.getText();
		String username = textUsername6.getText();
		String password = textPassword6.getText();
		String confirmPassword = textConfirmPassword6.getText();
		
		user = new UserBean();
		user.setUsbUsername(username);
		control = new UserController();
		
		ok = control.checkRegistration(user);
		Alert alert6 = new Alert(AlertType.ERROR);
		if (name.isEmpty() || surname.isEmpty()) {
			alert6.setTitle("Registration Failed - Invalid name");
			alert6.setHeaderText(null);
			alert6.setContentText("Name and surname cannot be empty.");
			alert6.showAndWait();
			return;
		}
		
		if (!emailAddress.contains("@gmail.com") && !emailAddress.contains("@yahoo.com") &&
			!emailAddress.contains("@libero.it") && !emailAddress.contains("@hotmail.it") &&
			!emailAddress.contains("@outlook.com") && !emailAddress.contains("@mail.com") &&
			!emailAddress.contains("@virgilio.it") && !emailAddress.contains("@email.it"))	
		{
			alert6.setTitle("Registration Failed - Invalid email");
			alert6.setHeaderText(null);
			alert6.setContentText("Insert a valid email address.");
			alert6.showAndWait();
			return;
		}
		
		if (username.isEmpty()) {
			alert6.setTitle("Registration Failed - Missing username");
			alert6.setHeaderText(null);
			alert6.setContentText("Insert an username.");
			alert6.showAndWait();
			return;
		}
		
		if (!ok) {
			alert6.setTitle("Registration Failed - Username already existing");
			alert6.setHeaderText(null);
			alert6.setContentText("Choose another username available.");
			alert6.showAndWait();
			return;
		}
		
		if (phoneNumber.isEmpty() || phoneNumber.matches("[0-9]+") == Tool.isFalse()) {
			alert6.setTitle("Registration Failed - Invalid phone number");
			alert6.setHeaderText(null);
			alert6.setContentText("Insert a valid phone number.");
			alert6.showAndWait();
			return;
		}
		
		if (password.isEmpty() || (password.length() < 8)) {
			alert6.setTitle("Registration Failed - Password too weak");
			alert6.setHeaderText(null);
			alert6.setContentText("Choose a password with at least 8 characters.");
			alert6.showAndWait();
			return;
		}
		
		if (!password.equalsIgnoreCase(confirmPassword)) {
			alert6.setTitle("Registration Failed - Wrong password confirmation");
			alert6.setHeaderText(null);
			alert6.setContentText("The two passwords you entered not match, try again.");
			alert6.showAndWait();
			return;
		}
		user.setUsbName(name);
		user.setUsbSurname(surname);
		user.setUsbEmail(emailAddress);
		user.setUsbPhone(phoneNumber);
		user.setUsbPassword(password);
		user.setEcopoints(0);
		control.saveRegistration(user);
		alertConfig("Welcome to BeEcological!", "Registration completed, you will be redirected to the homepage.", event);
	}
	
	
	//------------------------------------------------------------------------------
	public void alertConfig(String title, String contentText, Event event) {
		Alert alert6 = new Alert(AlertType.INFORMATION);
		try {
			alert6.setTitle(title);
			alert6.setHeaderText(null);
			alert6.setContentText(contentText);
			alert6.showAndWait();
		    PageLoader pageLoader6 = new PageLoader(PageLoader.Page.HOMEPAGE, event);
		    pageLoader6.homeConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginUsername6.requestFocus();
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
	}
	
}
