package cn.cloud.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());


	@GetMapping({ "/", "" })
	String welcome(Model model) {
		return "redirect:/blog";
	}

	@GetMapping("/login")
	String login() {
		return "login";
	}


	@GetMapping("/main")
	String main() {
		return "main";
	}

	@GetMapping("/403")
	String error403() {
		return "403";
	}

}
