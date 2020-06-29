package org.d11.rest.repository;

import org.d11.rest.model.jpa.PlayerMatchStat;
import org.d11.rest.util.PlayerMatchStats;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerMatchStatRepository extends D11RestRepository<PlayerMatchStat> {

    public PlayerMatchStats findByMatchId(@Param("matchId")Long matchId);
    
    @Query("SELECT pms " + 
           "FROM PlayerMatchStat pms " + 
           "JOIN Match m ON m.id = pms.match.id " + 
           "JOIN D11MatchDay d11md ON d11md.matchDay.id = m.matchDay.id " + 
           "JOIN D11Match d11m ON d11m.d11MatchDay.id = d11md.id " + 
           "JOIN Position p ON p.id = pms.position.id " + 
           "WHERE d11m.id = :d11MatchId " + 
           "AND (pms.d11Team.id = d11m.homeD11Team.id " + 
           "     OR pms.d11Team.id = d11m.awayD11Team.id) " + 
           "ORDER BY p.sortOrder")
    public PlayerMatchStats findByD11MatchId(@Param("d11MatchId")Long d11MatchId);
    
}
