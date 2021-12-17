import java.util.List;

public class Cart {

	public double getEstimatedTax() {
		
		return 0;
	}
	
	public boolean applyDiscount() {
		
		return false;
	}
	public List<Item> getContent(){
		return null;
	}
	public boolean addItem(Item it) {
		return true;
	}
	
	public boolean updateQuantity(List<Item> its, List<Integer> quant){
		return true;
	}
	
	public void writeToFile() {
		
	}
}
