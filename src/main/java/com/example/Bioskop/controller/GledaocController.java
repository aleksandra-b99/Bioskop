package com.example.Bioskop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.Bioskop.service.GledaocService;


@Controller
public class GledaocController {
	@Autowired
	private GledaocService gledaocService;
	
}
