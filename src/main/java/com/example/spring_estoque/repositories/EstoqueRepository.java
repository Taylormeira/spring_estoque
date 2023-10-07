package com.example.spring_estoque.repositories;

import com.example.spring_estoque.models.EstoqueModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstoqueRepository extends JpaRepository<EstoqueModel, UUID> {
}
