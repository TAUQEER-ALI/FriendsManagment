/**
 * 
 */
package com.cg.friends.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Tauqeer Ali Chaudhari
 *
 */
@Entity
@Table(name="FRIENDS")
public class FriendsBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "FRIEND_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer friendId;
	@Column(name = "FROM_EMAIL_ID")
	private String fromEmailID;
	
	@Column(name = "TO_EMAIL_ID")
	private String toEmailID;

	private String status;
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getFriendId() {
		return friendId;
	}
	public void setFriendId(Integer friendId) {
		this.friendId = friendId;
	}
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
