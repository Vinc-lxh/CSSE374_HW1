import java.time.LocalDateTime;

public class DiscountCode {
	private LocalDateTime expireDateTime;
	private double discount;
	private Item applyingItem;
	public DiscountCode(LocalDateTime expireDateTime,double discount, Item it) {
		this.expireDateTime = expireDateTime;
		this.discount = discount;
		this.applyingItem = it;
	}
	
	public Item getItem() {
		return applyingItem;
	}
	public double getDiscount() {
		return discount;
	}
	public LocalDateTime getExpireDate() {
		return expireDateTime;
	}
}
