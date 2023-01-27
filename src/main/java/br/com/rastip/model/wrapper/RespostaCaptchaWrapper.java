package br.com.rastip.model.wrapper;

import java.io.Serializable;

public class RespostaCaptchaWrapper implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean success;
	String challenge_ts;// timestamp,  // timestamp of the challenge load (ISO format yyyy-MM-dd'T'HH:mm:ssZZ)
	String hostname;         // the hostname of the site where the reCAPTCHA was solved   
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getChallenge_ts() {
		return challenge_ts;
	}
	public void setChallenge_ts(String challenge_ts) {
		this.challenge_ts = challenge_ts;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	
}
