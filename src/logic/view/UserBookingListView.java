package logic.view;

import java.io.File;
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
import logic.bean.UserBean;
import logic.controller.BookingController;
import logic.utilities.PageLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Text;
import javafx.stage.Stage;


@SuppressWarnings({ "unused" })
public class UserBookingListView implements Initializable {
	
	String errorData = "Error on Building Data";
	String tableHeader = "TableHeaderRow";
	String centerString = "bbCenter";
	
	private List<BookingBean> data = new ArrayList<>();
	private ObservableList<BookingBean> bookingWaitList = FXCollections.observableArrayList();
	private ObservableList<BookingBean> bookingAcceptList = FXCollections.observableArrayList();
	private ObservableList<BookingBean> bookingRefuseList = FXCollections.observableArrayList();
	
	@FXML private MenuItem userProfileItem; 
	@FXML private MenuItem logoutItem;
	@FXML private MenuButton userButton;
	@FXML private Group userGroup;
	@FXML private Button homeButton;
	@FXML private Button searchButton;
	@FXML private Button shopButton;
	@FXML private Button seeBookingButton; 
	@FXML private Button editDataButton;
	@FXML private Button changeLoginButton;
	@FXML private Button deleteAccountButton;
	@FXML private Text userNick;
	@FXML private Text ecoPoints;
	@FXML private Text name;
	@FXML private Text surname; 
	@FXML private Text email;
	@FXML private Text phoneNumber;
	@FXML private TableView<BookingBean> refusedTable;
	@FXML private TableView<BookingBean> acceptedTable;
	@FXML private TableView<BookingBean> waitingTable;
	@FXML private TableColumn<BookingBean, String> colCenter; 
	@FXML private TableColumn<BookingBean, String> colDate;
	@FXML private TableColumn<BookingBean, String> colTime; 
	@FXML private TableColumn<BookingBean, String> colCenter1; 
	@FXML private TableColumn<BookingBean, String> colDate1; 
	@FXML private TableColumn<BookingBean, String> colTime1; 
	@FXML private TableColumn<BookingBean, String> colCenter2; 
	@FXML private TableColumn<BookingBean, String> colDate2;
	@FXML private TableColumn<BookingBean, String> colTime2;
	
	private BookingBean booking;
	private BookingController control;
	
	
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
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		userGroup.setVisible(true);
		booking = new BookingBean();
		booking.setBbUser(UserBean.getInstance().getUsbUsername());
		booking.setBbStatus("W");
		userNick.setText(booking.getBbUser());
		userButton.setText(booking.getBbUser());
		
		bookingWaitList.removeAll(bookingWaitList);
	    try {
			control = new BookingController();
	    	data = control.bookingListByUser(booking); //richieste di prenotazione in attesa
	        bookingWaitList.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println(errorData);            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
	    colCenter.setCellValueFactory(new PropertyValueFactory<>(centerString));
		colDate.setCellValueFactory(new PropertyValueFactory<>("bbDate"));
		colTime.setCellValueFactory(new PropertyValueFactory<>("bbTime"));
		waitingTable.setItems(bookingWaitList);
		
		//blocco dello spostamento delle colonne della tabella
		waitingTable.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) waitingTable.lookup(tableHeader);
		        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header.setReordering(false);
		            }
		        });
		    }
		});
		
		bookingAcceptList.removeAll(bookingAcceptList);
	    try {
	    	booking.setBbStatus("A");
	        data = control.bookingListByUser(booking);	//richieste di prenotazione accettate
	        bookingAcceptList.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println(errorData);            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
	    colCenter1.setCellValueFactory(new PropertyValueFactory<>(centerString));
		colDate1.setCellValueFactory(new PropertyValueFactory<>("bbDate"));
		colTime1.setCellValueFactory(new PropertyValueFactory<>("bbTime"));
		acceptedTable.setItems(bookingAcceptList);
		
		//blocco dello spostamento delle colonne della tabella
		acceptedTable.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) acceptedTable.lookup(tableHeader);
		        header.reorderingProperty().addListener(new ChangeListener<Boolean>() {
		            @Override
		            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
		                header.setReordering(false);
		            }
		        });
		    }
		});

		bookingRefuseList.removeAll(bookingRefuseList);
	    try {
	    	booking.setBbStatus("D");
	    	data = control.bookingListByUser(booking);
	        bookingRefuseList.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println(errorData);            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
	    colCenter2.setCellValueFactory(new PropertyValueFactory<>(centerString));
		colDate2.setCellValueFactory(new PropertyValueFactory<>("bbDate"));
		colTime2.setCellValueFactory(new PropertyValueFactory<>("bbTime"));
		refusedTable.setItems(bookingRefuseList);
		
		//blocco dello spostamento delle colonne della tabella
		refusedTable.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) refusedTable.lookup(tableHeader);
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
