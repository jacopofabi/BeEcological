package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;


public class OwnerProfileView implements Initializable {
	
	@FXML private MenuItem ownerProfileItem; 
	@FXML private MenuItem logoutItem;
	@FXML private MenuButton ownerButton;
	@FXML private Button homeButton;
	@FXML private Button searchButton;
	@FXML private Button editDataButton;
	@FXML private Button changeCredButton;
	@FXML private Button deleteAccountButton;
	@FXML private Text userNick;
	@FXML private Text ecoPoints;
	@FXML private Text name;
	@FXML private Text surname; 
	@FXML private Text email;
	@FXML private Text phoneNumber;
	
	private CenterOwnerBean owner;
	private OwnerController control;
	
	
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
	public void editData(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Edit Personal Data");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
		return;
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void changeCred(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Change Login Credentials");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
		return;
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void deleteAccount(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Account");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete your account?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			try {
				control = new OwnerController();
				
				PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller = (HomepageView) pageLoader.getController();
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
				
				control.deleteAccount(CenterOwnerBean.getInstance());
				CenterOwnerBean.setInstance(null);
				pageLoader.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
			try {
				PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller = (HomepageView) pageLoader.getController();
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
				CenterOwnerBean.setInstance(null);
				pageLoader.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoOwnerProfile(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.OWNER_PROFILE, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		owner = new CenterOwnerBean();
		control = new OwnerController();
		owner.setCobUsername(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		ownerButton.setText(owner.getCobUsername());
		userNick.setText(owner.getCobUsername());
		
		List<String> ownerInfo = control.ownerData(owner);

		name.setText(ownerInfo.get(0));
		surname.setText(ownerInfo.get(1));
		email.setText(ownerInfo.get(2));
		phoneNumber.setText(ownerInfo.get(3));
	}
}
