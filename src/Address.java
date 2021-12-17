
public class Address {
	private String State;
	private String ZipCode;
	private String detailAddress;
	public Address(String State,String  ZipCode,String detailAddress) {
		this.State = State;
		this.ZipCode = ZipCode;
		this.detailAddress = detailAddress;
	}
	public String getState() {
		return State;
	}
	public String getZipCode() {
		return ZipCode;
	}
	public String getdetailAddress() {
		return detailAddress;
	}
	
}
