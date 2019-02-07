package library;


public class MaximumBookIssuedException extends Exception {
	/**
* 
*/
	private static final long serialVersionUID = 1L;

	MaximumBookIssuedException() {
		System.out.println("Maximum Book issued by member");
	}
}