package com.smpinheiro.agendaservico.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smpinheiro.agendaservico.models.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID>{

	Optional<Funcionario> findByNome(String nome);
	
}
