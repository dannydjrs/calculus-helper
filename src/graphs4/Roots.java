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
public class Roots extends SubMenu{
	static Label root1, root2, root3;
	
	// method to return generated scene to main window
	public static Scene getScene(Equation[] equations) {
		createScene(equations);
		
		// initialises label to display user what to do
		Label label = new Label("Which graph?");
		// sets the font type and size
		label.setFont(new Font("Arial", 24));
		
		// initialises variable for input of x values by user
		Label rootslabel = new Label("Roots:");
		// sets the font type and size
		rootslabel.setFont(new Font("Arial", 18));
		
		// create labels to display calculated roots to user
		root1 = new Label("...");
		root2 = new Label("...");
		root3 = new Label("...");
		
		// create button for user to get system to calculate roots
		Button getRoot = new Button("Get Roots");
		// sets tooltip for button
		getRoot.setTooltip(new Tooltip("Calculate roots"));
		// go to method to display roots when button is clicked
		getRoot.setOnAction(e -> getRoots());
		
		// set layout to hold nodes on left of screen
		VBox left = new VBox(10);
		// set size for vbox
		left.setPrefSize(200, 600);
		
		//add radio buttons to left of screen
		left.getChildren().add(label);
		// loop to go through each
		for(int x = 0; x < 5; x ++) {
			// go through array of radio buttons and add each element
			left.getChildren().add(rButton[x]);
		}
		// add remaining nodes to left of screen
		left.getChildren().addAll(confirm, rootslabel, root1, root2, root3, getRoot);
		
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
	public static void getRoots() {
		// list to store retrived roots of equation
		ArrayList<String> roots = new ArrayList<>();
		
		// if first equation is selected 
		if(eq1.isSelected()) {
			// set array to its already calculated roots
			roots = eqs[0].getRoots();
		// if second equation is selected 
		} else if(eq2.isSelected()) {
			// set array to its already calculated roots
			roots = eqs[1].getRoots();
		// if third equation is selected 
		} else if(eq3.isSelected()) {
			// set array to its already calculated roots
			roots = eqs[2].getRoots();
		// if fourth equation is selected 
		} else if(eq4.isSelected()) {
			// set array to its already calculated roots
			roots = eqs[3].getRoots();
		// if fifth equation is selected 
		} else if(eq5.isSelected()) {
			// set array to its already calculated roots
			roots = eqs[4].getRoots();
		} else {}
		
		try {
			// set labels to display the roots of the equation
			root1.setText(roots.get(0));
			root2.setText(roots.get(1));
			root3.setText(roots.get(2));
		} catch(Exception ex) {}
	}
}
