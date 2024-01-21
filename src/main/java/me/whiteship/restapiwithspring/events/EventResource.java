package me.whiteship.restapiwithspring.events;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * 이전버전 / 현재버전
 * ResourceSupport / RepresentationModel
 * Resource / EntityModel
 * Resources / CollectionModel
 * PageResources / PageModel
 * 출처: https://docs.spring.io/spring-hateoas/docs/current/reference/html/#migrate-to-1.0.changes.representation-models
 */
public class EventResource extends EntityModel<Event> {
    public EventResource(Event content) {
        super(content);
        add(linkTo(EventController.class).slash(content.getId()).withSelfRel());
    }
}
