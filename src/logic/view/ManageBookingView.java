package logic.view;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.javafx.scene.control.skin.TableHeaderRow;

import error.InexistentUsernameException;
import logic.bean.BookingBean;
import logic.bean.CenterOwnerBean;
import logic.bean.UserBean;
import logic.controller.ManageBookingController;
import logic.controller.AccountInformationController;
import logic.controller.LoginController;
import logic.controller.MakeBookingController;
import logic.utilities.PageLoader;
import logic.utilities.Tool;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;


public class ManageBookingView implements Initializable {
	
	List<BookingBean> data = new ArrayList<>();
	ObservableList<BookingBean> bookingList7 = FXCollections.observableArrayList();
	String invalidBooking = "Invalid booking request.";
	String okBooking = "Booking request completed.";
	String insertSuccess = "' has been completed successfully.\n\nYou can check the list of booking accepted through\n         'Homepage> History & Unloads'.";
	String bookInsert = "Booking insert for '";
	String errorData = "Error on Building Data";
	
	@FXML private Button homeButton;
	@FXML private Button bookingButton;
	@FXML private MenuButton ownerButton;
	@FXML private MenuItem ownerProfileItem; 
	@FXML private MenuItem logoutItem;
	@FXML private DatePicker dateToBook;
	@FXML private TextField userToBook;
	@FXML private TextField hourToBook;
	@FXML private TableView<BookingBean> tableBookingRequest;
	@FXML private TableColumn<BookingBean, String> colUser;
	@FXML private TableColumn<BookingBean, String> 	colDate;
	@FXML private TableColumn<BookingBean, String> colTime;
	
	@FXML private CenterOwnerBean owner;
	@FXML private UserBean user;
	@FXML private BookingBean booking;
	@FXML private AccountInformationController control;
	@FXML private LoginController control1;
	@FXML private ManageBookingController control2;

	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage7(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void saveBookingRequest7(ActionEvent event) {
		boolean res = true;
		booking = new BookingBean();
		user = new UserBean();
		owner = new CenterOwnerBean();
		
		control = new AccountInformationController();
		
		owner.setCobUsername(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		user.setUsbUsername(userToBook.getText());
		List<String> ownerData = control.ownerInformation(owner);
		String center = ownerData.get(4);
		LocalDate date = dateToBook.getValue();
		String time = hourToBook.getText();
		
		control1 = new LoginController();
		
		Alert alert = new Alert(AlertType.ERROR);		
		res = control1.checkRegistrationUser(user);
		//se true username non esiste, non posso aggiungere prenotazione
		if (res) {
			alert.setTitle(invalidBooking);
			alert.setHeaderText(null);
			alert.setContentText("The username inserted is not registered to BeEcological. Retry.");		
			alert.showAndWait();
			return;
		}
		
		if (date == null || time.isEmpty()) {
			alert.setTitle(invalidBooking);
			alert.setHeaderText(null);
			alert.setContentText("Date and time incorrectly.");		
			alert.showAndWait();
			return;
		}
		
		if (!Tool.checkDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))) {
			alert.setTitle(invalidBooking);
			alert.setHeaderText(null);
			alert.setContentText("You cannot insert a booking request for a day in the past. Choose a correct date.");		
			alert.showAndWait();
			return;
		}
		if (!Tool.checkTime(time)) {
			alert.setTitle(invalidBooking);
			alert.setHeaderText(null);
			alert.setContentText("Insert a correct time format: [HH:MM]");		
			alert.showAndWait();
			return;
		}
		
		booking.setBbUser(user.getUsbUsername());
		booking.setBbCenter(center);
		booking.setBbDate(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		booking.setBbTime(time);
		
		booking.setBbStatus("W");
		control2 = new ManageBookingController();
		int count = control2.verifyBooking(booking);
    	if(count!=0) {
    		//esiste prenotazione, la aggiorno accettandola
    		booking.setBbStatus("A");
    		control2.modifyBooking(booking);
    		alert.setAlertType(AlertType.INFORMATION);
    		alert.setTitle(okBooking);
    		alert.setHeaderText(null);
    		alert.setContentText(bookInsert+booking.getBbUser()+insertSuccess);		
    		alert.showAndWait();
	        bookingList7.removeAll(bookingList7);
		    try {
		        booking.setBbCenter(CenterOwnerBean.getInstance().getCobCenter());
		        booking.setBbStatus("W");
		    	data = control2.bookingListByCenter(booking); //richieste di prenotazione da accettare
		        bookingList7.addAll(data);
		    }
		    catch(Exception e){
		    	Logger.getGlobal().log(Level.SEVERE, errorData);        
		    }
			tableBookingRequest.setItems(bookingList7);
    		return;
    	}
		
    	booking.setBbStatus("A");
		count = control2.verifyBooking(booking);
		if (count!=0) {
			//la prenotazione già è stata accettata
			alert.setAlertType(AlertType.INFORMATION);
			alert.setTitle("Booking request not valid.");
			alert.setHeaderText(null);
			alert.setContentText(bookInsert+booking.getBbUser()+"' already exists. Retry.");		
			alert.showAndWait();
			return;
		}
		//la prenotazione non esiste
		try {
			MakeBookingController control3 = new MakeBookingController();
			control3.insertBooking(booking);
		} catch (InexistentUsernameException e) {
			Logger.getGlobal().log(Level.SEVERE, "Invalid username"); 
		}
		alert.setAlertType(AlertType.INFORMATION);
		alert.setTitle(okBooking);
		alert.setHeaderText(null);
		alert.setContentText(bookInsert+booking.getBbUser()+insertSuccess);		
		alert.showAndWait();
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void confirmBookingRequest7(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableBookingRequest.getSelectionModel().getSelectedIndex();
	        BookingBean book = tableBookingRequest.getItems().get(index);
	        
	        control2 = new ManageBookingController();
	        
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Validate booking request");
	        alert.setHeaderText(null);
	        alert.setContentText("Do you want to confirm this booking?");

	        ButtonType buttonTypeYes = new ButtonType("Yes");
	        ButtonType buttonTypeNo = new ButtonType("No");
	        ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

	        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo, buttonTypeCancel);

	        Optional<ButtonType> result = alert.showAndWait();
	        Alert alert1 = new Alert(AlertType.INFORMATION);
	        if (result.get() == buttonTypeYes){
	    		book.setBbStatus("A");
	        	control2.modifyBooking(book);
	    		alert1.setTitle(okBooking);
	    		alert1.setHeaderText(null);
	    		alert1.setContentText(bookInsert+book.getBbUser()+insertSuccess);		
	    		alert1.showAndWait();
	        } else if (result.get() == buttonTypeNo) {
	            book.setBbStatus("D");
	        	control2.modifyBooking(book);
	    		alert1.setTitle("Booking request refused.");
	    		alert1.setHeaderText(null);
	    		alert1.setContentText("Booking refused for '"+book.getBbUser()+"' has been completed successfully");		
	    		alert1.showAndWait();
	        } else {
	            //do nothing
	        }
	        bookingList7.removeAll(bookingList7);
		    try {
		        book.setBbCenter(CenterOwnerBean.getInstance().getCobCenter());
		        book.setBbStatus("W");
		    	data = control2.bookingListByCenter(book); //richieste di prenotazione da accettare
		        bookingList7.addAll(data);
		    }
		    catch(Exception e){
		    	Logger.getGlobal().log(Level.SEVERE, errorData);        
		    }
			tableBookingRequest.setItems(bookingList7);
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoOwnerProfile7(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.OWNER_PROFILE, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout7(ActionEvent event) {
		Alert alert7 = new Alert(AlertType.CONFIRMATION);
		alert7.setTitle("Logout");
		alert7.setHeaderText(null);
		alert7.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert7.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				PageLoader pageLoader7 = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller7 = (HomepageView) pageLoader7.getController();
				controller7.userGroup4.setVisible(false);
				controller7.loginGroup4.setVisible(true);
				CenterOwnerBean.setInstance(null);
				pageLoader7.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		ownerButton.setText(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		bookingList7.removeAll(bookingList7);
		try {
	    	booking = new BookingBean();
	    	control2 = new ManageBookingController();
	    	booking.setBbCenter(CenterOwnerBean.getOwnerInstance("").getCobCenter());
	    	booking.setBbStatus("W");
	        control2 = new ManageBookingController();
	    	data = control2.bookingListByCenter(booking); //richieste di prenotazione da accettare
	        bookingList7.addAll(data);
	    }
	    catch(Exception e){
	    	Logger.getGlobal().log(Level.SEVERE, errorData);          
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
	    colUser.setCellValueFactory(new PropertyValueFactory<>("bbUser"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("bbDate"));
		colTime.setCellValueFactory(new PropertyValueFactory<>("bbTime"));
		tableBookingRequest.setItems(bookingList7);
		
		//blocco dello spostamento delle colonne della tabella
		tableBookingRequest.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) tableBookingRequest.lookup("TableHeaderRow");
		        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header.setReordering(false);
		            }
		        });
		    }
		});
	}
	
}
