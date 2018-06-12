package ssm.web.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/hello")
	@ResponseBody
	public Object hello(HttpServletResponse response) {
		Cookie demo = new Cookie("name", "Tom");
		// demo.setMaxAge(60*60*24*30);
		demo.setPath("/");
		response.addCookie(demo);

		return new String[] { "Hello", "World!" };
	}

	@RequestMapping("/hi")
	@ResponseBody
	public Object hi(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		for (Cookie cookie : cookies) {
			System.out.println(cookie);
		}
		return new String[] { "HI" };
	}

	@RequestMapping("/test")
	@ResponseBody
	public Object test() throws Exception {

		Thread t = Thread.currentThread();
		String name = t.getName();
		long id = t.getId();

		Thread.sleep(1000);

		return new String[] { name + ":" + id, " OK!" };
	}
}
