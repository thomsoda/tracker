package com.rocketleague;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TrackedPlayer {
    @Id
    private String idPlayer;
    private Date dtTrackingStarted;

    public String getIdPlayer() {
        return idPlayer;
    }

    public Date getDtTrackingStarted() {
        return dtTrackingStarted;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public void setDtTrackingStarted(Date dtTrackingStarted) {
        this.dtTrackingStarted = dtTrackingStarted;
    }
}
