package prob3;

public class Book {
	private int bookNum;
	private String title;
	private String genre;
	private int stateCode;
	
	public Book(int i, String string, String string2) {
		// TODO Auto-generated constructor stub
		this.bookNum = i;
		this.title = string;
		this.genre = string2;
		this.stateCode = 1;
	}
	
	public int getBookNum() {
		return bookNum;
	}

	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public void rent() {
		this.stateCode = 0;
		System.out.println(this.title + "이(가) 대여 됐습니다.");
	}
	
	public void print() {
		String s = "";
		if(this.stateCode == 1) {
			s = "재고 있음";
		}else {
			s = "재고 없음";
		}
		System.out.println("책 제목:"+this.title+", 장르:"+this.genre+", 대여 유무"+ s);
	}
	
}
