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

import logic.bean.UserBean;
import logic.bean.WasteUnloadedBean;
import logic.controller.UserController;
import logic.controller.WasteUnloadedController;
import logic.utilities.PageLoader;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;


public class UserProfileView implements Initializable {
	
	private static String errorData = "Error On Building Data";
	private ObservableList<WasteUnloadedBean> unloadList = FXCollections.observableArrayList();
	
	@FXML private MenuItem userProfileItem; 
	@FXML private MenuItem logoutItem;
	@FXML public MenuButton userButton;
	@FXML private Group userGroup;
	@FXML private Button homeButton;
	@FXML private Button searchButton;
	@FXML private Button shopButton;
	@FXML private Button seeBookingButton; 
	@FXML private Button editDataButton;
	@FXML private Button changeCredButton;
	@FXML private Button deleteAccountButton;
	@FXML private Text userNick;
	@FXML private Text ecoPoints;
	@FXML private Text name;
	@FXML private Text surname; 
	@FXML private Text email;
	@FXML private Text phoneNumber;
	
	@FXML private TableView<WasteUnloadedBean> unloadTable;
	@FXML private TableColumn<WasteUnloadedBean, String> colCenter; 
	@FXML private TableColumn<WasteUnloadedBean, String> colDate; 
	@FXML private TableColumn<WasteUnloadedBean, String> colTime;
	@FXML private TableColumn<WasteUnloadedBean, String> colWaste;
	@FXML private TableColumn<WasteUnloadedBean, String> colQuantity;
	@FXML private TableColumn<WasteUnloadedBean, String> colPoints;
	
	private UserController control;
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void returnHomepage13(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
			pageLoader.homeConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoUserBookingList13(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.USER_BOOKING_LIST, event);
			pageLoader.homeConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void gotoShop13(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.SHOP, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void editData13(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Edit Personal Data");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void changeCred13(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);		
		alert.setTitle("Change Login Credentials");
		alert.setHeaderText(null);
		alert.setContentText("Functionality not implemented.");		
		alert.showAndWait();
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void deleteAccount13(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Delete Account");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to delete your account?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK) {
			try {
				control = new UserController();
				
				PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller = (HomepageView) pageLoader.getController();
				controller.userGroup4.setVisible(false);
				controller.loginGroup4.setVisible(true);
				
				control.deleteAccount(UserBean.getInstance());
				UserBean.setInstance(null);
				pageLoader.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
		
	//------------------------------------------------------------------------------
	@FXML
	public void gotoUserProfile13(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.USER_PROFILE, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout13(ActionEvent event) {
		Alert alert13 = new Alert(AlertType.CONFIRMATION);
		alert13.setTitle("Logout");
		alert13.setHeaderText(null);
		alert13.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert13.showAndWait();
		if (result.get() == ButtonType.OK) {
			try {
				PageLoader pageLoader13 = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller13 = (HomepageView) pageLoader13.getController();
				controller13.userGroup4.setVisible(false);
				controller13.loginGroup4.setVisible(true);
				UserBean.setInstance(null);
				pageLoader13.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		userGroup.setVisible(true);
		control = new UserController();
		List<String> userInfo = control.userInformation(UserBean.getInstance());
		userButton.setText(UserBean.getInstance().getUsbUsername());
		userNick.setText(UserBean.getInstance().getUsbUsername());
		name.setText(userInfo.get(0));
		surname.setText(userInfo.get(1));
		email.setText(userInfo.get(2));
		phoneNumber.setText(userInfo.get(3));
		ecoPoints.setText(userInfo.get(4));
		
		unloadList.removeAll(unloadList);
		List<WasteUnloadedBean> data = new ArrayList<>();
	    try {
	        WasteUnloadedController control1 = new WasteUnloadedController();
	    	data = control1.listUnloadByUser(UserBean.getInstance());
	        unloadList.addAll(data);
	    }
	    catch(Exception e){
	    	Logger.getGlobal().log(Level.SEVERE, errorData);         
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		colCenter.setCellValueFactory(new PropertyValueFactory<>("wbCenter"));
	    colDate.setCellValueFactory(new PropertyValueFactory<>("wbDate"));
		colTime.setCellValueFactory(new PropertyValueFactory<>("wbTime"));
		colWaste.setCellValueFactory(new PropertyValueFactory<>("wbWaste"));
		colQuantity.setCellValueFactory(new PropertyValueFactory<>("wbWasteQuantity"));
		colPoints.setCellValueFactory(new PropertyValueFactory<>("wbEcoPoints"));
		unloadTable.setItems(unloadList);
		
		unloadTable.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) unloadTable.lookup("TableHeaderRow");
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
