package com.casebackend.categoriaproduto.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.casebackend.categoriaproduto.model.Categoria;
import com.casebackend.categoriaproduto.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> listarCategorias() {
		List<Categoria> listaDeCategorias = categoriaService.listarCategorias();
		return ResponseEntity.ok().body(listaDeCategorias);
	} 
	
	@PostMapping
	public ResponseEntity<Categoria> criarCategoria(@Valid @RequestBody Categoria categoria) {
		Categoria categoriaSalva = categoriaService.criarCategoria(categoria);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{codigo}")
				.buildAndExpand(categoriaSalva.getCodigo()).toUri();
		return ResponseEntity.created(uri).body(categoriaSalva);
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
		Categoria categoriaResponse = categoriaService.buscarPeloCodigo(codigo);
		return ResponseEntity.ok().body(categoriaResponse);
		
	}
	
	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<Void> excluirCategoria(@PathVariable Long codigo) {
		categoriaService.excluirCategoria(codigo);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/{codigo}")
	public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long codigo, @Valid @RequestBody Categoria categoria) {
		categoria = categoriaService.atualizarCategoria(codigo, categoria);
		return ResponseEntity.ok().body(categoria);
		
	}

}
