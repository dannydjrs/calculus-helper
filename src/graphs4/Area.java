package graphs4;

import java.text.DecimalFormat;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedAreaChart;
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

// inherits look from sub menu class
public class Area extends SubMenu{
	// textfields where user will input 2 x values for system to calculate area between
	static TextField xinputone, xinputtwo;
	// label to display calculated area to user
	static Label area;
	
	// method to return generated scene to main window
	public static Scene getScene(Equation[] equations) {
		createScene(equations);
		
		// initialises two variables for input of x values by user
		Label xlabelone = new Label("X value 1: ");
		Label xlabeltwo = new Label("X value 2: ");
		
		// initialises textfield input for users x value 1
		xinputone = new TextField();
		// sets tooltip for textfield
		xinputone.setTooltip(new Tooltip("Input first x value"));
		// initialises textfield input for users x value 2
		xinputtwo = new TextField();
		// sets tooltip for textfield
		xinputtwo.setTooltip(new Tooltip("Input second x value"));
		
		// label to display user what system is showing
		Label arealabel = new Label("Area below:");
		// sets font type and size
		arealabel.setFont(new Font("Arial", 18));
		
		// initialises calculated final area label
		area = new Label("...");
		// sets font type and size
		area.setFont(new Font("Arial", 14));
		
		// initialises button
		Button calcarea = new Button("Get Area");
		// what to do when calcarea button is clicked
		calcarea.setOnAction(e -> calcArea());
		// sets tooltip for button
		calcarea.setTooltip(new Tooltip("Get Area"));
		
		// adds label to vertical box
		left.getChildren().add(label);
		// loop to add all equations to vertical box
		for(int x = 0; x < 5; x ++) {
			left.getChildren().add(rButton[x]);
		}
		// adds remaining nodes to verical box
		left.getChildren().addAll(confirm, xlabelone, xinputone, xlabeltwo, xinputtwo, arealabel, area, calcarea);
		
		// horizontal box for bottom of scene
		HBox bottom = new HBox(8);
		// sets nodes to right side of box
		bottom.setAlignment(Pos.CENTER_RIGHT);
		// adds back button to hbox
		bottom.getChildren().add(back);
		
		// initialises gridpane layout
		grid = new GridPane();
		// sets vertical and horizontal gap between node placements
		grid.setHgap(10);
		grid.setVgap(8);
		// sets padding for grid so it doesnt have nodes on edge of scene
		grid.setPadding(new Insets(20, 20, 20, 20));
		
		// sets positions for nodes to be added
		GridPane.setConstraints(left, 0, 0);
		GridPane.setConstraints(bottom, 0, 1, 2, 1);
		GridPane.setConstraints(lineChart, 1, 0);
		// adds nodes to grid
		grid.getChildren().addAll(left, lineChart, bottom);
		
		// initialises scene with grid as layout
		Scene scene = new Scene(grid, 1000, 700);
		// returns scene to be used for window
		return scene;
		
	}
	// method that will calculate area between 2 x values
	public static void calcArea() {
		// initialises neccessary variables
		String integ;
		String one;
		String two;
		double areacalc;
		
		// will get integrated equation of selected equation
		if(eq1.isSelected()) {
			integ = eqs[0].getInteg();
		} else if(eq2.isSelected()) {
			integ = eqs[1].getInteg();
		} else if(eq3.isSelected()) {
			integ = eqs[2].getInteg();
		} else if(eq4.isSelected()) {
			integ = eqs[3].getInteg();
		} else if(eq5.isSelected()) {
			integ = eqs[4].getInteg();
		} else {
			integ = "";
		}
		try {
			// calculates area
			DecimalFormat df = new DecimalFormat("#.##");
			one = integ.replaceAll("x", xinputone.getText());
			two = integ.replaceAll("x", xinputtwo.getText());
			areacalc = (Evaluate.eval(one) - Evaluate.eval(two));
			if(areacalc < 0)
				area.setText(df.format((areacalc) * -1) + "");
			else
				area.setText(df.format(areacalc) + "");
		} catch(Exception ex) {}
		
	}
}
