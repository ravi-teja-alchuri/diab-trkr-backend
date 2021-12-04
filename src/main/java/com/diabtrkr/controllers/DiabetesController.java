package com.diabtrkr.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diabtrkr.models.Diabetes;
import com.diabtrkr.request.dtos.DiabetesDTO;
import com.diabtrkr.services.DiabetesService;

@RestController
@RequestMapping("/rest/diabetes")
public class DiabetesController {

	@Autowired
	DiabetesService service;

	@PostMapping
	public ResponseEntity<Diabetes> add(@RequestBody DiabetesDTO dto) {
		Diabetes model = service.create(dto);
		return ResponseEntity.ok(model);
	}

	@GetMapping
	public ResponseEntity<List<Diabetes>> get() {
		return ResponseEntity.ok(service.get());
	}

}
