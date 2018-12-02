/**
 * 
 */
package com.cg.friends.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * @author Tauqeer Ali Chaudhari
 *
 */
@Embeddable
public class MutualFriends implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "FROM_EMAIL_ID")
	private String fromEmailID;
	
	@Column(name = "TO_EMAIL_ID")
	private String toEmailID;

	public String getFromEmailID() {
		return fromEmailID;
	}

	public void setFromEmailID(String fromEmailID) {
		this.fromEmailID = fromEmailID;
	}

	public String getToEmailID() {
		return toEmailID;
	}

	public void setToEmailID(String toEmailID) {
		this.toEmailID = toEmailID;
	}

	
	
	
}
