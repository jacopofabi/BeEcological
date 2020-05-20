package logic.view;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.sun.javafx.scene.control.skin.TableHeaderRow;

import logic.bean.CenterBean;
import logic.bean.CenterOwnerBean;
import logic.bean.UserBean;
import logic.controller.CenterController;
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
import javafx.stage.Stage;


public class SearchResultView implements Initializable {
	
	List<CenterBean> data = new ArrayList<>();
	ObservableList<CenterBean> center_list = FXCollections.observableArrayList();
	ObservableList<String> list = FXCollections.observableArrayList();
	
	@FXML private ComboBox<String> hourBooking;
	@FXML private MenuItem userProfileItem; 
	@FXML private MenuItem logoutItem;
	@FXML public MenuButton userButton;
	@FXML private Button homeButton;
	@FXML private Button loginButton;
	@FXML private Button searchButton;
	@FXML private Button shopButton;
	@FXML public Group loginGroup, userGroup;
	@FXML private TextField searchBar;
	@FXML public Text textSearched;
	
    @FXML private TableView<CenterBean> tableView;
    @FXML private TableColumn<CenterBean, String> col_name;
    @FXML private TableColumn<CenterBean, String> col_city;
    @FXML private TableColumn<CenterBean, String> col_cap;
    @FXML private TableColumn<CenterBean, String> col_address;
    @FXML private TableColumn<CenterBean, String> col_phone;
	
    private CenterBean center;
    private CenterOwnerBean owner;
    private CenterController control;
    
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
	
	public void doSearch(ActionEvent event) {
		tool.string = searchBar.getText();
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
			controller.textSearched.setText(tool.string); //setta il testo del risultato come quello cercato
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

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
	
	public void gotoShop(ActionEvent event) {
		try {
			//ricavo lo stage dal menuButton, poichè il menuItem non è una sottoclasse di Node
			//Stage window = (Stage) userButton.getScene().getWindow();
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
	
	public void openCenterPage(MouseEvent event) {
		if (event.getButton().equals(MouseButton.PRIMARY)) {
	        int index = tableView.getSelectionModel().getSelectedIndex();
	        center = new CenterBean();
	        control = new CenterController();
	        center = tableView.getItems().get(index);
	        owner = control.ownerOfTheSelectedCenter(center);
		}
		System.out.println("Nome cliccato: "+center.getCbName());
		tool.centerName = center.getCbName();
		try {
			Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
		    URL url = new File("src/res/fxml/CenterPage.fxml").toURI().toURL();
		    FXMLLoader loader = new FXMLLoader(url);
			Parent tableViewParent = loader.load();
			Scene tableViewScene = new Scene(tableViewParent);
			window.setScene(tableViewScene);
			window.setTitle("BeEcological - Center Page");
			
			CenterPageView controller = (CenterPageView) loader.getController();
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
			window.show();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void gotoUserProfile(ActionEvent event) {
		try {
			//ricavo lo stage dal menuButton, poichè il menuItem non è una sottoclasse di Node
			//Stage window = (Stage) userButton.getScene().getWindow();
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
	
	public void doLogout(ActionEvent event) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Logout");
		alert.setHeaderText(null);
		alert.setContentText("Are you sure you want to logout?");
		Optional<ButtonType> result = alert.showAndWait();

		if (result.get() == ButtonType.OK){
			try {
				Stage window = (Stage) userButton.getScene().getWindow();
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
		else {
		    //do nothing
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		homeButton.setTooltip(new Tooltip("Return to BeEcological Homepage"));
		searchBar.setFont(Font.font("Calibri Light", FontWeight.NORMAL, 15));
		
		center = new CenterBean();
		center.setCbName(tool.string);
		center_list.removeAll(center_list);
	    try{      
	    	control = new CenterController();
	        data = control.centerList(center);
	        center_list.addAll(data);
	    }
	    catch(Exception e){
	          e.printStackTrace();
	          System.out.println("Error on Building Data");            
	    }
	    //riempio le colonne tramite il corrispondente nome dell'attributo dato nella definizione della classe
		col_name.setCellValueFactory(new PropertyValueFactory<>("cbName"));
		col_city.setCellValueFactory(new PropertyValueFactory<>("cbCity"));
		col_cap.setCellValueFactory(new PropertyValueFactory<>("cbCap"));
		col_address.setCellValueFactory(new PropertyValueFactory<>("cbAddress"));
		col_phone.setCellValueFactory(new PropertyValueFactory<>("cbPhone"));
		tableView.setItems(center_list);
		
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
