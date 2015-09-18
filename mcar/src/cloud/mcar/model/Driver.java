package cloud.mcar.model;

public class Driver {
	private String licenseID;
	private String driverName;
	private String vehicalLicenseNumber;
	private String phoneNumber;
	private int acceptedorders;
	private int finishedorders;
	private int rank;
	private double rate;
	private int driverID;
	
	public Driver(String licenseID, String driverName,
			String vehicalLicenseNumber, String phoneNumber,
			int acceptedorders, int finishedorders, int rank, double rate,
			int driverID) {
		super();
		this.licenseID = licenseID;
		this.driverName = driverName;
		this.vehicalLicenseNumber = vehicalLicenseNumber;
		this.phoneNumber = phoneNumber;
		this.acceptedorders = acceptedorders;
		this.finishedorders = finishedorders;
		this.rank = rank;
		this.rate = rate;
		this.driverID = driverID;
	}
	public String getLicenseID() {
		return licenseID;
	}
	public String getDriverName() {
		return driverName;
	}
	public String getVehicalLicenseNumber() {
		return vehicalLicenseNumber;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public int getAcceptedorders() {
		return acceptedorders;
	}
	public int getFinishedorders() {
		return finishedorders;
	}
	public int getRank() {
		return rank;
	}
	public double getRate() {
		return rate;
	}
	public int getDriverID() {
		return driverID;
	}
	
}
