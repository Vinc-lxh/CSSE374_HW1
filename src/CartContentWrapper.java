import java.util.List;

public class CartContentWrapper {
	public List<Item> items;
	public List<Double> CostOfItems;
	public double Discounts;
	public double EstimatedTaxes;
	public double totalCost;
	
	public CartContentWrapper(List<Item> items,List<Double> CostOfItems,double Discounts
			,double EstimatedTaxes,double totalCost) {
		this.items = items;
		this.CostOfItems = CostOfItems;
		this.Discounts = Discounts;
		this.EstimatedTaxes = EstimatedTaxes;
		this.totalCost = totalCost;
	}
}
