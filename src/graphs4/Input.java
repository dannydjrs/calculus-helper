package graphs4;

import java.text.DecimalFormat;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Input{
	static int xVals = 1;
	static Scene last;
	static String derive;
	static String[] vals;
	static Equation eqinput;
	static Stage window;
	
	// display seperate window for user input of graphs
	public static void display(Equation eqinput1) {
		// create new stage
		window = new Stage();
		// block events with other windows until this one is finished
		window.initModality(Modality.APPLICATION_MODAL);
		// set title of input window
		window.setTitle("Input graph");
		// set scene
		window.setScene(getScene(eqinput1));
		// show window
		window.show();
	}
	// method to create and return scene
	public static Scene getScene(Equation eqinput1) {
		// declare scene
		Scene scene;
		eqinput = eqinput1;
		// when user clicks button add x value it will add an x value type so therefore create a separate scene
		if(xVals == 1) {
			// vertical layout
			VBox layout = new VBox(8);
			// Horizontal scene for labels and input boxes
			HBox h = new HBox(10);
			// horizontal scene for buttons
			HBox h2 = new HBox(10);
			// label
			Label y = new Label("y = ");
			// input for x values
			TextField b = new TextField();
			Label bx = new Label(" x + ");
			TextField a = new TextField();
			// button will add an x value then go onto next scene 
			Button addX = new Button("Add an x type");
			// set tooltip of button so user knows what it does
			addX.setTooltip(new Tooltip("Add x^2"));
			// what to do when button is clicked
			addX.setOnAction(e -> {
				if(xVals != 3)
					// adds an x value
					xVals = xVals + 1;
				// restarts scene
				window.setScene(getScene(eqinput));
			});
			// disabled button so user doesn't remove an x type
			Button delX = new Button("Remove an x type");
			delX.setDisable(true);
			
			// confirm input of graph to add to main page
			Button confirm = new Button("Confirm");
			// set tooltip of button so user knows what it does
			confirm.setTooltip(new Tooltip("Add equation to graph"));
			// what to do when button is clicked
			confirm.setOnAction(e -> {
				try {
					// if a value is input into both text fields
					if(confirm(a) && confirm(b)) {
						// create an list
						ArrayList<String> eq = new ArrayList<>();
						eq.add(b.getText());
						eq.add(a.getText());
						// confirm equation input is possible
						confirmEquation(eq, 1);
						// close window
						window.close();
					}
					else {
						//print out error statement
					}
				}catch (Exception ex) {}
			});
			// back button to return to main window
			Button back = new Button("Back");
			// set tooltip of button so user knows what it does
			back.setTooltip(new Tooltip("Go back to main page"));
			// what to do when button is clicked
			back.setOnAction(e -> {
				// close sub window
				xVals = 1;
				window.close();
				// set window to original
				graphs2.setOriginal();
			});
			
			// add labels and inputs to top horizontal box 
			h.getChildren().addAll(y, b, bx, a);
			// add buttons to bottom horizontal box
			h2.getChildren().addAll(addX, delX, confirm, back);
			// add horizontal boxes to layout
			layout.getChildren().addAll(h, h2);
			// set distance nodes will be from edge of screen
			layout.setPadding(new Insets(10, 10, 10, 10));
			
			// create scene
			scene = new Scene(layout, 500, 200);
			// last scene was this one
			last = scene;
		}
		// if they have 2 x value i.e. x^2 and x
		else if(xVals == 2) {
			VBox layout = new VBox(8);
			HBox h = new HBox(10);
			HBox h2 = new HBox(10);
			
			Label y = new Label("y = ");
			
			TextField c = new TextField();
			Label cx = new Label(" x^2 + ");
			TextField b = new TextField();
			Label bx = new Label(" x + ");
			TextField a = new TextField();
			
			Button addX = new Button("Add an x type");
			addX.setTooltip(new Tooltip("Add x^3"));
			addX.setOnAction(e -> {
				if(xVals != 3)
					xVals = xVals + 1;
				window.setScene(getScene(eqinput));
			});
			Button delX = new Button("Remove an x type");
			delX.setTooltip(new Tooltip("Remove x^2"));
			delX.setOnAction(e -> {
				if(xVals != 1)
					xVals = xVals - 1;
				window.setScene(getScene(eqinput));
			});
			Button dif = new Button("Confirm");
			dif.setTooltip(new Tooltip("Add equation to graph"));
			dif.setOnAction(e -> {
				try {
					if(confirm(a) && confirm(b) && confirm(c)) {
						ArrayList<String> eq = new ArrayList<>();
						eq.add(c.getText());
						eq.add(b.getText());
						eq.add(a.getText());
						confirmEquation(eq, 2);
						window.close();
					}
					else {
						//print out error statement
					}
				}catch (Exception ex) {}
			});
			Button back = new Button("Back");
			back.setTooltip(new Tooltip("Go back to main page"));
			back.setOnAction(e -> {
				xVals = 1;
				window.close();
				graphs2.setOriginal();
			});
			
			h.getChildren().addAll(y, c, cx, b, bx, a);
			h2.getChildren().addAll(addX, delX, dif, back);
			layout.getChildren().addAll(h, h2);
			layout.setPadding(new Insets(10, 10, 10, 10));
			
			scene = new Scene(layout, 800, 200);
			last = scene;
		}
		// if they have 3 x values i.e. x^3 and x^2 and x
		else if(xVals == 3) {
			VBox layout = new VBox(8);
			HBox h = new HBox(10);
			HBox h2 = new HBox(10);
			
			Label y = new Label("y = ");
			
			TextField d = new TextField();
			Label dx = new Label(" x^3 + ");
			TextField c = new TextField();
			Label cx = new Label(" x^2 + ");
			TextField b = new TextField();
			Label bx = new Label(" x + ");
			TextField a = new TextField();
			
			Button addX = new Button("Add an x type");
			addX.setDisable(true);
			
			Button delX = new Button("Remove an x type");
			delX.setTooltip(new Tooltip("Remove x^3"));
			delX.setOnAction(e -> {
				if(xVals != 1)
					xVals = xVals - 1;
				window.setScene(getScene(eqinput));
			});
			Button dif = new Button("Confirm");
			dif.setTooltip(new Tooltip("Add equation to graph"));
			dif.setOnAction(e -> {
				try {
					if(confirm(a) && confirm(b) && confirm(c) && confirm(d)) {
						ArrayList<String> eq = new ArrayList<>();
						eq.add(d.getText());
						eq.add(c.getText());
						eq.add(b.getText());
						eq.add(a.getText());
						confirmEquation(eq, 3);
						window.close();
					}
					else {
						//print out error statement
					}
				}catch (Exception ex) {}
			});
			Button back = new Button("Back");
			back.setTooltip(new Tooltip("Go back to main page"));
			back.setOnAction(e -> {
				xVals = 1;
				window.close();
				graphs2.setOriginal();
			});
			
			h.getChildren().addAll(y, d, dx, c, cx, b, bx, a);
			h2.getChildren().addAll(addX, delX, dif, back);
			layout.getChildren().addAll(h, h2);
			layout.setPadding(new Insets(10, 10, 10, 10));
			
			scene = new Scene(layout, 1000, 200);
			last = scene;
		}
		else {
			scene = last;
		}
		return scene;
		
	}
	// confirms a double value was input into text box
	private static boolean confirm(TextField text) {
		try{
			// attempts to convert it to double
			Double.parseDouble(text.getText());
			// if it works return true
			return true;
		// if ever there is an error return false
		} catch(Exception ex) {
			return false;
		}
	}
	// sets all values into equation object
	private static void confirmEquation(ArrayList<String> a, int num) {
		// equation string
		String eq = "";
		// create equation name
		if(num == 1) {
			eq = (a.get(0) + "*x + " + a.get(1)); 
		} else if(num == 2) {
			eq = (a.get(0) + "*x^2 + " + a.get(1) + "*x + " + a.get(2)); 
		} else if(num == 3) {
			eq = (a.get(0) + "*x^3 + " + a.get(1) + "*x^2 + " + a.get(2) + "*x + " + a.get(3)); 
		}
		// set back to original number of x values for scene 
		xVals = 1;
		// set the equation to being used currently
		eqinput.setUsed(true);
		// set name for it
		eqinput.setEquation(eq);;
		// objects to differentiate and integrate
		Differentiation dif = new Differentiation();
		Integration iteg = new Integration();
		// set derivative of equation
		eqinput.setDerive(dif.differentiate(a));
		// set integral of equation
		eqinput.setInteg(iteg.integrate(a));
		
		try {
			// calculate the roots and set the values
			eqinput.setRoots(dif.roots(a));
		} catch (Exception ex) {}
		
		try {
			// calculate stationary points and set them
			eqinput.setStats(dif.stationaryPoints(a));
		} catch (Exception ex) {}
		
		// add xvalues and yvalues for use in graphs
		setValues();
		setSeries();
		// update values in main page
		graphs2.updateValues();
	}
	// method to calculate and add xvalues and yvalues to equation object
	public static void setValues() {
		try {
			// gets equation
			String equation = eqinput.getEquation();
			// creates a temporary equation where we will add actual x values to calulate with
			String tempEquation = "";
			try {
				// replace all multiply signs with * for use in calculations
				equation = equation.replace("X", "*");
				// replace x^2 with x*x
				equation = equation.replace("^2", "*x");
				// replace x^3 with x*x*x
				equation = equation.replace("^3", "*x*x");
			} catch(Exception ex) {}
			// type of rounding format used
			DecimalFormat df = new DecimalFormat("#.#");
			
			// between x values -5 and 5 with interval 0.1 calculate y values and add to array
			for(float i = -5; i <= 5; i += 0.1) {
				try {
					// replace all x's with current looping x value
					tempEquation = equation.replace("x", (Float.valueOf(df.format(i)) + ""));
					// add x value to equation object
					eqinput.xValues.add((float) Float.valueOf(df.format(i)));
					// add y value to equation object
					eqinput.yValues.add((float) Evaluate.eval(tempEquation));
				}catch(Exception ex) {}
			}
		} catch(Exception ex) {}
	}
	
	public static void setSeries() {
		for(int i = 0; i < eqinput.getxValues().size(); i++) {
			// ensure size stays below 100 in y axis
			if(eqinput.getyValues().get(i) < 100 && eqinput.getyValues().get(i) > -100) {
				eqinput.getSeries().getData().add(new XYChart.Data<Number, Number>(eqinput.getxValues().get(i), eqinput.getyValues().get(i)));
			}
			eqinput.getSeries().setName(eqinput.getEquation());
			
		}
	}
}
