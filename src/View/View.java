package View;

import java.util.Scanner;

public class View {

	public View prevView;

	public void start() {
		System.out.print("------------- MAIN MENU -------------\n"
				+ "1: I am a Movie Goer\n"
				+ "2: I am a Staff\n"
				+ "3: Quit application\n\n"
				+ "Please enter your choice: ");
		Scanner scan = new Scanner(System.in);
		int choice = scan.nextInt();
		if (choice == 1) {
			navigateNextView(this, new MovieGoerView());
		} else if (choice == 2) {
			navigateNextView(this, new StaffView());
		} else
			this.end();
	}

	protected void end() {
		if (this.prevView != null)
			this.prevView.start();
		else {
			System.out.println("Thank you for using MOBLIMA!");
			System.exit(0);
		}
	}

	/**
	 * This method navigates the user from one View to the next.
	 * This allows the user to return to a previous View.
	 * 
	 * @param cur
	 * @param next
	 */
	protected void navigateNextView(View cur, View next) {
		next.prevView = cur;
		next.start();
	}

	protected View getPrevView() {
		return this.prevView;
	}

}