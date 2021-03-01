package f21as_coursework;

public class IncorrectOrderException extends Exception {
	public IncorrectOrderException(String id) {
		super("Incorrect ID provided " + id);
	}
}
