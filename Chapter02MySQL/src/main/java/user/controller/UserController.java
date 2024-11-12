package user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import user.bean.UserDTO;
import user.service.UserService;

@Controller
@RequestMapping(value="user")
public class UserController {
	
	private UserService userService;
	
	//생성자 인젝션 (근데 걍 @Autowired 쓰면 됨...ㅋ)
	//@Autowired를 대신해서 생성자 인젝션을 만드는 방법도 있다.
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping(value="writeForm")
	public String writeForm() {
		
		return "user/writeForm";
	}

	@PostMapping(value="getExistId")
	@ResponseBody
	public String getExistId(@RequestParam("id") String id) {
		return userService.getExistId(id);
	}
	
	@PostMapping(value="write")
	@ResponseBody
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
	}
	
	@PostMapping(value="write2")
	public String write2(@ModelAttribute UserDTO userDTO) {
		System.out.println(userDTO);
		userService.write(userDTO);
		
		return "user/write";
	}
	
	@GetMapping(value="list")
	public String list(Model model) {
		System.out.println("list");
		List<UserDTO> list = new ArrayList<>();
		
		list = userService.list();
		model.addAttribute("list", list);
		return "user/list";
	}
	
	@GetMapping(value="updateForm")
	public String updateForm(@RequestParam(name="id") String id,
							Model model) {
		UserDTO userDTO = new UserDTO();
		
		userDTO = userService.getUserById(id);
		model.addAttribute("userDTO", userDTO);
		
		return "user/updateForm";
	}
	
	@PostMapping(value="update")
	@ResponseBody
	public void update(@ModelAttribute UserDTO userDTO) {
		userService.update(userDTO);
	}
	
	@DeleteMapping(value="delete")
	@ResponseBody
	public void delete(@RequestParam(name="id") String id,
						@RequestParam(name="pwd") String pwd) {
		Map<String, String> map = new HashMap();
		UserDTO userDTO = new UserDTO();
		
		map.put("id", id);
		map.put("pwd", pwd);
		
		userDTO = userService.verifyPassword(map);
		System.out.println(userDTO);
		
		if(userDTO != null) 
			userService.delete(id);
	}
	

	
	
	
}
