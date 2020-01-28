package graphs4;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class HowTo {
	
	// method to create and return scene to main page
	public static Scene getScene() {
		// layout for scene gui
		BorderPane layout = new	BorderPane();
		// set distance from edge of screen nodes will be placed
		layout.setPadding(new Insets(10, 10, 10, 10));
		// allow ability to access world wide web
		WebView webview = new WebView();
		// load embedded youtube video on how to do topics
	    webview.getEngine().load(
	      "http://www.youtube.com/embed/r7_emiKfXwM?autoplay=1"
	    );
	    // set size of video
	    webview.setPrefSize(640, 390);
	    
	    // create back button
	    Button back = new Button("Back");
	    // set tooltip of button so user knows what it does
	    back.setTooltip(new Tooltip("Go back to main page"));
	    // what to do when button is clicked
		back.setOnAction(e -> graphs2.setOriginal());
		// set center of layout to loaded video
		layout.setCenter(webview);
		// set bottom to back button
		layout.setBottom(back);
	
		// create scene
		Scene scene = new Scene(layout, 1000, 700);
		// return scene to main window
		return scene;

	}
}
