package com.smpinheiro.agendaservico.controllers;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smpinheiro.agendaservico.models.Agendamento;
import com.smpinheiro.agendaservico.services.AgendamentoService;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

	@Autowired
	private AgendamentoService agendamentoService;

	@PostMapping
	public ResponseEntity<Agendamento> createAgendamento(@RequestBody Agendamento agendamento) {
		
		return ResponseEntity.accepted().body(agendamentoService.createAgendamento(agendamento));
	}
	
	
	
	@PutMapping
	public ResponseEntity<Agendamento> updateAgendamento(
			@RequestBody Agendamento agendamento, @RequestParam("novaDataHora") LocalDateTime novaDataHora) {
		
		return ResponseEntity.accepted().body(agendamentoService.editAgendamento(agendamento, novaDataHora) );
	}
	
	@DeleteMapping("/funcionario")
	public ResponseEntity<Void> funcionarioDeleteAgendamento(@RequestBody Agendamento agendamento) {
		
		agendamentoService.deleteAgendamentoFuncionario(agendamento);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/cliente")
	public ResponseEntity<Void> clienteDeleteAgendamento(@RequestBody Agendamento agendamento) {
		
		agendamentoService.deleteAgendamentoCliente(agendamento);
		return ResponseEntity.noContent().build();
	}
}
