package logic.view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import error.EmptyFieldException;
import error.InexistentUsernameException;
import logic.bean.BookingBean;
import logic.bean.CenterBean;
import logic.bean.CenterOwnerBean;
import logic.bean.UserBean;
import logic.controller.BookingController;
import logic.controller.UserController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
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
import javafx.util.Pair;
import logic.utilities.PageLoader;
import logic.utilities.Tool;



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
    private long end;
    boolean isBooking;
    
    
    //------------------------------------------------------------------------------
	public void loadData2() {
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
	public void promptLogin2() {
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
	@FXML
	public void returnHomepage2(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
			pageLoader.homeConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doSearch2(ActionEvent event) {
		Tool.setString(searchBar.getText());
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.SEARCH_RESULT, event);
			pageLoader.searchConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}

	
	//------------------------------------------------------------------------------
	@FXML
	public void toUserLogin2(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN_USER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoShop2(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.SHOP, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoUserProfile2(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.USER_PROFILE, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout2(ActionEvent event) {
		Alert alert2 = new Alert(AlertType.CONFIRMATION);
		alert2.setTitle("Logout");
		alert2.setHeaderText(null);
		alert2.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert2.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				PageLoader pageLoader2 = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller2 = (HomepageView) pageLoader2.getController();
				controller2.userGroup4.setVisible(false);
				controller2.loginGroup4.setVisible(true);
				CenterOwnerBean.setInstance(null);
				pageLoader2.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
	
	//------------------------------------------------------------------------------
	public void openBookingRequest2() {
		long start = System.currentTimeMillis();
		isBooking = true;
		promptBox.setVisible(true);
		end = start + 60*1000; // 60 seconds * 1000 ms/sec
		TimeoutThread2 timeout = new TimeoutThread2();
		timeout.start();
	}
	
	
	//------------------------------------------------------------------------------
	public void showTimeoutAlert2() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setAlertType(AlertType.ERROR);
		alert.setTitle("Timeout expired");
		alert.setHeaderText(null);
		alert.setContentText("Booking timeout expired, retry!");		
		alert.show();
	}
	
	
	
	//------------------------------------------------------------------------------
	 public class TimeoutThread2 extends Thread { //thread which control if the booking request is made within 2 minutes
		 
		 	@Override
		    public void run() {
				while (System.currentTimeMillis() < end) {
					//nothing
				}
				if (isBooking) {
				Platform.runLater(new Runnable() { //javaFX thread to modify GUI. Useful to show alerts. a classic java thread can't do this.
				    public void run(){
						showTimeoutAlert2();
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
	public void closeBookingRequest2() {
		isBooking = false;
		promptBox.setVisible(false);
	}

	
	//------------------------------------------------------------------------------
	public void makeBookingRequest2() throws IOException{
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
			closeBookingRequest2();
		}
		else {
			alert.setAlertType(AlertType.CONFIRMATION);
			alert.setTitle("Login to continue");
			((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText(login);
			alert.setHeaderText(null);
			alert.setContentText("To book an unload you must be registered to BeEcological.\nLogin to continue.");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK){
				promptLogin2();
			}
		}
		
				
	}
	
	
	//------------------------------------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadData2();
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
