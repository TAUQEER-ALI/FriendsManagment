/**
 * 
 */
package com.cg.friends.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.friends.model.BlockFriendBean;
import com.cg.friends.model.FriendsModel;
import com.cg.friends.service.FriendService;

/**
 * @author Tauqeer Ali Chaudhari
 *
 */
@RestController
@RequestMapping("/socialmedia")
public class FriendsController {

	@Autowired
	FriendService friendsServices;
	@GetMapping("/test")
	public String sayHello() {
		return "sayHello !!!!";
	}
	@PostMapping("/newFriends")
	public ResponseEntity<?> friendsConnection(@RequestBody FriendsModel friends){
		System.out.println("FriendsController.friendsConnection() friends ===>>>"+friends);
		String msg = friendsServices.saveFriend(friends);
		return ResponseEntity.ok( msg);
	}
	
	@PostMapping("/getMatualFriends")
	public ResponseEntity<?> getManualFriends(@RequestBody FriendsModel friends){
		System.out.println("FriendsController.friendsConnection() friends ===>>>"+friends);
		String msg = friendsServices.getManualFriends(friends);
		return ResponseEntity.ok( msg);
	}
	
	@PostMapping("/getBlockFriends")
	public ResponseEntity<?> blockFriend(@RequestBody BlockFriendBean friends){
		System.out.println("FriendsController.friendsConnection() friends ===>>>"+friends);
		String msg = friendsServices.blockFriends(friends);
		return ResponseEntity.ok( msg);
	}
	
	@PostMapping("/test")
	public ResponseEntity<?> testNewfriendsConnection(){
		System.out.println("FriendsController.friendsConnection() friends ===>>>");
		return ResponseEntity.ok("SUUCCSESS");
	}
}
