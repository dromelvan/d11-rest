package org.d11.rest.service;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.d11.rest.api.model.D11RestApiDTO;
import org.d11.rest.model.jpa.D11RestEntity;
import org.d11.rest.repository.D11RestRepository;
import org.d11.rest.util.NotFoundException;
import org.junit.jupiter.api.Test;

public abstract class RepositoryServiceTests<T extends D11RestEntity, U extends D11RestApiDTO, V extends D11RestRepository<T>, X extends RepositoryService<T, U, V>> {

	private List<T> d11RestEntities;
	private X repositoryService;
	
	public List<T> getD11RestEntities() {
		return d11RestEntities;
	}
	
	public void setD11RestEntities(List<T> d11RestEntities) {	
		this.d11RestEntities = d11RestEntities;
	}
	
	public X getRepositoryService() {
		return repositoryService;
	}

	public void setRepositoryService(X repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void beforeAll() {		
		this.d11RestEntities.forEach(d11RestEntity -> when(this.repositoryService.getJpaRepository().findById(d11RestEntity.getId())).thenReturn(Optional.of(d11RestEntity)));
		
		when(this.repositoryService.getJpaRepository().findAll()).thenReturn(this.d11RestEntities);
		when(this.repositoryService.getJpaRepository().findById((long)-1)).thenReturn(Optional.empty());		
	}
	
	@Test
	public void findAll() {
		List<U> result = this.repositoryService.findAll();
		assertNotNull(result);
		assertEqualsDTO(this.d11RestEntities, result);
	}

	@Test
	public void findById() {
		T d11RestEntity = this.d11RestEntities.get(0);
		U result = this.repositoryService.findById(d11RestEntity.getId());
		
		assertNotNull(result);
		assertEqualsDTO(d11RestEntity, result);
		
		assertThrows(NotFoundException.class, () -> this.repositoryService.findById((long)-1));				
	}
	
}
