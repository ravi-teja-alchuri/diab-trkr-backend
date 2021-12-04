package com.diabtrkr.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.diabtrkr.models.Therapy;
import com.diabtrkr.request.dtos.TherapyDTO;
import com.diabtrkr.services.TherapyService;

@RestController
@RequestMapping("/rest/therapy")
public class TherapyController {

	@Autowired
	TherapyService service;

	@PostMapping
	public ResponseEntity<Therapy> add(@RequestBody TherapyDTO dto) {
		Therapy model = service.create(dto);
		return ResponseEntity.ok(model);
	}

	@GetMapping
	public ResponseEntity<Therapy> get() {
		return ResponseEntity.ok(service.get());
	}

}
