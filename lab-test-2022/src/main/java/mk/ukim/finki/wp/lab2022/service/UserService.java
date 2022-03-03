package mk.ukim.finki.wp.lab2022.service;


import mk.ukim.finki.wp.lab2022.model.Role;
import mk.ukim.finki.wp.lab2022.model.User;

public interface UserService {

    /**
     * (5 points) This method should create a new user.
     * The password should be encrypted before saving.
     *
     * @param username
     * @param password
     * @param role
     * @return
     */
    User create(String username, String password, Role role);

}
