package com.smpinheiro.agendaservico.services;

import java.lang.foreign.Linker.Option;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smpinheiro.agendaservico.models.Funcionario;
import com.smpinheiro.agendaservico.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario createFuncionario (Funcionario funcionario) {
		
		if (funcionario.getNome().isEmpty() || funcionario.getNome().isBlank() || funcionario.getNome().matches("^_+$") ) {
			throw new RuntimeException("funcionarioName não deve estar vaio");
		}
		
		if (funcionario.getPeriodo().isBlank() ) {
			throw new RuntimeException("um prazo em minutos deve ser informado");
		}
		
		Optional<Funcionario> funcionarOpt = funcionarioRepository.findByNome(funcionario.getNome());
		if(funcionarOpt.isPresent()) {
			throw new RuntimeException("Funcionario já cadastrado");
		}
		
		return funcionarioRepository.save(funcionario);
	}
	
	public Funcionario getFuncionario (String nome) {
		
		Optional<Funcionario> funcionarOpt = funcionarioRepository.findByNome(nome);
		
		if(!funcionarOpt.isPresent()) {
			throw new RuntimeException("Funcionario não encontrado");
		}
		return funcionarOpt.get();
	}
	
	public Funcionario updateFuncionario (String nome, Funcionario updateFuncionario) {
		
		Optional<Funcionario> funcionarOpt = funcionarioRepository.findByNome(nome);
		
		if(!funcionarOpt.isPresent()) {
			throw new RuntimeException("Funcionario não encontrado");
		}
		
		Funcionario funcionario = funcionarOpt.get();
		funcionario.setPeriodo(updateFuncionario.getPeriodo());
		
		return funcionarioRepository.save(funcionario);
	}
	
	public void deleteFuncionario (Funcionario funcionario) {
		
		Optional<Funcionario> funcionarOpt = funcionarioRepository.findByNome(funcionario.getNome());
		
		if(!funcionarOpt.isPresent()) {
			throw new RuntimeException("Funcionario não encontrado");
		}
		
		funcionarioRepository.delete(funcionario);
	}

}
