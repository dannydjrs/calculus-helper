package graphs4;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//inherits look from sub menu class
public class Gradient extends SubMenu{
	static TextField xinput;
	// label to display gradient to user
	static Label grad;
	// method to create and return scene to window
	public static Scene getScene(Equation[] equations) {
		// label to show what user needs to input
		Label xlabel = new Label("X -Value:");
		// set font type and size for label
		xlabel.setFont(new Font("Arial", 18));
		
		// initialise input field for user
		xinput = new TextField();
		// set tooltip so user knows what to do
		xinput.setTooltip(new Tooltip("X value to calculate gradient at"));
		
		// label to show user what system is displaying
		Label gradlabel = new Label("Gradient at x:");
		// set font type and size for label
		gradlabel.setFont(new Font("Arial", 18));
		
		// initialise label to show gradient calculated to user
		grad = new Label("...");
		// set font type and size for label
		grad.setFont(new Font("Arial", 14));
		
		// create button for user to confirm chosen equation
		Button confirmx = new Button("Get Gradient");
		// set tooltip for button so user knows what it does
		confirmx.setTooltip(new Tooltip("Calculate gradient"));
		// what to do when button is clicked
		confirmx.setOnAction(e -> {
			// calculate gradient method
			calcGrad();
		});
		
		// add title to left layout
		left.getChildren().add(label);
		for(int x = 0; x < 5; x ++) {
			// add all the radio buttons to left layout
			left.getChildren().add(rButton[x]);
		}
		// add needed nodes to layout
		left.getChildren().addAll(confirm, xlabel, xinput, gradlabel, grad, confirmx);
		
		// layout for bottom of screen
		HBox bottom = new HBox(8);
		// set added nodes to be placed on right
		bottom.setAlignment(Pos.CENTER_RIGHT);
		// add back button to bottom right of screen
		bottom.getChildren().add(back);
		
		// initialise grid layout
		grid = new GridPane();
		// set horizontal distance between nodes
		grid.setHgap(10);
		// set vertical distance between nodes
		grid.setVgap(8);
		// set nodes distance from screen edge
		grid.setPadding(new Insets(20, 20, 20, 20));
		// set positioning of embedded layouts
		GridPane.setConstraints(left, 0, 0);
		GridPane.setConstraints(bottom, 0, 1, 2, 1);
		GridPane.setConstraints(lineChart, 1, 0);
		// add embedded layouts to main layout grid
		grid.getChildren().addAll(left, lineChart, bottom);
		
		// create scene
		Scene scene = new Scene(grid, 1000, 700);
		// return scene to main page
		return scene;
		
	}
	// method to calculate gradient at x value
	public static void calcGrad() {
		// variable to hold derived version of graph
		String derive;
		// depending on which equation is selected set varibale to its derived version
		if(eq1.isSelected()) {
			derive = eqs[0].getDerive();
		} else if(eq2.isSelected()) {
			derive = eqs[1].getDerive();
		} else if(eq3.isSelected()) {
			derive = eqs[2].getDerive();
		} else if(eq4.isSelected()) {
			derive = eqs[3].getDerive();
		} else if(eq5.isSelected()) {
			derive = eqs[4].getDerive();
		} else {
			derive = "";
		}
		try {
			// if input isn't empty
			if(xinput.getText().isEmpty() == false) {
				DecimalFormat df = new DecimalFormat("#.##");
				// replace all non x's with x's
				derive = derive.replace("^2", "*x");
				derive = derive.replace("^3", "*x*x");
				// replace x with input x value
				derive = derive.replace("x", xinput.getText());
				// set label to display gradient
				grad.setText(df.format(Evaluate.eval(derive))+ "");
			}
		} catch(Exception ex) {}
		
	}
}
