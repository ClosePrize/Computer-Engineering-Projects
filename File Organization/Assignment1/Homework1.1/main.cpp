#include <fstream>
#include <iostream>
#include <string>
using namespace std;

void metniDosyayaYaz(string &metin, string &dosyaAdi) {
  ofstream dosya(dosyaAdi);
  if (dosya.is_open()) {
    dosya << metin;
    dosya.close();
    cout << "Metin dosyaya yazildi." << endl;
  } else {
    cout << "Dosya acilamadi." << endl;
  }
}

string dosyadanMetniOku(string &dosyaAdi) {
  ifstream dosya(dosyaAdi);
  string metin;
  if (dosya.is_open()) {
    getline(dosya, metin);
    dosya.close();
  } else {
    cout << "Dosya acilamadi." << endl;
  }
  return metin;
}

int main() {
  string metin;
  cout << "Dosyaya yazmak istediğiniz metni giriniz: ";
  getline(cin, metin);
  string dosyaAdi = "metinOkumaYazma.txt";
  metniDosyayaYaz(metin, dosyaAdi);
  string okunanMetin = dosyadanMetniOku(dosyaAdi);
  cout << "Dosyadan okunan metin:" << endl;
  cout << okunanMetin << endl;
  return 0;
}