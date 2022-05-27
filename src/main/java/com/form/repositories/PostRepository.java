package com.form.repositories;

import com.form.model.data.Location;
import com.form.model.enums.Period;
import com.form.model.enums.Sport;
import com.form.model.post.Post;
import com.form.model.utils.Tree;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * MVC - Post Repository
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
@Repository
public class PostRepository implements com.form.repositories.Repository<Post> {
    /** Uses an N-Tree to store the posts by locations */
    private Tree locations = new Tree();
    /** Uses a hashmap to store the posts by sport type */
    private HashMap<Sport, List<Post>> sports = new HashMap<>();
    /** Uses a hashmap to store the posts by date */
    private HashMap<Period, List<Post>> dataPosts = new HashMap<>();

    /**
     * Save request
     * <p>
     *     Saves the given post in the 3 repositories: by location,
     *     by dates and by sport.
     * </p>
     * */
    public Post save(Post post) {
        Location location = post.getLocation();

        /** Adds the Post in the tree of locations */
        locations.addPost(post);
        List<Post> postsBySport = sports.get(post.getSport());

        /** Adds the Post to the hashmap of sport types */
        if(postsBySport == null) {
            postsBySport = new ArrayList<>();
            postsBySport.add(post);
            sports.put(post.getSport(), postsBySport);
        } else {
            postsBySport.add(post);
            sports.put(post.getSport(), postsBySport);
        }

        /** Adds the Post to the hashmap of dates */
        for(Period date : post.getDates()) {
            List<Post> postsByDate = dataPosts.get(date);
            if(postsByDate == null) {
                postsByDate = new ArrayList<>();
            }
            postsByDate.add(post);
            dataPosts.put(date, postsByDate);
        }

        /** Returns the post stored in the location Tree */
        return locations.query(location.getCountry(), location.getState(), location.getCity()).get(0);
    }

    /**
     * Makes a query on the repository of posts with a given Location
     * */
    public List<Post> query(String country, String state, String city) {
        return locations.query(country, state, city);
    }

    /**
     * Makes a query on the repository with a given list of dates
     * */
    public List<Post> query(List<Period> dates) {
        List<Post> posts = new ArrayList<>();
        for(Period date : dates) {
            if(dataPosts.get(date) != null) {
                posts.addAll(dataPosts.get(date));
            }
        }
        return posts;
    }

    /**
     * Makes a query on the repository with a given sport
     * */
    public List<Post> query(Sport sport) {
        return sports.get(sport);
    }




}
