package com.ntt.clients.controllers;

import com.ntt.clients.dto.ClientDTO;
import com.ntt.clients.dto.ManageClientDTO;
import com.ntt.clients.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;

    @GetMapping("/{id}")
    public ClientDTO findClientById(@PathVariable Long id) {
        return clientService.findClientById(id);
    }

    @PostMapping
    public Map<String, Long> createClient(@RequestBody @Validated ManageClientDTO dto) {
        return clientService.createClient(dto);
    }

    @PutMapping("/{id}")
    public Map<String, Long> updateClient(@PathVariable Long id, @RequestBody ManageClientDTO dto) {
        return clientService.updateClient(id, dto);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteClient(@PathVariable Long id) {
        return clientService.deleteClient(id);
    }
}
