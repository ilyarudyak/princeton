//
// Created by Ilya Rudyak on 5/25/16.
//

#ifndef PASSWORD_CRACKER_KEY_H
#define PASSWORD_CRACKER_KEY_H

#include <string>
#include <vector>
#include <iostream>
using namespace std;

class Key {
public:
    const static int C = 8;
    const static int B = 5;
    const static int R = 1 << B;
    const static int N = B * C;
    const static string ALPHABET;

    Key() = default;
    Key(string s) {
        digit.resize(C, 0);
        KEYinit(s);
    }

    void KEYinit(string s) {
        int i, j;
        for (i = 0; i < C; i++) {
            for (j = 0; j < R; j++) {
                if (s[i] == ALPHABET[j]) {
                    digit[i] = (unsigned char) j;
                }
            }
        }
    }
    void KEYshow() {
        int i;
        for (i = 0; i < C; i++)
            putchar(ALPHABET[digit[i]]);
        printf("  ");
        for (i = 0; i < C; i++)
            printf("%2d ", digit[i]);
        printf("  ");
        for (i = 0; i < N; i++)
            printf("%d", KEYbit(i));
        putchar('\n');
    }
    int  KEYbit(int i) {
        return (digit[i/B] >> (B - 1 - i % B)) & 1;
    }

    // add this to other and store result in third object
    Key *KEYadd(Key &other) {
        int i;
        Key *res = new Key("a");
        int carry = 0;
        for (i = C-1; i >= 0; i--) {
            res->digit[i] = (unsigned char) (
                    (this->digit[i] + other.digit[i] + carry) % R);
            carry = (this->digit[i] + other.digit[i] + carry) >= R;
        }
        return res;
    }
    Key *KEYsubsetsum(vector<Key> &keys) {
        int i;
        Key *res = new Key("a");
        for (i = 0; i < N; i++)
            if (KEYbit(i)) {
                res = res->KEYadd(keys[i]);
                printf("%2d ", i);               // for debugging
                keys[i].KEYshow();               // for debugging
            }
        return res;
    }

private:
    vector<unsigned char> digit;
};

#endif //PASSWORD_CRACKER_KEY_H












