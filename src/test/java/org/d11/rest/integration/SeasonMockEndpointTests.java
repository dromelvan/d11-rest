package org.d11.rest.integration;

import static org.d11.rest.model.D11RestMock.cards;
import static org.d11.rest.model.D11RestMock.countries;
import static org.d11.rest.model.D11RestMock.d11League;
import static org.d11.rest.model.D11RestMock.d11MatchDays;
import static org.d11.rest.model.D11RestMock.d11Matches;
import static org.d11.rest.model.D11RestMock.d11TeamTableStats;
import static org.d11.rest.model.D11RestMock.d11Teams;
import static org.d11.rest.model.D11RestMock.goals;
import static org.d11.rest.model.D11RestMock.matchDays;
import static org.d11.rest.model.D11RestMock.matches;
import static org.d11.rest.model.D11RestMock.playerMatchStats;
import static org.d11.rest.model.D11RestMock.playerSeasonInfo;
import static org.d11.rest.model.D11RestMock.players;
import static org.d11.rest.model.D11RestMock.positions;
import static org.d11.rest.model.D11RestMock.premierLeague;
import static org.d11.rest.model.D11RestMock.seasons;
import static org.d11.rest.model.D11RestMock.stadium;
import static org.d11.rest.model.D11RestMock.substitutions;
import static org.d11.rest.model.D11RestMock.teamTableStats;
import static org.d11.rest.model.D11RestMock.teams;
import static org.d11.rest.model.D11RestMock.user;

import java.util.*;

import org.d11.rest.api.model.D11RestApiDTO;
import org.d11.rest.controller.RepositoryController;
import org.d11.rest.model.jpa.*;
import org.d11.rest.repository.*;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class SeasonMockEndpointTests<T extends D11RestEntity, U extends D11RestApiDTO, V extends RepositoryController<U, ?>> extends RepositoryIntegrationTests<T, U, V> {

    // @PersistenceContext
    // private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private SeasonRepository seasonRepository;
    @Autowired
    private PremierLeagueRepository premierLeagueRepository;
    @Autowired
    private D11LeagueRepository d11LeagueRepository;
    @Autowired
    private MatchDayRepository matchDayRepository;
    @Autowired
    private D11MatchDayRepository d11MatchDayRepository;
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private D11MatchRepository d11MatchRepository;
    @Autowired
    private GoalRepository goalRepository;
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private SubstitutionRepository substitutionRepository;
    @Autowired
    private StadiumRepository stadiumRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private D11TeamRepository d11TeamRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private PlayerSeasonInfoRepository playerSeasonInfoRepository;    
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private PlayerMatchStatRepository playerMatchStatRepository;
    @Autowired
    private TeamTableStatRepository teamTableStatRepository;
    @Autowired
    private D11TeamTableStatRepository d11TeamTableStatRepository;    

    @BeforeAll
    public void beforeAll() {
        getJpaRepositories().add(this.userRepository);
        getJpaRepositories().add(this.countryRepository);
        getJpaRepositories().add(this.seasonRepository);
        getJpaRepositories().add(this.premierLeagueRepository);
        getJpaRepositories().add(this.d11LeagueRepository);
        getJpaRepositories().add(this.matchDayRepository);
        getJpaRepositories().add(this.d11MatchDayRepository);
        getJpaRepositories().add(this.matchRepository);
        getJpaRepositories().add(this.d11MatchRepository);
        getJpaRepositories().add(this.goalRepository);
        getJpaRepositories().add(this.cardRepository);
        getJpaRepositories().add(this.substitutionRepository);
        getJpaRepositories().add(this.stadiumRepository);
        getJpaRepositories().add(this.teamRepository);
        getJpaRepositories().add(this.d11TeamRepository);
        getJpaRepositories().add(this.playerRepository);
        getJpaRepositories().add(this.playerSeasonInfoRepository);
        getJpaRepositories().add(this.positionRepository);
        getJpaRepositories().add(this.playerMatchStatRepository);
        getJpaRepositories().add(this.teamTableStatRepository);
        getJpaRepositories().add(this.d11TeamTableStatRepository);
    }

    public void beforeEach() {
        Random random = new Random(System.currentTimeMillis());
        // Setting all ids to null before persisting since it for some unknown reason
        // messes things up otherwise.
        User user = user();
        user.setId(null);
        user.setUsername(String.valueOf(System.currentTimeMillis()));
        user = getRepository(UserRepository.class).save(user);

        Stadium stadium = stadium();
        stadium.setId(null);
        stadium = getRepository(StadiumRepository.class).save(stadium);

        List<Team> teams = teams();
        for(Team team : teams) {
            team.setId(null);
            team.setStadium(stadium);
        }
        teams = getRepository(TeamRepository.class).saveAll(teams);

        List<D11Team> d11Teams = d11Teams();
        for(D11Team d11Team : d11Teams) {
            d11Team.setId(null);
            d11Team.setOwner(user);
        }
        d11Teams = getRepository(D11TeamRepository.class).saveAll(d11Teams);

        List<Season> seasons = seasons();
        for(Season season : seasons) {
            season.setId(null);
            PremierLeague premierLeague = premierLeague();
            premierLeague.setId(null);
            season.setPremierLeague(premierLeague);
            for(MatchDay matchDay : matchDays()) {
                matchDay.setId(null);
                premierLeague.addMatchDay(matchDay);
                for(Match match : matches()) {
                    match.setId(null);
                    match.setHomeTeam(teams.get(random.nextInt(teams.size())));
                    match.setAwayTeam(teams.get(random.nextInt(teams.size())));
                    match.setStadium(stadium);
                    matchDay.addMatch(match);
                }
            }
            D11League d11League = d11League();
            d11League.setId(null);
            season.setD11League(d11League);
            for(D11MatchDay d11MatchDay : d11MatchDays()) {
                d11MatchDay.setId(null);
                d11League.addD11MatchDay(d11MatchDay);
                for(D11Match d11Match : d11Matches()) {
                    d11Match.setId(null);
                    d11Match.setHomeD11Team(d11Teams.get(random.nextInt(d11Teams.size())));
                    d11Match.setAwayD11Team(d11Teams.get(random.nextInt(d11Teams.size())));
                    d11MatchDay.addD11Match(d11Match);
                }
            }

            for(int i = 0; i < premierLeague.getMatchDays().size(); ++i) {
                D11MatchDay d11MatchDay = d11League.getD11MatchDays().get(i);
                MatchDay matchDay = premierLeague.getMatchDays().get(i);
                d11MatchDay.setMatchDay(matchDay);
                matchDay.setD11MatchDay(d11MatchDay);
            }
        }

        getRepository(SeasonRepository.class).saveAll(seasons);

        // TODO: Figure out why @EntityGraph doesn't seem to work in tests so we can add
        // this back in.
        // this.entityManager.flush();
        // this.entityManager.clear();

        seasons = getRepository(SeasonRepository.class).findAll();
        List<PremierLeague> premierLeagues = getRepository(PremierLeagueRepository.class).findAll();
        List<MatchDay> matchDays = getRepository(MatchDayRepository.class).findAll();
        List<Match> matches = getRepository(MatchRepository.class).findAll();

        List<D11League> d11Leagues = getRepository(D11LeagueRepository.class).findAll();
        List<D11MatchDay> d11MatchDays = getRepository(D11MatchDayRepository.class).findAll();
//        List<D11Match> d11Matches = getRepository(D11MatchRepository.class).findAll();

        List<Country> countries = countries(10);
        countries.forEach(country -> country.setId(null));
        countries = getRepository(CountryRepository.class).saveAll(countries);

        List<Player> players = players(10);
        for(Player player : players) {
            player.setId(null);
            player.setCountry(countries.get(random.nextInt(countries.size())));
        }
        players = getRepository(PlayerRepository.class).saveAll(players);
        
        for(Match match : matches) {
            List<Goal> goals = goals(random.nextInt(4) + 1);
            for(Goal goal : goals) {
                goal.setId(null);
                match.addGoal(goal);
                goal.setTeam(random.nextInt(10) % 2 == 0 ? match.getHomeTeam() : match.getAwayTeam());
                goal.setPlayer(players.get(random.nextInt(players.size())));
            }
            getRepository(GoalRepository.class).saveAll(goals);

            List<Card> cards = cards(random.nextInt(4) + 1);
            for(Card card : cards) {
                card.setId(null);
                match.addCard(card);
                card.setTeam(random.nextInt(10) % 2 == 0 ? match.getHomeTeam() : match.getAwayTeam());
                card.setPlayer(players.get(random.nextInt(players.size())));
            }
            getRepository(CardRepository.class).saveAll(cards);

            List<Substitution> substitutions = substitutions(random.nextInt(4) + 1);
            for(Substitution substitution : substitutions) {
                substitution.setId(null);
                match.addSubstitution(substitution);
                substitution.setTeam(random.nextInt(10) % 2 == 0 ? match.getHomeTeam() : match.getAwayTeam());
                substitution.setPlayer(players.get(random.nextInt(players.size() - 1) + 1));
                substitution.setPlayerIn(players.get(0));
            }
            getRepository(SubstitutionRepository.class).saveAll(substitutions);
        }

        List<Position> positions = positions();
        positions = getRepository(PositionRepository.class).saveAll(positions);

        List<PlayerMatchStat> playerMatchStats = playerMatchStats();
        for(PlayerMatchStat playerMatchStat : playerMatchStats) {
            playerMatchStat.setId(null);
            playerMatchStat.setPlayer(players.get(random.nextInt(players.size())));
            matches.get(random.nextInt(matches.size())).addPlayerMatchStat(playerMatchStat);
            playerMatchStat.setTeam(teams.get(random.nextInt(teams.size())));
            playerMatchStat.setD11Team(d11Teams.get(random.nextInt(d11Teams.size())));
            playerMatchStat.setPosition(positions.get(random.nextInt(positions.size())));
        }
        playerMatchStats = getRepository(PlayerMatchStatRepository.class).saveAll(playerMatchStats);

        List<PlayerSeasonInfo> playerSeasonInfos = new ArrayList<PlayerSeasonInfo>();
        for(Player player : players) {
            for(Season season : seasons) {
                PlayerSeasonInfo playerSeasonInfo = playerSeasonInfo();
                playerSeasonInfo.setPlayer(player);
                playerSeasonInfo.setSeason(season);                
                playerSeasonInfo.setTeam(teams.get(random.nextInt(teams.size())));
                playerSeasonInfo.setD11Team(d11Teams.get(random.nextInt(d11Teams.size())));
                playerSeasonInfo.setPosition(positions.get(random.nextInt(positions.size())));
                playerSeasonInfo.setValue(random.nextInt(100) * 5);
                playerSeasonInfos.add(playerSeasonInfo);
            }
        }
        playerSeasonInfos = getRepository(PlayerSeasonInfoRepository.class).saveAll(playerSeasonInfos);
        
        List<TeamTableStat> teamTableStats = teamTableStats();
        for(TeamTableStat teamTableStat : teamTableStats) {
            teamTableStat.setId(null);
            teamTableStat.setTeam(teams.get(random.nextInt(teams.size())));
            teamTableStat.setPremierLeague(premierLeagues.get(random.nextInt(premierLeagues.size())));
            teamTableStat.setMatchDay(matchDays.get(random.nextInt(matchDays.size())));
        }
        teamTableStats = getRepository(TeamTableStatRepository.class).saveAll(teamTableStats);
        
        List<D11TeamTableStat> d11TeamTableStats = d11TeamTableStats();
        for(D11TeamTableStat d11TeamTableStat : d11TeamTableStats) {
            d11TeamTableStat.setId(null);
            d11TeamTableStat.setD11Team(d11Teams.get(random.nextInt(d11Teams.size())));
            d11TeamTableStat.setD11League(d11Leagues.get(random.nextInt(d11Leagues.size())));
            d11TeamTableStat.setD11MatchDay(d11MatchDays.get(random.nextInt(d11MatchDays.size())));
        }
        d11TeamTableStats = getRepository(D11TeamTableStatRepository.class).saveAll(d11TeamTableStats);        
    }

}
