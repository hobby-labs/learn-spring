#!/usr/bin/env python3
import os

class CreateUserLdif:
    script_dir = os.path.dirname(__file__)

    def exec(self):
        f = open("user_ldif.ldif", mode='w')
        f.truncate(0)

        #for i in range(20000000):
        for i in range(10):
            f.write("dn: uid=user" + str(i).zfill(9) + ",ou=People,dc=mysite,dc=example,dc=com\n");

#{SSHA}i7SP94SddUe85Sxx4TEcbq0bRu8db5Ks

if __name__ == "__main__":
    instance = CreateUserLdif()
    instance.exec()
