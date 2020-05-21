package logic.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;

import error.EmptyFieldException;
import error.InexistentUsernameException;
import logic.bean.BookingBean;
import logic.bean.CenterBean;
import logic.bean.UserBean;
import logic.controller.BookingController;
import logic.controller.UserController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Pair;

import logic.utilities.Tool;



@SuppressWarnings("unused")
public class CenterPageView implements Initializable {
	
	ObservableList<String> data = FXCollections.observableArrayList();
	ObservableList<CenterBean> center = FXCollections.observableArrayList();
	ObservableList<String> list = FXCollections.observableArrayList();
	
	String login = "Login";
	
	@FXML private ComboBox<String> hourBooking;
	@FXML private DatePicker datePicker;
	@FXML private MenuItem userProfileItem; 
	@FXML private MenuItem logoutItem;
	@FXML public MenuButton userButton;
	@FXML private AnchorPane promptBox;
	@FXML private Button homeButton;
	@FXML private Button loginButton; 
	@FXML private Button searchButton; 
	@FXML private Button shopButton; 
	@FXML private Button confirmButton; 
	@FXML private Button bookingButton;
	@FXML public Group loginGroup;
	@FXML public Group userGroup;
	@FXML private TextField searchBar;
	@FXML public Text textSearched;
	@FXML public Text centerSearched;
	@FXML public Text infoSearched; 
	@FXML public Text emailSearched; 
	@FXML public Text centerPhoneSearched;
	@FXML public Text ownerPhoneSearched;
	
    @FXML private TableView<CenterBean> tableView;
    @FXML private TableColumn<CenterBean, String> centerName;
    @FXML private TableColumn<CenterBean, String> city;
    @FXML private TableColumn<CenterBean, String> address;
    
    @FXML private ImageView centerImageView;
    
    private UserBean user;
    private UserController control;
    public long start;
    public long end;
    boolean isBooking;
    
    
    //------------------------------------------------------------------------------
	public void loadData() {
		list.removeAll(list);
		String a = "08:00";
		String b = "08:30";
		String c = "09:00";
		String d = "09:30";
		String e = "10:00";
		String f = "10:30";
		String g = "11:00";
		String h = "11:30";
		String i = "12:00";
		String j = "12:30";
		String k = "14:00";
		String l = "14:30";
		String m = "15:00";
		String n = "15:30";
		String o = "16:00";
		String p = "16:30";
		String q = "17:00";
		String r = "17:30";
		String s = "18:00";
		String t = "18:30";
		String u = "19:00";
		String v = "19:30";
		list.addAll(a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v);
		hourBooking.getItems().addAll(list);
	}
	
	
	//------------------------------------------------------------------------------
	public void promptLogin() {
		// Create the custom dialog.
		Dialog<Pair<String, String>> dialog = new Dialog<>();
		dialog.setTitle("Login to continue");
		dialog.setHeaderText(null);

		// Set the button types.
		ButtonType loginButtonType = new ButtonType(login, ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));

		TextField username = new TextField();
		username.setPromptText("Username");
		PasswordField password = new PasswordField();
		password.setPromptText("Password");

		grid.add(new Label("Username:"), 0, 0);
		grid.add(username, 1, 0);
		grid.add(new Label("Password:"), 0, 1);
		grid.add(password, 1, 1);

		// Enable/Disable login button depending on whether a username was entered.
		Node logButton = dialog.getDialogPane().lookupButton(loginButtonType);
		logButton.setDisable(true);

		// Do some validation (using the Java 8 lambda syntax).
		username.textProperty().addListener((observable, oldValue, newValue) -> logButton.setDisable(newValue.trim().isEmpty()));

		dialog.getDialogPane().setContent(grid);

		// Convert the result to a username-password-pair when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		        return new Pair<>(username.getText(), password.getText());
		    }
		    return null;
		});

		Optional<Pair<String, String>> result = dialog.showAndWait();
		result.ifPresent(usernamePassword -> {
			String usr = usernamePassword.getKey();
		    String psw = usernamePassword.getValue();
		    
		    boolean ok = true;
			user = new UserBean();
			user.setUsbUsername(UserBean.getUserInstance(usr).getUsbUsername());
			user.setUsbPassword(psw);
			control = new UserController();
			try {
				ok = control.login(user);
			} catch (InexistentUsernameException | EmptyFieldException e) {
				e.printStackTrace();
			}
			Alert alert1 = new Alert(AlertType.ERROR);
			if (!ok) {
				UserBean.setInstance(null);
				alert1.setTitle("Login failed");
				alert1.setHeaderText(null);
				alert1.setContentText("User not registered: incorrect username or password.\nRetry or register!");
				alert1.showAndWait();
				return;
			}
			try {
				alert1.setAlertType(AlertType.INFORMATION);
				alert1.setTitle("Welcome Back!");
				alert1.setHeaderText("User verified, login completed");
				alert1.setContentText("Now you can confirm your booking request for the center '"+centerSearched.getText()+"'.");
				alert1.showAndWait();
				loginGroup.setVisible(false);
				userGroup.setVisible(true);
				userButton.setText(UserBean.getUserInstance(usr).getUsbUsername());
			}catch(Exception e){
				e.printStackTrace();
			}
		});

	}
	
	
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
			if (UserBean.getInstance() != null) {
				controller.loginGroup.setVisible(false);
				controller.userGroup.setVisible(true);
				controller.userButton.setText(UserBean.getUserInstance("").getUsbUsername());
				controller.circleUserGroup.setVisible(true);
				controller.circleOwnerGroup.setVisible(false);
				controller.welcomebackText.setText("Welcome back, "+UserBean.getUserInstance("").getUsbUsername());
			}
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
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
				controller.userButton.setText(UserBean.getUserInstance("").getUsbUsername());
			}
			else {
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
			}
			controller.textSearched.setText(searchBar.getText()); //setta il testo del risultato come quello cercato
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
			window.setTitle(login);
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------------------------------------
	/*public void gotoUserProfile(ActionEvent event) {
		try {
			//ricavo lo stage dal menuButton, il menuItem non e' una sottoclasse di Node
			//Stage window = (Stage) userButton.getScene().getWindow();
		    URL url = new File("src/res/fxml/UserProfile.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Profile");
			UserProfileView controller = (UserProfileView) loader.getController();
			controller.userButton.setText(UserBean.getUserInstance("").getUsbUsername());
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	
	//------------------------------------------------------------------------------
	public void doLogout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		alert.showAndWait();
		
		try {
			//Stage window = (Stage) userButton.getScene().getWindow();
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
			URL url = new File("src/res/fxml/Homepage.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("Homepage");
			UserBean.setInstance(null);
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	//------------------------------------------------------------------------------
	public void gotoShop(ActionEvent event) throws IOException {
		//ricavo lo stage dal menuButton, il menuItem non e' una sottoclasse di Node
		//Stage window = (Stage) userButton.getScene().getWindow();
		Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		URL url = new File("src/res/fxml/Shop.fxml").toURI().toURL();
	    FXMLLoader loader = new FXMLLoader(url);
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		window.setScene(tableViewScene);
		window.setTitle("BeEcological - Shop");
		window.show();
	}
	
	
	//------------------------------------------------------------------------------
	public void openBookingRequest() {
		isBooking = true;
		promptBox.setVisible(true);
		start = System.currentTimeMillis();
		end = start + 60*1000; // 60 seconds * 1000 ms/sec
		TimeoutThread timeout = new TimeoutThread();
		timeout.start();
	}
	
	
	//------------------------------------------------------------------------------
	public void showTimeoutAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setAlertType(AlertType.ERROR);
		alert.setTitle("Timeout expired");
		alert.setHeaderText(null);
		alert.setContentText("Booking timeout expired, retry!");		
		alert.show();
	}
	
	
	
	//------------------------------------------------------------------------------
	 public class TimeoutThread extends Thread { //thread which control if the booking request is made within 2 minutes
		 
		 	@Override
		    public void run() {
				while (System.currentTimeMillis() < end) {
					//nothing
				}
				if (isBooking) {
				Platform.runLater(new Runnable() { //javaFX thread to modify GUI. Useful to show alerts. a classic java thread can't do this.
				    public void run(){
						showTimeoutAlert();
						datePicker.setValue(null);
						datePicker.getEditor().clear();
						hourBooking.getSelectionModel().clearSelection();
						hourBooking.getEditor().clear();
						hourBooking.setValue(null);
						promptBox.setVisible(false);
				    	}
					});
				}
		    } 	
	 }
	
	 
	//------------------------------------------------------------------------------ 
	public void closeBookingRequest() {
		isBooking = false;
		promptBox.setVisible(false);
	}

	
	//------------------------------------------------------------------------------
	public void makeBookingRequest() throws IOException{
		Alert alert = new Alert(AlertType.ERROR);
		if (datePicker.getValue() == null || hourBooking.getValue() == null) {
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle("Invalid booking request.");
			alert.setHeaderText(null);
			alert.setContentText("Insert date and time correctly.");		
			alert.showAndWait();
			return;
		}
		String date = datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		String time = hourBooking.getValue();
		
		if (!Tool.checkDate(date)) {
			alert.setAlertType(AlertType.ERROR);
			alert.setTitle("Invalid booking request.");
			alert.setHeaderText(null);
			alert.setContentText("You cannot book for a day in the past. Choose a correct date.");		
			alert.showAndWait();
			return;
		}
		
		
		if (UserBean.getInstance() != null) {
			BookingBean booking = new BookingBean();
			booking.setBbUser(UserBean.getInstance().getUsbUsername());
			booking.setBbCenter(centerSearched.getText());
			booking.setBbDate(date);
			booking.setBbTime(time);
			booking.setBbStatus("W");
			
			BookingController control1 = new BookingController();
			try {
				control1.insertBooking(booking);
			} catch (InexistentUsernameException e) {
				e.printStackTrace();
			}
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("Booking request completed.");
			alert.setHeaderText(null);
			alert.setContentText("Your booking request for '"+centerSearched.getText()+"' has been completed successfully, the center owner will check it shortly.\n\nYou can check the status of your request through\n         'My Profile> See Booking Request'.");		
			alert.show();
			isBooking = false;
			closeBookingRequest();
		}
		else {
			alert.setAlertType(AlertType.CONFIRMATION);
			alert.setTitle("Login to continue");
			((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(login);
			alert.setHeaderText(null);
			alert.setContentText("To book an unload you must be registered to BeEcological.\nLogin to continue.");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK){
				promptLogin();
			}
		}
		
				
	}
	
	
	//------------------------------------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData();
		isBooking = false;
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		searchBar.setFont(Font.font("Calibri Light", FontWeight.NORMAL, 15));
		userGroup.setVisible(false);
		String filename = Tool.getCenterName();
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
