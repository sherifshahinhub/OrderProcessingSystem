package ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import core.Book;

class BookTableModel extends AbstractTableModel {
	public static final int OBJECT_COL = -1;
	private static final int isbn = 0;
	private static final int title = 1;
	private static final int author = 2;
	private static final int publisher = 3;
	private static final int publication_year = 4;
	private static final int selling_price = 5;
	private static final int category = 6;
	private static final int thershold = 7;
	private static final int copies = 8;

	private String[] columnNames = { "ISBN", "title", "author",
			"publisher","publication_year", "selling_price",  "category", 
			"thershold", "copies"};
	private List<Book> books;

	public BookTableModel(List<Book> theBooks) {
		books = theBooks;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
	
		Book tempBook = books.get(row);

		switch (col) {
		case isbn:
			return tempBook.getIsbn();
		case title:
			return tempBook.getTitle();
		case author:
			return tempBook.getAuthor();
		case publisher:
			return tempBook.getPublisher();
		case publication_year:
			return tempBook.getPublication_year();
		case selling_price:
			return tempBook.getSelling_price();
		case category:
			return tempBook.getCategory();
		case thershold:
			return tempBook.getThershold();
		case OBJECT_COL:
			return tempBook;
		default:
			return tempBook.getCopies();
		}
	}

	@Override
	public Class getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}
