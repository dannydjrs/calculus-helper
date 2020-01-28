package graphs4;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Differentiation {
	
	// variable to store the derivative generated
	String derive;
	
	// method to return derived equation
	public String differentiate(ArrayList<String> arr) {

		try {
			// used when rounding a double
		    DecimalFormat df = new DecimalFormat ("#.##");
		    // gets size of original equation
		    int a = arr.size() - 1;
		    // create array to store coefficients
		    double coefficient[] = new double [a + 1];
		    // create array to store coefficients when derived
		    double dxdy[] = new double [a];
		    
		    // gets original coefficients
		    for(int x = 0; x < arr.size(); x++) {
		    	coefficient[x] = Double.parseDouble(arr.get(x));
		    }
		    
		    // if ce will be first x type
		    int ce = a;
		    for (int j = 0 ; j < a ; j++) {
		    	// stores derivative coefficient in array
		    	dxdy[j] = coefficient[j] * ce;
		    	// goes to next x type
		    	ce--;
		    }
		    // store new equation
		    String eq = "";
		    
		    // if original equation only had one x type
		    if(arr.size() == 2) {
		    	eq = eq + df.format(dxdy[0]) + "";
		    // if original equation had two x types
		    } else if(arr.size() == 3) {
		    	eq = eq + df.format(dxdy[0]) + "*x + ";
		    	eq = eq + df.format(dxdy[1]) + "";
		    // if original equation had 3 x types
		    } else if(arr.size() == 4) {
		    	eq = eq + df.format(dxdy[0]) + "*x^2 + ";
		    	eq = eq + df.format(dxdy[1]) + "*x + ";
		    	eq = eq + df.format(dxdy[2]) + "";
		    }
		    
		    // store ne equations
		    derive = eq;
		    
		    // return equation
		    return derive;
		// if there is an error  
		} catch (Exception e) {
			// set derive as null
		    derive = "null";
		    // return back
		    return derive;
		}
	}
	
	// method to calculate roots of an equation
	public ArrayList<String> roots(ArrayList<String> arr) {
		// array to store roots calculated
		ArrayList<String> calcroots = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("#.##");
		// if array has one x type
		if(arr.size() == 2) {
			// if they put it as 0x + c then there will be no roots
			if(Double.parseDouble(arr.get(0)) == 0) {
				calcroots.add("null");
				calcroots.add("null");
				calcroots.add("null");
			// calculate root for equation
			} else {
				calcroots.add(df.format((Double.parseDouble(arr.get(1)) / Double.parseDouble(arr.get(0)) * -1)) + "");
				calcroots.add("null");
				calcroots.add("null");
			}
		// if the equation is a quadratic
		} else if(arr.size() == 3) {
			// ensures 0 wasnt put for any x values
			if(Double.parseDouble(arr.get(0)) == 0) {
				if(Double.parseDouble(arr.get(1)) == 0){
					calcroots.add("null");
					calcroots.add("null");
					calcroots.add("null");
				} else {
					calcroots.add(df.format((Double.parseDouble(arr.get(2)) / Double.parseDouble(arr.get(1)) * -1)) + "");
					calcroots.add("null");
					calcroots.add("null");
				}
			} else {
				// go to quadratic calculator method to calculate roots
				calcroots = QuadraticCalc(Double.parseDouble(arr.get(0)),Double.parseDouble(arr.get(1)), Double.parseDouble(arr.get(2)));
				calcroots.add("null");
			}
		// if the equation is a cubic
		} else if(arr.size() == 4) {
			// ensures 0 wasnt put for any x values
			if(Double.parseDouble(arr.get(0)) == 0) {
				if(Double.parseDouble(arr.get(1)) == 0){
					if (Double.parseDouble(arr.get(2)) == 0) {
						calcroots.add("null");
						calcroots.add("null");
						calcroots.add("null");
					} else {
						calcroots.add(df.format((Double.parseDouble(arr.get(2)) / Double.parseDouble(arr.get(1)) * -1)) + "");
						calcroots.add("null");
						calcroots.add("null");
					}
				} else {
					calcroots = QuadraticCalc(Double.parseDouble(arr.get(1)),Double.parseDouble(arr.get(2)), Double.parseDouble(arr.get(3)));
					calcroots.add("null");
				}
			} else {
				// go to quadratic calculator method to calculate roots
				calcroots = CubicCalc(Double.parseDouble(arr.get(0)),Double.parseDouble(arr.get(1)), Double.parseDouble(arr.get(2)), Double.parseDouble(arr.get(3)));
			}
		}
		// return calculated roots
		return calcroots;
	}
	
	// method that uses the quadratic formula to calculate roots
	public ArrayList<String> QuadraticCalc(double a, double b, double c) {
		// array to store the x values of roots calculated
		ArrayList<String> roots = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("#.##");
		
		// gets discriminant 
		double disc = (b*b - 4*a*c);
		// if discriminant is bigger than 0 then there will be 2 roots
		if(disc > 0) {
			// calculates roots using quadratic formula
			double root1 = ((-b + Math.sqrt( b*b - 4*a*c ) )/ (2*a));
			double root2 = ((-b - Math.sqrt( b*b - 4*a*c ) )/ (2*a));
			// adds roots to array
			roots.add(df.format(root1) + "");
			roots.add(df.format(root2) + "");
		}
		// if discriminant is 0 there is only one root
		else if(disc == 0) {
			double root1;
			try {
				root1 = ((-b + Math.sqrt( b*b - 4*a*c ) )/ (2*a));
			} catch(Exception ex) {
				root1 = ((+b + Math.sqrt( b*b - 4*a*c ) )/ (2*a));
			}
			// add root to array
			roots.add(df.format(root1) + "");
			roots.add("null");
		// if disriminant is less than 0 there are no roots
		} else {
			roots.add("null");
			roots.add("null");
		}
		// return array holding calculated roots
		return roots;
	}
	
	// method to calcualate roots for cubic equation
	public ArrayList<String> CubicCalc(double a, double b, double c, double d) {
		// array to store possible roots
		ArrayList<String> factors = new ArrayList<>();
		// store actual roots
		ArrayList<String> roots = new ArrayList<>();
		
		// minimum and maximum values it can be
		double min;
		double max;
		
		if(d < 0) {
			min = d;
			max = d*-1;
		}else if(d > 0) {
			min = -d;
			max = d;
		}else {
			min = 0;
			max = 0;
		}
		// used when rounding doubles
		DecimalFormat df = new DecimalFormat("#.#");
		
		// calculates all simple factors for graph and adds to array
		for(double factor = min; factor <= max; factor += 0.001) {
			if((d / Double.valueOf(df.format(factor))) % 1 == 0) {
				factors.add(Double.valueOf(df.format(factor)) + "");
			} else {}
		} 
		// sees if factor is a root if it is it will add to root array
		for(int x = 0; x < factors.size(); x++) {
			String equation = a + "*x*x*x + " + b + "*x*x + " + c + "*x + " + d +"";
			equation = equation.replaceAll("x", factors.get(x));
			if(Evaluate.eval(equation) == 0) {
				roots.add(df.format(factors.get(x)));
			}
		}
		// returns roots array
		return roots;
	}
	// method to calculate stationary point of a graph
	public ArrayList<String> stationaryPoints(ArrayList<String> arr) {
		// array to hold calculated stationary points
		ArrayList<String> calcstats = new ArrayList<>();
		DecimalFormat df = new DecimalFormat("#.##");
		// if only one x value then there are no turning point as straight line
		if(arr.size() == 2) {
			calcstats.add("null");
			calcstats.add("null");
		// calculates stationary points for graph if quadratic
		} else if(arr.size() == 3) {
			// ensures not 0x^2 + bx + c
			if(Double.parseDouble(arr.get(0)) == 0) {
				calcstats.add("null");
				calcstats.add("null");
			} else {
				calcstats.add(df.format(Double.parseDouble(arr.get(1)) / (-2 * Double.parseDouble(arr.get(0)))) + "");
				calcstats.add("null");
			}
		// calculates stationary points for graph if cubic
		} else if(arr.size() == 4) {
			// ensures its not 0x^3
			if(Double.parseDouble(arr.get(0)) == 0) {
				// ensures its not 0x^2
				if(Double.parseDouble(arr.get(1)) == 0) {
					calcstats.add("null");
					calcstats.add("null");
				} else {
					calcstats.add(df.format(Double.parseDouble(arr.get(2)) / (-2 * Double.parseDouble(arr.get(1)))) + "");
					calcstats.add("null");
				}
			} else {
				calcstats = QuadraticCalc(Double.parseDouble(arr.get(0)) * 3, Double.parseDouble(arr.get(1)) * 2, Double.parseDouble(arr.get(2)));
			}	
		}
		
		// return calculated stationary points
		return calcstats;
	}
}
