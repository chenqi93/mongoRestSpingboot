package com.threezebra.restapi.resource;

import org.springframework.hateoas.ResourceSupport;

import com.threezebra.restapi.controller.UserRestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

/**
 * Created by ala on 9.5.16.
 */
public class RootResource extends ResourceSupport {

    public RootResource() {

        add(
                linkTo(UserRestController.class).withRel("users")
        );

    }
}
