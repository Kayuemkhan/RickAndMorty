package com.example.rickandmorty.network.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterDetailsResponse {

@SerializedName("id")
@Expose
private Long id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("status")
@Expose
private String status;
@SerializedName("species")
@Expose
private String species;
@SerializedName("type")
@Expose
private String type;
@SerializedName("gender")
@Expose
private String gender;
@SerializedName("origin")
@Expose
private Origin origin;
@SerializedName("location")
@Expose
private Location location;
@SerializedName("image")
@Expose
private String image;
@SerializedName("episode")
@Expose
private List<String> episode;
@SerializedName("url")
@Expose
private String url;
@SerializedName("created")
@Expose
private String created;

public Long getId() {
return id;
}

public void setId(Long id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getSpecies() {
return species;
}

public void setSpecies(String species) {
this.species = species;
}

public String getType() {
return type;
}

public void setType(String type) {
this.type = type;
}

public String getGender() {
return gender;
}

public void setGender(String gender) {
this.gender = gender;
}

public Origin getOrigin() {
return origin;
}

public void setOrigin(Origin origin) {
this.origin = origin;
}

public Location getLocation() {
return location;
}

public void setLocation(Location location) {
this.location = location;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public List<String> getEpisode() {
return episode;
}

public void setEpisode(List<String> episode) {
this.episode = episode;
}

public String getUrl() {
return url;
}

public void setUrl(String url) {
this.url = url;
}

public String getCreated() {
return created;
}

public void setCreated(String created) {
this.created = created;
}

}