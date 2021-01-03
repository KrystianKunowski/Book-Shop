package assignmentBookShop;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


@SuppressWarnings("serial")
public class bookShopPanel extends JPanel{

	//GUI elements
	private JPanel displayPanel, addPanel, updatePricePanel, updateStockPanel,searchBookPanel, deletePanel;
	private JTextArea displayArea;
	JButton addButton, deleteButton,updatePriceButton,searchBookButton, updateStockButton;
	private JTextField titleField, idField, qtyField, firstAuthorField, priceField, deleteField,searchField, updateStockISBNField,qtyStockField, upadatePriceField, updateIDField;

	private bookShop bookShopApp2 = bookShopApp.getbookShop();
	JLabel titleLabel = new JLabel("Title:"), idLabel = new JLabel("ISBN:"), 
			qtyLabel = new JLabel("Qty:"), firstAuthorLabel= new JLabel("Author:"),
			priceLabel = new JLabel("Price:");
	
	
	private Locale locale;
	private ResourceBundle bun;
	
	public bookShopPanel() {
		//-------------------------------------------------------------------------retrieve translation for English language (default).			
		locale = Locale.getDefault();
		bun = ResourceBundle.getBundle("lang2",locale);

		this.add(displayPanel = new JPanel(), BorderLayout.NORTH);
		displayPanel.setLayout(new FlowLayout());
		this.add(addPanel = new JPanel(), BorderLayout.CENTER);
		addPanel.setLayout(new FlowLayout());
//-------------------------------------------------------------------------------------instantiate addPanel and add it to the frame.
		this.add(updatePricePanel = new JPanel(), BorderLayout.CENTER);
		updatePricePanel.setLayout(new FlowLayout());
//-------------------------------------------------------------------------------------instantiate updatePricePanel and add it to the frame.
		
		this.add(updateStockPanel = new JPanel(), BorderLayout.CENTER);
		updateStockPanel.setLayout(new FlowLayout());
		
//-------------------------------------------------------------------------------------instantiate updateStockPanel and add it to the frame.
		
		this.add(searchBookPanel = new JPanel(), BorderLayout.CENTER);
		searchBookPanel.setLayout(new FlowLayout());
//--------------------------------------------------------------------------------------instantiate searchBookPanel and add it to the frame.
		this.add(deletePanel = new JPanel(), BorderLayout.CENTER);
		deletePanel.setLayout(new FlowLayout());
		//-------------------------------------------------------------------------------instantiate deletePanel and add it to the frame.
		
		//add the Text area called "displayArea" to the display panel
		displayPanel.add(displayArea = new JTextArea("", 15, 65));
		try(bookShopQueries queries = new bookShopQueries()){	
			bookShopApp2.setBooks(queries.getAllBooks());
			for(book boo : bookShopApp2.getBooks())
				displayArea.append(boo.toString() + "\n");
		}catch(SQLException | IOException ex) {
			JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred when trying to load records from the database");
			ex.printStackTrace();
		}
//----------------------------------------------------------------------------------adding 5 labels, 5 text fields and button to the add panel.
		addPanel.setBorder(new TitledBorder("Add Books ..."));
		titleLabel = new JLabel(bun.getString("title"));
		addPanel.add(titleLabel);
		addPanel.add(titleField = new JTextField(16));
		idLabel = new JLabel(bun.getString("ISBN"));
		addPanel.add(idLabel);
		addPanel.add(idField = new JTextField(10));
		qtyLabel = new JLabel(bun.getString("qty"));
		addPanel.add(qtyLabel);
		addPanel.add(qtyField = new JTextField(10));
		firstAuthorLabel = new JLabel(bun.getString("author"));
		addPanel.add(firstAuthorLabel);
		addPanel.add(firstAuthorField = new JTextField(10));
		
		priceLabel = new JLabel(bun.getString("price"));
		addPanel.add(priceLabel);
		addPanel.add(priceField = new JTextField(10));
		
		addButton = new JButton(bun.getString("add"));
		addPanel.add(addButton);
//------------------------------------------------------------------------------------adding 2 labels, 2 text fields and button to the update price panel.
		updatePricePanel.setBorder(new TitledBorder("Update Price ..."));
		
		idLabel = new JLabel(bun.getString("ISBN"));
		updatePricePanel.add(idLabel);
		updatePricePanel.add(updateIDField = new JTextField(10));
		
		priceLabel = new JLabel(bun.getString("price"));
		updatePricePanel.add(priceLabel);
		updatePricePanel.add(upadatePriceField = new JTextField(10));
		
		updatePriceButton = new JButton(bun.getString("update"));
		updatePricePanel.add(updatePriceButton);
//-------------------------------------------------------------------------------------adding 2 labels, 2 text fields and button to the update stock panel.	
		updateStockPanel.setBorder(new TitledBorder("Update Stock ..."));
		
		idLabel = new JLabel(bun.getString("ISBN"));
		updateStockPanel.add(idLabel);
		updateStockPanel.add(updateStockISBNField = new JTextField(10));
		
		qtyLabel = new JLabel(bun.getString("qty"));
		updateStockPanel.add(qtyLabel);
		updateStockPanel.add(qtyStockField = new JTextField(10));
		
		updateStockButton = new JButton(bun.getString("update"));
		updateStockPanel.add(updateStockButton);
//--------------------------------------------------------------------------------------adding label, text field and button to the search book panel.
		searchBookPanel.setBorder(new TitledBorder("Search Books ..."));
		
		idLabel = new JLabel(bun.getString("ISBN"));
		searchBookPanel.add(idLabel);
		searchBookPanel.add(searchField = new JTextField(10));
		
		searchBookButton = new JButton(bun.getString("search"));
		searchBookPanel.add(searchBookButton);

//--------------------------------------------------------------------------------------adding label, text field and button to the delete panel.
		deletePanel.setBorder(new TitledBorder("Delete Books ..."));
		idLabel = new JLabel(bun.getString("ISBN"));
		deletePanel.add(idLabel);
		deletePanel.add(deleteField = new JTextField(10));
		deleteButton = new JButton(bun.getString("delete"));
		deletePanel.add(deleteButton);
		
//--------------------------------------------------------------------------------------register event with the button "add" from add panel
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){							
				String title = titleField.getText();
				if(title == null || title.equals("")) 
					JOptionPane.showMessageDialog(getRootPane(), "Title cannot be empty!");
				
//------------------------------------------------------------------------------------------ISBN Pattern
				
				String ISBN = idField.getText();
				if(!Pattern.matches("[0-9]{3}-[0-9]{1}-[0-9]{2}-[0-9]{6}-[0-9]{1}", ISBN)) {
					JOptionPane.showMessageDialog(getRootPane(), "Enter a ISBN in the following format : XXX-X-XX-XXXXXX-X, where X is a digit");
				}else
					
				{
					ISBN = idField.getText();
				}

//------------------------------------------------------------------------------------------Quantity Pattern	
				int quantityInStock;
				String qtyChcek = qtyField.getText();
				if(!Pattern.matches("[0-9]{1,2}", qtyChcek)) {
					JOptionPane.showMessageDialog(getRootPane(), "Quantity cannot be negative and need to be represented in maximum of 2 digits");
				return;
				}else 
				
				{
					quantityInStock = Integer.parseInt(qtyChcek);
				}
				
//-------------------------------------------------------------------------------------------Price Pattern
				double price;
				String priceChcek = priceField.getText();
				if(!Pattern.matches("[0-9]{1,3}\\.?[0-9]{1,2}", priceChcek)) {
					JOptionPane.showMessageDialog(getRootPane(), "Price cannot be negative and need to be represented in maximum of 3 digits with maximum of 2 decimal places");
				return;
				}else 
				
				{
					price = Double.parseDouble(priceChcek);
				}
//----------------------------------------------------------------------------------------------trigger to add data into database / retrieve data from database			
				
				String firstAuthor = firstAuthorField.getText();
				try(bookShopQueries queries = new bookShopQueries()){
					displayArea.setText("");
					queries.insertBook(ISBN, title, firstAuthor, price, quantityInStock);
					bookShopApp2.setBooks(queries.getAllBooks());
					for(book boo : bookShopApp2.getBooks())
						displayArea.append(boo.toString() + "\n");
				}catch(SQLException | IOException ex) {
					JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred while trying to access the database");
					//clear the fields
					titleField.setText("");
					idField.setText("");
					qtyField.setText("");
					firstAuthorField.setText("");
					priceField.setText("");
					ex.printStackTrace();
				}
				//clear the fields
				titleField.setText("");
				idField.setText("");
				qtyField.setText("");
				firstAuthorField.setText("");
				priceField.setText("");
			}
		});
		
//--------------------------------------------------------------------------------------register event with the button "delete" from delete panel.		
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
				
//--------------------------------------------------------------------------------------ISBN Pattern
				
				String ISBN = deleteField.getText();
				if(!Pattern.matches("[0-9]{3}-[0-9]{1}-[0-9]{2}-[0-9]{6}-[0-9]{1}", ISBN)) {
					JOptionPane.showMessageDialog(getRootPane(), "Enter a ISBN in the following format : XXX-X-XX-XXXXXX-X, where X is a digit");
				}else
					
				{
					ISBN = deleteField.getText();
				}

//--------------------------------------------------------------------------trigger to delete data into database / retrieve data from database.						
				
				try(bookShopQueries queries = new bookShopQueries()){
					displayArea.setText("");
					queries.deleteBook(ISBN);
					bookShopApp2.setBooks(queries.getAllBooks());
					for(book boo : bookShopApp2.getBooks())
						displayArea.append(boo.toString() + "\n");
				}catch(SQLException | IOException ex) {
					JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred while trying to access the database");
					//clear the fields
					deleteField.setText("");
					ex.printStackTrace();
				}				
				//clear the fields
				deleteField.setText("");
			}
		});
		
		
//------------------------------------------------------------------------------------register event with the button "search" from search book panel
		searchBookButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

//--------------------------------------------------------------------------------------ISBN Pattern
				
				String ISBN = searchField.getText();
				if(!Pattern.matches("[0-9]{3}-[0-9]{1}-[0-9]{2}-[0-9]{6}-[0-9]{1}", ISBN)) {
					JOptionPane.showMessageDialog(getRootPane(), "Enter a ISBN in the following format : XXX-X-XX-XXXXXX-X, where X is a digit");
				}else
					
				{
					ISBN = searchField.getText();
				}

//--------------------------------------------------------------------------trigger to retrieve data from database and retrieve particular 
//--------------------------------------------------------------------------searching data and display it in showMessageDialog.								
				
				try(bookShopQueries queries = new bookShopQueries()){
					displayArea.setText("");
					queries.searchBook(ISBN);
					bookShopApp2.setBooks(queries.getAllBooks());
					for(book boo : bookShopApp2.getBooks())
						displayArea.append(boo.toString() + "\n");
					JOptionPane.showMessageDialog(getRootPane(), "Searching Book : " + queries.searchBook(ISBN));
				}catch(SQLException | IOException ex) {
					JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred while trying to access the database");
					//clear the fields
					searchField.setText("");
					ex.printStackTrace();
				}				
				//clear the fields
				searchField.setText("");
			}
		});
		
//---------------------------------------------------------------------------------------register event with the button "update" from update stock panel
		
		updateStockButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

//--------------------------------------------------------------------------------------ISBN Pattern
				String ISBN = updateStockISBNField.getText();
				if(!Pattern.matches("[0-9]{3}-[0-9]{1}-[0-9]{2}-[0-9]{6}-[0-9]{1}", ISBN)) {
					JOptionPane.showMessageDialog(getRootPane(), "Enter a ISBN in the following format : XXX-X-XX-XXXXXX-X, where X is a digit");
				}else
					
				{
					ISBN = updateStockISBNField.getText();
				}
//--------------------------------------------------------------------------------------Stock Pattern
				int quantityInStock;
				String qtyChcek2 = qtyStockField.getText();
				if(!Pattern.matches("[0-9]{1,2}", qtyChcek2)) {
					JOptionPane.showMessageDialog(getRootPane(), "Quantity cannot be negative and need to be represented in maximum of 2 digits");
				return;
				}else 
				
				{
					quantityInStock = Integer.parseInt(qtyChcek2);
				}
//-----------------------------------------------------------------------------------------------trigger to update and retrieve data from database.			
				
				try(bookShopQueries queries = new bookShopQueries()){
					displayArea.setText("");
					queries.updateBookQty(ISBN, quantityInStock);
					bookShopApp2.setBooks(queries.getAllBooks());
					for(book boo : bookShopApp2.getBooks())
					displayArea.append(boo.toString() + "\n");
				}catch(SQLException | IOException ex) {
					JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred while trying to access the database");
					//clear the fields
					updateStockISBNField.setText("");
					qtyStockField.setText("");
					ex.printStackTrace();
				}				
				//clear the fields
				updateStockISBNField.setText("");
				qtyStockField.setText("");
			}
		});
		
		
//--------------------------------------------------------------------------------------register event with the button "update" from update price panel
		
		updatePriceButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
//--------------------------------------------------------------------------------------ISBN Pattern				
				String ISBN = updateIDField.getText();
				if(!Pattern.matches("[0-9]{3}-[0-9]{1}-[0-9]{2}-[0-9]{6}-[0-9]{1}", ISBN)) {
					JOptionPane.showMessageDialog(getRootPane(), "Enter a ISBN in the following format : XXX-X-XX-XXXXXX-X, where X is a digit");
				}else
					
				{
					ISBN = updateIDField.getText();
				}
//--------------------------------------------------------------------------------------Price Pattern
				double price;
				String priceChcek2 = upadatePriceField.getText();
				if(!Pattern.matches("[0-9]{1,3}\\.?[0-9]{1,2}", priceChcek2)) {
					JOptionPane.showMessageDialog(getRootPane(), "Price cannot be negative and need to be represented in maximum of 3 digits with maximum of 2 decimal places");
				return;
				}else 
				
				{
					price = Double.parseDouble(priceChcek2);
				}
//-------------------------------------------------------------------------------------------------trigger to update and retrieve data from database.				
				
				try(bookShopQueries queries = new bookShopQueries()){
					displayArea.setText("");
					queries.updateBookPrice(ISBN, price);
					bookShopApp2.setBooks(queries.getAllBooks());
					for(book boo : bookShopApp2.getBooks())
					displayArea.append(boo.toString() + "\n");
				}catch(SQLException | IOException ex) {
					JOptionPane.showMessageDialog(getRootPane(), "A problem has occurred while trying to access the database");
					//clear the fields
					updateIDField.setText("");
					upadatePriceField.setText("");
					ex.printStackTrace();
				}				
				//clear the fields
				updateIDField.setText("");
				upadatePriceField.setText("");
			}
		});
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.getWidth(), this.getHeight());
	}
}
