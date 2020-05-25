package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.CenterOwnerBean;
import logic.controller.AccountInformationController;
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
	@FXML private MenuButton ownerButton8;
	@FXML private Button homeButton8;
	@FXML private Button searchButton8;
	@FXML private Button editDataButton8;
	@FXML private Button changeCredButton8;
	@FXML private Button deleteAccountButton8;
	@FXML private Text userNick8;
	@FXML private Text ecoPoints8;
	@FXML private Text name8;
	@FXML private Text surname8; 
	@FXML private Text email8;
	@FXML private Text phoneNumber8;
	
	private AccountInformationController control;
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage8(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void editData8(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Edit Personal Data");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void changeCred8(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Change Login Credentials");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void deleteAccount8(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Account");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete your account?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			try {
				control = new AccountInformationController();
				
				PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller = (HomepageView) pageLoader.getController();
				controller.userGroup4.setVisible(false);
				controller.loginGroup4.setVisible(true);
				
				control.deleteOwner(CenterOwnerBean.getInstance());
				CenterOwnerBean.setInstance(null);
				pageLoader.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout8(ActionEvent event) {
		Alert alert8 = new Alert(AlertType.CONFIRMATION);
		alert8.setTitle("Logout");
		alert8.setHeaderText(null);
		alert8.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert8.showAndWait();

		if (result.get() == ButtonType.OK){
			try {
				PageLoader pageLoader8 = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller8 = (HomepageView) pageLoader8.getController();
				controller8.userGroup4.setVisible(false);
				controller8.loginGroup4.setVisible(true);
				CenterOwnerBean.setInstance(null);
				pageLoader8.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
		
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoOwnerProfile8(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.OWNER_PROFILE, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton8.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		CenterOwnerBean owner = new CenterOwnerBean();
		control = new AccountInformationController();
		owner.setCobUsername(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		ownerButton8.setText(owner.getCobUsername());
		userNick8.setText(owner.getCobUsername());
		
		List<String> ownerInfo = control.ownerInformation(owner);

		name8.setText(ownerInfo.get(0));
		surname8.setText(ownerInfo.get(1));
		email8.setText(ownerInfo.get(2));
		phoneNumber8.setText(ownerInfo.get(3));
	}
}
