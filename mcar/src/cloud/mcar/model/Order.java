package cloud.mcar.model;

public class Order {
	private int orderID;
	private int customerID;
	private int driverID;
	private double fromLat;
	private double fromLongti;
	private double toLat;
	private double toLongti;
	private String fromName;
	private String toName;
	private int finished;
	private double fee;
	private double rate;
	private int real;
	public Order(int customerID, int driverID, double fromLat,
			double fromLongti, double toLat, double toLongti, String fromName,
			String toName) {
		super();
		this.orderID = 0;
		this.customerID = customerID;
		this.driverID = driverID;
		this.fromLat = fromLat;
		this.fromLongti = fromLongti;
		this.toLat = toLat;
		this.toLongti = toLongti;
		this.fromName = fromName;
		this.toName = toName;
		this.finished = 0;
		this.fee = 0;
		this.rate = 0;
		this.real = 0;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getDriverID() {
		return driverID;
	}
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
	public double getFromLat() {
		return fromLat;
	}
	public void setFromLat(double fromLat) {
		this.fromLat = fromLat;
	}
	public double getFromLongti() {
		return fromLongti;
	}
	public void setFromLongti(double fromLongti) {
		this.fromLongti = fromLongti;
	}
	public double getToLat() {
		return toLat;
	}
	public void setToLat(double toLat) {
		this.toLat = toLat;
	}
	public double getToLongti() {
		return toLongti;
	}
	public void setToLongti(double toLongti) {
		this.toLongti = toLongti;
	}
	public String getFromName() {
		return fromName;
	}
	public void setFromName(String fromName) {
		this.fromName = fromName;
	}
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	public int getFinished() {
		return finished;
	}
	public void setFinished(int finished) {
		this.finished = finished;
	}
	public double getFee() {
		return fee;
	}
	public void setFee(double fee) {
		this.fee = fee;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public int getReal() {
		return real;
	}
	public void setReal(int real) {
		this.real = real;
	}
	
}
