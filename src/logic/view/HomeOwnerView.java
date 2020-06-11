package logic.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import logic.bean.CenterOwnerBean;
import logic.controller.AccountInformationController;
import logic.utilities.PageLoader;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class HomeOwnerView implements Initializable {
	
	@FXML private Button homeButton;
	@FXML private MenuButton ownerButton;
	@FXML private MenuItem ownerProfileItem; 
	@FXML private MenuItem logoutItem;
	@FXML private Button registerUnloadButton;
	@FXML private Button manageBookingButton;
	@FXML private Button bookingANDUnloadsButton;
	@FXML private Button manageInformationButton;
	@FXML private Button changePhotoButton;
	@FXML private Text centerName;
	@FXML private Text centerAddress; 
	@FXML private Text ownerMail;
	@FXML private Text ownerPhone;
	@FXML private Text centerPhone;
	@FXML private ImageView centerImageView;
	
	@FXML private CenterOwnerBean owner;
	@FXML private AccountInformationController control;

	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage3(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	

	//------------------------------------------------------------------------------
	@FXML
	public void registerUnload3(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.REGISTER_UNLOAD, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void manageBooking3(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.MANAGE_BOOKING, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}

	
	//------------------------------------------------------------------------------
	@FXML
	public void manageInformation3(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Manage Information");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void bookingANDUnloads3(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.BOOKING_AND_UNLOADS, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoOwnerProfile3(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.OWNER_PROFILE, ownerButton);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout3(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, ownerButton);
				HomepageView controller = (HomepageView) pageLoader.getController();
				controller.userGroup4.setVisible(false);
				controller.loginGroup4.setVisible(true);
				CenterOwnerBean.setInstance(null);
				pageLoader.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void changePhoto3(ActionEvent event) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(//
			       new FileChooser.ExtensionFilter("JPG", "*.jpg"), 
			       new FileChooser.ExtensionFilter("PNG", "*.png"),
		new FileChooser.ExtensionFilter("All Files", "*.*"));
		
		fileChooser.setTitle("Choose a photo for your center");
		Stage window = (Stage) changePhotoButton.getScene().getWindow();
		File source = fileChooser.showOpenDialog(window);
		String destFileName = CenterOwnerBean.getOwnerInstance("").getCobCenter()+".jpg";
		File dest = new File ("src/jpeg/"+destFileName);
		try {
		    FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
		returnHomepage3(event);
	}
		

	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		registerUnloadButton.setTooltip(new Tooltip("Register the data of an unload, inserting, date, time,\n type and quantity of waste."));
		manageBookingButton.setTooltip((new Tooltip("Add a new unload booking, or confirm\nan existing booking request")));
		bookingANDUnloadsButton.setTooltip((new Tooltip("See a list of all the accepted booking request\nand all the registered unloads.")));
		manageInformationButton.setTooltip(new Tooltip("Edit or update your center information"));
		
		owner = new CenterOwnerBean();
		control = new AccountInformationController();
		owner.setCobUsername(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		ownerButton.setText(owner.getCobUsername());
		
		List<String> ownerInfo = control.ownerInformation(owner);
		centerName.setText(ownerInfo.get(4));
		centerAddress.setText(ownerInfo.get(7)+" "+ownerInfo.get(9));
		ownerMail.setText(ownerInfo.get(2));
		ownerPhone.setText(ownerInfo.get(3));
		centerPhone.setText(ownerInfo.get(5));
		
		String filename = ownerInfo.get(4);
		CenterOwnerBean.getOwnerInstance("").setCobCenter(filename);
		File sourcePhoto = new File("src/res/jpeg/"+filename+".jpg");
		if(sourcePhoto.exists() && !sourcePhoto.isDirectory()) { 
			Image image = new Image(sourcePhoto.toURI().toString());
	        centerImageView.setImage(image);
		}
		else {
			File defaultFile = new File("src/jpeg/default.jpg");
			Image image2 = new Image(defaultFile.toURI().toString());
	        centerImageView.setImage(image2);
		}
	}
	
}
