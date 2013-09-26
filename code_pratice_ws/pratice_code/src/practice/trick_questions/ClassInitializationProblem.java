package practice.trick_questions;

public class ClassInitializationProblem {

	public static void main(String[] s) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				// if this line runs first it will cause deadlock
//				App.getInstance().run();
			}
		}).start();

		// if this line runs first, we will get a NPE.
		Controller.getInstance().getRetries();
	}
}

final class App {
	// if we make it Integer the program either deadlocks or throws Null Pointer Exception.
	public static final Integer RETRIES = 3;
	private static final App instance = new App(Controller.getInstance());

	private Controller controller;
	public App(Controller controller) {
		this.controller = controller;
	}

	public void run() {
		controller.run();
	}

	public static App getInstance() {
		return instance;
	}
}

final class Controller {
	private static final Controller instance = new Controller(App.RETRIES);
	private final Integer retries;

	public Controller(Integer retries) {
		this.retries = retries;
	}

	public static Controller getInstance(){
		if (instance == null)
			throw new RuntimeException("should not happen");
		return instance;
	}

	public void run() {

	}

	public Integer getRetries() {
		return retries;
	}
}