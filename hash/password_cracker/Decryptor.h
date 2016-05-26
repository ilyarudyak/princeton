//
// Created by Ilya Rudyak on 5/25/16.
//

#ifndef PASSWORD_CRACKER_DECRYPTOR_H
#define PASSWORD_CRACKER_DECRYPTOR_H

#include "Key.h"
#include <vector>
#include <fstream>
using namespace std;

class Decryptor {

public:
    Decryptor() = default;
    Decryptor(fstream &in, string encPass) {
        string word;
        while (in >> word) {
            if (!word.empty()) {
                table.push_back(Key(word));
            }
        }

        encryptedPass = new Key(encPass);
    }

    void decryptBrute(int n) {
        // 1) generate all subsets of table
        // 2) calculate sum for each subset
        // 3) if sum == encryptedPass build plainPass
        decryptBrute("", n);
    }

private:
    vector<Key> table;
    Key *plainPass;
    Key *encryptedPass;

    void decryptBrute(string subset, int n) {
        Key *key;

        if (n == 0) {
            key = encryptedPass->KEYsubsetsum(table, subset);
            if (key->getKeyStr() == encryptedPass->getKeyStr()) {
//                key->KEYshow();
                Key res;
                res.buildFromSubset(subset);
                res.KEYshow();
            }
            return;
        }
        decryptBrute(subset + "0", n - 1);
        decryptBrute(subset + "1", n - 1);
    }
};

#endif //PASSWORD_CRACKER_DECRYPTOR_H


















