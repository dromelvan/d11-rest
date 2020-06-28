package org.d11.rest.model.jpa;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "substitutions")
public class Substitution extends MatchEvent {

    @ManyToOne
    @JoinColumn(name = "player_in_id")
    @NotNull
    private Player playerIn;

    protected Substitution() {
    }

    public Substitution(Integer time, Integer addedTime) {
        super(time, addedTime);
    }

    public Player getPlayerIn() {
        return playerIn;
    }

    public void setPlayerIn(Player playerIn) {
        this.playerIn = playerIn;
    }

    @Override
    public boolean isValid() {
        // TODO: Better validation with messages etc.
        if (getPlayer() != null && getPlayerIn() != null && getPlayer().getId() == getPlayerIn().getId()) {
            return false;
        }
        return super.isValid();
    }

}
