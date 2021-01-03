package assignmentBookShop;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;


public class bookShopQueries implements AutoCloseable{
	private String user;
	private String password;
	private Connection conn;	
		
	public bookShopQueries() throws SQLException, IOException{
		conn = getConnection();
	}
//-------------------------------------loading file with user and password to make connection with database.
	private void loadProps() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(".\\src\\assignmentBookShop\\bookShop.properties"));
		
		Properties bookShopProps = new Properties();
		bookShopProps.load(reader);
		this.user = bookShopProps.getProperty("user");
		this.password = bookShopProps.getProperty("password");
	}

//----------------------------------------------- only looking at mysql connection by 3309 localhost !
	private Connection getConnection() throws SQLException, IOException{
		loadProps();
		return DriverManager.getConnection("jdbc:mysql://localhost:3309/bookshop", user, password);
	}
	
	@Override
	public void close() throws SQLException {
		conn.close();
	}
	//------------------------------------------------------------------------------------------------methods with SQL queries : 
	
//------------------------------------------------------------------------------------------------method getAllBooks to retrieve all data from database.
	public ArrayList<book> getAllBooks() throws SQLException{
		PreparedStatement getAllBooks = conn.prepareStatement("SELECT * FROM book");
		ArrayList<book> books = new ArrayList<>();
		ResultSet result = getAllBooks.executeQuery();
		while(result.next()) {
			String ISBN = result.getString(1);
			String title = result.getString(2);
			String firstAuthor = result.getString(3);
			double price = result.getDouble(4);
			int quantityInStock = result.getInt(5);
			
			books.add(new book(ISBN, title, firstAuthor, price, quantityInStock));
		}
		return books;
	}

	//------------------------------------------------------------------------------------------------method updateBookQty to update data in database.
	public int updateBookQty(String ISBN, int quantityInStock) throws SQLException{
		PreparedStatement updateBookQty = conn.prepareStatement("UPDATE book SET quantityInStock = ? WHERE ISBN = ?");
		updateBookQty.setString(2, ISBN);
		updateBookQty.setInt(1, quantityInStock);
		return updateBookQty.executeUpdate();
	}
	//------------------------------------------------------------------------------------------------method updateBookPrice to update data in database.
	public int updateBookPrice(String ISBN, double price) throws SQLException{
		PreparedStatement updateBookPrice = conn.prepareStatement("UPDATE book SET price = ? WHERE ISBN = ?");
		updateBookPrice.setString(2, ISBN);
		updateBookPrice.setDouble(1, price);
		return updateBookPrice.executeUpdate();
	}
	//------------------------------------------------------------------------------------------------method deleteBook to delete data in database.
	public int deleteBook(String ISBN) throws SQLException{
		PreparedStatement deleteBook = conn.prepareStatement("DELETE FROM book WHERE ISBN = ?");
		deleteBook.setString(1, ISBN);
		return deleteBook.executeUpdate();
	}

	//------------------------------------------------------------------------------------------------method insertBook to add data into database.
	public int insertBook(String ISBN, String title, String firstAuthor,  double price, int quantityInStock) 
			throws SQLException{
		PreparedStatement insertBook = conn.prepareStatement("INSERT INTO book(ISBN, title, firstAuthor, price, quantityInStock)"
				+ " VALUES (?, ?, ?, ?, ?)");
		insertBook.setString(1, ISBN);
		insertBook.setString(2, title);
		insertBook.setString(3, firstAuthor);
		insertBook.setDouble(4, price);
		insertBook.setInt(5, quantityInStock);
		return insertBook.executeUpdate();
	}
	//------------------------------------------------------------------------------------------------method searchBook to retrieve data from database.
	public ArrayList<book> searchBook(String ISBN) throws SQLException{
		PreparedStatement searchBook = conn.prepareStatement("SELECT * FROM book WHERE ISBN = ?");
		searchBook.setString(1, ISBN);
		ArrayList<book> books = new ArrayList<>();
		ResultSet result = searchBook.executeQuery();
		if(result.next()) {
		String ISBN1 = result.getString(1);
		String title = result.getString(2);
		String firstAuthor = result.getString(3);
		double price = result.getDouble(4);
		int quantityInStock = result.getInt(5);
		
		books.add(new book(ISBN1, title, firstAuthor, price, quantityInStock));
		}
		return books;
	}

	
}