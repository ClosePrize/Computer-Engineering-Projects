#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
using namespace std;

int main() {
  ifstream dosyaOku("metin.txt");
  int kelimeSayisi = 1;
  char karakter;

  if (dosyaOku.is_open()) {

    while (dosyaOku.get(karakter)) {
      if (karakter == ' ') {
        kelimeSayisi++;
      }
    }
    cout << "Metin dosyasindaki kelime sayisi: " << kelimeSayisi << endl;
    dosyaOku.close();
  }
}