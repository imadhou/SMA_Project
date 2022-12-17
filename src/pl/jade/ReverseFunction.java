package pl.jade;

/**
 * Implementation of the reverse function f(x) = 1/x in R*
 *
 * WARNING:
 * DO NOT try to pass 0 as x value, or prepare a proper way to handle the exception
 *
 * @author HOUARI Mourtada KASMI Ghilas
 */
public class ReverseFunction extends Function {

	/**
	 * Calculate the reverse function given by the mathematics formula : f(x) = 1/x
	 * The calculus can be computed in the R* interval of numbers, else the function throws an exception.
	 * @param x : variable x
	 * @return f(x) = 1/x
	 * @throws Exception : Since calculating 1/0 is impossible the function throws an Exception in case 0 is passed as x value
	 */
	public double f(double x) throws Exception {
		if ( x == 0 ) {
			throw new Exception("Division per 0 is prohibited");
		}
		return 1/x;
	}

	public ReverseFunction(double min, double max, double delta) {
		super(min, max, delta);
	}

}
