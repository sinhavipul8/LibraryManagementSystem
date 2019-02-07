package library;


	public class NoBookAvailableException extends Exception {
		/**
	* 
	*/
		private static final long serialVersionUID = 1L;

		/**
	* 
	*/

		NoBookAvailableException(int n) {

			System.out
					.println("All copies are already issued, copy is available in Library");
		}
	}
