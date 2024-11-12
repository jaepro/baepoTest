package user.service;

import java.util.List;
import java.util.Map;

import user.bean.UserDTO;

public interface UserService {

	public String getExistId(String id);

	public void write(UserDTO userDTO);

	public List<UserDTO> list();

	public UserDTO getUserById(String id);

	public void update(UserDTO userDTO);

	public UserDTO verifyPassword(Map<String, String> map);

	public void delete(String id);

}
