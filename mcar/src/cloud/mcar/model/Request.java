package cloud.mcar.model;

public class Request {
	private int customerID;
	private int driverID;
	private double pickUplat;
	private double pickUpLng;
	private double DestLat;
	private double DestLng;
	private String pickUpAddress;
	private String Destination;
	
	public Request(){
		
	}
	public Request(int customerID, double pickUplat, double pickUpLng,
			double destLat, double destLng, String pickUpAddress,
			String destination) {
		super();
		this.customerID = customerID;
		this.pickUplat = pickUplat;
		this.pickUpLng = pickUpLng;
		DestLat = destLat;
		DestLng = destLng;
		this.pickUpAddress = pickUpAddress;
		Destination = destination;
		this.driverID = 0;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public double getPickUplat() {
		return pickUplat;
	}
	public void setPickUplat(double pickUplat) {
		this.pickUplat = pickUplat;
	}
	public double getPickUpLng() {
		return pickUpLng;
	}
	public void setPickUpLng(double pickUpLng) {
		this.pickUpLng = pickUpLng;
	}
	public double getDestLat() {
		return DestLat;
	}
	public void setDestLat(double destLat) {
		DestLat = destLat;
	}
	public double getDestLng() {
		return DestLng;
	}
	public void setDestLng(double destLng) {
		DestLng = destLng;
	}
	public String getPickUpAddress() {
		return pickUpAddress;
	}
	public void setPickUpAddress(String pickUpAddress) {
		this.pickUpAddress = pickUpAddress;
	}
	public String getDestination() {
		return Destination;
	}
	public void setDestination(String destination) {
		Destination = destination;
	}
	public int getDriverID() {
		return driverID;
	}
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
	
}
