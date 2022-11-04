package View;

public abstract class View {

	public View prevView;

	protected abstract void start();

	protected void end() {
		// TODO - implement View.end
		throw new UnsupportedOperationException();
	}

	protected void navigateNextView(View cur, View next) {
		cur.prevView = next;
		next.start();
	}

	protected void getPrevView() {
		// TODO - implement View.getPrevView
		throw new UnsupportedOperationException();
	}

}