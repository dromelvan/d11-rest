INSERT INTO player_season_stats (player_id, season_id, goals, goal_assists, own_goals, goals_conceded, clean_sheets, yellow_cards, red_cards, 
                                  man_of_the_match, shared_man_of_the_match, rating, points, games_started, games_substitute, games_did_not_participate, 
                                  substitutions_on, substitutions_off, minutes_played, ranking, form_points, points_per_appearance, created_at, updated_at)
(SELECT
    players.id AS player_id, 
    seasons.id AS season_id, 
    0 AS goals, 
    0 AS goal_assists, 
    0 AS own_goals, 
    0 AS goals_conceded, 
    0 AS clean_sheets, 
    0 AS yellow_cards, 
    0 AS red_cards, 
    0 AS man_of_the_match, 
    0 AS shared_man_of_the_match, 
    0 AS rating, 
    0 AS points, 
    0 AS games_started, 
    0 AS games_substitute,
    0 AS games_did_not_participate, 
    0 AS substitutions_on, 
    0 AS substitutions_off, 
    0 AS minutes_played, 
    0 AS ranking, 
    0 AS form_points, 
    0 AS points_per_appearance,
    now()::timestamp AS created_at, 
    now()::timestamp AS updated_at
FROM seasons
JOIN players ON NOT EXISTS (SELECT * FROM player_season_stats WHERE player_id = players.id AND season_id = seasons.id)
ORDER BY season_id)
