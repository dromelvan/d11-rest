package org.d11.rest.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "goals")
public class Goal extends MatchEvent {

    @NotNull
    private Boolean penalty;
    @NotNull
    private Boolean ownGoal;

    protected Goal() {
    }

    public Goal(Integer time, Integer addedTime, Boolean penalty, Boolean ownGoal) {
        super(time, addedTime);
        this.penalty = penalty;
        this.ownGoal = ownGoal;
    }

    public Boolean isPenalty() {
        return penalty;
    }

    public void setPenalty(Boolean penalty) {
        this.penalty = penalty;
    }

    public Boolean isOwnGoal() {
        return ownGoal;
    }

    public void setOwnGoal(Boolean ownGoal) {
        this.ownGoal = ownGoal;
    }

    @Override
    public boolean isValid() {
        if (penalty != null && ownGoal != null && penalty && ownGoal) {
            return false;
        }
        return super.isValid();
    }

}
