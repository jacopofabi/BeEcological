package logic.view;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import logic.bean.UserBean;
import logic.controller.UserController;
import logic.utilities.PageLoader;
import javafx.application.Platform;
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


public class ShopView implements Initializable {
	
	@FXML private MenuItem userProfileItem; 
	@FXML private MenuItem logoutItem;
	@FXML private MenuButton userButton;
	@FXML private Button homeButton;
	@FXML private Button searchButton;
	@FXML private Button am05;
	@FXML private Button am10;
	@FXML private Button am25;
	@FXML private Button za05;
	@FXML private Button za10;
	@FXML private Button gp05;
	@FXML private Button gp10;
	@FXML private Button ap10;
	@FXML private Button rm10;
	@FXML private Button ms10;
	@FXML private Button fe10;
	@FXML private Button ps05;
	@FXML private Button xb05;
	@FXML private Button nx10;
	@FXML private Text ecoPoints;
	private long end;
	private Alert alert;
	private boolean isBuying = false;
	
	private UserController control;
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void showTimeoutAlert() {
		alert = new Alert(AlertType.ERROR);
		alert.setAlertType(AlertType.ERROR);
		alert.setTitle("Timeout expired");
		alert.setHeaderText(null);
		alert.setContentText("Buying timeout expired, retry!");		
		alert.show();
	}

	
	
	//------------------------------------------------------------------------------
	public class TimeoutThread extends Thread { //thread which control if the booking request is made within 2 minutes
		 
		@Override
		public void run(){
			while (System.currentTimeMillis() < end) {
				isBuying = false;
			}
			if (isBuying) {
			Platform.runLater(new Runnable(){ //javaFX thread to modify GUI. Useful to show alerts. a classic java thread can't do this.
			    public void run(){
			    	isBuying = false;
			    	alert.close();
			    	showTimeoutAlert();
			    	}
				});
			}
	    } 	
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
			pageLoader.homeConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout(ActionEvent event) {
		alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller = (HomepageView) pageLoader.getController();
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
				UserBean.setInstance(null);
				pageLoader.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoUserProfile(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.USER_PROFILE, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoShop(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.SHOP, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void buyItem(ActionEvent event) {
		Button clicked=(Button) event.getSource();
		String id = clicked.getId();
		switch(id) {
		case "am05":
		case "za05":
		case "gp05":
			updateEcoPoints(event,100);
			return;
		case "rm10":
		case "fe10":
			updateEcoPoints(event,180);
			return;
		case "am10":
		case "za10":
		case "gp10":
		case "ps05":
		case "xb05":
			updateEcoPoints(event,200);
			return;
		case "ms10":
			updateEcoPoints(event,210);
			return;
		case "nx10":
		case "ap10":
			updateEcoPoints(event,220);
			return;
		case "am25":
			updateEcoPoints(event,450);
			return;
		default:
			return;
		}
	}
	
	
	//------------------------------------------------------------------------------
	public void updateEcoPoints(ActionEvent event, int cost) {
		int oldEcoPoints = Integer.parseInt(ecoPoints.getText());
		alert = new Alert(AlertType.ERROR);
		if (oldEcoPoints-cost<0) {
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("You don't have enough ecoPoints to purchase the selected item");		
			alert.showAndWait();
			isBuying = false;
			return;
		}
		long start = System.currentTimeMillis();
		isBuying = true;
		end = start + 10*1000; // 60 seconds * 1000 ms/sec
		TimeoutThread timeout = new TimeoutThread();
		timeout.start();
		alert.setAlertType(AlertType.CONFIRMATION);
		alert.setTitle("Confirm");
		alert.setHeaderText("Do you want to purchase this item?");
		alert.setContentText("Current ecoPoints:     "+oldEcoPoints+"\nNew ecoPoints:          "+Integer.toString(oldEcoPoints-cost));
	
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			int newEcoPoints = oldEcoPoints - cost;
			UserBean.getInstance().setEcopoints(newEcoPoints);
			control = new UserController();
			control.updateEcoPoints(UserBean.getUserInstance(""));
			isBuying = false;
			gotoShop(event);
		}	
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		control = new UserController();
		List<String> userInfo = control.userInformation(UserBean.getInstance());
		userButton.setText(UserBean.getInstance().getUsbUsername());
		ecoPoints.setText(userInfo.get(4));
	}
}