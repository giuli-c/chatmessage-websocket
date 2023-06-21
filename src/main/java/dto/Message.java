package dto;

public class Message {
	private String name;
	private String textMex;
	
	public Message() {
		
	}
	
	public Message(String name, String t) {
		this.name = name;
		this.textMex = t;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTextMex() {
		return textMex;
	}

	public void setTextMex(String textMex) {
		this.textMex = textMex;
	}
	
	
}
