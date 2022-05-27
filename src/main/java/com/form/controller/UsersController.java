package com.form.controller;

import com.form.model.data.Location;
import com.form.model.data.PriceTag;
import com.form.model.enums.Period;
import com.form.model.enums.Role;
import com.form.model.enums.Sport;
import com.form.model.post.Post;
import com.form.model.queries.Query;
import com.form.model.users.User;
import com.form.services.PostService;
import com.form.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * MVC - Controller
 *
 * <p>
 *      Main Entry in platform.
 *
 *      Uses the platform's services and a private User
 *      object that stores a reference to the current user in the platform.
 *      Depending on the HTTP methods, the class triggers actions and
 *      updates in the repositories.
 * </p>
 *
 * @author Dumitrescu Alexandra
 * @since May 2022
 * */
@Controller
public class UsersController {
    /** User service - Singleton, Constructor Injected */
    private final UserService userService;
    /** Post service - Singleton, Constructor Injected */
    private final PostService postService;
    /** Reference to current user in the platform*/
    private User authenticated = null;
    /** Current timeline in user's homepage */
    private List<Post> listed = null;

    /** Constructor */
    public UsersController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    /**
     *  Register Page
     *  <p>
     *      "register_request"  = Model Attribute with incoming user
     *      "role_types"        = Model Attribute with possible roles
     *  </p>
     *
     * */
    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("register_request", new User());
        model.addAttribute("role_types", Role.values());
        return "register_page";
    }

    /**
     * Register Form
     * <p>
     *     Obtains the registered user and checks its validation
     *     in the user's repository, sends the register request
     *     to the service.
     * </p>
     * */
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        User registered_user = userService.registerRequest(user.getUsername(),
                        user.getPassword(),
                        user.getRole());

        return "redirect:/login";

    }
    /**
     * Login Page
     * <p>
     *     "login_request" - Model Attribute with new user, used for
     *                      request in the user's service
     * </p>
     * */
    @RequestMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("login_request", new User());
        return "login_page";
    }

    /**
     * Login Form
     * <p>
     *     Retrieves the user from the login form, sends it to
     *     the user's service in order to validate its existence.
     *
     *     If there is an account in the repository, continues with the homepage,
     *     otherwise return an error page.
     *
     *     If the registered user is a normal user, we send the homepage
     *     of an user, otherwise send the admin homepage.
     * </p>
     * */
    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        authenticated = userService.loginRequest(user.getUsername(), user.getPassword());
        if(authenticated == null) {
            return "login_error";
        }
        System.out.println("Logged in: " + authenticated);
        if(authenticated.getRole().equals(Role.ADMIN)) {
            return "redirect:/admin/homepage";
        } else {
            return "redirect:/user/homepage";
        }
    }

    /**
     * User Homepage
     * <p>
     *     Adds the Model Attributes of incoming possible query
     *     and the information on the authenticated user. Adds
     *     the list of possible annual dates and possible sports.
     *     The user has to select from the given possibilities.
     * </p>
     * */
    @GetMapping("user/homepage")
    public String getUserHomepage(Model model) {
        if(authenticated == null) {
            return "redirect:/";
        }
        model.addAttribute("username", authenticated.getUsername());
        model.addAttribute("location", new Location());
        model.addAttribute("query", new Query());
        model.addAttribute("sports", Sport.values());
        model.addAttribute("dates", Period.values());

        return "user_homepage";
    }

    /**
     * User Homepage
     *
     * */
    @PostMapping("user/homepage")
    public String newQuery(@ModelAttribute Query query,
                           @ModelAttribute Location location) {
        if(authenticated == null) {
            return "redirect:/";
        }
        query.setLocation(location);
        listed = postService.query(query);
        return "redirect:/user/result";
    }

    @GetMapping("user/result")
    public String newQueryResult(Model model) {
        if(authenticated == null) {
            return "redirect:/";
        }
        model.addAttribute("posts", listed);
        return "query_result";
    }


    @GetMapping("/admin/homepage")
    public String getAdminHomepage(Model model) {
        if(authenticated == null) {
            return "redirect:/";
        }
        model.addAttribute("username", authenticated.getUsername());
        model.addAttribute("location", new Location());
        model.addAttribute("price_tag", new PriceTag());
        model.addAttribute("new_post", new Post());
        model.addAttribute("sports", Sport.values());
        model.addAttribute("dates", Period.values());

        return "admin_homepage";
    }

    @PostMapping("/admin/homepage")
    public String newPostPage(@ModelAttribute Post newPost,
                              @ModelAttribute PriceTag priceTag,
                              @ModelAttribute Location location) {
        if(authenticated == null) {
            return "redirect:/";
        }

        priceTag.setPrice(Integer.parseInt(priceTag.getPriceStr()));

        newPost.setLocation(location);
        newPost.setPriceTag(priceTag);
        newPost = postService.postRequest(newPost);
        System.out.println(newPost);

        return "success_page";
    }

}
