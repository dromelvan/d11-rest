package org.d11.rest.service;

import static org.d11.rest.model.D11RestMock.match;
import static org.d11.rest.model.D11RestMock.player;
import static org.d11.rest.model.D11RestMock.substitutions;
import static org.d11.rest.model.D11RestMock.team;
import static org.mockito.Mockito.mock;

import java.util.List;

import org.d11.rest.Tags;
import org.d11.rest.api.model.SubstitutionDTO;
import org.d11.rest.model.jpa.Substitution;
import org.d11.rest.repository.SubstitutionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@Tag(Tags.UNIT_TEST)
@TestInstance(Lifecycle.PER_CLASS)
public class SubstitutionServiceTests extends RepositoryServiceTests<Substitution, SubstitutionDTO, SubstitutionRepository, SubstitutionService> {

	@BeforeAll
	public void beforeAll() {
		setRepositoryService(new SubstitutionService(mock(SubstitutionRepository.class)));
		List<Substitution> substitutions = substitutions();
		for(Substitution substitution : substitutions) {
			match().addSubstitution(substitution);
			substitution.setTeam(team());
			substitution.setPlayer(player());
			substitution.setPlayerIn(player(substitution.getPlayer().getId().intValue() + 1));
		}
		setD11RestEntities(substitutions);
		
		super.beforeAll();
	}	
	
}
