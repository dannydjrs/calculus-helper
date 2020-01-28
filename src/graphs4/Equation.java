package graphs4;

import java.util.ArrayList;

import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class Equation {
		
		// all properties of an equation will be stored in this class
		public String equation;
		public ArrayList<Float> xValues = new ArrayList<>();
		public ArrayList<Float> yValues = new ArrayList<>();
		public XYChart.Series<Number, Number> series;
		public String derive;
		public String integ;
		public boolean used;
		public ArrayList<String> roots;
		public ArrayList<String> stats;
		
		// when created initialises all properties 
		public Equation() {
			this.equation = "";
			this.series = new Series<>();
			this.derive = "";
			this.integ = "";
			this.used = false;
		}

		// actual equation
		public void setEquation(String equation) {
			this.equation = equation;
		}
		
		public String getEquation() {
			return equation;
		}
		
		// derivative of equation
		public String getDerive() {
			return derive;
		}

		public void setDerive(String derive) {
			this.derive = derive;
		}
		
		// integral of equation
		public String getInteg() {
			return integ;
		}

		public void setInteg(String integ) {
			this.integ = integ;
		}
		
		// xValues for graph
		public ArrayList<Float> getxValues() {
			return xValues;
		}

		public void setxValues(ArrayList<Float> xValues) {
			this.xValues = xValues;
		}
		
		// yValues for graph
		public ArrayList<Float> getyValues() {
			return yValues;
		}

		public void setyValues(ArrayList<Float> yValues) {
			this.yValues = yValues;
		}
		
		// series for graph
		public Series<Number, Number> getSeries() {
			return series;
		}

		public void setSeries(Series<Number, Number> series) {
			this.series = series;
		}
		
		// if equation is currently being used by system
		public void setUsed(boolean used) {
			this.used = used;
		}
		public boolean getUsed() {
			return used;
		}
		
		// roots of graph
		public void setRoots(ArrayList<String> roots) {
			this.roots = roots;
		}
		public ArrayList<String> getRoots() {
			return roots;
		}
		
		// stationary points
		public void setStats(ArrayList<String> stats) {
			this.stats = stats;
		}
		
		public ArrayList<String> getStats() {
			return stats;
		}
		
		// clear all data from this equation
		public void clearData() {
			equation = "";
			derive = "";
			integ = "";
			series.getData().clear();
			xValues.clear();
			yValues.clear();
			roots.clear();
			stats.clear();
			used = false;
		}
		
}
