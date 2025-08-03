package com.github.hobbylabs.ldapsample.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class People {
    @JsonProperty("cn")
    private String fullName;
    @JsonProperty("mail")
    private String mail;
    @JsonProperty("uidNumber")
    private Integer uidNumber;
    @JsonProperty("gidNumber")
    private Integer gidNumber;
    @JsonProperty("homeDirectory")
    private String homeDirectory;
}
