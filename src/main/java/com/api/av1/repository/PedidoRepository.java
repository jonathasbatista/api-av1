package com.api.av1.repository;

import com.api.av1.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, String> {

    @Query("SELECT p FROM Pedido p WHERE p.status = 'Pendente' ORDER BY p.valorTotal DESC")
    List<Pedido> findPedidosPendentesOrdenadosPorValor();
}
