package com.starking.TedI.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.starking.TedI.model.Pessoa;
import com.starking.TedI.repositories.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public List<Pessoa> findAll(){
		return this.pessoaRepository.findAll();
	}
	
	public void buscarPorId(Long id){
	this.pessoaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public Pessoa salvar(Pessoa pessoa) {
		return this.pessoaRepository.save(pessoa);
	}
	
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	public void deletar(Long id) {
		this.pessoaRepository.deleteById(id);
	}
}
