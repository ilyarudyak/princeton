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
                table.push_back(Key(word));
            }
        }

        plainPass = new Key(password);
    }

    void encrypt() {
        encryptedPass = plainPass->KEYsubsetsum(table);
    }

    Key *getEncryptedPass() {
        return encryptedPass;
    }


    Key *getPlainPass() {
        return plainPass;
    }

private:
    vector<Key> table;
    Key *plainPass;
    Key *encryptedPass;

};

#endif //PASSWORD_CRACKER_ENCRYPTOR_H
















