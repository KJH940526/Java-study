package praticee02;

public class Goods {
	private String name;
	private int price;
	private int stockCount;
	
	public Goods(String name, int price, int stockCount) {
		this.name = name;
		this.price = price;
		this.stockCount = stockCount;
	}
	
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getStockCount() {
		return stockCount;
	}

}
