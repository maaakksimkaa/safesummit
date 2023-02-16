package com.ssummit.rest.controller;


import javax.servlet.http.*;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomErrorController implements ErrorController {


	@RequestMapping("/error")
	@ResponseBody
	String error(HttpServletRequest request) {
		return "<h1>Error occurred</h1>";
	}


	public String getErrorPath() {
		return "/error";
	}
}
