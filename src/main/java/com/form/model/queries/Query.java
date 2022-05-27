package com.form.model.queries;

import com.form.model.data.Location;
import com.form.model.enums.Period;
import com.form.model.enums.Sport;

import java.util.ArrayList;
import java.util.List;
/**
 * Query
 * <p>
 *     Prototype of query command.
 *     Includes a location, a sport and a list of dates.
 *     The location and its fields are optional. So is the list
 *     of available dates. The sport is mandatory.
 * </p>
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class Query {
    /** Location [Optional] */
    private Location location;
    /** Sport [Mandatory] */
    private Sport sport;
    /** Available dates [Optional] */
    private List<Period> dates = new ArrayList<>();

    /** Getters and setters */
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public List<Period> getDates() {
        return dates;
    }

    public void setDates(List<Period> dates) {
        this.dates = dates;
    }

}
