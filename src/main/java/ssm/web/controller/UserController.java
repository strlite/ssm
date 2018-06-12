package ssm.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ssm.pojo.User;
import ssm.service.UserService;
import ssm.service.exception.PasswordException;
import ssm.service.exception.UserNameException;
import ssm.util.JsonResult;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	@Resource
	private UserService userService;

	@RequestMapping("/login")
	@ResponseBody
	public JsonResult<User> login(String name, String password, HttpServletResponse response) {
		User user = userService.login(name, password);
		Cookie cookie = new Cookie("token", user.getToken());
		cookie.setPath("/");
		response.addCookie(cookie);
		return new JsonResult<User>(user);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(UserNameException.class)
	@ResponseBody
	public JsonResult userName(UserNameException e) {
		e.printStackTrace();
		return new JsonResult(2, e);
	}

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(PasswordException.class)
	public JsonResult password(PasswordException e) {
		e.printStackTrace();
		return new JsonResult(3, e);
	}

}
