package vn.iotstar.services;

import vn.iotstar.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);
	
	UserModel FindByUserName(String username);
	

    boolean register(String username, String password, String email, String fullname, String phone);
    boolean checkExistEmail(String email);
    boolean checkExistUsername(String username);
    boolean checkExistPhone(String phone);
    
    
    boolean matchUsernameEmail(String username, String email);
    boolean resetPassword(String username, String newPassword);

}
