package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.bean.UserBean;
import logic.utilities.PageLoader;
import logic.utilities.Tool;



public class HomepageView implements Initializable {
	
	ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML private Label circleOwnerText4;
	@FXML private ComboBox<String> hourBooking4;
	@FXML private MenuItem userProfileItem4;
	@FXML private MenuItem logoutItem4;
	@FXML public MenuButton userButton4;
	@FXML private Button homeButton4; 
	@FXML private Button loginButton4; 
	@FXML private Button searchButton4;
	@FXML private Button shopButton4;
	@FXML private Button circleOwnerButton4;
	@FXML private Button circleUserButton4;
	@FXML public Group loginGroup4;
	@FXML public Group userGroup4;
	@FXML public Group circleOwnerGroup4;
	@FXML public Group circleUserGroup4;
	@FXML private TextField searchBar4;
	@FXML public Text welcomebackText4;
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage4(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
			pageLoader.homeConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doSearch4(ActionEvent event) {
		Tool.setString(searchBar4.getText());
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.SEARCH_RESULT, event);
			pageLoader.searchConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void toUserLogin4(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN_USER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void toOwnerLogin4(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoShop4(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.SHOP, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoUserProfile4(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.USER_PROFILE, userButton4);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoRequest4(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.USER_BOOKING_LIST, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout4(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, userButton4);
				HomepageView controller = (HomepageView) pageLoader.getController();
				controller.userGroup4.setVisible(false);
				controller.loginGroup4.setVisible(true);
				UserBean.setInstance(null);
				pageLoader.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	

	//------------------------------------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton4.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		searchBar4.setFont(Font.font("Calibri Light", FontWeight.NORMAL, 15));
		userGroup4.setVisible(false);
		circleUserGroup4.setVisible(false);
	}
	
}
