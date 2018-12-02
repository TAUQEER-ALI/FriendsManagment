/**
 * 
 */
package com.cg.friends.service;

import com.cg.friends.model.BlockFriendBean;
import com.cg.friends.model.FriendsModel;

/**
 * @author Tauqeer Ali Chaudhari
 *
 */
public interface FriendService {

	/**
	 * @param friends
	 * @return
	 */
	String saveFriend(FriendsModel friends);

	/**
	 * @param friends
	 * @return
	 */
	String getManualFriends(FriendsModel friends);

	/**
	 * @param friends
	 * @return
	 */
	String blockFriends(BlockFriendBean friends);

}
