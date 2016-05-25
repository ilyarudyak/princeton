//
// Created by Ilya Rudyak on 5/25/16.
//

#ifndef PASSWORD_CRACKER_ENCRYPTOR_H
#define PASSWORD_CRACKER_ENCRYPTOR_H

#include <vector>
#include <fstream>
#include "Key.h"
using namespace std;

class Encryptor {
public:

    Encryptor() = default;
    Encryptor(fstream &in, string password) {
        string word;
        while (in >> word) {
            if (!word.empty()) {
                T.push_back(Key(word));
            }
        }

        plainPass = password;
    }

    void encrypt() {
        encriptedPass = plainPass.KEYsubsetsum(T);
    }

    Key &getEncriptedPass() {
        return encriptedPass;
    }


    Key &getPlainPass() {
        return plainPass;
    }

private:
    vector<Key> T;
    Key plainPass;
    Key encriptedPass;

};

#endif //PASSWORD_CRACKER_ENCRYPTOR_H
















