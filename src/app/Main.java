package app;

import providers.InfoProvider;

public class Main {

	public static void main(String[] args) {
		InfoProvider infoProvider = new InfoProvider();
		infoProvider.getTasklistDetailsStream();
	}
}