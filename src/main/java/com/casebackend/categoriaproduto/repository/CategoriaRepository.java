package com.casebackend.categoriaproduto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.casebackend.categoriaproduto.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {



}
