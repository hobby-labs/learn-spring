package com.github.hobbylabs.ldapsample.data;

import lombok.Data;

import java.util.Map;

@Data
public class LdapData {
    private int id;
    private Map<String, LdapEntry> ldapEntries;
}
