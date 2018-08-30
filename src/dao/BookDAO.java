package dao;

import java.util.*;
import java.sql.*;
import java.io.*;
import java.math.BigDecimal;

import core.Book;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class BookDAO {

	private Connection myConn;
	
	public BookDAO() throws Exception {
		
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
	}
	
	public void updateBook(Book theBook, int oldIspn) throws SQLException {
		PreparedStatement myStmt = null;

		try {
		//	ispn, title, author, publisher, publication_year,
		//	selling_price,category,thershold,copies
			// prepare statement
			myStmt = myConn.prepareStatement("update book"
					+ " set isbn=?, title=?, author=?, publisher=?, publication_year=?, "
					+ "selling_price=?, category=?, threshold=?, copies=?"
					+ " where isbn=?");
			
			// set params
			myStmt.setInt(1, theBook.getIsbn());
			myStmt.setString(2, theBook.getTitle());
			myStmt.setString(3, theBook.getAuthor());
			myStmt.setString(4, theBook.getPublisher());
			myStmt.setString(5, theBook.getPublication_year());
			myStmt.setFloat(6, theBook.getSelling_price());
			myStmt.setString(7, theBook.getCategory());
			myStmt.setInt(8, theBook.getThershold());
			myStmt.setInt(9, theBook.getCopies());
			myStmt.setInt(10, oldIspn);
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	public void addBook(Book theBook) throws Exception {
		PreparedStatement myStmt = null;

		try {
			//	ispn, title, author, publisher, publication_year,
			//	selling_price,category,thershold,copies
			// prepare statement
			myStmt = myConn.prepareStatement("insert into book"
					+ " (isbn, title, author, publisher, publication_year, "
					+ "selling_price, category, threshold, copies)"
					+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			// set params
			myStmt.setInt(1, theBook.getIsbn());
			myStmt.setString(2, theBook.getTitle());
			myStmt.setString(3, theBook.getAuthor());
			myStmt.setString(4, theBook.getPublisher());
			myStmt.setString(5, theBook.getPublication_year());
			myStmt.setFloat(6, theBook.getSelling_price());
			myStmt.setString(7, theBook.getCategory());
			myStmt.setInt(8, theBook.getThershold());
			myStmt.setInt(9, theBook.getCopies());
			
			// execute SQL
			myStmt.executeUpdate();			
		}
		finally {
			close(myStmt);
		}
		
	}
	
	public void deleteBook(int ispn) throws SQLException{
		PreparedStatement myStmt = null;
		try {
			myStmt = myConn.prepareStatement("delete from employees where ispn=?");
			myStmt.setInt(1, ispn);
			myStmt.executeUpdate();
		}
		finally {
			close(myStmt);
		}
	}
	
	public List<Book> getAllBooks() throws Exception {
		List<Book> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from book");
			
			while (myRs.next()) {
				Book temp = convertRowToEmployee(myRs);
				list.add(temp);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	public List<Book> searchBook(int isbn, String title, 
			String category, String author, String publisher) throws Exception {
		List<Book> list = new ArrayList<>();

		Statement myStmt = null;
		ResultSet myRs = null;

		try {
			String sql = "select * from book where ";
			int count = 0, first = 0;
			if(isbn != -1)count++;
			if(title != null)count++;
			if(category != null)count++;	
			if(author != null)count++;	
			if(publisher != null)count++;
				
			if(isbn != -1) {
				if(count > 0 && first != 0) sql+= " and ";	
				count --;
				first = 1;
				sql += "isbn="+isbn;
			}
			if(title != null) {
				if(count > 0 && first != 0) sql+= " and ";
				first = 1;
				count --;
				title += "%";
				sql += "title like '" + title + "'";
			}
			if(category != null) {
				if(count > 0 && first != 0) sql+= " and ";
				first = 1;
				count --;
				category += "%";
				sql += "category like '" + category +"'";
			}
			if(author != null) {
				if(count > 0 && first != 0) sql+= " and ";
				first = 1;
				count --;
				author += "%";
				sql += "author like '"+ author +"'";
			}
			if(publisher != null) {
				if(count > 0 && first != 0) sql+= " and ";
				first = 1;
				count --;
				publisher += "%";
				sql += "publisher like '"+ publisher +"'";
			}
			System.out.println("sql= "+sql);
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while (myRs.next()) {
				Book temp = convertRowToEmployee(myRs);
				list.add(temp);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	private Book convertRowToEmployee(ResultSet myRs) throws SQLException {
        //ispn, title, author, publisher, publication_year,
    	//	selling_price,category,thershold,copies
		int ispn = myRs.getInt("isbn");
		String title = myRs.getString("title");
		String author = myRs.getString("author");
		String publisher = myRs.getString("publisher");
		String publication_year = myRs.getString("publication_year");
		float selling_price = myRs.getFloat("selling_price");
		String category = myRs.getString("category");
		int thershold = myRs.getInt("threshold");
		int copies = myRs.getInt("copies");
		
		return new Book(ispn, title, author, publisher, publication_year,
		    	selling_price,category,thershold,copies);
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	private void close(Statement myStmt) throws SQLException {
		close(null, myStmt, null);		
	}
	
	public static void main(String[] args) throws Exception {
		
		BookDAO dao = new BookDAO();
		//searchBook( isbn, title, category, author, publisher)
		System.out.println(dao.searchBook(-1, null ,null,"Auther1","PUB_1"));

	//	System.out.println(dao.getAllBooks());
	}

}
