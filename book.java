package assignmentBookShop;



public class book extends shop{
	private String firstAuthor;
	private String ISBN;
	private double price;
	
	public book() {
		super();
		firstAuthor = "unknown";
		ISBN = "000-0-00-000000-0";
		price = 1;
	}
//-----------------------------------------------------------methods that check wrong input
	
	
	public book(String ISBN, String title, String firstAuthor, double price, int quantityInStock) {
		super(title, quantityInStock);
		if(firstAuthor == null || firstAuthor.equals(""))
			throw new RuntimeException("First Author must have a value");
		else
			this.firstAuthor = firstAuthor;
		if(ISBN == null || ISBN.equals(""))
			throw new RuntimeException("ISBN must have a value");
		else
			this.ISBN = ISBN;
		if(price <= 0 || price > 1000)
			throw new RuntimeException("Price must be between 0 and 1000");
		else
			this.price = price;
	}

	public String getFirstAuthor() {
		return firstAuthor;
	}

	public void setFirstAuthor(String firstAuthor) {
		if(firstAuthor == null || firstAuthor.equals(""))
			throw new RuntimeException("First Author must have a value");
		else
			this.firstAuthor = firstAuthor;
	}
	
	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String ISBN) {
		if(ISBN == null || ISBN.equals(""))
			throw new RuntimeException("ISBN must have a value");
		else
			this.ISBN = ISBN;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if(price <= 1 || price >999)
			throw new RuntimeException("Price must be between 1 and 999");
		else
			this.price = price;
	}
//--------------------------------------------------------override method toString to display in desired way data.
	@Override
	public String toString() {
		return " "+ title+"," + " Quantity In Stock = " + quantityInStock + ", First Author = " 
						+ firstAuthor+ ", ISBN = "+ ISBN + ", Price = " + price;
	}
}

