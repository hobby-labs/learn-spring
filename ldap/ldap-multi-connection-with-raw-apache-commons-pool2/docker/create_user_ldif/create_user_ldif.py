#!/usr/bin/env python3
import os

class CreateUserLdif:
    script_dir = os.path.dirname(__file__)

    def exec(self):


        sum_of_count = -1
        for i in range(200):
            file_name = "user_ldif_" + str(i).zfill(4) + ".ldif"
            f = open(file_name, mode='w')
            print("Creating file_name: " + file_name)
            f.truncate(0)
            for j in range(100000):
                sum_of_count += 1
                user_num = str(sum_of_count).zfill(9)
                f.write("dn: uid=user" + user_num + ",ou=People,dc=mysite,dc=example,dc=com\n");
                f.write("objectClass: posixAccount\n");
                f.write("objectClass: inetOrgPerson\n");
                f.write("sn: user\n");
                f.write("cn: " + user_num + "\n");
                f.write("displayName: user" + user_num + "\n");
                f.write("uid: user" + user_num + "\n");
                f.write("uidNumber: " + str(sum_of_count + 10000) + "\n");
                f.write("gidNumber: 1000\n");
                f.write("homeDirectory: " + user_num + "\n");
                f.write("loginShell: /bin/bash\n");
                f.write("userPassword: {SSHA}i7SP94SddUe85Sxx4TEcbq0bRu8db5Ks\n");
                f.write("mail: user" + user_num + "\n");
                f.write("\n");
            f.close()

if __name__ == "__main__":
    instance = CreateUserLdif()
    instance.exec()
