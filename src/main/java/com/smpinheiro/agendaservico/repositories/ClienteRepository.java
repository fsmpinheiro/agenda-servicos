package com.smpinheiro.agendaservico.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smpinheiro.agendaservico.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, UUID>{

	Optional<Cliente> findByNome(String name);
	Optional<Cliente> findByTelefone(String telefone);
	
}
