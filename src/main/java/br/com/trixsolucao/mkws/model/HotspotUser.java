package br.com.trixsolucao.mkws.model;

import br.com.trixsolucao.mkws.model.mapping.MkMapping;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author Renan
 */

public class HotspotUser {


    @MkMapping(from = ".id")
    private String id;

    @MkMapping(from = "comment")
    private String comment;

    @MkMapping(from = "name")
    private String name;
    @MkMapping(from = "password")
    private String password;
    @MkMapping(from = "profile")
    private String profile;


    private boolean online;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
