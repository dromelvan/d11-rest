package org.d11.rest.util;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.d11.rest.controller.MatchController;
import org.d11.rest.model.jpa.*;

public class HATEOAS {

    public static void addLinks(D11RestEntity entity) {
        entity.removeLinks();
        if (entity instanceof Match) {
            entity.add(linkTo(methodOn(MatchController.class).findById(entity.getId())).withSelfRel());
            entity.add(linkTo(methodOn(MatchController.class).findMatchEventsById(entity.getId())).withRel("events"));
        }
    }

}
