package cim.util;

import cim.models.*;

public interface AuthenticatorInterface {
	/**
	 * Checks if user is authenticated
	 * @return true if user is authenticated
	 */
	public boolean isAuthenticated();
	
	/**
	 *  Authenticates a user
	 * @param email User email
	 * @param password User password, unhashed
	 * @return true if authenticated, false otherwise
	 */
	public boolean authenticate(String email, String password);
	
	/**
	 * @return Returns the Account to the currently logged in user. Returns null if not logged in.
	 */
	public Account getSelf();
	
}
