package cloud.mcar.model;

public class Customer {
	private String customerName;
	private String address;
	private String phoneNumber;
	private int customerid;
	public Customer(String customerName, String address, String phoneNumber,
			int customerid) {
		super();
		this.customerName = customerName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.customerid = customerid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public String getAddress() {
		return address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public int getCustomerid() {
		return customerid;
	}
	
}
