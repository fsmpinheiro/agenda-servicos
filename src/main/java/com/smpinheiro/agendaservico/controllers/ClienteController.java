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

import com.smpinheiro.agendaservico.models.Cliente;
import com.smpinheiro.agendaservico.services.ClienteService;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService; 
	
	@PostMapping					// 		"/cliente"  + JSON
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente){
		
		return ResponseEntity.accepted().body(clienteService.createCliente(cliente));
	}
	
	@GetMapping("/{telefone}")		// 		"/cliente/{telefone}"
	public ResponseEntity<Cliente> getCliente(@PathVariable("telefone") String telefone) {
		
		return ResponseEntity.ok().body(clienteService.getCliente(telefone) );
	}
	
	@PutMapping						//	 	"/cliente?telefone="  + JSON
	public ResponseEntity<Cliente> getCliente(
			@RequestParam("telefone") String telefone, @RequestBody Cliente updatedCliente){
		
		return ResponseEntity.accepted().body(clienteService.updateCliente(telefone, updatedCliente) );
	}
	
	@DeleteMapping					//		JSON
	public ResponseEntity<Void> deleteCliente(@RequestBody Cliente cliente) {
		
		clienteService.deleteCliente(cliente);
		return ResponseEntity.noContent().build();
	}
}
