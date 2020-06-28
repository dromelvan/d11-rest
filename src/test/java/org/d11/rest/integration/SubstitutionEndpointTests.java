package org.d11.rest.integration;

import java.util.List;

import org.d11.rest.api.Endpoint;
import org.d11.rest.api.model.SubstitutionDTO;
import org.d11.rest.controller.SubstitutionController;
import org.d11.rest.model.jpa.Substitution;
import org.d11.rest.repository.SubstitutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

@TestInstance(Lifecycle.PER_CLASS)
public class SubstitutionEndpointTests extends SeasonMockEndpointTests<Substitution, SubstitutionDTO, SubstitutionController> {
	
	@Override
	protected SubstitutionDTO readValue(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<SubstitutionDTO>() {});
	}
	
	@Override
	protected List<SubstitutionDTO> readValues(MvcResult mvcResult) throws Exception {
		return readValue(mvcResult, new TypeReference<List<SubstitutionDTO>>() {});
	}

	@BeforeEach
	public void beforeEach() {
		super.beforeEach();
		SubstitutionRepository substitutionRepository = getRepository(SubstitutionRepository.class);
		setD11RestEntities(substitutionRepository.findAll());
	}
	
	@Test
	public void findAll() throws Exception {
		super.findAll(Endpoint.SUBSTITUTIONS);
	}

	@Test
	public void findAllIds() throws Exception {
		super.findAllIds(Endpoint.SUBSTITUTIONS_IDS);
	}
	
	@Test
	public void findById() throws Exception {
		super.findById(Endpoint.SUBSTITUTION);
	}
	
}
