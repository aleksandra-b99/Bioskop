package com.example.Bioskop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Bioskop.repository.GledaocRepository;
@Service
public class GledaocService {
	@Autowired
	private GledaocRepository gledaocRepository;
}
