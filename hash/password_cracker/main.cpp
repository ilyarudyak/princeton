#include <iostream>
#include <fstream>
#include "Key.h"
#include "Encryptor.h"
#include "Decryptor.h"
using namespace std;

int main() {

    // test decryptor brute force
    fstream in("rand5.txt");
    Decryptor decryptor(in, "exvx5");
    decryptor.decryptBrute(Key::N);

    // test buildFromSubset()
//    Key key;
//    key.buildFromSubset("0111100000100101001010110");
//    key.KEYshow();

//    Key key("passw");
//    key.KEYshow();

//    Key key("test");
//    cout << key.getKeyStr() << endl;

    // test encryptor
//    fstream in("rand4.txt");
//    Encryptor encryptor(in, "pass");
//
//    encryptor.getPlainPass()->KEYshow();
//    cout << endl;
//    encryptor.encrypt();
//    cout << endl;
//    encryptor.getEncryptedPass()->KEYshow();

    return 0;
}































