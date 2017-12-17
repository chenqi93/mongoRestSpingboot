package com.threezebra.restapi.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.threezebra.restapi.resource.RootResource;


/**
 * @author vikas.sharma
 *
 */
@RestController
@RequestMapping("/api")
public class RootRestController {

    @RequestMapping(method = RequestMethod.GET)
    public RootResource getRoot() {
        return new RootResource();
    }

}
