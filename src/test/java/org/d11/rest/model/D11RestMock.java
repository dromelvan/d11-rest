package org.d11.rest.model;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

import org.d11.rest.api.model.*;
import org.d11.rest.model.jpa.*;
import org.d11.rest.model.jpa.projection.*;
import org.d11.rest.security.Role;
import org.d11.rest.util.JWT;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class D11RestMock {

    public final static String USER = "username";
    public final static String ADMINISTRATOR = "administrator";
    public final static String PASSWORD = "password";
    public final static Random random = new Random(System.currentTimeMillis());

    public static EntityId entityId(long id) {
        EntityId entityId = new EntityId() {
            @Override
            public Long getId() {
                return id;
            }
        };
        return entityId;
    }

    public static List<EntityId> entityIds(Long... ids) {
        List<EntityId> entityIds = new ArrayList<>();
        for(Long id : ids) {
            entityIds.add(entityId(id));
        }
        return entityIds;
    }

    // User ----------------------------------------------------------------- //

    public static User user() {
        User user = new User(USER, new BCryptPasswordEncoder().encode(PASSWORD));

        user.setName("User");
        user.setCurrentSignInIp("127.0.0.1");
        user.setLastSignInIp(user.getCurrentSignInIp());
        user.setCurrentSignInAt(LocalDateTime.now());
        user.setLastSignInAt(user.getCurrentSignInAt());

        user.addRole(Role.USER);

        return user;
    }

    public static User administrator() {
        User administrator = user();

        administrator.setUsername(ADMINISTRATOR);
        administrator.setName("Administrator");

        administrator.addRole(Role.ADMINISTRATOR);

        return administrator;
    }

    public static String userToken() {
        return userToken(user());
    }

    public static String userToken(User user) {
        return JWT.generateToken(user);
    }

    public static String administratorToken() {
        return JWT.generateToken(administrator());
    }

    public static String administratorToken(User administrator) {
        return JWT.generateToken(administrator);
    }

    // Country -------------------------------------------------------------- //

    public static Country country() {
        return country(1);
    }

    public static Country country(int id) {
        Country country = new Country("Country" + id, id < 10 ? "0" + id : "" + id);
        country.setId((long) id);
        return country;
    }

    public static List<Country> countries() {
        return countries(2);
    }

    public static List<Country> countries(int count) {
        List<Country> countries = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            countries.add(country(i + i));
        }
        return countries;
    }

    // Player --------------------------------------------------------------- //

    public static Player player() {
        return player(1);
    }

    public static Player player(int id) {
        Player player = new Player(id, "Player" + id, "Player" + id, LocalDate.now(), 160 + id, 80 + id);
        player.setId((long) id);
        return player;
    }

    public static List<Player> players() {
        return players(2);
    }

    public static List<Player> players(int count) {
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            players.add(player(i + 1));
        }
        return players;
    }
    
    public static PlayerSearchResult playerSearchResult(Player player, Team team) {
        return new PlayerSearchResult() {
            public Long getId() {
                return player.getId();
            }
            
            public String getName() {
                return player.getName();
            }

            public Long getTeamId() {
                return team.getId();
            }

            public String getTeamName() {
                return team.getName();
            }            
        };
    }

    // PlayerSeasonInfo ----------------------------------------------------- //
    
    public static PlayerSeasonInfo playerSeasonInfo() {
        return playerSeasonInfo(1);
    }

    public static PlayerSeasonInfo playerSeasonInfo(int id) {
        PlayerSeasonInfo playerSeasonInfo = new PlayerSeasonInfo(id * 5);
        playerSeasonInfo.setId((long) id);
        return playerSeasonInfo;
    }

    public static List<PlayerSeasonInfo> playerSeasonInfos() {
        return playerSeasonInfos(2);
    }

    public static List<PlayerSeasonInfo> playerSeasonInfos(int count) {
        List<PlayerSeasonInfo> playerSeasonInfos = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            playerSeasonInfos.add(playerSeasonInfo(i + 1));
        }
        return playerSeasonInfos;
    }
    
    // Stadium -------------------------------------------------------------- //

    public static Stadium stadium() {
        return stadium(1);
    }

    public static Stadium stadium(int id) {
        Stadium stadium = new Stadium("Stadium" + id, "City" + id, 10000 + id, 1900 + id);
        stadium.setId((long) id);
        return stadium;
    }

    public static List<Stadium> stadia() {
        return stadia(2);
    }

    public static List<Stadium> stadia(int count) {
        List<Stadium> stadia = new ArrayList<>();
        for(int i = 0; i < count; ++i) {
            stadia.add(stadium(i + i));
        }
        return stadia;
    }

    // Position ------------------------------------------------------------- //

    public static Position position() {
        return position(1);
    }

    public static Position position(int id) {
        Position position = new Position("Position" + id, (id < 10 ? "P" + id : "" + id), id <= 3, id);
        position.setId((long) id);
        return position;
    }

    public static List<Position> positions() {
        return positions(5);
    }

    public static List<Position> positions(int count) {
        List<Position> positiones = new ArrayList<Position>();
        for(int i = 0; i < count; ++i) {
            Position positionDay = position(i + 1);
            positiones.add(positionDay);
        }
        return positiones;
    }

    // Team ----------------------------------------------------------------- //

    public static Team team() {
        return team(1);
    }

    public static Team team(int id) {
        Team team = new Team(id, "Team" + id, "T" + id, id < 10 ? "TM" + id : "T" + id, "The Teams " + id, 1900 + id, "Motto" + id, id < 10 ? "#00000" + id : "#0000" + id, false);
        team.setId((long) id);
        return team;
    }

    public static List<Team> teams() {
        return teams(20);
    }

    public static List<Team> teams(int count) {
        List<Team> teams = new ArrayList<Team>();
        for(int i = 0; i < count; ++i) {
            teams.add(team(i + 1));
        }
        return teams;
    }

    // D11Team -------------------------------------------------------------- //

    public static D11Team d11Team() {
        return d11Team(1);
    }

    public static D11Team d11Team(int id) {
        D11Team d11Team = new D11Team("D11Team" + id, "D11T" + id, id < 10 ? "DT" + id : "D" + id, false);
        d11Team.setId((long) id);
        return d11Team;
    }

    public static List<D11Team> d11Teams() {
        return d11Teams(20);
    }

    public static List<D11Team> d11Teams(int count) {
        List<D11Team> d11Teams = new ArrayList<D11Team>();
        for(int i = 0; i < count; ++i) {
            d11Teams.add(d11Team(i + 1));
        }
        return d11Teams;
    }

    // Season --------------------------------------------------------------- //

    public static Season season() {
        return season(1);
    }

    public static Season season(int id) {
        Season season = new Season(id < 10 ? "100" + id + "-100" + (id + 1) : "10" + id + "-" + "10" + (id + 1), LocalDate.now().minus(id, ChronoUnit.DAYS), true);
        if(id == 9) {
            season.setName("1009-1010");
        }
        season.setId((long) id);
        return season;
    }

    public static List<Season> seasons() {
        return seasons(2);
    }

    public static List<Season> seasons(int count) {
        List<Season> seasons = new ArrayList<Season>();
        for(int i = 0; i < count; ++i) {
            seasons.add(season(i + 1));
        }
        return seasons;
    }

    // Premier League ------------------------------------------------------- //

    public static PremierLeague premierLeague() {
        return premierLeague(1);
    }

    public static PremierLeague premierLeague(int id) {
        PremierLeague premierLeague = new PremierLeague("Premier League " + id);
        premierLeague.setId((long) id);
        return premierLeague;
    }

    public static List<PremierLeague> premierLeagues() {
        return premierLeagues(2);
    }

    public static List<PremierLeague> premierLeagues(int count) {
        List<PremierLeague> premierLeagues = new ArrayList<PremierLeague>();
        for(int i = 0; i < count; ++i) {
            premierLeagues.add(premierLeague(i + 1));
        }
        return premierLeagues;
    }

    // Match Day ------------------------------------------------------------ //

    public static MatchDay matchDay() {
        return matchDay(1);
    }

    public static MatchDay matchDay(int id) {
        MatchDay matchDay = new MatchDay(LocalDate.now().plusDays(id - 1), id, Status.PENDING);
        matchDay.setId((long) id);
        return matchDay;
    }

    public static List<MatchDay> matchDays() {
        return matchDays(38);
    }

    public static List<MatchDay> matchDays(int count) {
        List<MatchDay> matchDays = new ArrayList<MatchDay>();
        for(int i = 0; i < count; ++i) {
            MatchDay matchDay = matchDay(i + 1);
            matchDays.add(matchDay);
        }
        return matchDays;
    }

    // Match ---------------------------------------------------------------- //

    public static Match match() {
        return match(1);
    }

    public static Match match(int id) {
        Match match = new Match(id, 0, 0, 0, 0, LocalDateTime.now().plusDays(id - 1), "NA", Status.PENDING);
        match.setId((long) id);
        return match;
    }

    public static List<Match> matches() {
        return matches(10);
    }

    public static List<Match> matches(int count) {
        List<Match> matches = new ArrayList<Match>();
        for(int i = 0; i < count; ++i) {
            Match matchDay = match(i + 1);
            matches.add(matchDay);
        }
        return matches;
    }

    // Goal ----------------------------------------------------------------- //

    public static Goal goal() {
        return goal(1);
    }

    public static Goal goal(int id) {
        Goal goal = new Goal(id % 45 + 1, 0, false, false);
        goal.setId((long) id);
        return goal;
    }

    public static List<Goal> goals() {
        return goals(4);
    }

    public static List<Goal> goals(int count) {
        List<Goal> goals = new ArrayList<Goal>();
        for(int i = 0; i < count; ++i) {
            Goal goal = goal(i + 1);
            goals.add(goal);
        }
        return goals;
    }

    // Card ----------------------------------------------------------------- //

    public static Card card() {
        return card(1);
    }

    public static Card card(int id) {
        Card card = new Card(id % 45 + 1, 0, CardType.YELLOW);
        card.setId((long) id);
        return card;
    }

    public static List<Card> cards() {
        return cards(4);
    }

    public static List<Card> cards(int count) {
        List<Card> cards = new ArrayList<Card>();
        for(int i = 0; i < count; ++i) {
            Card card = card(i + 1);
            cards.add(card);
        }
        return cards;
    }

    // Substitution --------------------------------------------------------- //

    public static Substitution substitution() {
        return substitution(1);
    }

    public static Substitution substitution(int id) {
        Substitution substitution = new Substitution(id % 45 + 1, 0);
        substitution.setId((long) id);
        return substitution;
    }

    public static List<Substitution> substitutions() {
        return substitutions(4);
    }

    public static List<Substitution> substitutions(int count) {
        List<Substitution> substitutions = new ArrayList<Substitution>();
        for(int i = 0; i < count; ++i) {
            Substitution substitution = substitution(i + 1);
            substitutions.add(substitution);
        }
        return substitutions;
    }

    // PlayerMatchStat ------------------------------------------------------ //

    public static PlayerMatchStat playerMatchStat() {
        return playerMatchStat(1);
    }

    public static PlayerMatchStat playerMatchStat(int id) {
        PlayerMatchStat playerMatchStat = new PlayerMatchStat("P" + id, id % 3, 0, 0, 0, 0, false, false, 0, 0, 0, 0, 0, 0);
        playerMatchStat.setId((long) id);
        return playerMatchStat;
    }

    public static List<PlayerMatchStat> playerMatchStats() {
        return playerMatchStats(10);
    }

    public static List<PlayerMatchStat> playerMatchStats(int count) {
        List<PlayerMatchStat> playerMatchStates = new ArrayList<PlayerMatchStat>();
        for(int i = 0; i < count; ++i) {
            PlayerMatchStat playerMatchStatDay = playerMatchStat(i + 1);
            playerMatchStates.add(playerMatchStatDay);
        }
        return playerMatchStates;
    }

    // D11 League ----------------------------------------------------------- //

    public static D11League d11League() {
        return d11League(1);
    }

    public static D11League d11League(int id) {
        D11League d11League = new D11League("D11 League " + id);
        d11League.setId((long) id);
        return d11League;
    }

    public static List<D11League> d11Leagues() {
        return d11Leagues(2);
    }

    public static List<D11League> d11Leagues(int count) {
        List<D11League> d11Leagues = new ArrayList<D11League>();
        for(int i = 0; i < count; ++i) {
            d11Leagues.add(d11League(i + 1));
        }
        return d11Leagues;
    }

    // D11 Match Day -------------------------------------------------------- //

    public static D11MatchDay d11MatchDay() {
        return d11MatchDay(1);
    }

    public static D11MatchDay d11MatchDay(int id) {
        D11MatchDay d11MatchDay = new D11MatchDay(LocalDate.now().plusDays(id - 1), id);
        d11MatchDay.setId((long) id);
        return d11MatchDay;
    }

    public static List<D11MatchDay> d11MatchDays() {
        return d11MatchDays(38);
    }

    public static List<D11MatchDay> d11MatchDays(int count) {
        List<D11MatchDay> d11MatchDays = new ArrayList<D11MatchDay>();
        for(int i = 0; i < count; ++i) {
            D11MatchDay d11MatchDay = d11MatchDay(i + 1);
            d11MatchDays.add(d11MatchDay);
        }
        return d11MatchDays;
    }

    // D11 Match ------------------------------------------------------------ //

    public static D11Match d11Match() {
        return d11Match(1);
    }

    public static D11Match d11Match(int id) {
        D11Match d11Match = new D11Match(0, 0, 0, 0, 0, 0, 0, 0, LocalDate.now().plusDays(id - 1), "NA", Status.PENDING);
        d11Match.setId((long) id);
        return d11Match;
    }

    public static List<D11Match> d11Matches() {
        return d11Matches(10);
    }

    public static List<D11Match> d11Matches(int count) {
        List<D11Match> d11Matches = new ArrayList<D11Match>();
        for(int i = 0; i < count; ++i) {
            D11Match d11MatchDay = d11Match(i + 1);
            d11Matches.add(d11MatchDay);
        }
        return d11Matches;
    }

    // TeamTableStat -------------------------------------------------------- //

    public static TeamTableStat teamTableStat() {
        return teamTableStat(1);
    }

    public static TeamTableStat teamTableStat(int id) {
        TeamTableStat teamTableStat = new TeamTableStat(random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(50), random.nextInt(50), random.nextInt(50), random.nextInt(37) + 1, random.nextInt(19) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(50), random.nextInt(50), random.nextInt(50), random.nextInt(37) + 1, random.nextInt(19) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(50), random.nextInt(50), random.nextInt(50), random.nextInt(37) + 1, random.nextInt(19) + 1, random.nextInt(50), random.nextInt(19) + 1);
        teamTableStat.setId((long) id);
        return teamTableStat;
    }

    public static List<TeamTableStat> teamTableStats() {
        return teamTableStats(20);
    }

    public static List<TeamTableStat> teamTableStats(int count) {
        List<TeamTableStat> teamTableStats = new ArrayList<TeamTableStat>();
        for(int i = 0; i < count; ++i) {
            teamTableStats.add(teamTableStat(i + 1));
        }
        return teamTableStats;
    }

    // D11TeamTableStat ----------------------------------------------------- //

    public static D11TeamTableStat d11TeamTableStat() {
        return d11TeamTableStat(1);
    }

    public static D11TeamTableStat d11TeamTableStat(int id) {
        D11TeamTableStat d11TeamTableStat = new D11TeamTableStat(random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(50), random.nextInt(50), random.nextInt(50), random.nextInt(37) + 1, random.nextInt(19) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(50), random.nextInt(50), random.nextInt(50), random.nextInt(37) + 1, random.nextInt(19) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(37) + 1, random.nextInt(50), random.nextInt(50), random.nextInt(50), random.nextInt(37) + 1, random.nextInt(19) + 1, random.nextInt(50), random.nextInt(19) + 1);
        d11TeamTableStat.setId((long) id);
        return d11TeamTableStat;
    }

    public static List<D11TeamTableStat> d11TeamTableStats() {
        return d11TeamTableStats(20);
    }

    public static List<D11TeamTableStat> d11TeamTableStats(int count) {
        List<D11TeamTableStat> d11TeamTableStats = new ArrayList<D11TeamTableStat>();
        for(int i = 0; i < count; ++i) {
            d11TeamTableStats.add(d11TeamTableStat(i + 1));
        }
        return d11TeamTableStats;
    }
    
}
