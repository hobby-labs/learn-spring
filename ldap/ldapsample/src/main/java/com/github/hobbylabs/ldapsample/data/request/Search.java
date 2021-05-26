package com.github.hobbylabs.ldapsample.data.request;

import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class Search {
    @NotNull
    private String cn;
    @NotNull
    private String mail;
}
