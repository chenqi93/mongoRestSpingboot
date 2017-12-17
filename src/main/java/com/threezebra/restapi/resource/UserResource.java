package com.threezebra.restapi.resource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;

import com.threezebra.domain.User;
import com.threezebra.restapi.controller.ReportWeekRestController;
import com.threezebra.restapi.controller.TimeEntryRestController;
import com.threezebra.restapi.controller.UserRestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


/**
 * @author vikas.sharma
 *
 */
public class UserResource extends ResourceSupport {

    private String fullName;

    private String userName;

    private String roles[];

    public UserResource(User user) {

        this.fullName = user.getFullName();

        this.userName = user.getUserName();

        this.roles = user.getRoles();

        this.add(linkTo(methodOn(
                UserRestController.class
                ).getUser(user.getUserName())
        ).withSelfRel());

        this.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(
                TimeEntryRestController.class, user.getUserName()
        ).getTimeEntries(user.getUserName(), null, null)
        ).withRel("timeEntries"));

        this.add(ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(
                ReportWeekRestController.class, user.getUserName()
                ).getWeeklyReport(user.getUserName())
        ).withRel("reportWeeks"));
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }
}
