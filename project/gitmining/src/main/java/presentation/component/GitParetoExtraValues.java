package presentation.component;

/** Data extra values for storing close, high and low. */
public class GitParetoExtraValues {
	private double close;
	private double high;
	private double low;
	private double average;

	public GitParetoExtraValues(double close, double high, double low, double average) {
		this.close = close;
		this.high = high;
		this.low = low;
		this.average = average;
	}

	public double getClose() {
		return close;
	}

	public double getHigh() {
		return high;
	}

	public double getLow() {
		return low;
	}

	public double getAverage() {
		return average;
	}
}