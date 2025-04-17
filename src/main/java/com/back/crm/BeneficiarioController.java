package com.back.crm;


import com.back.crm.repository.Beneficiario;
import com.back.crm.repository.BeneficiarioRepository;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Beneficiarios")

public class BeneficiarioController {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Column
    @PostMapping
    public Beneficiario Insert(@RequestBody Beneficiario beneficiario) {
        Beneficiario result = beneficiarioRepository.save(beneficiario);
        return result;
    }

    @GetMapping
    public ResponseEntity<List<Beneficiario>> findAll() {
        List<Beneficiario> beneficiarios = beneficiarioRepository.findAll();
        return ResponseEntity.ok(beneficiarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Beneficiario> findById(@PathVariable Long id) {
        return beneficiarioRepository.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Beneficiario> update(@PathVariable Long id, @RequestBody Beneficiario beneficiario) {
        if (!beneficiarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        beneficiario.setId(id);
        Beneficiario updated = beneficiarioRepository.save(beneficiario);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!beneficiarioRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        beneficiarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}







