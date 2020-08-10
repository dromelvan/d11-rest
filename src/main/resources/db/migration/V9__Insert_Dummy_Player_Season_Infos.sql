INSERT INTO player_season_infos (player_id, season_id, team_id, d11_team_id, position_id, value, created_at, updated_at)
(SELECT
    players.id AS player_id, 
    seasons.id AS season_id, 
    1 AS team_id, 
    1 AS d11_team_id, 
    (SELECT CASE WHEN 
        position_id = 2 
     THEN 
        3 
     ELSE 
        position_id 
     END 
     FROM (SELECT position_id FROM player_season_infos WHERE player_id = players.id ORDER BY season_id DESC LIMIT 1) AS defender_sub_query) AS position_id, 
    0 AS value, 
    now()::timestamp AS created_at, 
    now()::timestamp AS updated_at
FROM seasons
JOIN players ON NOT EXISTS (SELECT * FROM player_season_infos WHERE player_id = players.id AND season_id = seasons.id)
ORDER BY season_id)
