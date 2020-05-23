package logic.utilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;
import logic.bean.UserBean;
import logic.view.HomepageView;
import logic.view.SearchResultView;

public class PageLoader {
	private FXMLLoader loader;
	private Stage stage;
	private static String errorMessage = "Page loading error";
	
	
	public PageLoader(Page page, Event event) throws IOException {
		init(page.getResource(), page.getTitle(), event);
	}
	
	
	public PageLoader(Page page, MenuButton button) throws IOException {
		init(page.getResource(), page.getTitle(), button);
	}
	
	
	private void init(String resource, String title, Event event) throws IOException {
		stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	    URL url = new File(resource).toURI().toURL();
	    loader = new FXMLLoader(url);
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		stage.setScene(tableViewScene);
		stage.setTitle(title);
	}
	
	
	private void init(String resource, String title, MenuButton button) throws IOException {
		stage = (Stage) button.getScene().getWindow();
	    URL url = new File(resource).toURI().toURL();
	    loader = new FXMLLoader(url);
		Parent tableViewParent = loader.load();
		Scene tableViewScene = new Scene(tableViewParent);
		stage.setScene(tableViewScene);
		stage.setTitle(title);
	}

	
	public Object getController() {
		return loader.getController();
	}

	
	public void stageShow() {
		stage.show();
	}
	
	
	public void homeConfig() {
		HomepageView controller = (HomepageView) loader.getController();
		if(UserBean.getInstance() != null) {
			controller.loginGroup.setVisible(false);
			controller.userGroup.setVisible(true);
			controller.circleUserGroup.setVisible(true);
			controller.circleOwnerGroup.setVisible(false);
			controller.userButton.setText(UserBean.getInstance().getUsbUsername());
			controller.welcomebackText.setText("Welcome back, "+UserBean.getUserInstance("").getUsbUsername());
		}
		stage.show();
	}
	
	
	public void searchConfig() {
		SearchResultView controller = (SearchResultView) loader.getController();
		if(UserBean.getInstance() != null) {
			controller.loginGroup.setVisible(false);
			controller.userGroup.setVisible(true);
			controller.userButton.setText(UserBean.getInstance().getUsbUsername());
		}
		else {
			controller.userGroup.setVisible(false);
			controller.loginGroup.setVisible(true);
		}
		controller.textSearched.setText(Tool.getString()); //setta il testo del risultato come quello cercato
		stage.show();
	}
	
	
	public static String getErrorMessage() {
		return errorMessage;
	}
	
	
	
	public enum Page {
		BOOKING_AND_UNLOADS("src/res/fxml/BookingANDUnloads.fxml", "BeEcological - Booking & Unloads"),
		CENTER_PAGE("src/res/fxml/CenterPage.fxml", "BeEcological - Center Page"),
		HOME_OWNER("src/res/fxml/HomeOwner.fxml", "BeEcological for Managers - Homepage"),
		HOMEPAGE("src/res/fxml/Homepage.fxml", "BeEcological - Homepage"),
		LOGIN_OWNER("src/res/fxml/LoginOwner.fxml", "BeEcological - Login"),
		LOGIN_USER("src/res/fxml/LoginUser.fxml", "BeEcological - Login"),
		MANAGE_BOOKING("src/res/fxml/ManageBooking.fxml", "BeEcological for Managers - Manage Booking"),
		MANAGE_INFORMATION("src/res/fxml/ManageInformation.fxml", "BeEcological for Managers - Manage Information"),
		OWNER_PROFILE("src/res/fxml/OwnerProfile.fxml", "BeEcological for Managers - Profile"),
		REGISTER_UNLOAD("src/res/fxml/RegisterUnload.fxml", "BeEcological for Managers - Register Unload"),
		SEARCH_RESULT("src/res/fxml/SearchResult.fxml", "BeEcological - Search Result"),
		SHOP("src/res/fxml/Shop.fxml", "BeEcological - BeEcological - Shop"),
		USER_BOOKING_LIST("src/res/fxml/UserBookingList.fxml", "BeEcological - Booking List"),
		USER_PROFILE("src/res/fxml/UserProfile.fxml", "BeEcological - Profile");
		
		private String resource;
		private String title;
		
		private Page(String resource, String title) {
			this.resource = resource;
			this.title = title;
		}
		
		public String getResource() {
			return this.resource;
		}
		
		public String getTitle() {
			return this.title;
		}
	}
}
