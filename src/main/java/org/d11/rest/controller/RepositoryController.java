package org.d11.rest.controller;

import java.util.List;

import org.d11.rest.api.model.D11RestApiDTO;
import org.d11.rest.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class RepositoryController<T extends D11RestApiDTO, U extends RepositoryService<?, T, ?>> extends D11RestController {

    private U repositoryService;

    @Autowired
    public RepositoryController(U repositoryService) {
        this.repositoryService = repositoryService;
    }

    public ResponseEntity<List<T>> findAll() {
        List<T> d11RestApiDTOs = this.repositoryService.findAll();
        return ResponseEntity.ok(d11RestApiDTOs);
    }

    public ResponseEntity<List<Long>> findAllIds() {
        // TODO: Make repository method for returning EntityId list instead.
//		List<T> d11RestApiDTOs = this.repositoryService.findAll();
//		return ResponseEntity.ok(d11RestApiDTOs.stream()
//                			             .map(d11RestApiDTO-> d11RestApiDTO.getId())
//                                         .collect(Collectors.toList()));
        List<Long> ids = this.repositoryService.findAllIds();
        return ResponseEntity.ok(ids);
    }

    public ResponseEntity<T> findById(long id) {
        T d11RestApiDTO = this.repositoryService.findById(id);
        return ResponseEntity.ok(d11RestApiDTO);
    }

    protected U getRepositoryService() {
        return this.repositoryService;
    }

}
