package graphs4;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class graphs2 extends Application{
	// declare variables
	// actual window
	static Stage window;
	// main page scene so can be accessed later
	static Scene original;
	// scence used 
	static Scene scene;
	// create equation objects that will store details about input equations
	// a total of 5 equations can be input
	static Equation eq = new Equation();
	static Equation eq1 = new Equation();
	static Equation eq2 = new Equation();
	static Equation eq3 = new Equation();
	static Equation eq4 = new Equation();
	// store all equations in an array for easy access
	static Equation[] eqarr = {eq, eq1, eq2, eq3, eq4};
	// declare graph for main page
	static LineChart<Number, Number> lineChart;
	
	// main mathod to start application
	public static void main(String[] args) {
		launch(args);
	}
	// override start method from application class
	@Override
	public void start(Stage primaryStage) throws Exception {
		// set stage to declare varibale
		window = primaryStage;
		// set title of window
		window.setTitle("Maths");
		// sets it as a constant size
		window.setResizable(false);
		
		// set layouts to be used 
		VBox vertical = new VBox(10);
		HBox horizontal = new HBox(20);
		
		// Label to display what options user have
		Label label = new Label("Options");
		// set label font type and size
		label.setFont(new Font("Arial", 20));
		
		// equation button
		Button button = new Button("Write Equation.");
		button.setFont(new Font("Arial", 16));
		// set tooltip for button so user knows what it does
		button.setTooltip(new Tooltip("Input Graph."));
		// set size of button
		button.setPrefSize(400, 50);
		// what to do when button is clicked
		button.setOnAction(e -> {
			// gets series thats currently not being used
			if(getNotUsed() != 5) {
				// alllow user to input another equation
				Input.display(eqarr[getNotUsed()]);
			}
		});
		
		// clear button
		Button clear = new Button("Clear Graph.");
		clear.setFont(new Font("Arial", 16));
		// set tooltip for button so user knows what it does
		clear.setTooltip(new Tooltip("Clear a graph"));
		// set size of button 
		clear.setPrefSize(400, 50);
		// what to do when button is clicked
		clear.setOnAction(e -> {
			// go to clear graph method
			clearGraph();
			// set scene back to original
			setOriginal();
		});
		
		// final test button
		Button test = new Button("Final Test!");
		test.setFont(new Font("Arial", 16));
		// set tooltip for button so user knows what it does
		test.setTooltip(new Tooltip("Go to final testing"));
		// set size of button 
		test.setPrefSize(400, 50);
		// what to do when button is clicked
		test.setOnAction(e ->{
			// set scene to final testing scene
			window.setScene(FinalTest.getScene());
		});
		//test.setOnAction();
		
		// create tree
		TreeView<String> tree;
		TreeItem<String> root, diff, integ;
		
		// root setup
		root = new TreeItem<>();
		root.setExpanded(true);
		
		// differentiation branch
		diff = makeBranch("Differentiation", root);
		makeBranch("Roots", diff);
		makeBranch("Gradient", diff);
		makeBranch("Stationary points", diff);
		makeBranch("How to?", diff);
		
		// integration branch
		integ = makeBranch("Integration", root);
		makeBranch("Area under", integ);
		makeBranch("How to?", integ);
		
		// create tree
		tree = new TreeView<>(root);
		// set tooltip for tree so user knows what to do
		tree.setTooltip(new Tooltip("Options"));
		// dont show root
		tree.setShowRoot(false);
		
		// handle tree interrupts
		tree.getSelectionModel().selectedItemProperty().addListener(
				// ensures an new part was clicked to avoid accidental clicks
				(v, oldValue, newValue) -> {
					if(newValue != null) {
						// if they clicked on gradient branch
						if(newValue.getValue() == "Gradient") 
							window.setScene(Gradient.getScene(eqarr));
						// if they clicked on roots branch
						if(newValue.getValue() == "Roots") 
							window.setScene(Roots.getScene(eqarr));
						// if they clicked on stationary points branch
						if(newValue.getValue() == "Stationary points") 
							window.setScene(StatPoint.getScene(eqarr));
						// if they clicked on area branch
						if(newValue.getValue() == "Area under")
							window.setScene(Area.getScene(eqarr));
						// if they clicked on how to branch
						if(newValue.getValue() == "How to?")
							window.setScene(HowTo.getScene());
						
					}
				});
		
		// add nodes to box's
		horizontal.getChildren().addAll(button, clear);
		vertical.getChildren().addAll(label, tree);
		
		// defining axes tick distance and length
		final NumberAxis xAxis = new NumberAxis(-5, 5, 1);
		final NumberAxis yAxis = new NumberAxis(-100, 100, 10);
		// set labels for axis
		xAxis.setLabel("X");
		yAxis.setLabel("Y");
		// allow xis to range themselves to fit graph
		xAxis.setAutoRanging(true);
		yAxis.setAutoRanging(true);
		
		// add axis to line chart
		lineChart = new LineChart<>(xAxis, yAxis);
		// dont show symbols of coordinates
		lineChart.setCreateSymbols(false);
		// don't animate graph to remove bugs
		lineChart.setAnimated(false);
		
		// main layout grid to allow for better gui placement
		GridPane grid = new GridPane();
		// set distance nodes are placed from edge of window
		grid.setPadding(new Insets(10, 10, 10, 10));
		// set vertical distance between nodes
		grid.setVgap(8);
		// set horizontal distance between nodes
		grid.setHgap(10);
		
		// sizing chart
		lineChart.setMinSize(700, 500);
		lineChart.setPrefSize(800, 600);
		lineChart.setMaxSize(900, 700);
		
		// populating series
		updateValues();
		
		// set positioning of nodes added to grid
		GridPane.setConstraints(lineChart, 0, 0);
		GridPane.setConstraints(horizontal, 0, 1);
		GridPane.setConstraints(vertical, 1, 0);
		GridPane.setConstraints(test, 1, 1);
		// add nodes to grid
		grid.getChildren().addAll(lineChart, horizontal, vertical, test);
		
		// add to scene
		original = new Scene(grid,1000, 700);
		
		// set scence as main page
		window.setScene(original);
		// show window
		window.show();
		
	}
	
	// set to original scene
	public static void setOriginal() {
		window.setScene(original);
	}
	
	// update values on graph
	public static void updateValues() {
		// clear graph of current series
		lineChart.getData().clear();
		// add series to graph
		for(int y = 0; y < 5; y++) {
			eqarr[y].getSeries().setName(eqarr[y].getEquation());
			lineChart.getData().add(eqarr[y].getSeries());
		}
		// update original scene
		setOriginal();
	}

	// add branch to trees
	public TreeItem<String> makeBranch(String title, TreeItem<String> parent) {
		// create new item
		TreeItem<String> item = new TreeItem<>(title);
		// set ability to add more to true
		item.setExpanded(true);
		// add it to parent item
		parent.getChildren().add(item);
		// return item
		return item;
	}
	// get equations that are currently not being used by the system
	public int getNotUsed() {
		// if first equation isn't being used
		if(eq.getUsed() == false)
			return 0;
		// if second equation isn't being used
		else if(eq1.getUsed() == false)
			return 1;
		// if third equation isn't being used
		else if(eq2.getUsed() == false)
			return 2;
		// if fourth equation isn't being used
		else if(eq3.getUsed() == false)
			return 3;
		// if fifth equation isn't being used
		else if(eq4.getUsed() == false)
			return 4;
		// if all are being used allow user to clear one
		else {
			clearGraph();
			return getNotUsed();
		}
	}
	// method ti allow user to remove a input equation
	public void clearGraph() {
		// create another window for this
		Stage alert = new Stage();
		
		// block events with other windows until this one is finished
		alert.initModality(Modality.APPLICATION_MODAL);
		// set title of sub window
		alert.setTitle("Clear graph");
		// set size of window
		alert.setMinWidth(250);
		// ensure window can't be resized
		alert.setResizable(false);
		
		// create label to show user what to do
		Label label = new Label("Which graph would you like to clear?");
		
		// check boxes to allow for clearing of more than one graph
		// add name of equation to check box
		CheckBox boxOne = new CheckBox(eqarr[0].getEquation());
		CheckBox boxTwo = new CheckBox(eqarr[1].getEquation());
		CheckBox boxThree = new CheckBox(eqarr[2].getEquation());
		CheckBox boxFour = new CheckBox(eqarr[3].getEquation());
		CheckBox boxFive = new CheckBox(eqarr[4].getEquation());
		
		// button to cancel clearing of graph and return back to original window
		Button cancel = new Button("Cancel");
		// what to do when button is clicked
		cancel.setOnAction(e -> alert.close());
		
		// button to confirm clearing of graph
		Button confirm = new Button("Clear graph");
		// what to do when button is clicked
		confirm.setOnAction(e -> {
			// clears graph of all that were selected
			if(boxOne.isSelected()) {
				eqarr[0].clearData();
			}
			if(boxTwo.isSelected()) {
				eqarr[1].clearData();
			}
			if(boxThree.isSelected()) {
				eqarr[2].clearData();
			}
			if(boxFour.isSelected()) {
				eqarr[3].clearData();
			}
			if(boxFive.isSelected()) {
				eqarr[4].clearData();
			}
			// update values in main window
			updateValues();
			// close sub window
			alert.close();
		});
		
		// create layout for horizontal buttons
		HBox hbox = new HBox(10);
		// add buttons to hbox
		hbox.getChildren().addAll(confirm, cancel);
		
		// create layout to add checkboxes and label vertically
		VBox layout = new VBox(10);
		// set distance nodes are from edge of sub window
		layout.setPadding(new Insets(20,20,20,20));
		// add all nodes to layout vbox
		layout.getChildren().addAll(label, boxOne, boxTwo, boxThree, boxFour, boxFive, hbox);
		// put all nodes in center of box
		layout.setAlignment(Pos.CENTER);
		
		// create scene
		Scene scene = new Scene(layout);
		// set windows scene
		alert.setScene(scene);
		// show scene and wait until its closed
		alert.showAndWait();
	}
}
