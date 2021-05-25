package com.github.hobbylabs.ldapsample.data.request;

import lombok.Data;
import org.springframework.ldap.filter.AbstractFilter;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.lang.reflect.Field;

@Data
public class Search {
    @NotNull
    private String cn;
    @NotNull
    private String mail;
}
