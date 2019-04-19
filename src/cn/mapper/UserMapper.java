package cn.mapper;

import cn.model.User;

public interface UserMapper {

	User existsUser(User user);

	int addUser(User user);

	int existsUserName(User user);

	User existsAdmin(User user);

	User getPersonalInfo(int loginUserId);

	void updatePersonalInfo(User user);

	void updatePersonalPassword(User user);

	User getUser(User user);

	void setUserCurrency(User user);

	void setUser(User dbUser);

	void setUserCommonName(User user);

	void setUserPass(User user);

	void setUserPic(User user);

	int getUserName(User user);

}
