ALTER TABLE public.d11_matches
    ADD COLUMN date date NOT NULL DEFAULT '2000-01-01'::date;

/* 
 * Find the date for the last Premier League match where a player in either D11 team participates and set that as the date for the D11 match.
 * If no match is found (which means the Premier League match day hasn't started yet) then use the D11 match day date.
 */
UPDATE d11_matches d11ms set date = 
COALESCE(
   (SELECT distinct DATE(m.datetime) as date
    FROM player_match_stats pms
    JOIN matches m ON pms.match_id = m.id
    JOIN d11_match_days d11md ON d11md.match_day_id = m.match_day_id
    JOIN d11_matches d11m ON d11m.d11_match_day_id = d11md.id
    WHERE d11m.id = d11ms.id
    AND (pms.d11_team_id = d11m.home_d11_team_id OR pms.d11_team_id = d11m.away_d11_team_id)
    ORDER BY date DESC
    LIMIT 1),
   (SELECT d11md.date
    FROM d11_matches d11m
    JOIN d11_match_days d11md ON d11m.d11_match_day_id = d11md.id
    WHERE d11m.id = d11ms.id)   
) 
