package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.CenterOwnerBean;
import logic.controller.OwnerController;
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
	@FXML private TextField textName;
	@FXML private TextField textSurname;
	@FXML private TextField textEmailAddress;
	@FXML private TextField textPhoneNumber;
	@FXML private TextField textUsername;
	@FXML private TextField textPassword;
	@FXML private TextField textConfirmPassword;
	@FXML private TextField textCenterName;
	@FXML private TextField textCity;
	@FXML private TextField textAddress;
	@FXML private TextField textN;
	@FXML private TextField textCAP;
	@FXML private TextField textCenterPhone;
	@FXML private PasswordField loginPassword;
	
	@FXML private CenterOwnerBean owner;
	@FXML private OwnerController control;
		
	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	//------------------------------------------------------------------------------
	@FXML
	public void toUserLogin(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN_USER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void signIn(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Registration");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented. The registration must have a confirmation email, send by the"
				+ "administrator.");
		alert.showAndWait();
		return;
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void loginOwner(ActionEvent event) {
		boolean ok;
		String username = loginUsername.getText();
		String password = loginPassword.getText();
		
		owner = CenterOwnerBean.getOwnerInstance(username);
		owner.setCobPassword(password);
		
		control = new OwnerController();
		ok = control.login(owner);
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
