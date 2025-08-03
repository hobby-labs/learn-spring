#!/usr/bin/env bash

ulimit -n 1024
exec /usr/sbin/slapd -h "ldap:/// ldapi:///" -g openldap -u openldap -F /etc/ldap/slapd.d -d 3

