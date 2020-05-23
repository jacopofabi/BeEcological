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

import logic.bean.BookingBean;
import logic.bean.CenterOwnerBean;
import logic.bean.UnloadBean;
import logic.bean.UserBean;
import logic.bean.WasteUnloadedBean;
import logic.controller.BookingController;
import logic.controller.UnloadController;
import logic.controller.UserController;
import logic.controller.WasteUnloadedController;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;


public class RegisterUnloadView implements Initializable {
	
	private static String dateFormat = "yyyy-MM-dd";
	private static String invalidUnload = "Invalid unload registration.";
	private static String errorData = "Error On Building Data";
	
	private List<BookingBean> data = new ArrayList<>();
	private List<TextField> data1 = new ArrayList<>();
	private List<CheckBox> data2 = new ArrayList<>();
	private ObservableList<BookingBean> bookingList9 = FXCollections.observableArrayList();
	private ObservableList<TextField> wasteQuantityList = FXCollections.observableArrayList();
	private ObservableList<CheckBox> wasteList = FXCollections.observableArrayList();
	
	private int bookingID;
	private String userSelected;
	private String dateSelected; 
	private String hourSelected;
	
	@FXML private Button homeButton; 
	@FXML private Button confirmButton;
	@FXML private MenuButton ownerButton;
	@FXML private MenuItem ownerProfileItem;
	@FXML private MenuItem logoutItem;
	@FXML private CheckBox b1;
	@FXML private CheckBox b2;
	@FXML private CheckBox b3;
	@FXML private CheckBox b4;
	@FXML private CheckBox b5;
	@FXML private CheckBox b6;
	@FXML private CheckBox b7;
	@FXML private CheckBox b8;
	@FXML private CheckBox b9;
	@FXML private CheckBox b10;
	@FXML private CheckBox b11;
	@FXML private CheckBox b12;
	@FXML private CheckBox b13;
	@FXML private CheckBox b14;
	@FXML private TextField f1;
	@FXML private TextField f2;
	@FXML private TextField f3;
	@FXML private TextField f4;
	@FXML private TextField f5;
	@FXML private TextField f6;
	@FXML private TextField f7;
	@FXML private TextField f8;
	@FXML private TextField f9;
	@FXML private TextField f10;
	@FXML private TextField f11;
	@FXML private TextField f12;
	@FXML private TextField f13;
	@FXML private TextField f14;
	@FXML private TextField textUser; 
	@FXML private TextField textHour;
	@FXML private DatePicker textDate;
	@FXML private TableView<BookingBean> tableBookingAccepted;
	@FXML private TableColumn<BookingBean, String> colid; 
	@FXML private TableColumn<BookingBean, String> colUser; 
	@FXML private TableColumn<BookingBean, String> colDate; 
	@FXML private TableColumn<BookingBean, String> colTime;
	
	private BookingBean booking9;
	private BookingController control3;
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage9(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void getID9(ActionEvent event) {
		CheckBox chk = (CheckBox) event.getSource();
		String id = chk.getId();
		switch(id) {
		case "b1":
			textDisabler9(b1,f1);
			return;
		case "b2":
			textDisabler9(b2,f2);
			return;
		case "b3":
			textDisabler9(b3,f3);
			return;
		case "b4":
			textDisabler9(b4,f4);
			return;
		case "b5":
			textDisabler9(b5,f5);
			return;
		case "b6":
			textDisabler9(b6,f6);
			return;
		case "b7":
			textDisabler9(b7,f7);
			return;
		case "b8":
			textDisabler9(b8,f8);
			return;
		case "b9":
			textDisabler9(b9,f9);
			return;
		case "b10":
			textDisabler9(b10,f10);
			return;
		case "b11":
			textDisabler9(b11,f11);
			return;
		case "b12":
			textDisabler9(b12,f12);
			return;
		case "b13":
			textDisabler9(b13,f13);
			return;
		case "b14":
			textDisabler9(b14,f14);
			return;
		default:
			return;
		}
	}
	
	
	//------------------------------------------------------------------------------
	public void textDisabler9(CheckBox theBox, TextField theField) {
		if (theBox.isSelected()) {
			theField.setDisable(false);
		}
		else {
			theField.setDisable(true);
			theField.clear();
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void setRowSelected9(MouseEvent event) {
		BookingBean book = null;
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableBookingAccepted.getSelectionModel().getSelectedIndex();
	        book = tableBookingAccepted.getItems().get(index);
			textUser.setText(book.getBbUser());
	        textHour.setText(book.getBbTime());
			textDate.setValue(LocalDate.parse(book.getBbDate(), DateTimeFormatter.ofPattern(dateFormat)));
			
			bookingID = book.getBbId();
			userSelected = book.getBbUser();
			hourSelected = book.getBbTime();
			dateSelected = book.getBbDate();
		}
	}

	
	//------------------------------------------------------------------------------
	@FXML
	public void confirmRegistrationUnload9(ActionEvent event) {
		boolean res;
		UserBean user = new UserBean();
		UserController control = new UserController();
		user.setUsbUsername(textUser.getText());
		LocalDate date = textDate.getValue();
		String time = textHour.getText();
		wasteList.removeAll(wasteList);
		wasteQuantityList.removeAll(wasteQuantityList);
		
		Alert alert = new Alert(AlertType.ERROR);		
		res = control.checkRegistration(user);
		//se true username non esiste, non posso aggiungere prenotazione
		if (res) {
			alert.setTitle(invalidUnload);
			alert.setHeaderText(null);
			alert.setContentText("The username inserted is not registered to BeEcological. Retry.");		
			alert.showAndWait();
			return;
		}
		
		if (date == null || time.isEmpty()) {
			alert.setTitle(invalidUnload);
			alert.setHeaderText(null);
			alert.setContentText("Date and time incorrectly.");		
			alert.showAndWait();
			return;
		}
		
		if (!Tool.checkTime(time)) {
			alert.setTitle(invalidUnload);
			alert.setHeaderText(null);
			alert.setContentText("Insert a correct time format: [HH:MM]");		
			alert.showAndWait();
			return;
		}
		
		wasteQuantityList.addAll(f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14);
		wasteList.addAll(b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14);
		data1.addAll(wasteQuantityList);
		data2.addAll(wasteList);
		
		UnloadBean unload = new UnloadBean();
		UnloadController control1 = new UnloadController();
		unload.setUbUser(user.getUsbUsername());
		unload.setUbCenter(CenterOwnerBean.getInstance().getCobCenter());
		unload.setUbDate(date.format(DateTimeFormatter.ofPattern(dateFormat)));
		unload.setUbTime(time);
		control1.insertAnUnload(unload);
		
		int i; 
		int quantity;
		String waste;
		boolean check = false;
		
		for(i=0;i<14;i++) {
			if(!data1.get(i).getText().isEmpty()) {
				waste = data2.get(i).getText();
				quantity = Integer.parseInt(data1.get(i).getText());
				WasteUnloadedBean wasteUnloaded = new WasteUnloadedBean();
				WasteUnloadedController control2 = new WasteUnloadedController();
				wasteUnloaded.setWbUser(user.getUsbUsername());
				wasteUnloaded.setWbCenter(CenterOwnerBean.getInstance().getCobCenter());
				wasteUnloaded.setWbDate(date.format(DateTimeFormatter.ofPattern(dateFormat)));
				wasteUnloaded.setWbTime(time);
				wasteUnloaded.setWbWaste(waste);
				wasteUnloaded.setWbWasteQuantity(quantity);
				control2.insertWasteForAnUnload(wasteUnloaded);
				check = true;
			}
		}
		//se check rimane false vuol dire che non ho messo alcun rifiuto per lo scarico, quindi elimino l'unload registrato
		if (!check) {
			control1.deleteAnUnload(unload);
			alert.setTitle(invalidUnload);
			alert.setHeaderText(null);
			alert.setContentText("Cannot register an unload without any waste. Retry.");		
			alert.showAndWait();
			return;
		}
		alert.setAlertType(AlertType.INFORMATION);
		alert.setTitle("Unload registration complete.");
		alert.setHeaderText(null);
		alert.setContentText("Unload registration for '"+textUser.getText()+"' has been completed successfully.");		
		alert.showAndWait();

		//se era una prenotazione presente nella griglia la salvo come registrata
		if(user.getUsbUsername().equals(userSelected) && date.format(DateTimeFormatter.ofPattern(dateFormat)).equals(dateSelected) && time.equals(hourSelected )) {
			booking9 = new BookingBean();
			control3 = new BookingController();
			booking9.setBbId(bookingID);
			booking9.setBbStatus("R");
			control3.modifyBooking(booking9);
			
			bookingList9.removeAll(bookingList9);
		    try {
		    	booking9.setBbCenter(CenterOwnerBean.getInstance().getCobCenter());
		    	booking9.setBbStatus("A");
		    	data = control3.bookingListByCenter(booking9);	//prenotazioni accettate dal gestore
		        bookingList9.addAll(data);
		    }
		    catch(Exception e){
		    	Logger.getGlobal().log(Level.SEVERE, errorData);           
		    }
		    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		    colid.setCellValueFactory(new PropertyValueFactory<>("bbId"));
		    colUser.setCellValueFactory(new PropertyValueFactory<>("bbUser"));
			colDate.setCellValueFactory(new PropertyValueFactory<>("bbDate"));
			colTime.setCellValueFactory(new PropertyValueFactory<>("bbTime"));
			tableBookingAccepted.setItems(bookingList9);
		}
		textUser.clear();
		textHour.clear();
		textDate.setValue(null);
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoOwnerProfile9(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.OWNER_PROFILE, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout9(ActionEvent event) {
		Alert alert9 = new Alert(AlertType.CONFIRMATION);
		alert9.setTitle("Logout");
		alert9.setHeaderText(null);
		alert9.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert9.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				PageLoader pageLoader9 = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller9 = (HomepageView) pageLoader9.getController();
				controller9.userGroup4.setVisible(false);
				controller9.loginGroup4.setVisible(true);
				CenterOwnerBean.setInstance(null);
				pageLoader9.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		ownerButton.setText(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		bookingList9.removeAll(bookingList9);
	    try {
	    	booking9 = new BookingBean();
	    	control3 = new BookingController();
	    	booking9.setBbCenter(CenterOwnerBean.getInstance().getCobCenter());
	    	booking9.setBbStatus("A");
	    	data = control3.bookingListByCenter(booking9);	//prenotazioni accettate dal gestore
	        bookingList9.addAll(data);
	    }
	    catch(Exception e){
	    	Logger.getGlobal().log(Level.SEVERE, errorData);         
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
	    colid.setCellValueFactory(new PropertyValueFactory<>("bbId"));
	    colUser.setCellValueFactory(new PropertyValueFactory<>("bbUser"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("bbDate"));
		colTime.setCellValueFactory(new PropertyValueFactory<>("bbTime"));
		tableBookingAccepted.setItems(bookingList9);

		tableBookingAccepted.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header9 = (TableHeaderRow) tableBookingAccepted.lookup("TableHeaderRow");
		        header9.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header9.setReordering(false);
		            }
		        });
		    }
		});
	}
	
}
