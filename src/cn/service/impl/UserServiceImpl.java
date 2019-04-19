package cn.service.impl;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cn.mapper.UserMapper;
import cn.model.User;
import cn.service.UserService;

@Service
@Transactional // 此处不再进行创建SqlSession和提交事务，都已交由spring去管理了。
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper mapper;

	@Override
	public User existsUser(User user) {
		// TODO Auto-generated method stub
		return mapper.existsUser(user);

	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		if (mapper.getUserName(user) == 0) {
			mapper.addUser(user);
			return 1;
		} else
			return 0;
	}

	@Override
	public boolean existsUserName(String userName) {
		// TODO Auto-generated method stub
		User user = new User();
		user.setUserName(userName);
		if (mapper.existsUserName(user) == 0)
			return true;
		else
			return false;
	}

	@Override
	public User existsAdmin(User user) {
		// TODO Auto-generated method stub
		return mapper.existsAdmin(user);
	}

	@Override
	public User getPersonalInfo(int loginUserId) {
		// TODO Auto-generated method stub
		return mapper.getPersonalInfo(loginUserId);
	}

	@Override
	public void updatePersonalInfo(User user) {
		// TODO Auto-generated method stub
		mapper.updatePersonalInfo(user);
	}

	@Override
	public void updatePersonalPassword(User user) {
		// TODO Auto-generated method stub
		mapper.updatePersonalPassword(user);
	}

	@Override
	public User getUser(User user) {
		// TODO Auto-generated method stub
		return mapper.getUser(user);
	}

	@Override
	public void setUserCurrency(User user) {
		// TODO Auto-generated method stub
		mapper.setUserCurrency(user);
	}

	@Override
	public void setUser(User dbUser) {
		// TODO Auto-generated method stub
		mapper.setUser(dbUser);
	}

	@Override
	public void setUserCommonName(User user) {
		// TODO Auto-generated method stub
		mapper.setUserCommonName(user);
	}

	@Override
	public void setUserPass(User user) {
		// TODO Auto-generated method stub
		mapper.setUserPass(user);
	}

	@Override
	public int uploadHeadPhoto(Map<String, Object> requestMap) {
		// TODO Auto-generated method stub
		String pic = (String) requestMap.get("headPhoto");
		pic = "/images/user/" + pic;
		int userId = (Integer) requestMap.get("userId");
		User user = new User();
		user.setUserId(userId);
		user.setUserPic(pic);
		mapper.setUserPic(user);
		return 1;
	}

}
