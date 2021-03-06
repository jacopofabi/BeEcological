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

import logic.bean.CenterBean;
import logic.bean.CenterOwnerBean;
import logic.bean.UserBean;
import logic.controller.MakeBookingController;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.utilities.PageLoader;
import logic.utilities.Tool;



public class SearchResultView implements Initializable {
	
	List<CenterBean> data = new ArrayList<>();
	ObservableList<CenterBean> centerList = FXCollections.observableArrayList();
	ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML private ComboBox<String> hourBooking;
	@FXML private MenuItem userProfileItem; 
	@FXML private MenuItem logoutItem;
	@FXML public MenuButton userButton;
	@FXML private Button homeButton;
	@FXML private Button loginButton;
	@FXML private Button searchButton;
	@FXML private Button shopButton;
	@FXML public Group loginGroup; 
	@FXML public Group userGroup;
	@FXML private TextField searchBar;
	@FXML public Text textSearched;
	
    @FXML private TableView<CenterBean> tableView;
    @FXML private TableColumn<CenterBean, String> colName;
    @FXML private TableColumn<CenterBean, String> colCity;
    @FXML private TableColumn<CenterBean, String> colCap;
    @FXML private TableColumn<CenterBean, String> colAddress;
    @FXML private TableColumn<CenterBean, String> colPhone;
	
    private CenterBean center;
    private CenterOwnerBean owner;
    private MakeBookingController control;
    
    
	//------------------------------------------------------------------------------
    @FXML
	public void returnHomepage10(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.HOMEPAGE, event);
			pageLoader.homeConfig();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
    @FXML
	public void doSearch10(ActionEvent event) {
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
	public void toUserLogin10(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.LOGIN_USER, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
    @FXML
	public void gotoShop10(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.SHOP, event);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}

	
	//------------------------------------------------------------------------------
    @FXML
	public void gotoUserProfile10(ActionEvent event) {
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.USER_PROFILE, userButton);
			pageLoader.stageShow();
		} catch (IOException e) {
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
    @FXML
	public void openCenterPage10(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableView.getSelectionModel().getSelectedIndex();
	        center = new CenterBean();
	        control = new MakeBookingController();
	        center = tableView.getItems().get(index);
	        owner = control.ownerOfTheSelectedCenter(center);
		}
		Tool.setCenterName(center.getCbName());
		try {
			PageLoader pageLoader = new PageLoader(PageLoader.Page.CENTER_PAGE, event);
			CenterPageView controller = (CenterPageView) pageLoader.getController();
			if(UserBean.getInstance() != null) {
				controller.loginGroup.setVisible(false);
				controller.userGroup.setVisible(true);
				controller.userButton.setText(UserBean.getUserInstance("").getUsbUsername());
			}
			else {
				controller.userGroup.setVisible(false);
				controller.loginGroup.setVisible(true);
			}
			controller.centerSearched.setText(center.getCbName());
			controller.infoSearched.setText(center.getCbAddress()+"\n"+center.getCbCity()+" "+center.getCbCap());
			controller.centerPhoneSearched.setText(center.getCbPhone());
			controller.emailSearched.setText(owner.getCobEmail());
			controller.ownerPhoneSearched.setText(owner.getCobPhone());
			pageLoader.stageShow();
		}catch(Exception e){
			Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
		}
	}
	
	
	//------------------------------------------------------------------------------
	@FXML
	public void doLogout10(ActionEvent event){
		Alert alert10 = new Alert(AlertType.CONFIRMATION);
		alert10.setTitle("Logout");
		alert10.setHeaderText(null);
		alert10.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert10.showAndWait();
		if (result.get() == ButtonType.OK){
			try {
				PageLoader pageLoader10 = new PageLoader(PageLoader.Page.HOMEPAGE, event);
				HomepageView controller10 = (HomepageView) pageLoader10.getController();
				controller10.userGroup4.setVisible(false);
				controller10.loginGroup4.setVisible(true);
				UserBean.setInstance(null);
				pageLoader10.stageShow();
			} catch (IOException e) {
				Logger.getGlobal().log(Level.SEVERE, PageLoader.getErrorMessage());
			}
		}
	}

	
	//------------------------------------------------------------------------------
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		searchBar.setFont(Font.font("Calibri Light", FontWeight.NORMAL, 15));
		center = new CenterBean();
		center.setCbName(Tool.getString());
		centerList.removeAll(centerList);
	    try{      
	    	control = new MakeBookingController();
	        data = control.centerList(center);
	        centerList.addAll(data);
	    }
	    catch(Exception e){
	    	Logger.getGlobal().log(Level.SEVERE, "Error On Binding Data");  
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		colName.setCellValueFactory(new PropertyValueFactory<>("cbName"));
		colCity.setCellValueFactory(new PropertyValueFactory<>("cbCity"));
		colCap.setCellValueFactory(new PropertyValueFactory<>("cbCap"));
		colAddress.setCellValueFactory(new PropertyValueFactory<>("cbAddress"));
		colPhone.setCellValueFactory(new PropertyValueFactory<>("cbPhone"));
		tableView.setItems(centerList);
		
		tableView.widthProperty().addListener(new ChangeListener<Number>()
		{
			@Override
		    public void changed(ObservableValue<? extends Number> source, Number oldWidth, Number newWidth)
		    {
				TableHeaderRow header = (TableHeaderRow) tableView.lookup("TableHeaderRow");
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
