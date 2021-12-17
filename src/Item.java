import javax.sound.midi.VoiceStatus;

public class Item {
	private String ID;
	private String Name;
	private String Description;
	private String Picture;
	private double DiscountRate = 0.0;
	private double price;
	private int stock;
	
	public Item( String ID,String Name,String Description,String Picture,double price,int stock) {
		this.ID = ID;
		this.Name = Name;
		this.Description = Description;
		this.Picture = Picture;
		this.price = price;
		this.stock = stock;
	}
	
	public boolean checkStock() {
		if(this.stock<=0) {
			return false;
		}
		return true;
	}
	
	public boolean updateDiscountRate(double targetRate) {
		if(targetRate<0 ||targetRate>1) {
			System.out.println("targetRate invalid");
			return false;
		}
		else this.DiscountRate =targetRate;
		return true;
	}

	public double getPrice() {
		if(DiscountRate == 0) return this.price;
		else return this.DiscountRate*this.price;
	}
	@Override
	public String toString() {
		
		return this.Name;
	}
	
}
