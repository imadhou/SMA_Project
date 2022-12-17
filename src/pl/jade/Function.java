package pl.jade;

/**
 * Abstract Class defining the core of a mathematical function
 *
 * @author HOUARI Mourtada KASMI Ghilas
 */
public abstract class Function {

	public final double min;
	public final double max;
	public final double delta;

	/**
	 * Compute the mathematical value of the function f for the x value : f(x)
	 * @param x : real number between min and max
	 * @return f(x) : the value of the function at point x
	 * @throws Exception : Generic exception
	 */
	public abstract double f(double x) throws Exception;

	/**
	 * Calculate the inegral of the function using the simpson method
	 * @return Integral of the function for the interval [min, max]
	 * @throws Exception : Generic exception
	 */
	public double eval() throws Exception {
	double a = min;
	double b = max;              // precision parameter
	  double h = delta;
	  double N = ((b-a) / h) + 1;

      // 1/3 terms
      double sum = 1.0 / 3.0 * (f(a) + f(b));

      // 4/3 terms
      for (int i = 1; i < N - 1; i += 2) {
         double x = a + h * i;
         sum += 4.0 / 3.0 * f(x);
      }

      // 2/3 terms
      for (int i = 2; i < N - 1; i += 2) {
         double x = a + h * i;
         sum += 2.0 / 3.0 * f(x);
      }

      return sum * h;
    }

	/**
	 * Constructor of the generic function
	 * @param min : lower bound of the integration interval
	 * @param max : upper bound
	 * @param delta : step of integration
	 */
	public Function(double min, double max, double delta) {
		this.min = min;
		this.max = max;
		this.delta = delta;
	}
	
}
