package graphs4;

import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class SubMenu {
	// declare variables
	// declare line graph so it can draw coordinates
	static	LineChart<Number, Number> lineChart;
	// create series to hold coordinates of lines
	static Series<Number, Number> series = new Series<>();
	// create confirm and back buttons 
	static Button confirm, back, constat;
	// create layout grid for objects
	static GridPane grid;
	// create radio buttons and group together in array for choice of equation
	static RadioButton eq1 = new RadioButton();
	static RadioButton eq2 = new RadioButton();
	static RadioButton eq3 = new RadioButton();
	static RadioButton eq4 = new RadioButton();
	static RadioButton eq5 = new RadioButton();
	static ToggleGroup group;
	static RadioButton[] rButton = {eq1, eq2, eq3, eq4, eq5};
	// stores all currently input equations
	static Equation[] eqs;
	static VBox left;
	static Label label;
	
	public static void createScene(Equation[] equations) {
		// stores equations so they can be accessed later
		eqs = equations;
		// initialises radio button group
		group = new ToggleGroup();
		//loop to add equations name to radio buttons and add radio buttons to group
		for(int x = 0; x < 5; x++) {
			// sets text of radio button as equation
			rButton[x].setText(equations[x].getEquation());
			// adds radio button to group
			rButton[x].setToggleGroup(group);
		}
		// initialises confirm button
		confirm = new Button("Confirm Graph");
		// sets tooltip for button
		confirm.setTooltip(new Tooltip("Use selected graph"));
		// what to do when confirm button is clicked
		confirm.setOnAction(e -> {
			// clears all series on linechart in submenu
			lineChart.getData().clear();
			// if the first equation is selected
			if(eq1.isSelected()) {
				// sets series as series of equation
				series = equations[0].getSeries();
				// if the second equation is selected
			} else if(eq2.isSelected()) {
				// sets series as series of equation
				series = equations[1].getSeries();
				// if the third equation is selected
			} else if(eq3.isSelected()) {
				// sets series as series of equation
				series = equations[2].getSeries();
				// if the fourth equation is selected
			} else if(eq4.isSelected()) {
				// sets series as series of equation
				series = equations[3].getSeries();
				// if the fifth equation is selected
			} else if(eq5.isSelected()) {
				// sets series as series of equation
				series = equations[4].getSeries();
			}
			// adds series to sub menu line chart
			lineChart.getData().add(series);
		});
		// initialises back button for user to go back to main page
		back = new Button("Back");
		// sets tooltip for button
		back.setTooltip(new Tooltip("Go back to main page"));
		// what to do when back button is clicked
		back.setOnAction(e -> {
			for(RadioButton x : rButton) {
				x.setSelected(false);
			}
			// clear all series from sub menu linechart
			lineChart.getData().clear();
			// set window to original scene
			graphs2.setOriginal();
		});


		// chart
		// initialises xAxis to be added to linechart - sets limits and tick number
		final NumberAxis xAxis = new NumberAxis(-5, 5, 1);
		// initialises yAxis to be added to linechart - sets limits and tick number
		final NumberAxis yAxis = new NumberAxis(-100, 100, 10);
		// adds label to xAxis
		xAxis.setLabel("X");
		// adds label to yAxis
		yAxis.setLabel("Y");
		// allow both axis to range to fit the equation input
		xAxis.setAutoRanging(true);
		yAxis.setAutoRanging(true);

		// initialises lineChart and adds axis 
		lineChart = new LineChart<>(xAxis, yAxis);
		// coordinates of equation will not be displayed as a symbol
		lineChart.setCreateSymbols(false);
		// prevent linechart from going through expansion animation as can lead to bugs
		lineChart.setAnimated(false);
		// set the preferred size for line chart
		lineChart.setPrefSize(800, 600);

		// initialises label to display user what to do
		label = new Label("Which graph?");
		// sets the font type and size
		label.setFont(new Font("Arial", 24));

		// set layout to hold nodes on left of screen
		left = new VBox(10);
		// set size for vbox
		left.setPrefSize(200, 600);
	}
}
