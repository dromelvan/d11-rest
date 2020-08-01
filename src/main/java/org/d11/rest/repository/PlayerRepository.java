package org.d11.rest.repository;

import java.util.List;

import org.d11.rest.model.jpa.Player;

public interface PlayerRepository extends D11RestRepository<Player> {

    public List<Player> findByParameterizedNameLike(String parameterizedName);
    
}
