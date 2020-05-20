package logic.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.stage.Stage;
import logic.bean.UserBean;



public class HomepageView implements Initializable {
	
	ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML private Label circleOwnerText;
	@FXML private ComboBox<String> hourBooking;
	@FXML private MenuItem userProfileItem;
	@FXML private MenuItem logoutItem;
	@FXML public MenuButton userButton;
	@FXML private Button homeButton; 
	@FXML private Button loginButton; 
	@FXML private Button searchButton;
	@FXML private Button shopButton;
	@FXML private Button circleOwnerButton;
	@FXML private Button circleUserButton;
	@FXML public Group loginGroup;
	@FXML public Group userGroup;
	@FXML public Group circleOwnerGroup;
	@FXML public Group circleUserGroup;
	@FXML private TextField searchBar;
	@FXML public Text welcomebackText;
	
	
	//------------------------------------------------------------------------------
	public void returnHomepage(ActionEvent event) {
		try {
		    Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/res/fxml/Homepage.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("Homepage");
			
			HomepageView controller = (HomepageView) loader.getController();
			if(UserBean.getInstance() != null) {
				controller.loginGroup.setVisible(false);
				controller.userGroup.setVisible(true);
				controller.circleUserGroup.setVisible(true);
				controller.circleOwnerGroup.setVisible(false);
				controller.userButton.setText(UserBean.getUserInstance("").getUsbUsername());
				controller.welcomebackText.setText("Welcome back, "+UserBean.getUserInstance("").getUsbUsername());
			}
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------------------------------------
	public void gotoRequest(ActionEvent event) throws IOException {
		URL url = new File("src/res/fxml/UserBookingList.fxml").toURI().toURL();
		Parent tableViewParent = FXMLLoader.load(url);
		Scene tableViewScene = new Scene(tableViewParent);
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		window.setScene(tableViewScene);
		window.setTitle("BeEcological - User list");
		window.show();
	}
	
	
	//------------------------------------------------------------------------------
	public void doSearch(ActionEvent event) {
		Tool.string = searchBar.getText();
		try {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/res/fxml/SearchResult.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Search Result");
			
			SearchResultView controller = (SearchResultView) loader.getController();
			if(UserBean.getInstance() != null) {
				controller.loginGroup.setVisible(false);
				controller.userGroup.setVisible(true);
				controller.userButton.setText(UserBean.getInstance().getUsbUsername());
			}
			else {
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
			}
			controller.textSearched.setText(Tool.string); //setta il testo del risultato come quello cercato
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------------------------------------
	public void toUserLogin(ActionEvent event) {
		try {
			URL url = new File("src/res/fxml/LoginUser.fxml").toURI().toURL();
			Parent tableViewParent = FXMLLoader.load(url);
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.setTitle("Login");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------------------------------------
	public void gotoUserProfile(ActionEvent event) {
		try {
		    URL url = new File("src/res/fxml/UserProfile.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Profile");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------------------------------------
	public void doLogout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
			try {
			    URL url = new File("src/res/fxml/Homepage.fxml").toURI().toURL();
			    FXMLLoader loader = new FXMLLoader(url);
				Parent tableViewParent = loader.load();
				Scene tableViewScene = new Scene(tableViewParent);
				Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
				window.setScene(tableViewScene);
				window.setTitle("Homepage");
				HomepageView controller = (HomepageView) loader.getController();
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
				UserBean.setInstance(null);
				window.show();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else {
		    //do nothing
		}	
	}

	
	//------------------------------------------------------------------------------
	public void gotoShop(ActionEvent event) {
		try {
		    URL url = new File("src/res/fxml/Shop.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Shop");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------------------------------------
	public void gotoOwnerLogin(ActionEvent event) {
		try {
			URL url = new File("src/res/fxml/LoginOwner.fxml").toURI().toURL();
			Parent tableViewParent = FXMLLoader.load(url);
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.setTitle("Login");
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	

	//------------------------------------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		searchBar.setFont(Font.font("Calibri Light", FontWeight.NORMAL, 15));
		userGroup.setVisible(false);
		circleUserGroup.setVisible(false);
	}
	
}
