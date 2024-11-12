package user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import user.bean.UserDTO;

@Mapper  
public interface UserDAO {

	public UserDTO getExistId(String id);

	public void write(UserDTO userDTO);

	public List<UserDTO> list();

	public UserDTO getUserById(String id);

	public void update(UserDTO userDTO);

	public UserDTO verifyPassword(Map<String, String> map);

	public void delete(String id);
	
}
