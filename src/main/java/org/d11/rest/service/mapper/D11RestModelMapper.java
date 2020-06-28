package org.d11.rest.service.mapper;

import javax.persistence.EntityManagerFactory;

import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.*;
import org.modelmapper.*;

public class D11RestModelMapper extends ModelMapper {

    public D11RestModelMapper() {
        final D11RestModelMapper modelMapper = this;
        addConverter(new LinksConverter());
        // TODO: Try to make this work without passing the modelmapper to the converter.       
        addConverter(new PlayerMatchStatsByD11TeamIdPositionConverter(this));               

        addMappings(new PropertyMap<PremierLeague, PremierLeagueDTO>() {
            @Override
            protected void configure() {
                // This causes Illegal Reflective Access warnings on Java 9+.
                // See https://github.com/modelmapper/modelmapper/issues/414.
                // Run with -Djdk.module.illegalAccess=deny to suppress warnings until
                // ModelMapper hopefully fixes it.
                using(new IdListConverter()).map(source.getMatchDays()).setMatchDays(null);
            }
        });
        addMappings(new PropertyMap<MatchDay, MatchDayDTO>() {
            @Override
            protected void configure() {
                // skip().setMatches(null);
                using(new MatchesByDateConverter()).map(source.getMatches()).setMatches(null);
                map(source.getPremierLeague().getSeason()).setSeason(null);
            }
        });
        addMappings(new PropertyMap<Match, MatchMatchStatsDTO>() {
            @Override
            protected void configure() {
                using(new PlayerMatchStatsByTeamIdPositionConverter(modelMapper)).map(source.getPlayerMatchStats()).setPlayerMatchStats(null);
            }
        });
        addMappings(new PropertyMap<D11League, D11LeagueDTO>() {
            @Override
            protected void configure() {
                using(new IdListConverter()).map(source.getD11MatchDays()).setD11MatchDays(null);
            }
        });
        addMappings(new PropertyMap<D11MatchDay, D11MatchDayDTO>() {
            @Override
            protected void configure() {
                using(new D11MatchesByDateConverter()).map(source.getD11Matches()).setD11Matches(null);
                map(source.getD11League().getSeason()).setSeason(null);
            }
        });

        addMappings(new PropertyMap<PlayerDTO, Player>() {
            @Override
            protected void configure() {
                skip().setCountry(null);
            }
        });
        addMappings(new PropertyMap<TeamDTO, Team>() {
            @Override
            protected void configure() {
                skip().setStadium(null);
            }
        });
        addMappings(new PropertyMap<D11TeamDTO, D11Team>() {
            @Override
            protected void configure() {
                skip().setOwner(null);
            }
        });
        addMappings(new PropertyMap<SeasonDTO, Season>() {
            @Override
            protected void configure() {
                skip().setPremierLeague(null);
                skip().setD11League(null);
            }
        });
        addMappings(new PropertyMap<PremierLeagueDTO, PremierLeague>() {
            @Override
            protected void configure() {
                skip().setSeason(null);
                skip().setMatchDays(null);
            }
        });
        addMappings(new PropertyMap<MatchDayDTO, MatchDay>() {
            @Override
            protected void configure() {
                skip().setPremierLeague(null);
                skip().setMatches(null);
            }
        });
        addMappings(new PropertyMap<MatchDTO, Match>() {
            @Override
            protected void configure() {
                skip().setHomeTeam(null);
                skip().setAwayTeam(null);
                skip().setMatchDay(null);
                skip().setStadium(null);
            }
        });
        addMappings(new PropertyMap<D11MatchDTO, D11Match>() {
            @Override
            protected void configure() {
                skip().setHomeD11Team(null);
                skip().setAwayD11Team(null);
                skip().setD11MatchDay(null);
            }
        });
        addMappings(new PropertyMap<PlayerMatchStatBaseDTO, PlayerMatchStat>() {
            @Override
            protected void configure() {
                skip().setPlayer(null);
                skip().setMatch(null);
                skip().setTeam(null);
                skip().setD11Team(null);
                skip().setPosition(null);
            }
        });
        addMappings(new PropertyMap<GoalDTO, Goal>() {
            @Override
            protected void configure() {
                skip().setMatch(null);
                skip().setTeam(null);
                skip().setPlayer(null);
            }
        });
        addMappings(new PropertyMap<CardDTO, Card>() {
            @Override
            protected void configure() {
                skip().setMatch(null);
                skip().setTeam(null);
                skip().setPlayer(null);
            }
        });
        addMappings(new PropertyMap<SubstitutionDTO, Substitution>() {
            @Override
            protected void configure() {
                skip().setMatch(null);
                skip().setTeam(null);
                skip().setPlayer(null);
                skip().setPlayerIn(null);
            }
        });
        addMappings(new PropertyMap<D11LeagueDTO, D11League>() {
            @Override
            protected void configure() {
                skip().setSeason(null);
            }
        });
        addMappings(new PropertyMap<D11MatchDayDTO, D11MatchDay>() {
            @Override
            protected void configure() {
                skip().setD11League(null);
                skip().setMatchDay(null);
            }
        });
        addMappings(new PropertyMap<TeamTableStatDTO, TeamTableStat>() {
            @Override
            protected void configure() {
                skip().setTeam(null);
            }
        });
        addMappings(new PropertyMap<D11TeamTableStatDTO, D11TeamTableStat>() {
            @Override
            protected void configure() {
                skip().setD11Team(null);
            }
        });        
    }

    public D11RestModelMapper(EntityManagerFactory entityManagerFactory) {
        this();
        getConfiguration().setPropertyCondition(context -> entityManagerFactory.getPersistenceUnitUtil().isLoaded(context.getSource()));
    }

}
