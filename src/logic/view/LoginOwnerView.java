package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.CenterOwnerBean;
import logic.controller.LoginController;
import logic.utilities.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;


public class LoginOwnerView implements Initializable {
	
	@FXML private Button toUserLoginButton;
	@FXML private Button homeButton;
	@FXML private Button toHomeOwnerButton;
	@FXML private Button signInButton;
	@FXML private TextField loginUsername;
	@FXML private TextField textName1;
	@FXML private TextField textSurname1;
	@FXML private TextField textEmailAddress1;
	@FXML private TextField textPhoneNumber1;
	@FXML private TextField textUsername1;
	@FXML private TextField textPassword1;
	@FXML private TextField textConfirmPassword1;
	@FXML private TextField textCenterName1;
	@FXML private TextField textCity1;
	@FXML private TextField textAddress1;
	@FXML private TextField textN1;
	@FXML private TextField textCAP1;
	@FXML private TextField textCenterPhone1;
	@FXML private PasswordField loginPassword;
	
	@FXML private CenterOwnerBean owner;
	@FXML private LoginController control;
		
	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage5(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	//------------------------------------------------------------------------------
	@FXML
	public void toUserLogin5(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN_USER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void signIn5(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Registration");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented. The registration must have a confirmation email, send by the"
				+ "administrator.");
		alert.showAndWait();
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void loginOwner5(ActionEvent event) {
		boolean ok;
		String username = loginUsername.getText();
		String password = loginPassword.getText();
		
		owner = CenterOwnerBean.getOwnerInstance(username);
		owner.setCobPassword(password);
		
		control = new LoginController();
		ok = control.loginOwner(owner);
		Alert alert = new Alert(AlertType.ERROR);
		if (!ok) {
			CenterOwnerBean.setInstance(null);
			alert.setTitle("Login failed");
			alert.setHeaderText(null);
			alert.setContentText("Owner not registered: incorrect username or password.\nRetry or register!");
			alert.showAndWait();
			return;
		}

		try {
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("Welcome Back!");
			alert.setHeaderText(null);
			alert.setContentText("Owner verified, login completed.");
			alert.showAndWait();
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
	}
	
}
