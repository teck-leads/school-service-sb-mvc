package com.school.app.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.school.app.model.PickLIst;
import com.school.app.model.Users;
import com.school.app.service.UsersService;

@Controller
public class UserController {
	@Autowired
	private UsersService userService;
	private final String SHOW_CITY="block";
	private final String HIDE_CITY="none";
	
	

	@GetMapping(value = { "/register" })
	public String registrationPage(@ModelAttribute("usersCmd") Users usersCmd, Model model) throws Exception {
		model.addAttribute("idKey", 0);
		model.addAttribute("statesKey", userService.findAllStates());
		
		showHideCity(usersCmd, model);
		return "userRegistration";
	}

	private void showHideCity(Users usersCmd, Model model) {
		if(StringUtils.isEmpty(usersCmd.getState())) {
			model.addAttribute("showHideCity", HIDE_CITY);
		}else {
			model.addAttribute("showHideCity", SHOW_CITY);
		}
	}

	@PostMapping(value = {"/users", "/register"})
	public String saveUpdateUser(Model model, @ModelAttribute("usersCmd") Users usersCmd, BindingResult bindingResult) throws Exception {

		if(usersCmd.getStateFlag().equalsIgnoreCase("yes")) {
			usersCmd.setStateFlag("no");
			model.addAttribute("idKey", 0);
			model.addAttribute("statesKey", userService.findAllStates());
			for (PickLIst state : userService.findAllStates()) {
				if(state.getCode().equalsIgnoreCase(usersCmd.getState())) {
					usersCmd.setCapital(state.getCapital());
					break;
				}
			}
			
			showHideCity(usersCmd, model);
			return "userRegistration";
		}
		usersCmd = userService.saveUsersInfo(usersCmd);
		return "redirect:/users";
	}

	@GetMapping(value = {"/users" })
	public String users(Model model) throws Exception {
		List<Users> users = userService.findAllUsersInfo();
		model.addAttribute("usersList", users);
		return "users_list";
	}
	

	@GetMapping(value = { "/users/edit/{code}" })
	public String editUser(Model model, @PathVariable("code") String code, @ModelAttribute("usersCmd") Users usersCmd,BindingResult bindingResult) throws Exception {
		
		
		if(bindingResult.hasErrors()) {
			System.out.println("stop here");
		}
		Users user = userService.findUsersInfoById(code);
		if(StringUtils.isEmpty(user.getId())) {
			return "redirect:/register";
		}
		
		model.addAttribute("statesKey", userService.findAllStates());
		
		BeanUtils.copyProperties(user, usersCmd);
		model.addAttribute("idKey", usersCmd.getId());
		showHideCity(usersCmd, model);
		return "userRegistration";
	}

	@PostMapping(value = { "/users/edit/{id}" })
	public String updateUser(Model model, @ModelAttribute("usersCmd") Users usersCmd) throws Exception {
		
		if(usersCmd.getStateFlag().equalsIgnoreCase("yes")) {
			usersCmd.setStateFlag("no");
			model.addAttribute("statesKey", userService.findAllStates());
			for (PickLIst state : userService.findAllStates()) {
				if(state.getCode().equalsIgnoreCase(usersCmd.getState())) {
					usersCmd.setCapital(state.getCapital());
					break;
				}
			}
			model.addAttribute("idKey", usersCmd.getId());
			showHideCity(usersCmd, model);
			return "userRegistration";
		}
		
		usersCmd = userService.saveUsersInfo(usersCmd);
		return "redirect:/users";
	}

	@GetMapping(value = { "/users/delete/{id}" })
	public String deleteUser(@PathVariable("id") String id) throws Exception {
		userService.deleteUsersInfoById(id);
		return "redirect:/users";

	}
}
