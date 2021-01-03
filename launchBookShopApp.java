package assignmentBookShop;


public class launchBookShopApp {
//-------------------------------------------------launching Application...
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(
				() -> new bookShopApp()
			);

	}

}
