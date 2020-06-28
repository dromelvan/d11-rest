package org.d11.rest.integration;

import static org.d11.rest.DTOAssertions.assertEqualsDTO;
import static org.d11.rest.model.D11RestMock.administrator;
import static org.d11.rest.model.D11RestMock.user;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;
import java.util.stream.Collectors;

import org.d11.rest.api.model.D11RestApiDTO;
import org.d11.rest.controller.RepositoryController;
import org.d11.rest.model.jpa.D11RestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.type.TypeReference;

public abstract class RepositoryIntegrationTests<T extends D11RestEntity, U extends D11RestApiDTO, V extends RepositoryController<U,?>> extends IntegrationTests {

	private List<T> d11RestEntities;
		
	public RepositoryIntegrationTests() {}
	
	public RepositoryIntegrationTests(JpaRepository<?,?>... jpaRepositories) {
		super(jpaRepositories);
	}
	
	public List<T> getD11RestEntities() {
		return d11RestEntities;
	}

	public void setD11RestEntities(List<T> d11RestEntities) {
		this.d11RestEntities = d11RestEntities;
	}
	
	public void findAll(String endpoint) throws Exception {
		assertOk(get(endpoint));
		assertOk(get(endpoint), token(user()));			
		
		MvcResult mvcResult = assertOk(get(endpoint), token(administrator()));
		
		List<U> result = readValues(mvcResult);
		assertNotNull(result);
		assertFalse(result.isEmpty());
		for(int i = 0; i < this.d11RestEntities.size(); ++i) {
			T d11RestEntity = d11RestEntities.get(i);
			U d11RestApiDTO = result.get(i);
			
			assertEqualsDTO(d11RestEntity, d11RestApiDTO);			
		}		
	}
	
	public void findAllIds(String endpoint) throws Exception {
		assertOk(get(endpoint));
		assertOk(get(endpoint), token(user()));			
		
		MvcResult mvcResult = assertOk(get(endpoint), token(administrator()));
		
		List<Long> result = readValue(mvcResult, new TypeReference<List<Long>>(){});
		assertNotNull(result);
		assertFalse(result.isEmpty());
		assertEquals(this.d11RestEntities.stream().map(d11RestEntity -> d11RestEntity.getId()).collect(Collectors.toList()), result);
	}
	
	@Transactional
	public void findById(String endpoint) throws Exception {
		T d11RestEntity = this.d11RestEntities.get(0);
		
		assertOk(get(endpoint, d11RestEntity.getId()));
		assertOk(get(endpoint, d11RestEntity.getId()), token(user()));			
		
		String token = token(administrator());
		MvcResult  mvcResult = assertNotFound(get(endpoint, -1), token);
		mvcResult = assertOk(get(endpoint, d11RestEntity.getId()), token);
		
		U d11RestApiDTO = readValue(mvcResult);
		
		assertEqualsDTO(d11RestEntity, d11RestApiDTO);
	}	
	
	protected abstract U readValue(MvcResult mvcResult) throws Exception;
	
	protected abstract List<U> readValues(MvcResult mvcResult) throws Exception;
	
}
