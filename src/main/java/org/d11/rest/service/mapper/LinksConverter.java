package org.d11.rest.service.mapper;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.AbstractConverter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;

public class LinksConverter extends AbstractConverter<Links, Map<String, String>> {

    @Override
    protected Map<String, String> convert(Links links) {
        Map<String, String> map = new HashMap<String, String>();
        for (Link link : links) {
            map.put(link.getRel().toString(), link.getHref());
        }
        return map;
    }

}
