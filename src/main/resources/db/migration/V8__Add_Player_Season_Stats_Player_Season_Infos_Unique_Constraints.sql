ALTER TABLE public.player_season_stats
    ADD CONSTRAINT player_season_stats_unique_season_player UNIQUE (season_id, player_id); 

ALTER TABLE public.player_season_infos
    ADD CONSTRAINT player_season_infos_unique_season_player UNIQUE (season_id, player_id); 
