package user.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import user.bean.UserDTO;
import user.dao.UserDAO;
import user.service.UserService;

@Service
@Transactional  //자동 커밋/반환 해주는 역할
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;

	@Override
	public String getExistId(String id) {
		UserDTO userDTO = userDAO.getExistId(id);
		System.out.println(userDTO);
		
		if(userDTO == null)
			return "non_exist";
		else
			return "exist";
	}

	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
		
	}

	@Override
	public List<UserDTO> list() {
		List<UserDTO> list = new ArrayList<>();
		list = userDAO.list();
		
		return list;
	}

	@Override
	public UserDTO getUserById(String id) {
		UserDTO userDTO = new UserDTO();
		
		userDTO = userDAO.getUserById(id);
		return userDTO;
	}

	@Override
	public void update(UserDTO userDTO) {
		userDAO.update(userDTO);
	}

	@Override
	public UserDTO verifyPassword(Map<String, String> map) {
		UserDTO userDTO = new UserDTO();
		
		userDTO = userDAO.verifyPassword(map);
		
		return userDTO;
	}

	@Override
	public void delete(String id) {
		userDAO.delete(id);
		
	}

}
