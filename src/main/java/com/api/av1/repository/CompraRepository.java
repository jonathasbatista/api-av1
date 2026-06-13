package com.api.av1.repository;

import com.api.av1.model.Compra;
import com.api.av1.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    List<Compra> findByProdutoAndStatus(Produto produto, String status);
}