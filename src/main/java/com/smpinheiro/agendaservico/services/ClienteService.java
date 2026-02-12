package com.smpinheiro.agendaservico.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smpinheiro.agendaservico.models.Cliente;
import com.smpinheiro.agendaservico.repositories.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente createCliente (Cliente cliente) {
		
		if (cliente.getNome().isEmpty() || cliente.getNome().isBlank() || cliente.getNome().matches("^_+$") ) {
			throw new RuntimeException("clienteName não deve estar vazio");
		}
		
		if (cliente.getTelefone().isBlank() ) {
			throw new RuntimeException("clienteTelefone não deve estar vazio");
		}
		
		Optional<Cliente> clienteOpt = clienteRepository.findByTelefone(cliente.getTelefone());
		if(clienteOpt.isPresent()) {
			throw new RuntimeException("Número ja cadastrado no sistema");
		}
		
		return clienteRepository.save(cliente);
	}
	
	
	public Cliente getCliente (String telefone) {
		
		Optional<Cliente> clienteOpt = clienteRepository.findByTelefone(telefone);
		
		if(!clienteOpt.isPresent()) {
			throw new RuntimeException("Cliente não encontrado");
		}
		return clienteOpt.get() ;
	}
	
	
	public Cliente updateCliente (String telefone, Cliente updatedCliente) {
		
		Optional<Cliente> clienteOpt = clienteRepository.findByTelefone(telefone);
		
		if(!clienteOpt.isPresent()) {
			throw new RuntimeException("Cliente não encontrado");
		}
		
		Cliente cliente = clienteOpt.get();
		cliente.setNome(updatedCliente.getNome());
		
		return clienteRepository.save(cliente);
	}
	
	public void deleteCliente (Cliente cliente) {
		
		Optional<Cliente> clientByName = clienteRepository.findByNome(cliente.getNome());
		Optional<Cliente> clientByFone = clienteRepository.findByTelefone(cliente.getTelefone());
		
		if(!clientByName.isPresent()) {
			throw new RuntimeException("Nome de cliente não encontrado");
		}
		if(!clientByFone.isPresent()) {
			throw new RuntimeException("Telefone do cliente não encontrado");
		}
		if(!clientByFone.equals(clientByName) ) {
			throw new RuntimeException("Erro: dados não coincidem");
		}
		
		clienteRepository.delete(cliente);
	}

}
