package com.casebackend.categoriaproduto.resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.casebackend.categoriaproduto.model.Categoria;
import com.casebackend.categoriaproduto.service.CategoriaService;
import com.casebackend.categoriaproduto.service.exceptions.ResourceNotFoundException;



@RunWith(SpringRunner.class)
@WebMvcTest(CategoriaResource.class)
public class CategoriaResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoriaService categoriaService;

    @Test
    public void DeveRetornar_Sucesso() throws Exception {
        List<Categoria> result = new ArrayList<>();
        result.add(new Categoria(1L, "Bebidas"));
        result.add(new Categoria(2L, "Perecíveis"));
        Mockito.when(categoriaService.listarCategorias()).thenReturn(result);
        this.mockMvc.perform(get("/categorias")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }
    
    
    @Test
    public void DeveRetornar_NotFound() throws Exception {
    	ResourceNotFoundException notfound = new ResourceNotFoundException(getClass());
        Mockito.when(categoriaService.listarCategorias()).thenThrow(notfound);
        this.mockMvc.perform(get("/categorias")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.NOT_FOUND.value()));
    }

    @Test
    public void DeveRetornar_SucessoQuandoBuscarPorCodigo() throws Exception {
        Categoria categoria = new Categoria(1L, "Bebidas");
        Mockito.when(categoriaService.buscarPeloCodigo(1L)).thenReturn(categoria);
        this.mockMvc.perform(get("/categorias")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }
    

    @Test
    public void DeveRetornar_SucessoQuandoAtualizar() throws Exception {
        Categoria categoria = new Categoria(1L, "Bebidas");
        Categoria categoriaAtualizada = new Categoria(1L, "Higiene");
        Mockito.when(categoriaService.atualizarCategoria(1L, categoria)).thenReturn(categoriaAtualizada);
        this.mockMvc.perform(get("/categorias")).andExpect(MockMvcResultMatchers.status().is(HttpStatus.OK.value()));
    }
    

}