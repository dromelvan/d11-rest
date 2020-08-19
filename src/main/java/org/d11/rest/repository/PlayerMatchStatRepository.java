package org.d11.rest.repository;

import java.util.List;

import org.d11.rest.model.jpa.PlayerMatchStat;
import org.d11.rest.util.PlayerMatchStats;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlayerMatchStatRepository extends D11RestRepository<PlayerMatchStat> {

    public PlayerMatchStats findByMatchId(@Param("matchId")Long matchId);
    
    @Query("SELECT pms " + 
           "FROM PlayerMatchStat pms " + 
           "JOIN Match m ON m = pms.match " + 
           "JOIN D11MatchDay d11md ON d11md.matchDay = m.matchDay " + 
           "JOIN D11Match d11m ON d11m.d11MatchDay = d11md " + 
           "JOIN Position p ON p = pms.position " + 
           "WHERE d11m.id = :d11MatchId " + 
           "AND (pms.d11Team = d11m.homeD11Team " + 
           "     OR pms.d11Team = d11m.awayD11Team) " + 
           "ORDER BY p.sortOrder")
    public PlayerMatchStats findByD11MatchId(@Param("d11MatchId")Long d11MatchId);
    
    public List<PlayerMatchStat> findByPlayerIdAndMatchMatchDayPremierLeagueSeasonIdOrderByMatchDatetime(@Param("playerId")Long playerId, @Param("seasonId")Long seasonId);
    
}
