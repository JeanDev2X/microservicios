package com.mitocode.controller;

import com.mitocode.dto.ClientRequest;
import com.mitocode.dto.ClientResponse;
import com.mitocode.entity.Client;
import com.mitocode.mapper.ClientMapper;
import com.mitocode.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService service;

    public ClientController(ClientService service) {
        this.service = service;
    }

    // CRUD básico
    @GetMapping
    public List<ClientResponse> findAll() {
        return service.findAll().stream()
                .map(ClientMapper::toClientResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/page")
    public Page<ClientResponse> page(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "name,asc") String sort) {
        String[] parts = sort.split(",");
        Sort s = Sort.by(Sort.Direction.fromString(parts[1]), parts[0]);

        return service.findAll(PageRequest.of(page, size, s))
                .map(ClientMapper::toClientResponse);
    }

    @GetMapping("/{id}")
    public ClientResponse findById(@PathVariable Long id) {
        Client client = service.findById(id);
        return ClientMapper.toClientResponse(client);
    }

    @PostMapping
    public ClientResponse create(@RequestBody ClientRequest clientRequest) {
        Client saved = service.save(ClientMapper.toClient(clientRequest));
        return ClientMapper.toClientResponse(saved);
    }

    @PutMapping("/{id}")
    public ClientResponse update(@PathVariable Long id, @RequestBody ClientRequest clientRequest) {
        Client updated = service.save(ClientMapper.toClient(clientRequest.withId(id)));
        return ClientMapper.toClientResponse(updated);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    // Query Methods
    @GetMapping("/search")
    public List<ClientResponse> search(@RequestParam String name) {
        return service.searchByName(name).stream()
                .map(ClientMapper::toClientResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/countByEmail")
    public long countByEmail(@RequestParam String email) {
        return service.countByEmail(email);
    }

    // JPQL (clientes con más de N órdenes)
    @GetMapping("/with-more-than")
    public List<ClientResponse> withMoreThan(@RequestParam int orders) {
        return service.withMoreThanOrders(orders).stream()
                .map(ClientMapper::toClientResponse)
                .collect(Collectors.toList());
    }
}