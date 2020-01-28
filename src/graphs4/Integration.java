package graphs4;

import java.util.ArrayList;

public class Integration {
	
	String integ;
	
	// method to integrate an equation and return as string
	public String integrate(ArrayList<String> arr) {

		try {
			int size = arr.size();
			// if it has only one x coefficient 
		    if(size == 2) {
		    	// create integral
		    	integ = (Float.parseFloat(arr.get(0)) / 2) + "*x^2 + " + (Float.parseFloat(arr.get(1))) + "*x";
		    }
		    // if it has two x coefficients 
		    else if(size == 3) {
		    	// create integral
		    	integ = (Float.parseFloat(arr.get(0)) / 3) + "*x^3 + " + (Float.parseFloat(arr.get(1)) / 2) + "*x^2 + " + 
		    			(Float.parseFloat(arr.get(2))) + "*x";
		    }
		    // if it has three x coefficients
		    else if(size == 4) {
		    	// create integral
		    	integ = (Float.parseFloat(arr.get(0)) / 4) + "*x^4 + " + (Float.parseFloat(arr.get(1)) / 3) + "*x^3 + " + 
		    			(Float.parseFloat(arr.get(2)) / 2) + "*x^2 + " + (Float.parseFloat(arr.get(3))) + "*x";
		    }
		    // return string
		    return integ;
		     
		} catch (Exception e) {
			// if an error occurs return null as integral
		    integ = "null";
		    return integ;
		}
	}
}
