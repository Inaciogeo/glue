package br.org.funcate.glue.utilities;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * \brief This Class Launch Browser.
 * 
 * @author Moraes, Emerson Leite
 * 
 */
public class BrowserLauncher {

	private static BrowserLauncher _instance;

	/** <Attribute instance of BrowserLauncher */

	/**
	 * This method is a Singleton Implementation of this class
	 */
	public static BrowserLauncher getInstance() {
		if (_instance == null) {
			_instance = new BrowserLauncher();
		}
		return _instance;
	}

	/**
	 * This method opens the default browser with the requested URL
	 * 
	 * @param URL
	 */
	public void launchBrowser(String URL) {
		Desktop desktop = null;

		if (!Desktop.isDesktopSupported())
			throw new IllegalStateException("Desktop resources not supported!");

		desktop = Desktop.getDesktop();

		if (!desktop.isSupported(Desktop.Action.BROWSE))
			throw new IllegalStateException("No default browser set!");

		URI uri = null;
		try {
			uri = new URI(URL);
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}

		try {
			desktop.browse(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
