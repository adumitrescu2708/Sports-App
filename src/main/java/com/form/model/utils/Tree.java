package com.form.model.utils;

import com.form.model.data.Location;
import com.form.model.data.locations.City;
import com.form.model.data.locations.Country;
import com.form.model.data.locations.State;
import com.form.model.post.Post;

import java.util.ArrayList;
import java.util.List;
/**
 * Tree
 * <p>
 *     Describes the main logic of searching in the Locations
 *
 *                  Root
 *                 /  |  \
 *            [...] [...]  [...] ---> List of Countries
 *           / | \  / | \  / | \
 *       [] [] [] [] [] [] [] [] [] ---> List of States
 *      /|\ /|\ /|\ /|\ /|\ /|\ /|\
 *      .... ... ... ... ... .. ....  ---> List of Cities
 *
 *      The Leaves (Cities) contain a list of references to the posts
 *      available in the specific Region.
 *
 *      When making a query, we search for the given Node and end
 *      in the subtree of the first null parameter, then continue
 *      until reaching the leaves and return a list of all posts.
 *
 *      [Example]   Romania - Ilfov - Bucharest - Address#1 --> Post1
 *                  Romania - Olt - Slatina - Address#2     --> Post2
 *
 *                  Query [Romania] - Ends at Romania and returns Post1 and Post2
 *                  Query [Romania, Ilfov] - Ends at Ilfov and returns Post1
 *
 *
 * </p>
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
public class Tree {
    /**
     * The root is a node with Country children
     * */
    private Node<Country> root = new Node<Country>() {
        @Override
        public Country searchChild(String name) {
            for(Country country : children) {
                if(country.getName().equals(name)){
                    return country;
                }
            }
            return null;
        }
    };

    /**
     * Add new Post Method
     * Adds the path country - state - city - address in the tree
     * */
    public void addPost(Post post) {
        Location location = post.getLocation();

        /**
         * Search for the country in the tree and add a new
         * entry if it doesn't exist.
         * */
        Country country = root.searchChild(location.getCountry());
        if(country == null) {
            country = new Country(location.getCountry());
            root.addChild(country);
        }

        /**
         * Search for the state in the city and add a new
         * entry if it doesn't exist.
         * */
        State state = country.searchChild(location.getState());
        if(state == null) {
            state = new State(location.getState());
            country.addChild(state);
        }

        /**
         * Search for the city in the state and add a new
         * entry if it doesn't exist.
         * */
        City city = state.searchChild(location.getCity());
        if(city == null) {
            city = new City(location.getCity());
            state.addChild(city);
        }

        /**
         * Add the new Post
         * */
        city.addChild(post);
    }

    /**
     * Query
     * <p>
     *     Since the location's fields are mandatory in a query,
     *     we select a list of leaves (posts) starting from the
     *     last non - null field in the query
     *
     *     [Example]: Query [Romania] - search for the node Romania
     *     and return all leaves from that subtree
     *
     * </p>
     * */

    public List<Post> query(String countryName, String stateName, String cityName) {
        Country country = root.searchChild(countryName);
        if(country == null) {
            return null;
        }
        State state = country.searchChild(stateName);
        if(state == null) {
            return getLeaves(country);
        }
        City city = state.searchChild(cityName);
        if(city == null) {
            return getLeaves(state);
        }

        return city.children;
    }

    /**
     * Obtain a list of Leaves (Posts) from a country subtree
     * */
    private List<Post> getLeaves(Country country) {
        List<Post> result = new ArrayList<>();
        for(State state : country.children) {
            for(City city : state.children) {
                result.addAll(city.children);
            }
        }
        return result;
    }

    /**
     * Obtain a list of Leaves (Posts) from a state subtree
     * */
    private List<Post> getLeaves(State state) {
        List<Post> result = new ArrayList<>();
        for(City city : state.children) {
            result.addAll(city.children);
        }
        return result;
    }

    /**
     *  Getters and Setters
     */

    public Node<Country> getRoot() {
        return root;
    }

    public void setRoot(Node<Country> root) {
        this.root = root;
    }
}
