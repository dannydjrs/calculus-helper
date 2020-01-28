package graphs4;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

//inherits look from sub menu class
public class StatPoint extends SubMenu{
	static Label stat, stat1, stat2;

	// method to return generated scene to main window
	public static Scene getScene(Equation[] equations) {
		createScene(equations);
		
		// create label to display to user what system is showing
		stat = new Label("Stationary Points(X):");
		// sets the font type and size
		stat.setFont(new Font("Arial", 18));
		// labels that will display actual stationary point
		stat1 = new Label("...");
		// sets the font type and size
		stat1.setFont(new Font("Arial", 14));
		// labels that will display actual stationary point
		stat2 = new Label("...");
		// sets the font type and size
		stat2.setFont(new Font("Arial", 14));
		
		// create button for user to get system to calculate stationary points
		constat = new Button("Get Stationary Points");
		// sets tooltip for button to display to user what button does
		constat.setTooltip(new Tooltip("Calculate stationary points of equation"));
		// go to method to display stationary points when button is clicked
		constat.setOnAction(e -> getStats());
		
		//add radio buttons to left of screen
		left.getChildren().add(label);
		// loop to go through each radio button
		for(int x = 0; x < 5; x ++) {
			// go through array of radio buttons and add each element
			left.getChildren().add(rButton[x]);
		}
		// add remaining nodes to left of screen
		left.getChildren().addAll(confirm, stat, stat1, stat2, constat);
		
		// create layout to place nodes horizontally on bottom of screen
		HBox bottom = new HBox(8);
		// set all nodes aligned to right 
		bottom.setAlignment(Pos.CENTER_RIGHT);
		// add back button to bottom right of screen
		bottom.getChildren().add(back);
		
		// initialise main grid layout used to display everything
		grid = new GridPane();
		// set gap between horizontal nodes
		grid.setHgap(10);
		// set gap between vertical nodes
		grid.setVgap(8);
		// set distance nodes are placed from edge of screen
		grid.setPadding(new Insets(20, 20, 20, 20));
		// set coordinates of left embedded layout
		GridPane.setConstraints(left, 0, 0);
		// set coordinates of bottom embedded layout
		GridPane.setConstraints(bottom, 0, 1, 2, 1);
		// set coordinates of linechart
		GridPane.setConstraints(lineChart, 1, 0);
		// add nodes to grid
		grid.getChildren().addAll(left, lineChart, bottom);
		
		// create scene with layout and size
		Scene scene = new Scene(grid, 1000, 700);
		// return scene to be used on window
		return scene;
		
	}
	// method to get roots of selected equation then display them 
	public static void getStats() {
		// list to store retrived roots of equation
		ArrayList<String> stats = new ArrayList<>();

		// if first equation is selected 
		if(eq1.isSelected()) {
			// set array to its already calculated stats
			stats = eqs[0].getStats();
		// if second equation is selected 
		} else if(eq2.isSelected()) {
			// set array to its already calculated stats
			stats = eqs[1].getStats();
		// if third equation is selected 
		} else if(eq3.isSelected()) {
			// set array to its already calculated stats
			stats = eqs[2].getStats();
		// if fourth equation is selected 
		} else if(eq4.isSelected()) {
			// set array to its already calculated stats
			stats = eqs[3].getStats();
		// if fifth equation is selected 
		} else if(eq5.isSelected()) {
			// set array to its already calculated stats
			stats = eqs[4].getStats();
		} else {}
		
		try {
			// set labels to display the roots of the equation
			stat1.setText(stats.get(0));
			stat2.setText(stats.get(1));
		} catch(Exception ex) {}
	}
}
