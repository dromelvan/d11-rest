package org.d11.rest.repository;

import java.util.List;

import org.d11.rest.model.jpa.D11RestEntity;
import org.d11.rest.model.jpa.projection.EntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface D11RestRepository<T extends D11RestEntity> extends JpaRepository<T, Long> {

    public List<EntityId> findAllByOrderById();

}
