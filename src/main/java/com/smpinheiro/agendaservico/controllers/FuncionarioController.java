package com.smpinheiro.agendaservico.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smpinheiro.agendaservico.models.Funcionario;
import com.smpinheiro.agendaservico.services.FuncionarioService;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;
	
	@PostMapping
	public ResponseEntity<Funcionario> createFuncionario(@RequestBody Funcionario funcionario) {
		
		return ResponseEntity.accepted().body(funcionarioService.createFuncionario(funcionario));		
	}
	
	@GetMapping("/{nome}")
	public ResponseEntity<Funcionario> getFuncionario(@PathVariable("nome") String nome) {
		
		return ResponseEntity.ok().body(funcionarioService.getFuncionario(nome) );
	}
	
	@PutMapping
	public ResponseEntity<Funcionario> updateFuncionario(
			@RequestParam("nome") String nome, @RequestBody Funcionario updateFuncionario) {
		
		return ResponseEntity.accepted().body(funcionarioService.updateFuncionario(nome, updateFuncionario) );
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deleteFuncionario(@RequestBody Funcionario funcionario) {
		
		funcionarioService.deleteFuncionario(funcionario);
		return ResponseEntity.noContent().build();
	}
}
