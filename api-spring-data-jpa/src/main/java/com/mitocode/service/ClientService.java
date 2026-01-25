package com.mitocode.service;

import com.mitocode.entity.Client;
import com.mitocode.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repo;

    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    // CRUD básico
    public List<Client> findAll() {
        return repo.findAll();
    }

    public Page<Client> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    public Client save(Client c) {
        return repo.save(c);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Client findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Query methods
    public List<Client> searchByName(String name) {
        return repo.findByNameContainingIgnoreCase(name);
    }

    public long countByEmail(String email) {
        return repo.countByEmail(email);
    }

    // JPQL example
    public List<Client> withMoreThanOrders(int n) {
        return repo.findClientsWithMoreThanOrders(n);
    }

    // Ejemplo @Transactional (para 7)
    @Transactional
    public void batchCreate(List<Client> clients) {
        // saveAll ya optimiza, pero lo usamos para ilustrar @Transactional
        repo.saveAll(clients);
    }
}