package com.form.model.post;

import com.form.model.data.Location;
import com.form.model.data.PriceTag;
import com.form.model.enums.Period;
import com.form.model.enums.Sport;

import java.util.ArrayList;

/**
 * Post
 * <p>
 *     Prototype of Post object.
 *     Includes a Location, a sport, a price tag and a list of available dates.
 * </p>
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class Post {
    /** Location - Country, State, City, Address */
    private Location location;
    /** Average Price tag */
    private PriceTag priceTag;
    /** Sport */
    private Sport sport;
    /** List of available dates */
    private ArrayList<Period> dates = new ArrayList<>();

    /** Constructor */
    public Post() {
        this.location = new Location();
        this.priceTag = new PriceTag();
    }

    /** Pretty Format Method, used in HTML posts */
    public String getPrettyFormat() {
        StringBuilder builder = new StringBuilder();
        builder.append(location.getCountry() + "-")
                .append(location.getState() + "-")
                .append(location.getCity() + "@")
                .append(location.getAddress());
        return builder.toString();
    }

    /** Basic getters and setters */
    public ArrayList<Period> getDates() {
        return dates;
    }

    public void setDates(ArrayList<Period> dates) {
        this.dates = dates;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public PriceTag getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(PriceTag priceTag) {
        this.priceTag = priceTag;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

}
