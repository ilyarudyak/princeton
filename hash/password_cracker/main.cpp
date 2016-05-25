#include <iostream>
#include <fstream>
#include "Key.h"
#include "Encryptor.h"
using namespace std;

int main() {

    fstream in("easy8.txt");
    Encryptor encryptor(in, "password");

    printf("   ");
    encryptor.getPlainPass().KEYshow();
    cout << endl;
    encryptor.encrypt();
    cout << endl;
    printf("   ");
    encryptor.getEncriptedPass().KEYshow();

    return 0;
}































