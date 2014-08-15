package br.org.funcate.glue.os.model;

public class OSState {
	private static String user;
	private static String Address;
	private static boolean isAuth;
	
	public OSState() {
		super();
		setAuth(false);
		setUser("");
		setAddress("");
	}
	public static String getUser() {
		return user;
	}
	public static void setUser(String user) {
		OSState.user = user;
	}
	/**
	 * retrieves the authentication state, if logged in or not
	 * 
	 * @return boolean: state
	 */
	public static boolean isAuth() {
		return isAuth;
	}
	/**
	 * set the authentication state, if logged in or not
	 * 
	 * @return boolean: state
	 */
	public static void setAuth(boolean isAuth) {
		OSState.isAuth = isAuth;
	}

	/**
	 * retrieve last searched address
	 * 
	 * @return string: address
	 */
	public static String getAddress() {
		return Address;
	}

	/**
	 * set the last searched address in memory.   
	 */
	public static void setAddress(String address) {
		Address = address;
	}
	
}
