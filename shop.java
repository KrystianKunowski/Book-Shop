package assignmentBookShop;

public class shop {
	private static int counter;
	protected String title;
	protected int quantityInStock;
//-------------------------------------------------------------methods that check wrong input	
	public shop(String t, int q) {
		if(t==null || t.equals(""))
			throw new RuntimeException("Title cannot be null or empty!");
		else
			title = t;
		if(q < 0 || q > 100)
			throw new RuntimeException("Quantity In Stock cannot be negative or above 100");
		else
			quantityInStock = q;
		
		counter++;
	}
	
	public shop() {
		this("title...", 0);
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		if(title ==null || title.equals(""))
			throw new RuntimeException("Title cannot be null or empty!");
		else
			this.title = title;
	}

	/**
	 * @return the quantityInStock
	 */
	public int getQuantityInStock() {
		return quantityInStock;
	}

	/**
	 * @param quantityInStock the quantityInStock to set
	 */
	public void setQuantityInStock(int quantityInStock) {
		if(quantityInStock < 0 || quantityInStock > 100)
			throw new RuntimeException("Quantity In Stock cannot be negative or above 100!");
		else
			this.quantityInStock = quantityInStock;
	}

	public static int getCounter() {
		return counter;
	}
	//--------------------------------------------------------override method toString to display in desired way data.
	@Override 
	public String toString() {
		return "Shop: title = " + title + ", quantityInStock = " + quantityInStock;
	}
	
}
