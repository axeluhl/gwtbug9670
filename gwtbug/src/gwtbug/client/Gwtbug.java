package gwtbug.client;

import java.util.function.Consumer;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Gwtbug implements EntryPoint {
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final A1.C<String> instance1 = new A1.C<>();
		final Consumer<String> methodReference1 = instance1::m;
	    final A2.C<String> instance2 = new A2.C<>();
	    final Consumer<String> methodReference2 = instance2::m;
	    RootPanel.get().add(new Label("Result: "+methodReference1+", "+methodReference2));
	}
}
