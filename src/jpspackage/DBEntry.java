package jpspackage;

public class DBEntry {
//	private String;
	private String type;
	private String info;
	
	public static final String INFO_LINK = "link";
	public static final String INFO_IMAGE = "image";
	
	
	public DBEntry(String type, String info) {
		// TODO Auto-generated constructor stub
		this.type = type;
		this.info = info; 
	}
	
	public String getType() {
		return this.type;
	}
	
	public String getInfo() {
		return this.info;
	}

}
