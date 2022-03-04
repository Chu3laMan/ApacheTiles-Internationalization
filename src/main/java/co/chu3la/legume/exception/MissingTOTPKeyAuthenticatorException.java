package co.chu3la.legume.exception;

public class MissingTOTPKeyAuthenticatorException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6415523209031208517L;
	
	public MissingTOTPKeyAuthenticatorException(String msg) {
        super(msg);
    }
	
	public MissingTOTPKeyAuthenticatorException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
