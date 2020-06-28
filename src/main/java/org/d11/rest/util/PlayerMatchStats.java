package org.d11.rest.util;

import java.util.*;

import org.d11.rest.api.D11RestApiVersion;
import org.d11.rest.model.jpa.PlayerMatchStat;

public class PlayerMatchStats extends ArrayList<PlayerMatchStat> {

    private static final long serialVersionUID = D11RestApiVersion.SERIAL_VERSION_UID;

    public PlayerMatchStats() {}
    
    public PlayerMatchStats(Collection<PlayerMatchStat> playerMatchStats) {
        super(playerMatchStats);
    }
    
}
