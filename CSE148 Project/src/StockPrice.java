
public class StockPrice {

	private String date;
	private double high;
	private double low;
	private double open;
	private double close;
	private double adjclose;
	private int volume;
	
	
	public StockPrice(String date, double open, double high, double low, double close, double adjclose, int volume) {
		this.date = date;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.adjclose = adjclose;
		this.volume = volume;
	}
	
	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public double getHigh() {
		return high;
	}


	public void setHigh(double high) {
		this.high = high;
	}


	public double getLow() {
		return low;
	}


	public void setLow(double low) {
		this.low = low;
	}


	public double getOpen() {
		return open;
	}


	public void setOpen(double open) {
		this.open = open;
	}


	public double getClose() {
		return close;
	}


	public void setClose(double close) {
		this.close = close;
	}


	public double getAdjclose() {
		return adjclose;
	}


	public void setAdjclose(double adjclose) {
		this.adjclose = adjclose;
	}


	public int getVolume() {
		return volume;
	}


	public void setVolume(int volume) {
		this.volume = volume;
	}

		
	
	
	
}
