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
import logic.controller.BookingController;
import logic.controller.UnloadController;
import logic.controller.WasteUnloadedController;
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
	@FXML private TableView<BookingBean> tableBookingAccepted;
	@FXML private TableView<WasteUnloadedBean> tableRegisteredUnloads;
	@FXML private TableColumn<BookingBean, String> colid;
	@FXML private TableColumn<BookingBean, String> colUser; 
	@FXML private TableColumn<BookingBean, String> colDate; 
	@FXML private TableColumn<BookingBean, String> colTime;
	@FXML private TableColumn<WasteUnloadedBean, String> colUser1; 
	@FXML private TableColumn<WasteUnloadedBean, String> colDate1; 
	@FXML private TableColumn<WasteUnloadedBean, String> colTime1; 
	@FXML private TableColumn<WasteUnloadedBean, String>colWaste;
	@FXML private TableColumn<WasteUnloadedBean, String> colQuantity;
	@FXML private TableColumn<WasteUnloadedBean, String>colPoints;

	private BookingBean book;
	private WasteUnloadedBean waste;
	private UnloadBean unload;
	private WasteUnloadedController control;
	private UnloadController control1;
	private BookingController control2;
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOME_OWNER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void setRowSelected(MouseEvent event) {
		waste = null;
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableRegisteredUnloads.getSelectionModel().getSelectedIndex();
	        waste = tableRegisteredUnloads.getItems().get(index);
			
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Delete a registered unload");
			alert.setHeaderText(null);
			alert.setContentText("Are you sure you want to delete this unload?");
			Optional<ButtonType> result = alert.showAndWait();
			
			if (result.get() == ButtonType.OK) {
				//cancello lo scarico di un rifiuto e tolgo ecoPoints con trigger
				control = new WasteUnloadedController();
				control.deleteWasteForAnUnload(waste);
				alert.setAlertType(AlertType.INFORMATION);
				alert.setTitle("Unload waste deleted.");
	    		alert.setHeaderText(null);
	    		alert.setContentText("Unload waste delete for '"+waste.getWbUser()+"' has been completed successfully.");		
	    		alert.showAndWait();
				

				int count = control.numberOfWasteForAnUnload(waste);
				if(count==0) {
					//non ho piu niente registrato per quello scarico, lo elimino
			        unload = new UnloadBean();
					unload.setUbUser(waste.getWbUser());
					unload.setUbCenter(waste.getWbCenter());
					unload.setUbDate(waste.getWbDate());
					unload.setUbTime(waste.getWbTime());
					control1 = new UnloadController();
			        control1.deleteAnUnload(unload);
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
	    	    tableRegisteredUnloads.setItems(unloadList);
			}
		}
	}

	
	//------------------------------------------------------------------------------
	@FXML
	public void refuseBookingAccepted(MouseEvent event) {
		BookingBean booking = null;
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableBookingAccepted.getSelectionModel().getSelectedIndex();
	        booking = tableBookingAccepted.getItems().get(index);
	        
	        Alert alert = new Alert(AlertType.CONFIRMATION);
	        alert.setTitle("Delete booking accepted");
	        alert.setHeaderText(null);
	        alert.setContentText("Do you want to delete this booking accepted?");
	        Optional<ButtonType> result = alert.showAndWait();
	        
	        if (result.get() == ButtonType.OK){
	    		booking.setBbStatus("D");
	    		control2 = new BookingController();
	        	control2.modifyBooking(booking);
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
		        data = control2.bookingListByCenter(book);
		        bookingList.addAll(data);
		    }
		    catch(Exception e){
		    	Logger.getGlobal().log(Level.SEVERE, errorData);           
		    }
			tableBookingAccepted.setItems(bookingList);
		}
	}
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoOwnerProfile(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.OWNER_PROFILE, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
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
				CenterOwnerBean.setInstance(null);
				pageLoader.stageShow();
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
	    	control2 = new BookingController();
	        data = control2.bookingListByCenter(book);
	        bookingList.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println(errorData);            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		colid.setCellValueFactory(new PropertyValueFactory<>("bbId"));
	    colUser.setCellValueFactory(new PropertyValueFactory<>("bbUser"));
		colDate.setCellValueFactory(new PropertyValueFactory<>("bbDate"));
		colTime.setCellValueFactory(new PropertyValueFactory<>("bbTime"));
		tableBookingAccepted.setItems(bookingList);
		
		tableBookingAccepted.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) tableBookingAccepted.lookup("TableHeaderRow");
		        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header.setReordering(false);
		            }
		        });
		    }
		});
		
		waste = new WasteUnloadedBean();
		waste.setWbCenter(CenterOwnerBean.getOwnerInstance("").getCobCenter());
		unloadList.removeAll(unloadList);
	    try {
	    	control = new WasteUnloadedController();
	        data1 = control.listUnloadByCenter(waste);
	        unloadList.addAll(data1);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println(errorData);            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		colUser1.setCellValueFactory(new PropertyValueFactory<>("wbUser"));
	    colDate1.setCellValueFactory(new PropertyValueFactory<>("wbDate"));
		colTime1.setCellValueFactory(new PropertyValueFactory<>("wbTime"));
		colWaste.setCellValueFactory(new PropertyValueFactory<>("wbWaste"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<>("wbWasteQuantity"));
		colPoints.setCellValueFactory(new PropertyValueFactory<>("wbEcoPoints"));
		tableRegisteredUnloads.setItems(unloadList);
		
		tableRegisteredUnloads.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) tableRegisteredUnloads.lookup("TableHeaderRow");
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
