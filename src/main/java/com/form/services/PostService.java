package com.form.services;

import com.form.model.data.Location;
import com.form.model.post.Post;
import com.form.model.queries.Query;
import com.form.repositories.PostRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

/**
 * MVC - Post Service
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
@Service
public class PostService {
    /** Post repository - Singleton, Constructor injected */
    private final PostRepository postRepository;

    /** Constructor */
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    /** New post request
     * <p>
     *     Sends the new post request to the repository
     *     and returns the saved post.
     * </p>
     * */
    public Post postRequest(Post post) {
        if(validate(post)) {
            return postRepository.save(post);
        } else {
            return null;
        }
    }

    /** Validates the Post
     *  <p>
     *      The incoming data is validated:
     *      the strings received for city, country, address and state
     *      have to be completed, non-null.
     *  </p>
     * */
    private boolean validate(Post post) {
        Location location = post.getLocation();
        if(location.getAddress() == null
                || location.getCity() == null
                || location.getCountry() == null
                || location.getState() == null) {
            return false;
        }
        if(post.getDates() == null) {
            return false;
        }

        return true;
    }

    /**
     * Validates the query
     * <p>
     *     The query is validates:
     *     the location data has to be in the posts repository
     * </p>
     * */
    private boolean validate(Query query) {
        Location location = query.getLocation();
        if(postRepository.query(location.getCountry(), location.getState(), location.getCity()) == null) {
            return false;
        }
        return true;
    }

    /**
     * Query
     * <p>
     *     We obtain the queried sports, dates and location.
     *     Search in the Posts repository for the required
     *     data and intersect the obtain lists.
     *
     *     Use a TreeSet with a Price Comparator to store the
     *     lists retrieved.
     * </p>
     * */
    public List<Post> query(Query query) {
        /** First validate the query */
        if(validate(query) == false) {
            return null;
        }
        Location location = query.getLocation();
        List<Post> getByLocations = new ArrayList<>();
        List<Post> getByDates = new ArrayList<>();
        List<Post> getBySports = new ArrayList<>();

        /** Used a TreeSet to store the posts in ascending order
         *  after the price.
         * */
        TreeSet<Post> result = new TreeSet<>(new Comparator<Post>() {
            @Override
            public int compare(Post o1, Post o2) {
                return o1.getPriceTag().getPrice() - o2.getPriceTag().getPrice();
            }
        });

        /** Check location */
        getByLocations.addAll(postRepository.query(location.getCountry(), location.getState(), location.getCity()));

        /** Check dates */
        if(query.getDates() != null) {
            getByDates.addAll(postRepository.query(query.getDates()));
        }

        /** Check sports */
        if(query.getSport() != null) {
            getBySports.addAll(postRepository.query(query.getSport()));
        }

        /** Intersect lists */
        result.addAll(getByLocations.stream()
                .filter(getBySports::contains)
                .filter(getByDates::contains)
                .collect(Collectors.toSet()));

        /** Return the query result */
        return result.stream().collect(Collectors.toList());
    }



}
