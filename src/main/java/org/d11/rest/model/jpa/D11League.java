package org.d11.rest.model.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

@Entity
@Table(name = "d11_leagues")
public class D11League extends League {

    @OneToMany(mappedBy = "d11League", cascade = CascadeType.ALL)
    @OrderBy("matchDayNumber ASC")
    private List<D11MatchDay> d11MatchDays = new ArrayList<>();

    protected D11League() {
    }

    public D11League(String name) {
        super(name);
    }

    public List<D11MatchDay> getD11MatchDays() {
        return d11MatchDays;
    }

    public void setD11MatchDays(List<D11MatchDay> d11MatchDays) {
        this.d11MatchDays = d11MatchDays;
    }

    public void addD11MatchDay(D11MatchDay d11MatchDay) {
        if (d11MatchDay != null && !this.d11MatchDays.contains(d11MatchDay)) {
            this.d11MatchDays.add(d11MatchDay);
            d11MatchDay.setD11League(this);
        }
    }

    public void removeD11MatchDay(D11MatchDay d11MatchDay) {
        if (d11MatchDay != null && this.d11MatchDays.contains(d11MatchDay)) {
            this.d11MatchDays.remove(d11MatchDay);
            d11MatchDay.setD11League(null);
        }
    }

}
