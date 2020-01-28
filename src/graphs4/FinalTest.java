package graphs4;

import java.util.ArrayList;
import java.util.Random;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class FinalTest{
	
	// initialise all radio buttons used in question 1
	static RadioButton eq1 = new RadioButton();
	static RadioButton eq2 = new RadioButton();
	static RadioButton eq3 = new RadioButton();
	static RadioButton eq4 = new RadioButton();
	// initialise all radio buttons used in question 2
	static RadioButton eq12 = new RadioButton();
	static RadioButton eq22 = new RadioButton();
	static RadioButton eq32 = new RadioButton();
	static RadioButton eq42 = new RadioButton();
	// initialise all radio buttons used in question 3
	static RadioButton eq13 = new RadioButton();
	static RadioButton eq23 = new RadioButton();
	static RadioButton eq33 = new RadioButton();
	static RadioButton eq43 = new RadioButton();
	// initialise all radio buttons used in question 4
	static RadioButton eq14 = new RadioButton();
	static RadioButton eq24 = new RadioButton();
	static RadioButton eq34 = new RadioButton();
	static RadioButton eq44 = new RadioButton();
	// store radio buttons in an array for better access
	static RadioButton[] rButton = {eq1, eq2, eq3, eq4};
	static RadioButton[] rButton2 = {eq12, eq22, eq32, eq42};
	static RadioButton[] rButton3 = {eq13, eq23, eq33, eq43};
	static RadioButton[] rButton4 = {eq14, eq24, eq34, eq44};
	// group together the radio buttons
	static ToggleGroup group;
	static ToggleGroup group2;
	static ToggleGroup group3;
	static ToggleGroup group4;
	
	// store score of user
	static int total = 0;
	
	// method to create scene and return it to window
	public static Scene getScene() {
		// objects used in calculations
		Differentiation differ = new Differentiation();
		Integration integ = new Integration();
		// GridPane layout to allow for specific gui
		GridPane grid = new GridPane();
		// set the vertical gap between nodes added to grid
		grid.setHgap(10);
		// set the horizontal gap between nodes added to grid
		grid.setVgap(10);
		// set  distance from edge of window nodes are placed
		grid.setPadding(new Insets(20,20,20,20));
		// horizontal positioning for nodes
		HBox horiz = new HBox(40);
		
		// object to allow for random numbers to be generated
		Random rand = new Random();
		// label to display final score of test
		Label finallabel = new Label();
		// set font type and size for final label
		finallabel.setFont(new Font("Arial", 20));
		
		//
		// question 1 tangent
		//
		
		// vertical box to place radio buttons and question
		VBox row1 = new VBox(8);
		// toggle group for radio buttons
		group = new ToggleGroup();
		
		// generate random questions for polynomial
		int a = rand.nextInt(4) + 1;
		int b = rand.nextInt(5);
		int c = rand.nextInt(5);
		// x value used in question
		int x = rand.nextInt(5);
		// random positioning of correct answer to question
		int pos = rand.nextInt(4);
		// array to hold polynomial values
		ArrayList<String> orig = new ArrayList<>();
		// add values to array as strings
		orig.add(a + "");
		orig.add(b + "");
		orig.add(c + "");
		// initialise differentiated equation
		String diff = "";
		
		// differentiate equation
		diff = differ.differentiate(orig);
		// replace all x's with randomly generated x value
		diff = diff.replaceAll("x", x + "");
		// correct answer
		double grad = Evaluate.eval(diff);
		
		// create string of equation for question
		String equation = a + "*x^2 + " + b + "*x + " + c;
		// label to display to user the question
		Label label = new Label("Question 1)\nGradient at x = " + x + " for:\n" + equation);
		// set font type and size for question label
		label.setFont(new Font("Arial", 15));
		// loop to add incorrect answers to radio buttons
		for(int y = 0; y < 4; y ++) {
			// generate random answer
			int ans = rand.nextInt(21);
			// if the generated answer is the same as the correct one repeat to ensure no 2 values are the same
			while(ans == grad) {
				// make a new random value
				ans = rand.nextInt(21);
			}
			// set radio button to toggle group1
			rButton[y].setToggleGroup(group);
			// set text as random answer
			rButton[y].setText(ans + "");
		}
		// add correct answer to random radio buttoon 
		rButton[pos].setText((int)grad + "");
		// add all to vertical box
		row1.getChildren().add(label);
		for(int y = 0; y < 4; y ++) {
			row1.getChildren().add(rButton[y]);
		}
		
		//
		//question 2 roots
		//
		// vertical box to place radio buttons and question
		VBox row2 = new VBox(8);
		// toggle group for radio buttons
		group2 = new ToggleGroup();
		// generate simple roots for polynomial
		int root1 = -10 + rand.nextInt(20);
		int root2 = -10 + rand.nextInt(20);
		// generate questions for polynomial
		int a2 = 1;
		int b2 = (root1 * -1) + (root2 * -1);
		int c2  = (root1 * -1) * (root2 * -1);
		// random positioning of correct answer to question
		int pos2 = rand.nextInt(4);
		
		// create string of equation for question
		String equation2 = a2 + "*x^2 + " + b2 + "*x + " + c2;
		// label to display to user the question
		Label label2 = new Label("Question 2)\nRoots of:\n" + equation2);
		// set font type and size for question label
		label2.setFont(new Font("Arial", 15));
		// loop to add incorrect answers to radio buttons
		for(int y = 0; y < 4; y ++) {
			// generate random answers
			int ans1 = -10 + rand.nextInt(20);
			int ans2 = -10 + rand.nextInt(20);
			// if the generated answers are the same as the correct ones repeat to ensure no 2 values are the same
			while(ans1 == root1 && ans2 == root2) {
				// make a new random values
				ans1 = -10 + rand.nextInt(20);
				ans2 = -10 + rand.nextInt(20);
			}
			// set radio button to toggle group2
			rButton2[y].setToggleGroup(group2);
			// set text as incorrect answers
			rButton2[y].setText("x = " + ans1 + ", x = " + ans2);
		}
		// add correct answer to random radio button
		rButton2[pos2].setText("x = " + root1 + ", x = " + root2);
		// add all to vertical box
		row2.getChildren().add(label2);
		for(int y = 0; y < 4; y ++) {
			row2.getChildren().add(rButton2[y]);
		}
		
		//
		// question 3
		//
		
		// vertical box to place radio buttons and question
		VBox row3 = new VBox(8);
		// toggle group for radio buttons
		group3 = new ToggleGroup();
		// generate simple roots for polynomial
		int root12 = -10 + rand.nextInt(20);
		int root22 = -10 + rand.nextInt(20);
		// generate questions for polynomial
		int a3 = 1;
		int b3 = (root12 * -1) + (root22 * -1);
		int c3  = (root12 * -1) * (root22 * -1);
		// random positioning of correct answer to question
		int pos3 = rand.nextInt(4);
		// create string of equation for question
		String equation3 = a3 + "*x^2 + " + b3 + "*x + " + c3;
		// label to display to user the question
		Label label3 = new Label("Question 3)\nStationary point of of:\n" + equation3);
		// set font type and size for question label
		label3.setFont(new Font("Arial", 15));
		// find x value for stationary point
		double statpt = (root12 + root22) / 2;
		// loop to add incorrect answers to radio buttons
		for(int y = 0; y < 4; y ++) {
			// generate random answer
			int ans12 = -10 + rand.nextInt(20);
			// if the generated answers is the same as the correct one repeat to ensure no 2 values are the same
			while(ans12 == statpt) {
				// make a new random value
				ans12 = -10 + rand.nextInt(20);
			}
			// set radio button to toggle group3
			rButton3[y].setToggleGroup(group3);
			// randomly add 0.5 to ones to ensure they are all different
			if(rand.nextInt(2) == 1)
				// set text as incorrect answer
				rButton3[y].setText("x = " + ans12 + "");
			else
				rButton3[y].setText("x = " + ans12 + 0.5 + "");
		}
		// add correct answer to random radio button
		rButton3[pos3].setText("x = " + ((root12 + root22) / 2) + "");
		// add all to vertical box
		row3.getChildren().add(label3);
		for(int y = 0; y < 4; y ++) {
			row3.getChildren().add(rButton3[y]);
		}
		
		//
		// question 4
		//
		
		// vertical box to place radio buttons and question
		VBox row4 = new VBox(8);
		// toggle group for radio buttons
		group4 = new ToggleGroup();
		// generate simple roots for polynomial
		int root4 = -3 + rand.nextInt(3);
		int root42 = -3 + rand.nextInt(3);
		// generate questions for polynomial
		int a4 = 1;
		int b4 = (root4 * -1) + (root42 * -1);
		int c4  = (root4 * -1) * (root42 * -1);
		// generate 2 c xvalues for user to calculate area between
		int x1 = 4 + rand.nextInt(4);
		int x2 = x1 + 2;
		int pos4 = rand.nextInt(4);
		// create string of equation for question
		String equation4 = a4 + "*x^2 + " + b4 + "*x + " + c4;
		// label to display to user the question
		Label label4 = new Label("Question 4)\nArea between x = " + x1 + " and x = " + x2 + " for:\n" + equation4);
		// set font type and size for question label
		label4.setFont(new Font("Arial", 15));
		
		// array to store polynomial coefficients 
		ArrayList<String> arr4 = new ArrayList<>();
		// add values to array
		arr4.add(a4 + "");
		arr4.add(b4 + "");
		arr4.add(c4 + "");
		
		// get integral of polynomial
		String integral = integ.integrate(arr4);
		// calculate answer
		double area = Math.round(Evaluate.eval(integral.replaceAll("x", x2 + "")) - Evaluate.eval(integral.replaceAll("x", x1 + "")));
		// loop to add incorrect answers to radio buttons
		for(int y = 0; y < 4; y ++) {
			// generate random answer
			int ans14 = rand.nextInt(200);
			// if the generated answers is the same as the correct one repeat to ensure no 2 values are the same
			while(ans14 == area) {
				// make a new random value
				ans14 = rand.nextInt(200);
			}
			// set radio button to toggle group4
			rButton4[y].setToggleGroup(group4);
			// set text as incorrect answers
			rButton4[y].setText("Area = " + ans14 + "");
		
		}
		// add correct answer to random radio button
		rButton4[pos4].setText("Area = " + (int)area);
		// add all to vertical box
		row4.getChildren().add(label4);
		for(int y = 0; y < 4; y ++) {
			row4.getChildren().add(rButton4[y]);
		}
		
		//
		// Buttons
		//
		
		// button for user to confirm answers
		Button confirm = new Button("Confirm");
		// what to do when button is clicked
		confirm.setOnAction(e -> {
			// if question is answered correctly add to score
			if(rButton[pos].isSelected())
				total += 1;
			if(rButton2[pos2].isSelected())
				total += 1;
			if(rButton3[pos3].isSelected())
				total += 1;
			if(rButton4[pos4].isSelected())
				total += 1;
			// user can't change answers once clicked
			confirm.setDisable(true);
			// display final score to user
			finallabel.setText("You got " + total + " out of 4!");
		});
		// button to go back to main page
		Button back = new Button("Back");
		// what to do when button is clicked
		back.setOnAction(e -> {
			// ensure no radio buttons are clicked
			for(int y = 0; y < 4; y ++) {
				rButton[y].setSelected(false);
				rButton2[y].setSelected(false);
				rButton3[y].setSelected(false);
				rButton4[y].setSelected(false);
			}
			// go back to main page
			graphs2.setOriginal();
		});
		
		// add all questions to horizontal layout
		horiz.getChildren().addAll(row1, row2, row3, row4);
		
		// set positioning for everything on grid pane
		GridPane.setConstraints(horiz, 0, 0);
		GridPane.setConstraints(finallabel, 0, 1);
		GridPane.setConstraints(confirm, 0, 2);
		GridPane.setConstraints(back, 1, 2);
		// add all nodes to grid
		grid.getChildren().addAll(horiz, finallabel, confirm, back);
		
		// create scene
		Scene scene = new Scene(grid, 1000, 700);
		// return scene back to main page
		return scene;
	}
}