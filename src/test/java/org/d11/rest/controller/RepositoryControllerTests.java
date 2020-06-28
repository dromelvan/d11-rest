package org.d11.rest.controller;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.d11.rest.api.model.D11RestApiDTO;
import org.d11.rest.model.jpa.D11RestEntity;
import org.d11.rest.util.D11RestModelMapper;
import org.d11.rest.util.NotFoundException;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

public abstract class RepositoryControllerTests<T extends D11RestEntity, U extends D11RestApiDTO, V extends RepositoryController<U,?>> {

	private List<T> d11RestEntities;
	private V repositoryController;
	private ModelMapper modelMapper = new D11RestModelMapper();
		
	public List<T> getD11RestEntities() {
		return d11RestEntities;
	}


	public void setD11RestEntities(List<T> d11RestEntities) {
		this.d11RestEntities = d11RestEntities;
	}


	public V getRepositoryController() {
		return repositoryController;
	}


	public void setRepositoryController(V repositoryController) {
		this.repositoryController = repositoryController;
	}

	public void beforeAll() {
		this.d11RestEntities.forEach(d11RestEntity -> when(this.repositoryController.getRepositoryService().findById(d11RestEntity.getId())).thenReturn(map(d11RestEntity)));
		
		when(this.repositoryController.getRepositoryService().findById((long)-1)).thenThrow(NotFoundException.class);
		when(this.repositoryController.getRepositoryService().findAll()).thenReturn(map(this.d11RestEntities)); 		
		when(this.repositoryController.getRepositoryService().findAllIds()).thenReturn(this.d11RestEntities.stream()
																								.map(d11RestEntity -> d11RestEntity.getId())
																								.collect(Collectors.toList()));
	}
	
	protected U map(T entity) {
		return this.modelMapper.map(entity, getDTOClass());
	}
	
	protected List<U> map(List<T> entities) {
		return entities.stream()
				.map(entity -> map(entity))
				.collect(Collectors.toList());
	}
	
	protected abstract Class<U> getDTOClass();

	protected <S> S map(T entity, Class<S> clazz) {
		return this.modelMapper.map(entity, clazz);
	}
	
	@Test
	public void findAll() {
		ResponseEntity<List<U>> responseEntity = this.repositoryController.findAll();
		
		List<U> response = (List<U>)responseEntity.getBody();
		assertEqualsDTO(this.d11RestEntities, response);
	}
	
	@Test
	public void findAllIds() {
		ResponseEntity<List<Long>> responseEntity = this.repositoryController.findAllIds();
		
		List<Long> response = (List<Long>)responseEntity.getBody();
		assertNotNull(response);
		
		assertEquals(this.d11RestEntities.stream().map(d11RestEntity -> d11RestEntity.getId()).collect(Collectors.toList()), response);
	}
	
	@Test
	public void findById() {
		D11RestEntity d11RestEntity = this.d11RestEntities.get(0);

		ResponseEntity<U> responseEntity = this.repositoryController.findById(d11RestEntity.getId());
	
		U d11RestApiDTO = responseEntity.getBody();
		assertEqualsDTO(d11RestEntity, d11RestApiDTO);
		assertThrows(NotFoundException.class, () -> this.repositoryController.findById((long)-1));
	}
	
}
