package com.example.authserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Lakitha Prabudh on 5/11/20
 */
@RestController
public class HelloWorldController {

	@RequestMapping({ "/hello" })
	public String firstPage() {
		return "Hello World";
	}

}