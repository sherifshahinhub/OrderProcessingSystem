package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import core.Book;
import dao.BookDAO;
import javax.swing.JScrollPane;

public class BookSearchApp extends JDialog {
	private JTextField ISBNtextField;
	private JTextField titletextField;
	private JTextField categorytextField_1;
	private JTextField authortextField;
	private JTextField publishertextField;
	private BookDAO  bookDAO;
	private JScrollPane scrollPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BookSearchApp dialog = new BookSearchApp();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setSize(1000, 500);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BookSearchApp() {
		// create the DAO
		try {
			bookDAO = new BookDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		setBounds(100, 100, 810, 475);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(10);
			flowLayout.setHgap(10);
			flowLayout.setAlignment(FlowLayout.LEFT);
			getContentPane().add(panel, BorderLayout.NORTH);
			{
				JLabel lblEnterIspn = new JLabel("ISBN");
				panel.add(lblEnterIspn);
			}
			{
				ISBNtextField = new JTextField();
				panel.add(ISBNtextField);
				ISBNtextField.setColumns(10);
			}
			{
				JLabel lblTitle = new JLabel("Title");
				panel.add(lblTitle);
			}
			{
				titletextField = new JTextField();
				panel.add(titletextField);
				titletextField.setColumns(10);
			}
			{
				JLabel lblCategory = new JLabel("Category");
				panel.add(lblCategory);
			}
			{
				categorytextField_1 = new JTextField();
				panel.add(categorytextField_1);
				categorytextField_1.setColumns(10);
			}
			{
				JLabel lblAuthor = new JLabel("Author");
				panel.add(lblAuthor);
			}
			{
				authortextField = new JTextField();
				panel.add(authortextField);
				authortextField.setColumns(10);
			}
			{
				JLabel lblPublisher = new JLabel("Publisher");
				panel.add(lblPublisher);
			}
			{
				publishertextField = new JTextField();
				panel.add(publishertextField);
				publishertextField.setColumns(10);
			}
			{
				JButton btnSearch = new JButton("Search");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							/*
							private JTextField ISBNtextField;
							private JTextField titletextField;
							private JTextField categorytextField_1;
							private JTextField authortextField;
							private JTextField publishertextField;
							*/
							String isbn = ISBNtextField.getText();
							String title = titletextField.getText();
							String category = categorytextField_1.getText();
							String author = authortextField.getText();
							String publisher = publishertextField.getText();
							int isbs_int;
							
							List<Book> books = null;
							
							//in case of spaces
							if (isbn != null && isbn.length() > 0) {
								isbs_int = Integer.parseInt(isbn);
							} else {
								isbs_int = -1;
							}
							if(title != null && title.length() == 0) title = null;
							if(category != null && category.length() == 0) category = null;
							if(author != null && author.length() == 0) author = null;
							if(publisher != null && publisher.length() == 0) publisher = null;
							
							if(isbs_int == -1 && title == null && category == null && author == null && publisher == null)
								books = bookDAO.getAllBooks();
							else books = bookDAO.searchBook(isbs_int, title, category, author, publisher);
							
							
							// create the model and update the "table"
							BookTableModel model = new BookTableModel(books);
							
							table.setModel(model);
							
							/*
							for (Employee temp : employees) {
								System.out.println(temp);
							}
							*/
						} catch (Exception exc) {
							JOptionPane.showMessageDialog(BookSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
						}
					}
				});
				panel.add(btnSearch);
			}
		}
		{
			scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.CENTER);
			
			table = new JTable();
			scrollPane.setViewportView(table);
			
		//	table = new JTable();
		//	getContentPane().add(table, BorderLayout.CENTER);
			
		}
		{
			JPanel panel = new JPanel();
			getContentPane().add(panel, BorderLayout.SOUTH);
			{
				JButton btnAddBook = new JButton("Add Book");
				btnAddBook.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						// create dialog
						BookDialog dialog = new BookDialog(BookSearchApp.this, bookDAO);

						// show dialog
						dialog.setVisible(true);
					}
				});
				panel.add(btnAddBook);
			}
			{
				JButton btnUpdateBook = new JButton("Update Book");
				btnUpdateBook.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						// get the selected item
						int row = table.getSelectedRow();
						
						// make sure a row is selected
						if (row < 0) {
							JOptionPane.showMessageDialog(BookSearchApp.this, "You must select a book", "Error",
									JOptionPane.ERROR_MESSAGE);				
							return;
						}
						
						// get the current employee
						Book tempBook = (Book) table.getValueAt(row, BookTableModel.OBJECT_COL);
						
						// create dialog
						BookDialog dialog = new BookDialog(BookSearchApp.this, bookDAO, tempBook, true);

						// show dialog
						dialog.setVisible(true);
					}
				});
				panel.add(btnUpdateBook);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			getContentPane().add(scrollPane, BorderLayout.WEST);
		}
	}
	public void refreshBookView() {

		try {
			List<Book> books = bookDAO.getAllBooks();

			// create the model and update the "table"
			BookTableModel model = new BookTableModel(books);

			table.setModel(model);
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
}










