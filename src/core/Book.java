package core;

import java.math.BigDecimal;

/**
 * 
 * @author www.luv2code.com
 *
 */
public class Book {

	private int isbn;
	private String title;
	private String author;
	private String publisher;
	private String publication_year;
	private float selling_price;
	private String category;
	private int thershold;
	private int copies;

	
	
	public Book(int ispn, String title, String author,
			String publisher, String publication_year,float selling_price,
			String category, int thershold, int  copies) {
		super();
		this.isbn = ispn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.publication_year = publication_year;
		this.selling_price = selling_price;
		this.category = category;
		this.thershold = thershold;
		this.copies = copies;
	}
	
	
	
	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int ispn) {
		this.isbn = ispn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getPublication_year() {
		return publication_year;
	}

	public void setPublication_year(String publication_year) {
		this.publication_year = publication_year;
	}

	public float getSelling_price() {
		return selling_price;
	}

	public void setSelling_price(float selling_price) {
		this.selling_price = selling_price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getThershold() {
		return thershold;
	}

	public void setThershold(int thershold) {
		this.thershold = thershold;
	}

	public int getCopies() {
		return copies;
	}

	public void setCopies(int copies) {
		this.copies = copies;
	}

	@Override
	public String toString() {
		return String
				.format("Book [ispn=%d, title=%s, author=%s, "
						+ "publisher=%s, publication_year=%s,"
						+ "selling_price=%f,category=%s,thershold=%d,copies=%d]",
				isbn, title, author, publisher, publication_year,
				selling_price,category,thershold,copies);
	}
	
	
		
}
