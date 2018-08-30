package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.math.BigDecimal;

import core.Book;
import dao.BookDAO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BookDialog dialog = new BookDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private BookDAO bookDAO;

	private BookSearchApp bookSearchApp;

	private Book previousBook = null;
	private boolean updateMode = false;
	private int oldIspn;

	//update book
	public BookDialog(BookSearchApp theBookSearchApp,
			BookDAO theBookDAO, Book thePreviousBook, boolean theUpdateMode) {
		this();
		bookDAO = theBookDAO;
		bookSearchApp = theBookSearchApp;

		previousBook = thePreviousBook;
		
		updateMode = theUpdateMode;

		if (updateMode) {
			setTitle("Update Book");
			
			populateGui(previousBook);
		} else setTitle("Add new Book Book");
		
	}
	
	//add book
	public BookDialog(BookSearchApp theBookSearchApp,
			BookDAO theBookDAO) {
		this(theBookSearchApp, theBookDAO, null, false);
	}

	private void populateGui(Book theBook) {
		oldIspn = theBook.getIsbn();
		textField.setText(String.valueOf(theBook.getIsbn()));
		textField_1.setText(theBook.getTitle());
		textField_2.setText(theBook.getAuthor());
		textField_3.setText(theBook.getPublisher());
		textField_4.setText(theBook.getPublication_year());
		textField_5.setText(String.valueOf(theBook.getSelling_price()));
		textField_6.setText(theBook.getCategory());
		textField_7.setText(String.valueOf(theBook.getThershold()));
		textField_8.setText(String.valueOf(theBook.getCopies()));		
	}

	


	/**
	 * Create the dialog.
	 */
	public BookDialog() {
		setTitle("Add Book");
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblIsbn = new JLabel("ISBN");
			GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
			gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
			gbc_lblIsbn.anchor = GridBagConstraints.EAST;
			gbc_lblIsbn.gridx = 0;
			gbc_lblIsbn.gridy = 0;
			contentPanel.add(lblIsbn, gbc_lblIsbn);
		}
		{
			textField = new JTextField();
			GridBagConstraints gbc_textField = new GridBagConstraints();
			gbc_textField.insets = new Insets(0, 0, 5, 0);
			gbc_textField.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField.gridx = 1;
			gbc_textField.gridy = 0;
			contentPanel.add(textField, gbc_textField);
			textField.setColumns(10);
		}
		{
			JLabel lblTitle = new JLabel("Title");
			GridBagConstraints gbc_lblTitle = new GridBagConstraints();
			gbc_lblTitle.anchor = GridBagConstraints.EAST;
			gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
			gbc_lblTitle.gridx = 0;
			gbc_lblTitle.gridy = 1;
			contentPanel.add(lblTitle, gbc_lblTitle);
		}
		{
			textField_1 = new JTextField();
			GridBagConstraints gbc_textField_1 = new GridBagConstraints();
			gbc_textField_1.insets = new Insets(0, 0, 5, 0);
			gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_1.gridx = 1;
			gbc_textField_1.gridy = 1;
			contentPanel.add(textField_1, gbc_textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel lblAuthor = new JLabel("Author");
			GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
			gbc_lblAuthor.anchor = GridBagConstraints.EAST;
			gbc_lblAuthor.insets = new Insets(0, 0, 5, 5);
			gbc_lblAuthor.gridx = 0;
			gbc_lblAuthor.gridy = 2;
			contentPanel.add(lblAuthor, gbc_lblAuthor);
		}
		{
			textField_2 = new JTextField();
			GridBagConstraints gbc_textField_2 = new GridBagConstraints();
			gbc_textField_2.insets = new Insets(0, 0, 5, 0);
			gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_2.gridx = 1;
			gbc_textField_2.gridy = 2;
			contentPanel.add(textField_2, gbc_textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel lblPublisher = new JLabel("Publisher");
			GridBagConstraints gbc_lblPublisher = new GridBagConstraints();
			gbc_lblPublisher.anchor = GridBagConstraints.EAST;
			gbc_lblPublisher.insets = new Insets(0, 0, 5, 5);
			gbc_lblPublisher.gridx = 0;
			gbc_lblPublisher.gridy = 3;
			contentPanel.add(lblPublisher, gbc_lblPublisher);
		}
		{
			textField_3 = new JTextField();
			GridBagConstraints gbc_textField_3 = new GridBagConstraints();
			gbc_textField_3.insets = new Insets(0, 0, 5, 0);
			gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_3.gridx = 1;
			gbc_textField_3.gridy = 3;
			contentPanel.add(textField_3, gbc_textField_3);
			textField_3.setColumns(10);
		}
		{
			JLabel lblPublicationYear = new JLabel("Publication Year");
			GridBagConstraints gbc_lblPublicationYear = new GridBagConstraints();
			gbc_lblPublicationYear.anchor = GridBagConstraints.EAST;
			gbc_lblPublicationYear.insets = new Insets(0, 0, 5, 5);
			gbc_lblPublicationYear.gridx = 0;
			gbc_lblPublicationYear.gridy = 4;
			contentPanel.add(lblPublicationYear, gbc_lblPublicationYear);
		}
		{
			textField_4 = new JTextField();
			GridBagConstraints gbc_textField_4 = new GridBagConstraints();
			gbc_textField_4.insets = new Insets(0, 0, 5, 0);
			gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_4.gridx = 1;
			gbc_textField_4.gridy = 4;
			contentPanel.add(textField_4, gbc_textField_4);
			textField_4.setColumns(10);
		}
		{
			JLabel lblSellingPrice = new JLabel("Selling price");
			GridBagConstraints gbc_lblSellingPrice = new GridBagConstraints();
			gbc_lblSellingPrice.anchor = GridBagConstraints.EAST;
			gbc_lblSellingPrice.insets = new Insets(0, 0, 5, 5);
			gbc_lblSellingPrice.gridx = 0;
			gbc_lblSellingPrice.gridy = 5;
			contentPanel.add(lblSellingPrice, gbc_lblSellingPrice);
		}
		{
			textField_5 = new JTextField();
			GridBagConstraints gbc_textField_5 = new GridBagConstraints();
			gbc_textField_5.insets = new Insets(0, 0, 5, 0);
			gbc_textField_5.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_5.gridx = 1;
			gbc_textField_5.gridy = 5;
			contentPanel.add(textField_5, gbc_textField_5);
			textField_5.setColumns(10);
		}
		{
			JLabel lblCategory = new JLabel("Category");
			GridBagConstraints gbc_lblCategory = new GridBagConstraints();
			gbc_lblCategory.anchor = GridBagConstraints.EAST;
			gbc_lblCategory.insets = new Insets(0, 0, 5, 5);
			gbc_lblCategory.gridx = 0;
			gbc_lblCategory.gridy = 6;
			contentPanel.add(lblCategory, gbc_lblCategory);
		}
		{
			textField_6 = new JTextField();
			GridBagConstraints gbc_textField_6 = new GridBagConstraints();
			gbc_textField_6.insets = new Insets(0, 0, 5, 0);
			gbc_textField_6.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_6.gridx = 1;
			gbc_textField_6.gridy = 6;
			contentPanel.add(textField_6, gbc_textField_6);
			textField_6.setColumns(10);
		}
		{
			JLabel lblThreshold = new JLabel("Threshold");
			GridBagConstraints gbc_lblThreshold = new GridBagConstraints();
			gbc_lblThreshold.anchor = GridBagConstraints.EAST;
			gbc_lblThreshold.insets = new Insets(0, 0, 5, 5);
			gbc_lblThreshold.gridx = 0;
			gbc_lblThreshold.gridy = 7;
			contentPanel.add(lblThreshold, gbc_lblThreshold);
		}
		{
			textField_7 = new JTextField();
			GridBagConstraints gbc_textField_7 = new GridBagConstraints();
			gbc_textField_7.insets = new Insets(0, 0, 5, 0);
			gbc_textField_7.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_7.gridx = 1;
			gbc_textField_7.gridy = 7;
			contentPanel.add(textField_7, gbc_textField_7);
			textField_7.setColumns(10);
		}
		{
			JLabel lblCopies = new JLabel("Copies");
			GridBagConstraints gbc_lblCopies = new GridBagConstraints();
			gbc_lblCopies.anchor = GridBagConstraints.EAST;
			gbc_lblCopies.insets = new Insets(0, 0, 0, 5);
			gbc_lblCopies.gridx = 0;
			gbc_lblCopies.gridy = 8;
			contentPanel.add(lblCopies, gbc_lblCopies);
		}
		{
			textField_8 = new JTextField();
			GridBagConstraints gbc_textField_8 = new GridBagConstraints();
			gbc_textField_8.fill = GridBagConstraints.HORIZONTAL;
			gbc_textField_8.gridx = 1;
			gbc_textField_8.gridy = 8;
			contentPanel.add(textField_8, gbc_textField_8);
			textField_8.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveBook();
					}
				});
				okButton.setActionCommand("");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	protected void saveBook() {

		// get the Book info from gui
		
		int isbn =  Integer.parseInt(textField.getText());
		String title = textField_1.getText();
		String author = textField_2.getText();
		String publisher = textField_3.getText();
		String publicationYear = textField_4.getText();
		float sellingPrice = Float.parseFloat(textField_5.getText()) ;
		String category = textField_6.getText();
		int thershold = Integer.parseInt(textField_7.getText());
		int copies = Integer.parseInt(textField_8.getText());
		System.out.println("1");

		Book tempBook = null;

		if (updateMode) {
			tempBook = previousBook;
			tempBook.setIsbn(isbn);
			tempBook.setAuthor(author);
			tempBook.setCategory(category);
			tempBook.setPublication_year(publicationYear);
			tempBook.setPublisher(publisher);
			tempBook.setSelling_price(sellingPrice);
			tempBook.setThershold(thershold);
			tempBook.setTitle(title);	
		} else {
//			ispn, title, author, publisher, publication_year,selling_price,category,thershold,copies
			System.out.println("2");
			tempBook = new Book(isbn, title, author, publisher, publicationYear,sellingPrice,category,thershold,copies);
		}

		try {
			// save to the database
			if (updateMode) {
				bookDAO.updateBook(tempBook, oldIspn);
			} else {
				bookDAO.addBook(tempBook);
			}
			System.out.println("3");
			// close dialog
			setVisible(false);
			dispose();
			System.out.println("4");

			// refresh gui list
			bookSearchApp.refreshBookView();
			System.out.println("5");
			// show success message
			JOptionPane.showMessageDialog(bookSearchApp,
					"Book saved succesfully.", "Book Saved",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(bookSearchApp,
					"Error saving employee: " + exc.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

}
