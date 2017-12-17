package com.threezebra.restapi.resource;

import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;

/**
 * @author vikas.sharma
 *
 * @param <T>
 */
public class ResourceCollection<T> extends ResourceSupport {

    private Collection<T> data;

    public ResourceCollection(Collection<T> data) {
        this.data = data;
    }

    public Collection<T> getData() {
        return data;
    }
    
}
