package org.slazhy.hopto.todo.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public final class LinkMessage {

    private final String link;
    private final String rel;

    public LinkMessage(String link, String rel) {
        this.link = link;
        this.rel = rel;
    }

    public String getRel() {
        return rel;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return String.format("Link: %s\nRel: %s", getLink(), getRel());
    }
}
