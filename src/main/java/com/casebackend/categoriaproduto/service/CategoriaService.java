package com.casebackend.categoriaproduto.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.casebackend.categoriaproduto.model.Categoria;
import com.casebackend.categoriaproduto.repository.CategoriaRepository;
import com.casebackend.categoriaproduto.service.exceptions.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> listarCategorias() {
		return categoriaRepository.findAll();
	}
	
	
	public Categoria criarCategoria(Categoria categoria) {	
		return  categoria = categoriaRepository.save(categoria);
	}
	
	public Categoria buscarPeloCodigo(Long codigo) {
		Optional<Categoria> categoriaResponse = categoriaRepository.findById(codigo);
		return categoriaResponse.orElseThrow(() -> new ResourceNotFoundException(codigo));
		
	}
	
	public void excluirCategoria(Long codigo) {
		try {
			categoriaRepository.deleteById(codigo);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(codigo);
		}
	}
	
	public Categoria atualizarCategoria(Long codigo, Categoria categoria) {
		try {
			Categoria categoriaSalva = categoriaRepository.getOne(codigo);
			atualizaDados(categoriaSalva, categoria);
			return categoriaRepository.save(categoriaSalva);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(codigo);
		}
	}


	private void atualizaDados(Categoria categoriaSalva, Categoria categoria) {
		categoriaSalva.setNome(categoria.getNome());
		
	}

}
