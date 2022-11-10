package View;

import static Controller.InputController.readUserChoice; 

public class View {

	public View prevView;

	/**
	 * This method displays the menu and prompts for the user's choice
	 */
	public void start() {
		System.out.print("------------- MOBLIMA MAIN MENU -------------\n"
				+ "1: I am a Movie Goer\n"
				+ "2: I am a Staff\n"
				+ "3: Quit application\n\n"
				+ "Please enter your choice: ");

		int choice = readUserChoice(3,1); 
		if (choice == 1) {
			navigateNextView(this, new MovieGoerView());
		} else if (choice == 2) {
			navigateNextView(this, new StaffView());
		} else
			this.end();
	}

	/**
	 * This method terminates the current View
	 */
	protected void end() {
		if (this.prevView != null)
			this.prevView.start();
		else {
			System.out.println("Thank you for using MOBLIMA!");
			System.exit(0);
		}
	}

	/**
	 * This method navigates the user from one View to the next
	 * 
	 * @param cur
	 * @param movieListingsView
	 */
	protected void navigateNextView(View cur, View next) {
		next.prevView = cur;
		next.start();
	}

	/**
	 * This method gets the previous View and returns it
	 * 
	 * @return prevView
	 */
	protected View getPrevView() {
		return this.prevView;
	}

}