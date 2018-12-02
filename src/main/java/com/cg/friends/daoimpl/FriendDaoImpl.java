/**
 * 
 */
package com.cg.friends.daoimpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cg.friends.dao.FriendDao;
import com.cg.friends.model.FriendsBean;
/**
 * @author Tauqeer Ali Chaudhari
 *
 */
@Repository("friendDao")
public class FriendDaoImpl implements FriendDao {

	/* (non-Javadoc)
	 * @see com.jsoft.ems.dao.FriendDao#saveFriend(com.jsoft.ems.friends.FriendsBean)
	 */
	@Autowired
	private SessionFactory sessionFactory = null;
	@Override
	public Integer saveFriend(FriendsBean bean) {
		// TODO Auto-generated method stub
		Integer row = 0;
		String checkFrd = "from FriendsBean where  (fromEmailID = :fstFromEmailID and toEmailID = :fstToEmailID) OR  (fromEmailID = :secFromEmailID and toEmailID = :secToEmailID)";
		FriendsBean checkBean =  sessionFactory.getCurrentSession().createQuery(checkFrd, FriendsBean.class)
				.setParameter("fstFromEmailID", bean.getFromEmailID())
				.setParameter("fstToEmailID", bean.getToEmailID())
				.setParameter("secFromEmailID", bean.getToEmailID())
				.setParameter("secToEmailID", bean.getFromEmailID())
				.uniqueResult();
		System.out.println("FriendDaoImpl.saveFriend()"+checkBean);
		if(null == checkBean) 
			row = (Integer) sessionFactory.getCurrentSession().save(bean);
		return row ;
	}
	@Override
	public  Map<?, ?> getMatualFriends(FriendsBean bean) {
		String matualStr = "SELECT UserAFriends.UserId, count(UserAFriends.UserId) FROM (SELECT  TO_EMAIL_ID UserId FROM friends WHERE FROM_EMAIL_ID = :fstFromEmailID UNION SELECT  FROM_EMAIL_ID UserId FROM friends WHERE TO_EMAIL_ID = :fstToEmailID) AS UserAFriends JOIN (SELECT  TO_EMAIL_ID UserId FROM friends WHERE FROM_EMAIL_ID = :secFromEmailID UNION SELECT  FROM_EMAIL_ID  UserId FROM friends WHERE TO_EMAIL_ID = :secToEmailID) AS UserBFriends ON  UserAFriends.UserId = UserBFriends.UserId group by UserAFriends.UserId ";
		Map  map = new HashMap<>();
		List<String> al = new ArrayList<>();
		int rowCount = 0;
		List<Object[]> list = sessionFactory.getCurrentSession().createSQLQuery(matualStr)
				.setParameter("fstFromEmailID", bean.getFromEmailID())
				.setParameter("fstToEmailID", bean.getToEmailID())
				.setParameter("secFromEmailID", bean.getToEmailID())
				.setParameter("secToEmailID", bean.getFromEmailID()).list();
		System.out.println(list);
		if(list != null && list.size() > 0){
			for (Object[] row: list) {
				al.add(row[0].toString());
				rowCount += Integer.parseInt(row[1].toString());
			}
			map.put("email", al);
			map.put("row", rowCount);
		}
		return map;
	}
	/* (non-Javadoc)
	 * @see com.jsoft.ems.dao.FriendDao#blockFriends(com.jsoft.ems.friends.FriendsBean)
	 */
	@Override
	public int blockFriends(FriendsBean bean) {
		// TODO Auto-generated method stub
		int row = 0;
		String checkFrd = "from FriendsBean where  ((fromEmailID = :fstFromEmailID and toEmailID = :fstToEmailID) OR  (fromEmailID = :secFromEmailID and toEmailID = :secToEmailID)) AND status = :statusStr";
		FriendsBean checkBean =  sessionFactory.getCurrentSession().createQuery(checkFrd, FriendsBean.class)
				.setParameter("fstFromEmailID", bean.getFromEmailID())
				.setParameter("fstToEmailID", bean.getToEmailID())
				.setParameter("secFromEmailID", bean.getToEmailID())
				.setParameter("secToEmailID", bean.getFromEmailID())
				.setParameter("statusStr","B")
				.uniqueResult();
		System.out.println("FriendDaoImpl.blockFriends()"+checkBean);
		if(null == checkBean ) {
			row =  sessionFactory.getCurrentSession().createQuery("update FriendsBean set status = :statusStr  where  (fromEmailID = :fstFromEmailID and toEmailID = :fstToEmailID) OR  (fromEmailID = :secFromEmailID and toEmailID = :secToEmailID)").setParameter("statusStr", bean.getStatus())
					.setParameter("fstFromEmailID", bean.getFromEmailID())
					.setParameter("fstToEmailID", bean.getToEmailID())
					.setParameter("secFromEmailID", bean.getToEmailID())
					.setParameter("secToEmailID", bean.getFromEmailID()).executeUpdate(); 
		}else {
			row = -1;
		}
		
		System.out.println("FriendDaoImpl.blockFriends()"+checkBean);
		return row;

	}
}
