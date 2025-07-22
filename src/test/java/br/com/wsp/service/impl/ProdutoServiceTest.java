package br.com.wsp.service.impl;

import br.com.wsp.dto.ProdutoRequest;
import br.com.wsp.dto.ProdutoResponse;
import br.com.wsp.entity.Categoria;
import br.com.wsp.entity.Produto;
import br.com.wsp.exception.NotFoundException;
import br.com.wsp.repository.CategoriaRepository;
import br.com.wsp.repository.ProdutoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProdutoServiceTest {

    @InjectMocks
    ProdutoService service;
    @Mock
    ProdutoRepository repository;
    @Mock
    CategoriaRepository categoriaRepository;
    @Mock
    ProdutoRequest request;
    @Mock
    ProdutoResponse response;
    @Mock
    Produto produto;
    @Mock
    Pageable pageable;
    @Mock
    Page page;
    @Mock
    Categoria categoria;

    @Test
    @DisplayName("DEVE CRIAR PRODUTO")
    void save() throws Exception {

        doReturn(Optional.of(categoria)).when(categoriaRepository).findById(any(UUID.class));
        doReturn(produto).when(repository).save(any());

        Optional<ProdutoResponse> result = service.save(request);

        assertNotNull(result);

        verify(repository, times(1)).save(any());
        verify(categoriaRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    @DisplayName("DEVE LANCAR EXCECAO AO CRIAR PRODUTO")
    void save__throwsNotFoundException() {

        doThrow(NotFoundException.class).when(categoriaRepository).findById(any(UUID.class));

        assertThrows(NotFoundException.class, () -> service.save(request));

        verify(categoriaRepository, times(1)).findById(any(UUID.class));
    }

    @Test
    void findAll() {
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }

    @Test
    void delete() {
    }
}