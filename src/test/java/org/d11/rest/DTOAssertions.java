package org.d11.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.d11.rest.api.collection.*;
import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.*;
import org.d11.rest.service.mapper.*;
import org.d11.rest.util.PlayerMatchStats;

public class DTOAssertions {

    public static void assertEqualsDTO(D11RestEntity d11RestEntity, D11RestApiDTO d11RestApiDTO) {
        if(d11RestEntity instanceof Country && d11RestApiDTO instanceof CountryDTO) {
            assertEqualsDTO((Country) d11RestEntity, (CountryDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Player && d11RestApiDTO instanceof PlayerDTO) {
            assertEqualsDTO((Player) d11RestEntity, (PlayerDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Player && d11RestApiDTO instanceof PlayerBaseDTO) {
            assertEqualsDTO((Player) d11RestEntity, (PlayerBaseDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof PlayerSeasonInfo && d11RestApiDTO instanceof PlayerSeasonInfoDTO) {
            assertEqualsDTO((PlayerSeasonInfo) d11RestEntity, (PlayerSeasonInfoDTO) d11RestApiDTO);            
        } else if(d11RestEntity instanceof Position && d11RestApiDTO instanceof PositionDTO) {
            assertEqualsDTO((Position) d11RestEntity, (PositionDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Team && d11RestApiDTO instanceof TeamDTO) {
            assertEqualsDTO((Team) d11RestEntity, (TeamDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Team && d11RestApiDTO instanceof TeamBaseDTO) {
            assertEqualsDTO((Team) d11RestEntity, (TeamBaseDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof D11Team && d11RestApiDTO instanceof D11TeamDTO) {
            assertEqualsDTO((D11Team) d11RestEntity, (D11TeamDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof D11Team && d11RestApiDTO instanceof D11TeamBaseDTO) {
            assertEqualsDTO((D11Team) d11RestEntity, (D11TeamBaseDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Stadium && d11RestApiDTO instanceof StadiumDTO) {
            assertEqualsDTO((Stadium) d11RestEntity, (StadiumDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Season && d11RestApiDTO instanceof SeasonDTO) {
            assertEqualsDTO((Season) d11RestEntity, (SeasonDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Season && d11RestApiDTO instanceof SeasonBaseDTO) {
            assertEqualsDTO((Season) d11RestEntity, (SeasonBaseDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof PremierLeague && d11RestApiDTO instanceof PremierLeagueDTO) {
            assertEqualsDTO((PremierLeague) d11RestEntity, (PremierLeagueDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof D11League && d11RestApiDTO instanceof D11LeagueDTO) {
            assertEqualsDTO((D11League) d11RestEntity, (D11LeagueDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof League && d11RestApiDTO instanceof LeagueBaseDTO) {
            assertEqualsDTO(d11RestEntity, d11RestApiDTO);
        } else if(d11RestEntity instanceof MatchDay && d11RestApiDTO instanceof MatchDayDTO) {
            assertEqualsDTO((MatchDay) d11RestEntity, (MatchDayDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof MatchDay && d11RestApiDTO instanceof MatchDayBaseDTO) {
            assertEqualsDTO((MatchDay) d11RestEntity, (MatchDayBaseDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof D11MatchDay && d11RestApiDTO instanceof D11MatchDayDTO) {
            assertEqualsDTO((D11MatchDay) d11RestEntity, (D11MatchDayDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof D11MatchDay && d11RestApiDTO instanceof D11MatchDayBaseDTO) {
            assertEqualsDTO((D11MatchDay) d11RestEntity, (D11MatchDayBaseDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Match && d11RestApiDTO instanceof MatchDTO) {
            assertEqualsDTO((Match) d11RestEntity, (MatchDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Match && d11RestApiDTO instanceof MatchMatchEventsDTO) {
            assertEqualsDTO((Match) d11RestEntity, (MatchMatchEventsDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Match && d11RestApiDTO instanceof MatchBaseDTO) {
            assertEqualsDTO((Match) d11RestEntity, (MatchBaseDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof D11Match && d11RestApiDTO instanceof D11MatchDTO) {
            assertEqualsDTO((D11Match) d11RestEntity, (D11MatchDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof D11Match && d11RestApiDTO instanceof D11MatchBaseDTO) {
            assertEqualsDTO((D11Match) d11RestEntity, (D11MatchBaseDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof PlayerMatchStat && d11RestApiDTO instanceof PlayerMatchStatDTO) {
            assertEqualsDTO((PlayerMatchStat) d11RestEntity, (PlayerMatchStatDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof PlayerMatchStat && d11RestApiDTO instanceof PlayerMatchStatBaseDTO) {
            assertEqualsDTO((PlayerMatchStat) d11RestEntity, (PlayerMatchStatBaseDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Goal && d11RestApiDTO instanceof GoalDTO) {
            assertEqualsDTO((Goal) d11RestEntity, (GoalDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Card && d11RestApiDTO instanceof CardDTO) {
            assertEqualsDTO((Card) d11RestEntity, (CardDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof Substitution && d11RestApiDTO instanceof SubstitutionDTO) {
            assertEqualsDTO((Substitution) d11RestEntity, (SubstitutionDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof MatchEvent && d11RestApiDTO instanceof MatchEventBaseDTO) {
            assertEqualsDTO(d11RestEntity, d11RestApiDTO);
        } else if(d11RestEntity instanceof TeamTableStat && d11RestApiDTO instanceof TeamTableStatDTO) {
            assertEqualsDTO((TeamTableStat) d11RestEntity, (TeamTableStatDTO) d11RestApiDTO);
        } else if(d11RestEntity instanceof D11TeamTableStat && d11RestApiDTO instanceof D11TeamTableStatDTO) {
            assertEqualsDTO((D11TeamTableStat) d11RestEntity, (D11TeamTableStatDTO) d11RestApiDTO);            
        } else {
            // If we end up here, we've messed up the test somehow and then we want to be
            // notified.
            assertTrue(false);
        }
    }

    public static void assertEqualsDTO(List<? extends D11RestEntity> d11RestEntities, List<? extends D11RestApiDTO> d11RestApiDTOs) {
        assertNotNull(d11RestEntities);
        assertNotNull(d11RestApiDTOs);
        assertEquals(d11RestEntities.size(), d11RestApiDTOs.size());

        for(int i = 0; i < d11RestEntities.size(); ++i) {
            assertEqualsDTO(d11RestEntities.get(i), d11RestApiDTOs.get(i));
        }
    }

    public static void assertEqualsDTO(User user, UserDTO userDTO) {
        assertNotNull(userDTO);
        assertEquals(user.getUsername(), userDTO.getUsername());
        assertEquals(user.getName(), userDTO.getName());
    }

    public static void assertEqualsDTO(Country country, CountryDTO countryDTO) {
        assertNotNull(countryDTO);
        assertEquals(country.getId(), countryDTO.getId());
        assertEquals(country.getName(), countryDTO.getName());
        assertEquals(country.getIso(), countryDTO.getIso());
    }

    public static void assertEqualsDTO(Player player, PlayerBaseDTO playerBaseDTO) {
        assertNotNull(playerBaseDTO);
        assertEquals(player.getId(), playerBaseDTO.getId());
        assertEquals(player.getFirstName(), playerBaseDTO.getFirstName());
        assertEquals(player.getLastName(), playerBaseDTO.getLastName());
        assertEquals(player.getName(), playerBaseDTO.getName());
        assertEquals(player.getShortName(), playerBaseDTO.getShortName());
    }

    public static void assertEqualsDTO(Player player, PlayerDTO playerDTO) {
        assertEqualsDTO(player, (PlayerBaseDTO) playerDTO);
        assertEquals(player.getCountry().getId(), playerDTO.getCountryId());
        assertEquals(player.getCountry().getName(), playerDTO.getCountryName());
        assertEquals(player.getWhoScoredId(), playerDTO.getWhoScoredId());
        assertEquals(player.getDateOfBirth(), playerDTO.getDateOfBirth());
        assertEquals(player.getHeight(), playerDTO.getHeight());
        assertEquals(player.getWeight(), playerDTO.getWeight());
        assertEquals(player.getParameterizedName(), playerDTO.getParameterizedName());
    }

    public static void assertEqualsDTO(PlayerSeasonInfo playerSeasonInfo, PlayerSeasonInfoDTO playerSeasonInfoDTO) {
        assertEquals(playerSeasonInfo.getId(), playerSeasonInfoDTO.getId());
        assertEqualsDTO(playerSeasonInfo.getPlayer(), playerSeasonInfoDTO.getPlayer());
        assertEqualsDTO(playerSeasonInfo.getSeason(), playerSeasonInfoDTO.getSeason());
        assertEqualsDTO(playerSeasonInfo.getTeam(), playerSeasonInfoDTO.getTeam());
        assertEqualsDTO(playerSeasonInfo.getD11Team(), playerSeasonInfoDTO.getD11Team());
        assertEqualsDTO(playerSeasonInfo.getPosition(), playerSeasonInfoDTO.getPosition());        
        assertEquals(playerSeasonInfo.getValue(), playerSeasonInfoDTO.getValue());
    }
    
    public static void assertEqualsDTO(Position position, PositionDTO positionDTO) {
        assertNotNull(positionDTO);
        assertEquals(position.getId(), positionDTO.getId());
        assertEquals(position.getName(), positionDTO.getName());
        assertEquals(position.getCode(), positionDTO.getCode());
        assertEquals(position.isDefender(), positionDTO.isDefender());
        assertEquals(position.getSortOrder(), positionDTO.getSortOrder());
    }

    public static void assertEqualsDTO(Stadium stadium, StadiumDTO stadiumDTO) {
        assertNotNull(stadiumDTO);
        assertEquals(stadium.getId(), stadiumDTO.getId());
        assertEquals(stadium.getName(), stadiumDTO.getName());
        assertEquals(stadium.getCity(), stadiumDTO.getCity());
        assertEquals(stadium.getCapacity(), stadiumDTO.getCapacity());
        assertEquals(stadium.getOpened(), stadiumDTO.getOpened());
    }

    public static void assertEqualsDTO(Team team, TeamBaseDTO teamBaseDTO) {
        assertNotNull(teamBaseDTO);
        assertEquals(team.getId(), teamBaseDTO.getId());
        assertEquals(team.getName(), teamBaseDTO.getName());
        assertEquals(team.getShortName(), teamBaseDTO.getShortName());
        assertEquals(team.getCode(), teamBaseDTO.getCode());
    }

    public static void assertEqualsDTO(Team team, TeamDTO teamDTO) {
        assertEqualsDTO(team, (TeamBaseDTO) teamDTO);
        assertEquals(team.getWhoScoredId(), teamDTO.getWhoScoredId());
        assertEquals(team.getNickname(), teamDTO.getNickname());
        assertEquals(team.getEstablished(), teamDTO.getEstablished());
        assertEquals(team.getMotto(), teamDTO.getMotto());
        assertEquals(team.getColour(), teamDTO.getColour());
        assertEquals(team.isDummy(), teamDTO.isDummy());
        assertEquals(team.getStadium().getId(), teamDTO.getStadiumId());
        assertEquals(team.getStadium().getName(), teamDTO.getStadiumName());
    }

    public static void assertEqualsDTO(D11Team d11Team, D11TeamBaseDTO d11TeamBaseDTO) {
        assertNotNull(d11TeamBaseDTO);
        assertEquals(d11Team.getId(), d11TeamBaseDTO.getId());
        assertEquals(d11Team.getName(), d11TeamBaseDTO.getName());
        assertEquals(d11Team.getShortName(), d11TeamBaseDTO.getShortName());
        assertEquals(d11Team.getCode(), d11TeamBaseDTO.getCode());
        assertEquals(d11Team.isDummy(), d11TeamBaseDTO.isDummy());
    }

    public static void assertEqualsDTO(D11Team d11Team, D11TeamDTO d11TeamDTO) {
        assertEqualsDTO(d11Team, (D11TeamBaseDTO) d11TeamDTO);
        assertEquals(d11Team.getOwner().getId(), d11TeamDTO.getOwnerId());
        assertEquals(d11Team.getOwner().getName(), d11TeamDTO.getOwnerName());
    }

    public static void assertEqualsDTO(Season season, SeasonBaseDTO seasonBaseDTO) {
        assertNotNull(seasonBaseDTO);
        assertEquals(season.getId(), seasonBaseDTO.getId());
        assertEquals(season.getName(), seasonBaseDTO.getName());
        assertEquals(season.getShortName(), seasonBaseDTO.getShortName());
        assertEquals(season.getDate(), seasonBaseDTO.getDate());
        assertEquals(season.getStatus(), seasonBaseDTO.getStatus());
    }

    public static void assertEqualsDTO(Season season, SeasonDTO seasonDTO) {
        assertEqualsDTO(season, (SeasonBaseDTO) seasonDTO);
        assertEquals(season.isLegacy(), seasonDTO.isLegacy());
        assertEqualsDTO(season.getPremierLeague(), seasonDTO.getPremierLeague());
        assertEqualsDTO(season.getD11League(), seasonDTO.getD11League());
    }

    public static void assertEqualsDTO(PremierLeague premierLeague, LeagueBaseDTO leagueBaseDTO) {
        assertNotNull(leagueBaseDTO);
        assertEquals(premierLeague.getId(), leagueBaseDTO.getId());
        assertEquals(premierLeague.getName(), leagueBaseDTO.getName());
    }

    public static void assertEqualsDTO(PremierLeague premierLeague, PremierLeagueDTO premierLeagueDTO) {
        assertEqualsDTO(premierLeague, (LeagueBaseDTO) premierLeagueDTO);
        assertEqualsDTO(premierLeague.getSeason(), premierLeagueDTO.getSeason());
        assertEquals(premierLeague.getMatchDays().stream().map(matchDay -> matchDay.getId()).collect(Collectors.toList()), premierLeagueDTO.getMatchDays());
    }

    public static void assertEqualsDTO(D11League d11League, LeagueBaseDTO leagueBaseDTO) {
        assertNotNull(leagueBaseDTO);
        assertEquals(d11League.getId(), leagueBaseDTO.getId());
        assertEquals(d11League.getName(), leagueBaseDTO.getName());
    }

    public static void assertEqualsDTO(D11League d11League, D11LeagueDTO d11LeagueDTO) {
        assertEqualsDTO(d11League, (LeagueBaseDTO) d11LeagueDTO);
        assertEqualsDTO(d11League.getSeason(), d11LeagueDTO.getSeason());
        assertEquals(d11League.getD11MatchDays().stream().map(d11MatchDay -> d11MatchDay.getId()).collect(Collectors.toList()), d11LeagueDTO.getD11MatchDays());
    }

    public static void assertEqualsDTO(MatchDay matchDay, MatchDayBaseDTO matchDayBaseDTO) {
        assertNotNull(matchDayBaseDTO);
        assertEquals(matchDay.getId(), matchDayBaseDTO.getId());
        assertEquals(matchDay.getDate(), matchDayBaseDTO.getDate());
        assertEquals(matchDay.getMatchDayNumber(), matchDayBaseDTO.getMatchDayNumber());
        assertEquals(matchDay.getStatus(), matchDayBaseDTO.getStatus());
    }

    public static void assertEqualsDTO(MatchDay matchDay, MatchDayDTO matchDayDTO) {
        assertEqualsDTO(matchDay, (MatchDayBaseDTO) matchDayDTO);
        assertEqualsDTO(matchDay.getPremierLeague(), matchDayDTO.getPremierLeague());
        assertEqualsDTO(matchDay.getPremierLeague().getSeason(), matchDayDTO.getSeason());
        assertEqualsDTO(matchDay.getD11MatchDay(), matchDayDTO.getD11MatchDay());
        assertEquals(new MatchesByDateConverter().convert(matchDay.getMatches()), matchDayDTO.getMatches());
    }

    public static void assertEqualsDTO(D11MatchDay d11MatchDay, D11MatchDayBaseDTO d11MatchDayBaseDTO) {
        assertNotNull(d11MatchDayBaseDTO);
        assertEquals(d11MatchDay.getId(), d11MatchDayBaseDTO.getId());
        assertEquals(d11MatchDay.getDate(), d11MatchDayBaseDTO.getDate());
        assertEquals(d11MatchDay.getMatchDayNumber(), d11MatchDayBaseDTO.getMatchDayNumber());
    }

    public static void assertEqualsDTO(D11MatchDay d11MatchDay, D11MatchDayDTO d11MatchDayDTO) {
        assertEqualsDTO(d11MatchDay, (D11MatchDayBaseDTO) d11MatchDayDTO);
        assertEqualsDTO(d11MatchDay.getD11League(), d11MatchDayDTO.getD11League());
        assertEqualsDTO(d11MatchDay.getD11League().getSeason(), d11MatchDayDTO.getSeason());
        assertEqualsDTO(d11MatchDay.getMatchDay(), d11MatchDayDTO.getMatchDay());
        assertEquals(new D11MatchesByDateConverter().convert(d11MatchDay.getD11Matches()), d11MatchDayDTO.getD11Matches());
    }

    public static void assertEqualsDTO(Match match, MatchBaseDTO matchBaseDTO) {
        assertNotNull(matchBaseDTO);
        assertEquals(match.getId(), matchBaseDTO.getId());
        assertEquals(match.getWhoScoredId(), matchBaseDTO.getWhoScoredId());
        assertEquals(match.getHomeTeamGoals(), matchBaseDTO.getHomeTeamGoals());
        assertEquals(match.getAwayTeamGoals(), matchBaseDTO.getAwayTeamGoals());
        assertEquals(match.getPreviousHomeTeamGoals(), matchBaseDTO.getPreviousHomeTeamGoals());
        assertEquals(match.getPreviousAwayTeamGoals(), matchBaseDTO.getPreviousAwayTeamGoals());
        assertEquals(match.getDatetime(), matchBaseDTO.getDatetime());
        assertEquals(match.getElapsed(), matchBaseDTO.getElapsed());
        assertEquals(match.getStatus(), matchBaseDTO.getStatus());
    }

    public static void assertEqualsDTO(Match match, MatchDTO matchDTO) {
        assertEqualsDTO(match, (MatchBaseDTO) matchDTO);
        assertEqualsDTO(match.getHomeTeam(), matchDTO.getHomeTeam());
        assertEqualsDTO(match.getAwayTeam(), matchDTO.getAwayTeam());
        assertEqualsDTO(match.getMatchDay(), matchDTO.getMatchDay());
        assertEqualsDTO(match.getStadium(), matchDTO.getStadium());
    }

    public static void assertEqualsDTO(Match match, MatchMatchEventsDTO matchMatchEventsDTO) {
        assertEqualsDTO(match, (MatchDTO) matchMatchEventsDTO);
        assertEqualsDTO(match.getGoals(), matchMatchEventsDTO.getGoals());
        assertEqualsDTO(match.getCards(), matchMatchEventsDTO.getCards());
        assertEqualsDTO(match.getSubstitutions(), matchMatchEventsDTO.getSubstitutions());
    }

    public static void assertEqualsDTO(D11Match d11Match, D11MatchBaseDTO d11MatchBaseDTO) {
        assertNotNull(d11MatchBaseDTO);
        assertEquals(d11Match.getId(), d11MatchBaseDTO.getId());
        assertEquals(d11Match.getHomeTeamGoals(), d11MatchBaseDTO.getHomeTeamGoals());
        assertEquals(d11Match.getAwayTeamGoals(), d11MatchBaseDTO.getAwayTeamGoals());
        assertEquals(d11Match.getHomeTeamPoints(), d11MatchBaseDTO.getHomeTeamPoints());
        assertEquals(d11Match.getAwayTeamPoints(), d11MatchBaseDTO.getAwayTeamPoints());
        assertEquals(d11Match.getPreviousHomeTeamGoals(), d11MatchBaseDTO.getPreviousHomeTeamGoals());
        assertEquals(d11Match.getPreviousAwayTeamGoals(), d11MatchBaseDTO.getPreviousAwayTeamGoals());
        assertEquals(d11Match.getPreviousHomeTeamPoints(), d11MatchBaseDTO.getPreviousHomeTeamPoints());
        assertEquals(d11Match.getPreviousAwayTeamPoints(), d11MatchBaseDTO.getPreviousAwayTeamPoints());
        assertEquals(d11Match.getDate(), d11MatchBaseDTO.getDate());
        assertEquals(d11Match.getElapsed(), d11MatchBaseDTO.getElapsed());
        assertEquals(d11Match.getStatus(), d11MatchBaseDTO.getStatus());
    }

    public static void assertEqualsDTO(D11Match d11Match, D11MatchDTO d11MatchDTO) {
        assertEqualsDTO(d11Match, (D11MatchBaseDTO) d11MatchDTO);
        assertEqualsDTO(d11Match.getHomeD11Team(), d11MatchDTO.getHomeD11Team());
        assertEqualsDTO(d11Match.getAwayD11Team(), d11MatchDTO.getAwayD11Team());
        assertEqualsDTO(d11Match.getD11MatchDay(), d11MatchDTO.getD11MatchDay());
    }

    public static void assertEqualsDTO(PlayerMatchStat playerMatchStat, PlayerMatchStatBaseDTO playerMatchStatDTO) {
        assertNotNull(playerMatchStatDTO);
        assertEquals(playerMatchStat.getId(), playerMatchStatDTO.getId());
        assertEquals(playerMatchStat.getPlayedPosition(), playerMatchStatDTO.getPlayedPosition());
        assertEquals(playerMatchStat.getLineup(), playerMatchStatDTO.getLineup());
        assertEquals(playerMatchStat.getSubstitutionOnTime(), playerMatchStatDTO.getSubstitutionOnTime());
        assertEquals(playerMatchStat.getSubstitutionOffTime(), playerMatchStatDTO.getSubstitutionOffTime());
        assertEquals(playerMatchStat.getYellowCardTime(), playerMatchStatDTO.getYellowCardTime());
        assertEquals(playerMatchStat.getRedCardTime(), playerMatchStatDTO.getRedCardTime());
        assertEquals(playerMatchStat.isManOfTheMatch(), playerMatchStatDTO.isManOfTheMatch());
        assertEquals(playerMatchStat.isSharedManOfTheMatch(), playerMatchStatDTO.isSharedManOfTheMatch());
        assertEquals(playerMatchStat.getGoals(), playerMatchStatDTO.getGoals());
        assertEquals(playerMatchStat.getGoalAssists(), playerMatchStatDTO.getGoalAssists());
        assertEquals(playerMatchStat.getOwnGoals(), playerMatchStatDTO.getOwnGoals());
        assertEquals(playerMatchStat.getGoalsConceded(), playerMatchStatDTO.getGoalsConceded());
        assertEquals(playerMatchStat.getRating(), playerMatchStatDTO.getRating());
        assertEquals(playerMatchStat.getPoints(), playerMatchStatDTO.getPoints());
    }

    public static void assertEqualsDTO(PlayerMatchStat playerMatchStat, PlayerMatchStatDTO playerMatchStatDTO) {
        assertEqualsDTO(playerMatchStat, (PlayerMatchStatBaseDTO) playerMatchStatDTO);
        assertEqualsDTO(playerMatchStat.getMatch(), playerMatchStatDTO.getMatch());
        assertEqualsDTO(playerMatchStat.getPlayer(), playerMatchStatDTO.getPlayer());
        assertEqualsDTO(playerMatchStat.getTeam(), playerMatchStatDTO.getTeam());
        assertEqualsDTO(playerMatchStat.getD11Team(), playerMatchStatDTO.getD11Team());
        assertEqualsDTO(playerMatchStat.getPosition(), playerMatchStatDTO.getPosition());
    }

    public static void assertEqualsDTO(MatchEvent matchEvent, MatchEventDTO matchEventDTO) {
        assertNotNull(matchEventDTO);
        assertEquals(matchEvent.getId(), matchEventDTO.getId());
        assertEqualsDTO(matchEvent.getMatch(), matchEventDTO.getMatch());
        assertEqualsDTO(matchEvent.getTeam(), matchEventDTO.getTeam());
        assertEqualsDTO(matchEvent.getPlayer(), matchEventDTO.getPlayer());
        assertEquals(matchEvent.getTime(), matchEventDTO.getTime());
        assertEquals(matchEvent.getAddedTime(), matchEventDTO.getAddedTime());

    }

    public static void assertEqualsDTO(Goal goal, GoalDTO goalDTO) {
        assertEqualsDTO((MatchEvent) goal, (MatchEventDTO) goalDTO);
        assertEquals(goal.isPenalty(), goalDTO.isPenalty());
        assertEquals(goal.isOwnGoal(), goalDTO.isOwnGoal());
    }

    public static void assertEqualsDTO(Card card, CardDTO cardDTO) {
        assertEqualsDTO((MatchEvent) card, (MatchEventDTO) cardDTO);
        assertEquals(card.getCardType(), cardDTO.getCardType());
    }

    public static void assertEqualsDTO(Substitution substitution, SubstitutionDTO substitutionDTO) {
        assertEqualsDTO((MatchEvent) substitution, (MatchEventDTO) substitutionDTO);
        assertEqualsDTO(substitution.getPlayerIn(), substitutionDTO.getPlayerIn());
    }

    public static void assertEqualsDTO(TableStat tableStat, TableStatDTO tableStatDTO) {
        assertNotNull(tableStatDTO);
        assertEquals(tableStat.getId(), tableStatDTO.getId());
        assertEquals(tableStat.getMatchesPlayed(), tableStatDTO.getMatchesPlayed());
        assertEquals(tableStat.getMatchesWon(), tableStatDTO.getMatchesWon());
        assertEquals(tableStat.getMatchesDrawn(), tableStatDTO.getMatchesDrawn());
        assertEquals(tableStat.getMatchesLost(), tableStatDTO.getMatchesLost());
        assertEquals(tableStat.getGoalsFor(), tableStatDTO.getGoalsFor());
        assertEquals(tableStat.getGoalsAgainst(), tableStatDTO.getGoalsAgainst());
        assertEquals(tableStat.getGoalDifference(), tableStatDTO.getGoalDifference());
        assertEquals(tableStat.getPoints(), tableStatDTO.getPoints());
        assertEquals(tableStat.getRanking(), tableStatDTO.getRanking());

        assertEquals(tableStat.getHomeMatchesPlayed(), tableStatDTO.getHomeMatchesPlayed());
        assertEquals(tableStat.getHomeMatchesWon(), tableStatDTO.getHomeMatchesWon());
        assertEquals(tableStat.getHomeMatchesDrawn(), tableStatDTO.getHomeMatchesDrawn());
        assertEquals(tableStat.getHomeMatchesLost(), tableStatDTO.getHomeMatchesLost());
        assertEquals(tableStat.getHomeGoalsFor(), tableStatDTO.getHomeGoalsFor());
        assertEquals(tableStat.getHomeGoalsAgainst(), tableStatDTO.getHomeGoalsAgainst());
        assertEquals(tableStat.getHomeGoalDifference(), tableStatDTO.getHomeGoalDifference());
        assertEquals(tableStat.getHomePoints(), tableStatDTO.getHomePoints());
        assertEquals(tableStat.getHomeRanking(), tableStatDTO.getHomeRanking());

        assertEquals(tableStat.getAwayMatchesPlayed(), tableStatDTO.getAwayMatchesPlayed());
        assertEquals(tableStat.getAwayMatchesWon(), tableStatDTO.getAwayMatchesWon());
        assertEquals(tableStat.getAwayMatchesDrawn(), tableStatDTO.getAwayMatchesDrawn());
        assertEquals(tableStat.getAwayMatchesLost(), tableStatDTO.getAwayMatchesLost());
        assertEquals(tableStat.getAwayGoalsFor(), tableStatDTO.getAwayGoalsFor());
        assertEquals(tableStat.getAwayGoalsAgainst(), tableStatDTO.getAwayGoalsAgainst());
        assertEquals(tableStat.getAwayGoalDifference(), tableStatDTO.getAwayGoalDifference());
        assertEquals(tableStat.getAwayPoints(), tableStatDTO.getAwayPoints());
        assertEquals(tableStat.getAwayRanking(), tableStatDTO.getAwayRanking());

        assertEquals(tableStat.getFormPoints(), tableStatDTO.getFormPoints());
        assertEquals(tableStat.getPreviousRanking(), tableStatDTO.getPreviousRanking());
    }

    public static void assertEqualsDTO(TeamTableStat teamTableStat, TeamTableStatDTO teamTableStatDTO) {
        assertEqualsDTO((TableStat) teamTableStat, (TableStatDTO) teamTableStatDTO);
        assertEqualsDTO(teamTableStat.getTeam(), teamTableStatDTO.getTeam());
    }

    public static void assertEqualsDTO(D11TeamTableStat d11TeamTableStat, D11TeamTableStatDTO d11TeamTableStatDTO) {
        assertEqualsDTO((TableStat) d11TeamTableStat, (TableStatDTO) d11TeamTableStatDTO);
        assertEqualsDTO(d11TeamTableStat.getD11Team(), d11TeamTableStatDTO.getD11Team());
    }
    
    // Collections ------------------------------------------------------------------ //
    
    public static void assertEqualsDTO(PlayerMatchStats playerMatchStats, D11MatchPlayerMatchStatsDTO d11MatchPlayerMatchStatsDTO) {
        assertNotNull(playerMatchStats);
        assertNotNull(d11MatchPlayerMatchStatsDTO);
        
        int count = 0;
        for(PlayerMatchStat playerMatchStat : playerMatchStats) {
            assertNotNull(d11MatchPlayerMatchStatsDTO.get(playerMatchStat.getD11Team().getId()));
            assertNotNull(d11MatchPlayerMatchStatsDTO.get(playerMatchStat.getD11Team().getId()).get(playerMatchStat.getPosition().getName()));
            for(PlayerMatchStatDTO playerMatchStatDTO : d11MatchPlayerMatchStatsDTO.get(playerMatchStat.getD11Team().getId()).get(playerMatchStat.getPosition().getName())) {
                if(playerMatchStat.getId().equals(playerMatchStatDTO.getId())) {                    
                    assertEqualsDTO(playerMatchStat, playerMatchStatDTO);
                    ++count;
                }
            }
        }
        assertEquals(playerMatchStats.size(), count);        
    }
    
    public static void assertEqualsDTO(PlayerMatchStats playerMatchStats, MatchPlayerMatchStatsDTO matchPlayerMatchStatsDTO) {
        assertNotNull(playerMatchStats);
        assertNotNull(matchPlayerMatchStatsDTO);
        
        int includedCount = 0;
        int excludedCount = 0;
        for(PlayerMatchStat playerMatchStat : playerMatchStats) {
            if(playerMatchStat.getLineup() > 0) {
                assertNotNull(matchPlayerMatchStatsDTO.get(playerMatchStat.getTeam().getId()));
                assertNotNull(matchPlayerMatchStatsDTO.get(playerMatchStat.getTeam().getId()).get(playerMatchStat.getPosition().getName()));
                for(PlayerMatchStatDTO playerMatchStatDTO : matchPlayerMatchStatsDTO.get(playerMatchStat.getTeam().getId()).get(playerMatchStat.getPosition().getName())) {
                    if(playerMatchStat.getId().equals(playerMatchStatDTO.getId())) {                    
                        assertEqualsDTO(playerMatchStat, playerMatchStatDTO);
                        ++includedCount;
                    }
                }
            } else {
                ++excludedCount;
            }
        }
        assertEquals(playerMatchStats.size() - excludedCount, includedCount);        
    }
    
}
