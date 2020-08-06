package org.d11.rest.repository;

import java.util.List;

import org.d11.rest.model.jpa.Player;
import org.d11.rest.model.jpa.projection.PlayerSearchResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerRepository extends D11RestRepository<Player> {

    // There's no UNION in JPQL so we have to make a native query for this.
    @Query(value = "SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, ts.id AS teamId, ts.name AS teamName " + 
                   "FROM players ps " + 
                   "     JOIN player_season_infos psis ON ps.id = psis.player_id " + 
                   "     JOIN teams ts ON ts.id = psis.team_id " + 
                   "     WHERE season_id = (SELECT MAX(id) FROM seasons) " + 
                   "     AND parameterized_name LIKE :parameterizedName " + 
                   "UNION " + 
                   "SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, 1 AS teamId, 'None' AS teamName " + 
                   "FROM players ps " + 
                   "     WHERE parameterized_name LIKE :parameterizedName " + 
                   "     AND NOT EXISTS(SELECT id FROM player_season_infos psis WHERE player_id = ps.id AND season_id = (SELECT MAX(id) FROM seasons)) " + 
                   "ORDER BY name",
            nativeQuery = true)
    public List<PlayerSearchResult> findByParameterizedNameLike(@Param("parameterizedName")String parameterizedName);

    @Query(value = "SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, ts.id AS teamId, ts.name AS teamName " + 
                   "FROM players ps " + 
                   "     JOIN player_season_infos psis ON ps.id = psis.player_id " + 
                   "     JOIN teams ts ON ts.id = psis.team_id " + 
                   "     WHERE season_id = (SELECT MAX(id) FROM seasons) " + 
                   "     AND parameterized_name = :parameterizedName " + 
                   "UNION " + 
                   "SELECT ps.id, TRIM(FROM concat(first_name, ' ', last_name)) AS name, 1 AS teamId, 'None' AS teamName " + 
                   "FROM players ps " + 
                   "     WHERE parameterized_name = :parameterizedName " + 
                   "     AND NOT EXISTS(SELECT id FROM player_season_infos psis WHERE player_id = ps.id AND season_id = (SELECT MAX(id) FROM seasons)) " + 
                   "ORDER BY name",
            nativeQuery = true)
    public List<PlayerSearchResult> findByParameterizedName(@Param("parameterizedName")String parameterizedName);
    
}
