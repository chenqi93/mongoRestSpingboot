package com.threezebra.restapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.threezebra.domain.User;
import com.threezebra.restapi.dto.CreateUserDto;
import com.threezebra.restapi.dto.ModifyUserDto;
import com.threezebra.restapi.resource.ResourceCollection;
import com.threezebra.restapi.resource.UserResource;
import com.threezebra.service.UserService;
import com.threezebra.service.exception.UpdatedUserNotFoundException;

import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * @author vikas.sharma
 *
 */
@RestController
@RequestMapping("/api/users")
public class UserRestController {

    public static final String OWNER = "authentication.name == #userName";
    public static final String ADMIN = "hasRole('ADMIN')";

    private UserService userService;

    @PreAuthorize(ADMIN + " or " + OWNER)
    @RequestMapping(path = "/{userName}", method = RequestMethod.GET)
    public ResponseEntity<UserResource> getUser(@PathVariable String userName) {

        Optional<User> userOptional = userService.findByUserName(userName);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(
                new UserResource(userOptional.get())
        );
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResourceCollection<UserResource>> getUsers() {

        return ResponseEntity.ok(
                new ResourceCollection<>(
                        userService.findAll().stream()
                                .map(user -> new UserResource(user))
                                .collect(Collectors.toList())
                )
        );

    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResource> createUser(@Validated @RequestBody CreateUserDto userDto) {

        if (userService.findByUserName(userDto.getUserName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        User savedUser = userService.save(userDto);

        return ResponseEntity.created(
                linkTo(methodOn(UserRestController.class).getUser(savedUser.getUserName()))
                        .toUri()
        ).body(
                new UserResource(savedUser)
        );
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path = "/{userName}", method = RequestMethod.PUT)
    public ResponseEntity<UserResource> createUser(@PathVariable String userName,
                                                   @Validated @RequestBody ModifyUserDto userDto) {

        User savedUser;
        try {
            savedUser = userService.save(userDto, userName);
        } catch (UpdatedUserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        return ResponseEntity.ok(
                new UserResource(savedUser)
        );
    }

    @PreAuthorize(ADMIN)
    @RequestMapping(path = "/{userName}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteUser(@PathVariable String userName) {

        Optional<User> userOptional = userService.findByUserName(userName);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        userService.delete(userOptional.get());

        return ResponseEntity.ok().build();
    }

    @Autowired
    protected void setUserRepository(UserService userService) {
        this.userService = userService;
    }
}
