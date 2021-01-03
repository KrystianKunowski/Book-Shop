package assignmentBookShop;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;


@SuppressWarnings("serial")
public class bookShopApp extends JFrame {
//----------------------------------------------------------path for "logo" file !	
	private JPanel upper = new JPanel();
	private logoBookShopPanel logoPanel = new logoBookShopPanel(".\\src\\assignmentBookShop\\bookshop_logo.png");
	
	private bookShopPanel books = new bookShopPanel();
	private JTabbedPane tabs = new JTabbedPane();
	
    JButton language;
	private JPanel bottom = new JPanel();

	private static bookShop bookShop = new bookShop();			
	public static bookShop getbookShop() {
	return bookShop;
	}
	
	public bookShopApp() {
//-------------------------------------------------------------------------adding panels to main frame / showing main frame
		super("BookShop Application created by Krystian Kunowski");
		this.setSize(1000, 700);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.add(upper, BorderLayout.NORTH);
		upper.setLayout(new FlowLayout());
		upper.add(logoPanel);
		
		this.add(bottom, BorderLayout.SOUTH);
		bottom.setLayout(new FlowLayout());
		
		
//--------------------------------------------------------------------instantiate language button and add it to the frame.		
		language = new JButton(("Change language to Polish"));
		bottom.add(language);

//--------------------------------------------------------------------register event with the button "Change language to Polish" from bottom panel
		language.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){  

			ResourceBundle bun;
//-------------------------------------------------------------------------retrieve translation for polish language				
			Locale locale = new Locale("pl","PL");
			bun = ResourceBundle.getBundle("lang2_pl",locale);
				
			  books.titleLabel.setText(bun.getString("title"));
			  books.idLabel.setText(bun.getString("ISBN"));
			  books.qtyLabel.setText(bun.getString("qty"));
			  books.firstAuthorLabel.setText(bun.getString("author"));
			  books.priceLabel.setText(bun.getString("price"));
			  books.addButton.setText(bun.getString("add"));
			  books.updatePriceButton.setText(bun.getString("update"));
			  books.updateStockButton.setText(bun.getString("update"));
			  books.searchBookButton.setText(bun.getString("search"));
			  books.deleteButton.setText(bun.getString("delete")); 
				
			     }  
		      });  
		//-------------------------------------------------------------------------adding tab to main frame	
		tabs.addTab("BookShop", books);
		this.add(tabs, BorderLayout.CENTER);
		this.repaint();	
	}
}
