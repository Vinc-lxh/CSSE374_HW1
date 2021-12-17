import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Cart {
	private ArrayList<DiscountCode> dsCodes = new ArrayList<DiscountCode>();
	private ErrorLog errorLog = new ErrorLog();
	private HashMap<Item,Integer> items= new HashMap<Item,Integer>();
	private HashMap<String,Double> TaxByState = new HashMap<String, Double>() {{
        put("Alabama",0.04);
        put("Alaska", 0.0);
        put("California",0.72);
        put("Indiana",0.7);
        put("Georgia",0.4);
        put("Missouri",0.42);
        put("New York",0.4);
        put("Tennessee",0.7);
        //omit the state tax list for illustration
    }};
	
	public Cart() {
		
	}
	
	public double getEstimatedTax(String state) {
		if(TaxByState.containsKey(state)) {
			return TaxByState.get(state);
		}
		return -1;
	}
	
	public boolean applyDiscount(DiscountCode discountCode) {
		if(errorLog.ErrorCount>=5) {
			System.out.println("Discount failure more than 5 times, please try after 24 hours!");
			return false;
		}
		if(!items.containsKey(discountCode.getItem())) {
			errorLog.ErrorCount++;
			errorLog.Errortime = LocalDateTime.now();
			System.out.println("No such item in the cart, please add"+ discountCode.getItem());
			return false;
		}
		else if(discountCode.getExpireDate().isBefore(LocalDateTime.now())) {
			errorLog.ErrorCount++;
			System.out.println("The discount has expired. Please try another one!");
			return false;
		}else {
			dsCodes.add(discountCode);
			System.out.println("The discount successfully applied");
		}
		return true;
	}
	public CartContentWrapper getContent(Address add){
		double taxRate = getEstimatedTax(add.getState());
		if(taxRate==-1) return null;
		
		Set<Item> keySet = items.keySet();
		ArrayList<Double> costOfItem = new ArrayList<Double>();
		double TotalCost = 0;
		for(Item item : items.keySet()) {
			costOfItem.add(item.getPrice());
			TotalCost+=(item.getPrice()*items.get(item));
		}
		CartContentWrapper returningContentWrapper = 
				new CartContentWrapper(new ArrayList<Item>(keySet), 
						costOfItem, 
						0, TotalCost*taxRate, TotalCost*(1+taxRate));
		
		return returningContentWrapper;
	}
	
	public boolean addItem(Item it) {
		if(!it.checkStock()) {
			System.out.println("Item out of stock");
			return false;
		}
		
		if(!items.isEmpty() && items.containsKey(it)) {
			int num = items.get(it);
			items.put(it,num+1);
		}else {
			items.put(it, 1);
		}
		return true;
	}
	
	public List<Item> updateQuantity(List<Item> itemList, List<Integer> quant){
		Iterator itIterator = items.keySet().iterator();
		ArrayList<Item> outOfStockItems = new ArrayList<Item>();
		while(itIterator.hasNext()) {
			Item item = (Item) itIterator.next();
			if(!item.checkStock()) {
				outOfStockItems.add(item);
				items.put(item, 0);
			}
		}
		for(int i = 0;i<itemList.size();i++) {
			Item  oneItem = itemList.get(i);
			if(!items.containsKey(oneItem)) {
				System.out.println(oneItem+"is not in the cart!");
			}else if (quant.get(i)<0) {
				System.out.println("Quanlity for item" + oneItem+"can not be negative");
			}else {
				items.put(oneItem,quant.get(i));
			}
		}
		if(outOfStockItems.isEmpty()) return null;
		
		return outOfStockItems;
	}
	
	public void writeToFile() {
		//write the content to local file
	}
}
