package cn.service;

import java.util.Map;
import cn.model.User;

public interface UserService {

	User existsUser(User user);

	int addUser(User user);

	boolean existsUserName(String userName);

	User existsAdmin(User user);

	User getPersonalInfo(int loginUserId);

	void updatePersonalInfo(User user);

	void updatePersonalPassword(User user);

	User getUser(User user);

	void setUserCurrency(User user);

	void setUser(User dbUser);

	void setUserCommonName(User user);

	void setUserPass(User user);

	int uploadHeadPhoto(Map<String, Object> requestMap);

}
