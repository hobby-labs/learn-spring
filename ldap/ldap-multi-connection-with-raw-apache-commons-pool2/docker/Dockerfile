FROM ubuntu:20.04
MAINTAINER Tsutomu Nakamura <tsuna.0x00@gmail.com>

RUN apt-get update -q \
    && DEBIAN_FRONTEND=noninteractive apt-get install -yq --no-install-recommends slapd ldap-utils libnss-ldap \
    && rm -rf /var/lib/apt/lists/*

ARG CONFIG_PASSWORD=secret
ARG MANAGER_PASSWORD=secret
ARG DOMAIN=mysite.example.com

ENV CONFIG_PASSWORD=$CONFIG_PASSWORD
ENV MANAGER_PASSWORD=$MANAGER_PASSWORD
ENV DOMAIN=$DOMAIN

# Set root config informations.
# This instruction will run ulimit before launching slapd to avoid an error.
# https://stackoverflow.com/a/76332860
RUN ulimit -n 1024 && \
    /usr/sbin/slapd -h 'ldap:/// ldapi:///' -g openldap -u openldap -F /etc/ldap/slapd.d \
    && sleep 2 \
    && echo "\
dn: olcDatabase={0}config,cn=config\n\
changetype: modify\nadd: olcRootPW\n\
olcRootPW: $(slappasswd -s ${CONFIG_PASSWORD})\n\
\n\
dn: olcDatabase={0}config,cn=config\n\
changetype: modify\n\
add: olcRootDN" | ldapadd -Y EXTERNAL -H ldapi:/// \
    && ldapsearch -LLL -D "cn=config" -w ${CONFIG_PASSWORD} -b "olcDatabase={0}config,cn=config" "(olcDatabase=*)" 

# Set schemas (Duplicate these attribute on ubuntu)
## RUN /usr/sbin/slapd -h 'ldap:/// ldapi:///' -g openldap -u openldap -F /etc/ldap/slapd.d \
##     && sleep 2 \
##     && ldapadd -Y EXTERNAL -H ldapi:/// -f /etc/ldap/schema/cosine.ldif \
##     && ldapadd -Y EXTERNAL -H ldapi:/// -f /etc/ldap/schema/nis.ldif \
##     && ldapadd -Y EXTERNAL -H ldapi:/// -f /etc/ldap/schema/inetorgperson.ldif

# Set monitor DB (skipped on ubuntu)

# Set database information and basic data
RUN /usr/sbin/slapd -h 'ldap:/// ldapi:///' -g openldap -u openldap -F /etc/ldap/slapd.d \
    && sleep 2 \
    && __DOMAIN_COMPONENT__=$(echo "dc=$(echo ${DOMAIN} | sed -e 's|\.|,dc=|g')") \
    && __DC_DOMAIN__=$(echo "${DOMAIN}") \
    && echo "\
dn: olcDatabase={1}mdb,cn=config\n\
changetype: modify\n\
replace: olcSuffix\n\
olcSuffix: ${__DOMAIN_COMPONENT__}\n\
\n\
dn: olcDatabase={1}mdb,cn=config\nchangetype: modify\n\
replace: olcRootDN\n\
olcRootDN: cn=Manager,${__DOMAIN_COMPONENT__}\n\
\n\
dn: olcDatabase={1}mdb,cn=config\n\
changetype: modify\n\
replace: olcRootPW\n\
olcRootPW: $(slappasswd -s ${MANAGER_PASSWORD})" | ldapmodify -Y EXTERNAL -H ldapi:/// \
    && ldapsearch -x -LLL -D "cn=config" -w ${CONFIG_PASSWORD} -b "cn=config" "(olcDatabase=*)" \
    && echo "Adding base DN" \
    && echo "  Creating organization (${__DOMAIN_COMPONENT__})" \
    && echo "\
# Adding organizationalUnits\n\
dn: ${__DOMAIN_COMPONENT__}\n\
objectClass: dcObject\n\
objectClass: organization\n\
dc: mysite\n\
o: Example Inc.\n\
\n\
dn: cn=Manager,dc=mysite,dc=example,dc=com\n\
objectClass: organizationalRole\n\
cn: Manager\n\
description: Manager\n\
\n\
dn: ou=People,dc=mysite,dc=example,dc=com\n\
objectClass: organizationalUnit\n\
ou: People\n\
\n\
dn: ou=Group,${__DOMAIN_COMPONENT__}\n\
objectClass: organizationalUnit\n\
ou: Group\n\
\n\
# Groups ====================================================================================================\n\
dn: cn=admin,ou=Group,${__DOMAIN_COMPONENT__}\n\
objectClass: posixGroup\n\
cn: admin\n\
gidNumber: 1000\n\
\n\
dn: cn=develop,ou=Group,${__DOMAIN_COMPONENT__}\n\
objectClass: posixGroup\n\
cn: develop\n\
gidNumber: 1001\n\
\n\
dn: cn=sales,ou=Group,${__DOMAIN_COMPONENT__}\n\
objectClass: posixGroup\n\
cn: sales\n\
gidNumber: 1002\n\
\n\
# Users =====================================================================================================\n\
# Taro Suzuki. password: 'secret'
dn: uid=taro-suzuki,ou=People,${__DOMAIN_COMPONENT__}\n\
objectClass: posixAccount\n\
objectClass: inetOrgPerson\n\
sn: suzuki\n\
cn: taro\n\
displayName: Taro Suzuki\n\
uid: taro-suzuki\n\
uidNumber: 1000\n\
gidNumber: 1000\n\
homeDirectory: /home/taro-suzuki\n\
loginShell: /bin/bash\n\
userPassword: $(slappasswd -s secret)\n\
mail: taro-suzuki@${DOMAIN}\n\
\n\
# Hanako Suzuki. password: 'secret'\n\
dn: uid=hanako-suzuki,ou=People,${__DOMAIN_COMPONENT__}\n\
objectClass: posixAccount\n\
objectClass: inetOrgPerson\n\
sn: suzuki\n\
cn: hanako\n\
displayName: Hanako Suzuki\n\
uid: hanako-suzuki\n\
uidNumber: 1001\n\
gidNumber: 1002\n\
homeDirectory: /home/hanako-suzuki\n\
loginShell: /bin/bash\n\
userPassword: $(slappasswd -s secret)\n\
mail: hanako-suzuki@${DOMAIN}" | ldapadd -x -D "cn=Manager,${__DOMAIN_COMPONENT__}" -w ${MANAGER_PASSWORD} \
    && ldapwhoami -w secret -D "uid=taro-suzuki,ou=People,${__DOMAIN_COMPONENT__}" \
    && ldapwhoami -w secret -D "uid=hanako-suzuki,ou=People,${__DOMAIN_COMPONENT__}"

COPY entrypoint.sh /opt

ENTRYPOINT ["/opt/entrypoint.sh"]

