package assignmentBookShop;

import java.util.ArrayList;


public class bookShop {

	private ArrayList<book> books;
	
	public  bookShop() {

		books = new ArrayList<>();
	}

	public ArrayList<book> getBooks() {
		return books;
	}
	
	public void setBooks(ArrayList<book> books) {
		this.books = books;
	}
//----------------------------------------method that avoid overload the "store"/ database (bookShop)	
	public void addBook(book l) {
		if(books.size() >= 100)
			throw new RuntimeException("Store is full");
		else
			books.add(l);
	}
	
//----------------------------------------method that avoid delete all books from the "store"/ database (bookShop)	
	public book removeBookByISBN(String ISBN) {
		if(books.size() <= 1) 
			throw new RuntimeException("The bookShop must have at least 1 book"
					+ ", otherwise there would be no bookShop");
		for(int i = 0; i < books.size(); i++) {
			book l = books.get(i);
			if(l.getISBN().equals(ISBN)) {
				books.remove(l);
				return l;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		return " and the following books: " + books;
	}
}
