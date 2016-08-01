package com.shyyko.controller;

import com.shyyko.entity.FileEntity;
import com.shyyko.entity.UserEntity;
import com.shyyko.model.form.FileUploadForm;
import com.shyyko.model.form.UserCreateForm;
import com.shyyko.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;

@Controller
public class ApplicationController {
	@Autowired
	private UserService userService;
	@Autowired
	private PasswordEncoder encoder;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(ModelMap map) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetails =
					(UserDetails) authentication.getPrincipal();
			map.addAttribute("userDetails", userDetails);
		}
		return "index";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(ModelMap map) {
		UserDetails userDetails =
				(UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Collection<? extends GrantedAuthority> securedMessage = userDetails.getAuthorities();
		map.addAttribute("userDetails", userDetails);
		map.addAttribute("userAuthorities", securedMessage);
		return "admin";
	}

	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("saveUserForm") UserCreateForm userCreateForm) throws IOException {

		userService.saveUser(new UserEntity(userCreateForm.getUsername(),
				encoder.encode(userCreateForm.getPassword()),
				UserEntity.Role.valueOf(userCreateForm.getRole())));

		return "redirect:/";
	}
}
