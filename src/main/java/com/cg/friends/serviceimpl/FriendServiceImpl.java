/**
 * 
 */
package com.cg.friends.serviceimpl;

import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.friends.dao.FriendDao;
import com.cg.friends.model.BlockFriendBean;
import com.cg.friends.model.FriendsBean;
import com.cg.friends.model.FriendsModel;
import com.cg.friends.service.FriendService;

/**
 * @author Tauqeer Ali Chaudhari
 *
 */
@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {


	@Autowired
	FriendDao friendDao;
	
	/* (non-Javadoc)
	 * @see com.jsoft.ems.service.FriendService#saveFriend(com.jsoft.ems.friends.FriendsModel)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String saveFriend(FriendsModel friends) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = null;
		FriendsBean bean = new FriendsBean();
		bean.setFromEmailID(friends.getFriends().get(0));
		bean.setToEmailID(friends.getFriends().get(1));
		bean.setStatus("M");
		Integer row = friendDao.saveFriend(bean);
		if(row>0) {
			jsonObject = new JSONObject();
			jsonObject.put("success", true);
		}else {
			jsonObject = new JSONObject();
			jsonObject.put("unsuccess", false);
		}
		return jsonObject.toJSONString();
	}

	/* (non-Javadoc)
	 * @see com.jsoft.ems.service.FriendService#getManualFriends(com.jsoft.ems.friends.FriendsModel)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String getManualFriends(FriendsModel friends) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = null;
		FriendsBean bean = new FriendsBean();
		bean.setFromEmailID(friends.getFriends().get(0));
		bean.setToEmailID(friends.getFriends().get(1));
		Map map = friendDao.getMatualFriends(bean);
		if(map != null) {
			jsonObject =new  JSONObject(); 
			jsonObject.put("success", true);
			List<String> emailList = (List<String>) map.get("email") ;
			jsonObject.put("friends", emailList == null?"no matual friends":emailList);
			jsonObject.put("count",  map.get("row")== null?0:map.get("row"));
		}else {
			jsonObject =new  JSONObject(); 
			jsonObject.put("error", "due to some technical issue");	
		}
		return jsonObject.toJSONString();
	}

	/* (non-Javadoc)
	 * @see com.jsoft.ems.service.FriendService#blockFriends(com.jsoft.ems.friends.BlockFriendBean)
	 */
	@Override
	public String blockFriends(BlockFriendBean friends) {
		// TODO Auto-generated method stub
		JSONObject jsonObject = null;
		FriendsBean bean = new FriendsBean();
		bean.setFromEmailID(friends.getRequestor());
		bean.setToEmailID(friends.getTarget());
		bean.setStatus("B");
		int row = friendDao.blockFriends(bean);
		if(row == -1) {
			jsonObject = new JSONObject();
			jsonObject.put("success", true);
			jsonObject.put("msg", "this user already block !");
		}else if(row>0){
			jsonObject = new JSONObject();
			jsonObject.put("success", true);
		}else {
			jsonObject = new JSONObject();
			jsonObject.put("unsuccess", false);
		}
		return jsonObject.toJSONString();
	}

}
