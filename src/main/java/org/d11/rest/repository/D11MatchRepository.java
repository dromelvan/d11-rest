package org.d11.rest.repository;

import java.util.List;

import org.d11.rest.model.jpa.D11Match;
import org.d11.rest.model.jpa.projection.D11TeamRemainingPlayerCount;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface D11MatchRepository extends D11RestRepository<D11Match> {

    @Query("SELECT pms.d11Team.id as d11TeamId, COUNT(pms) as remainingPlayerCount " +
            "FROM PlayerMatchStat pms " +
            "JOIN Match m ON pms.match.id = m.id " +
            "JOIN D11MatchDay d11md ON d11md.matchDay.id = m.matchDay.id " +
            "JOIN D11Match d11m ON d11m.d11MatchDay.id = d11md.id " +
            "WHERE d11m.id = :d11MatchId " +
            "AND m.status <= 1 " +
            "AND (pms.d11Team.id = d11m.homeD11Team.id OR pms.d11Team.id = d11m.awayD11Team.id) " +
            "GROUP BY pms.d11Team.id")    
    public List<D11TeamRemainingPlayerCount> findD11TeamRemainingPlayerCountByD11MatchId(@Param("d11MatchId")Long d11MatchId);
    
}
