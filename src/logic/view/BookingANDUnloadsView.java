package logic.view;

import java.io.IOException;
import java.net.URL;
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
import logic.bean.WasteUnloadedBean;
import logic.controller.ManageBookingController;
import logic.controller.RegisterUnloadController;
import logic.utilities.PageLoader;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;


public class BookingANDUnloadsView implements Initializable {
	
	private List<BookingBean> data = new ArrayList<>();
	private List<WasteUnloadedBean> data1 = new ArrayList<>();
	private ObservableList<BookingBean> bookingList = FXCollections.observableArrayList();
	private ObservableList<WasteUnloadedBean> unloadList = FXCollections.observableArrayList();
	
	String errorData = "Error on Building Data";
	
	@FXML private Button homeButton;
	@FXML private MenuButton ownerButton;
	@FXML private MenuItem ownerProfileItem;
	@FXML private MenuItem logoutItem;
	@FXML private TableView<BookingBean> tableBookingAccepted1;
	@FXML private TableView<WasteUnloadedBean> tableRegisteredUnloads1;
	@FXML private TableColumn<BookingBean, String> col1id;
	@FXML private TableColumn<BookingBean, String> col1User; 
	@FXML private TableColumn<BookingBean, String> col1Date; 
	@FXML private TableColumn<BookingBean, String> col1Time;
	@FXML private TableColumn<WasteUnloadedBean, String> col2User; 
	@FXML private TableColumn<WasteUnloadedBean, String> col2Date; 
	@FXML private TableColumn<WasteUnloadedBean, String> col2Time; 
	@FXML private TableColumn<WasteUnloadedBean, String>col2Waste;
	@FXML private TableColumn<WasteUnloadedBean, String> col2Quantity;
	@FXML private TableColumn<WasteUnloadedBean, String>col2Points;

	private BookingBean book;
	private WasteUnloadedBean waste;
	private RegisterUnloadController control;
	private ManageBookingController control1;
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage1(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void setRowSelected1(MouseEvent event) {
		waste = null;
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableRegisteredUnloads1.getSelectionModel().getSelectedIndex();
	        waste = tableRegisteredUnloads1.getItems().get(index);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete a registered unload");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to delete this unload?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK) {
				//cancello lo scarico di un rifiuto e tolgo ecoPoints con trigger
				control = new RegisterUnloadController();
				control.deleteWasteForAnUnload(waste);
				alert.setAlertType(AlertType.INFORMATION);
				alert.setTitle("Unload waste deleted.");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Unload waste delete for '"+waste.getWbUser()+"' has been completed successfully.");		
	    		alert.showAndWait();
				

				int count = control.numberOfWasteForAnUnload(waste);
				if(count==0) {
					//non ho piu niente registrato per quello scarico, lo elimino
			        UnloadBean unload = new UnloadBean();
					unload.setUbUser(waste.getWbUser());
					unload.setUbCenter(waste.getWbCenter());
					unload.setUbDate(waste.getWbDate());
					unload.setUbTime(waste.getWbTime());
					RegisterUnloadController control2 = new RegisterUnloadController();
			        control2.deleteAnUnload(unload);
					alert.setAlertType(AlertType.INFORMATION);
					alert.setTitle("Unload deleted.");
		    		alert.setHeaderText(null);
		    		alert.setContentText("Unload delete for '"+unload.getUbUser()+"' has been completed successfully.\nNo one waste already exists for this unload.");		
		    		alert.showAndWait();
				}
	    		
	    		unloadList.removeAll(unloadList);
	    	    try {
	    	        data1 = control.listUnloadByCenter(waste);
	    	        unloadList.addAll(data1);
	    	    }
	    	    catch(Exception e){
	    	    	Logger.getGlobal().log(Level.SEVERE, errorData);          
	    	    }
	    	    tableRegisteredUnloads1.setItems(unloadList);
			}
		}
	}

	
	//------------------------------------------------------------------------------
	@FXML
	public void refuseBookingAccepted1(MouseEvent event) {
		BookingBean booking = null;
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableBookingAccepted1.getSelectionModel().getSelectedIndex();
	        booking = tableBookingAccepted1.getItems().get(index);
	        
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Delete booking accepted");
	        alert.setHeaderText(null);
	        alert.setContentText("Do you want to delete this booking accepted?");
	        Optional<ButtonType> result = alert.showAndWait();
	        
	        if (result.get() == ButtonType.OK){
	    		booking.setBbStatus("D");
	    		control1 = new ManageBookingController();
	        	control1.modifyBooking(booking);
	    		alert.setAlertType(AlertType.INFORMATION);
	    		alert.setTitle("Booking refuse completed.");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Booking refused successfully for '"+booking.getBbUser()+"'.\n\nYou can check the list of booking in wait through\n         'Homepage> Manage Booking'.");		
	    		alert.showAndWait();
	        }
	        
	        book = new BookingBean();
	        book.setBbCenter(CenterOwnerBean.getOwnerInstance("").getCobCenter());
	        book.setBbStatus("A");
	        bookingList.removeAll(bookingList);
		    try {
		        data = control1.bookingListByCenter(book);
		        bookingList.addAll(data);
		    }
		    catch(Exception e){
		    	Logger.getGlobal().log(Level.SEVERE, errorData);           
		    }
			tableBookingAccepted1.setItems(bookingList);
		}
	}
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoOwnerProfile1(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.OWNER_PROFILE, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout1(ActionEvent event) {
		Alert alert1 = new Alert(AlertType.CONFIRMATION);
		alert1.setTitle("Logout");
		alert1.setHeaderText(null);
		alert1.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert1.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				PageLoader pageLoader1 = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller1 = (HomepageView) pageLoader1.getController();
				controller1.userGroup4.setVisible(false);
				controller1.loginGroup4.setVisible(true);
				CenterOwnerBean.setInstance(null);
				pageLoader1.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		ownerButton.setText(CenterOwnerBean.getOwnerInstance("").getCobUsername());
		
		book = new BookingBean();
		book.setBbCenter(CenterOwnerBean.getOwnerInstance("").getCobCenter());
		book.setBbStatus("A");
		bookingList.removeAll(bookingList);
	    try {
	    	control1 = new ManageBookingController();
	        data = control1.bookingListByCenter(book);
	        bookingList.addAll(data);
	    }
	    catch(Exception e){
	    	Logger.getGlobal().log(Level.SEVERE, errorData);        
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		col1id.setCellValueFactory(new PropertyValueFactory<>("bbId"));
	    col1User.setCellValueFactory(new PropertyValueFactory<>("bbUser"));
		col1Date.setCellValueFactory(new PropertyValueFactory<>("bbDate"));
		col1Time.setCellValueFactory(new PropertyValueFactory<>("bbTime"));
		tableBookingAccepted1.setItems(bookingList);
		
		tableBookingAccepted1.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header1 = (TableHeaderRow) tableBookingAccepted1.lookup("TableHeaderRow");
		        header1.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header1.setReordering(false);
		            }
		        });
		    }
		});
		
		waste = new WasteUnloadedBean();
		waste.setWbCenter(CenterOwnerBean.getOwnerInstance("").getCobCenter());
		unloadList.removeAll(unloadList);
	    try {
	    	control = new RegisterUnloadController();
	        data1 = control.listUnloadByCenter(waste);
	        unloadList.addAll(data1);
	    }
	    catch(Exception e){
	    	Logger.getGlobal().log(Level.SEVERE, errorData);           
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		col2User.setCellValueFactory(new PropertyValueFactory<>("wbUser"));
	    col2Date.setCellValueFactory(new PropertyValueFactory<>("wbDate"));
		col2Time.setCellValueFactory(new PropertyValueFactory<>("wbTime"));
		col2Waste.setCellValueFactory(new PropertyValueFactory<>("wbWaste"));
		col2Quantity.setCellValueFactory(new PropertyValueFactory<>("wbWasteQuantity"));
		col2Points.setCellValueFactory(new PropertyValueFactory<>("wbEcoPoints"));
		tableRegisteredUnloads1.setItems(unloadList);
		
		tableRegisteredUnloads1.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header1 = (TableHeaderRow) tableRegisteredUnloads1.lookup("TableHeaderRow");
		        header1.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header1.setReordering(false);
		            }
		        });
		    }
		});
	}
	
}
