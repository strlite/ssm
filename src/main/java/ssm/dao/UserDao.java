package ssm.dao;

import java.util.Map;

import ssm.pojo.User;

public interface UserDao {
	
	void saveUser(User user);
	
	User findUserById(String id);
	
	User findUserByName(String name);
	
	int updateUser(Map<String, Object> user);
}




