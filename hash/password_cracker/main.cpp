#include <iostream>
#include <fstream>
#include "Key.h"
#include "Encryptor.h"
using namespace std;

int main() {

    fstream in("rand8.txt");
    Encryptor encryptor(in, "password");

    encryptor.getPlainPass()->KEYshow();
    cout << endl;
    encryptor.encrypt();
    cout << endl;
    encryptor.getEncriptedPass()->KEYshow();

    return 0;
}































