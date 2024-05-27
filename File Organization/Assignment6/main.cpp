#include <iostream>
#include <string>
#include <vector>

using namespace std;

class Dugum {
public:
    string ad;
    bool isDosya;
    vector<Dugum*> altDugumler;

    Dugum(string ad, bool isDosya) {
        this->ad = ad;
        this->isDosya = isDosya;
    }
};

class DosyaYonetimSistemi {
private:
    Dugum* root;

public:
    DosyaYonetimSistemi() {
        root = new Dugum("Media_Root", false);
    }

    void dosyaEkle(string yol) {
        vector<string> parcalar;
        string parca = "";
        for (char ch : yol) {
            if (ch == '/') {
                parcalar.push_back(parca);
                parca = "";
            } else {
                parca += ch;
            }
        }
        parcalar.push_back(parca);

        Dugum* aktifDugum = root;
        for (int i = 1; i < parcalar.size(); i++) {
            string hedefAd = parcalar[i];
            bool bulundu = false;
            for (Dugum* altDugum : aktifDugum->altDugumler) {
                if (altDugum->ad == hedefAd) {
                    aktifDugum = altDugum;
                    bulundu = true;
                    break;
                }
            }
            if (!bulundu) {
                Dugum* yeniDugum = new Dugum(hedefAd, (i == parcalar.size() - 1));
                aktifDugum->altDugumler.push_back(yeniDugum);
                aktifDugum = yeniDugum;
            }
        }

        cout << "Dosya eklendi: " << yol << endl;
    }

    void dosyaSil(string yol) {
        vector<string> parcalar;
        string parca = "";
        for (char ch : yol) {
            if (ch == '/') {
                parcalar.push_back(parca);
                parca = "";
            } else {
                parca += ch;
            }
        }
        parcalar.push_back(parca);

        Dugum* aktifDugum = root;
        Dugum* ebeveynDugum = nullptr;
        int silinecekIndex = -1;
        for (int i = 1; i < parcalar.size(); i++) { 
            string hedefAd = parcalar[i];
            bool bulundu = false;
            for (int j = 0; j < aktifDugum->altDugumler.size(); j++) {
                if (aktifDugum->altDugumler[j]->ad == hedefAd) {
                    ebeveynDugum = aktifDugum;
                    aktifDugum = aktifDugum->altDugumler[j];
                    silinecekIndex = j;
                    bulundu = true;
                    break;
                }
            }
            if (!bulundu) {
                cout << "Dosya veya klasör bulunamadı: " << yol << endl;
                return;
            }
        }
        if (aktifDugum->isDosya) {
            aktifDugum->altDugumler.clear(); 
        } else {
            delete aktifDugum; 
            ebeveynDugum->altDugumler.erase(ebeveynDugum->altDugumler.begin() + silinecekIndex);
        }

        cout << "Dosya silindi: " << yol << endl;
    }

    void dosyaAra(string ad) {
        dosyaAraRecursive(root, ad, "");
    }

    void dosyaAraRecursive(Dugum* aktifDugum, string ad, string yol) {
        if (aktifDugum->ad == ad) {
            cout << "Dosya bulundu: " << yol + "/" + aktifDugum->ad << endl;
        }

        if (!aktifDugum->isDosya) {
            for (Dugum* altDugum : aktifDugum->altDugumler) {
                dosyaAraRecursive(altDugum, ad, yol + "/" + aktifDugum->ad);
            }
        }
    }

    void agaciDolas(string dolasmaYontemi) {
        if (dolasmaYontemi == "in-order") {
            inOrderDolas(root);
        } else if (dolasmaYontemi == "pre-order") {
            preOrderDolas(root);
        } else if (dolasmaYontemi == "post-order") {
            postOrderDolas(root);
        } else {
            cout << "Geçersiz dolaşma yöntemi!" << endl;
        }
    }

    void inOrderDolas(Dugum* aktifDugum) {
        if (aktifDugum->isDosya) {
            cout << "Dosya: " << aktifDugum->ad << endl;
        } else {
            cout << "Klasör: " << aktifDugum->ad << endl;
            for (Dugum* altDugum : aktifDugum->altDugumler) {
                inOrderDolas(altDugum);
            }
        }
    }

    void preOrderDolas(Dugum* aktifDugum) {
        if (aktifDugum->isDosya) {
            cout << "Dosya: " << aktifDugum->ad << endl;
        } else {
            cout << "Klasör: " << aktifDugum->ad << endl;
            for (Dugum* altDugum : aktifDugum->altDugumler) {
                preOrderDolas(altDugum);
            }
        }
    }

    void postOrderDolas(Dugum* aktifDugum) {
        if (aktifDugum->isDosya) {
            cout << "Dosya: " << aktifDugum->ad << endl;
        } else {
            for (Dugum* altDugum : aktifDugum->altDugumler) {
                postOrderDolas(altDugum);
            }
            cout << "Klasör: " << aktifDugum->ad << endl;
        }
    }
};

int main() {
  DosyaYonetimSistemi dosyaYonetimSistemi;

  dosyaYonetimSistemi.dosyaEkle("Media_Root/Music/Pop/track1.mp3");
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Music/Pop/track2.mp3");
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Music/Jazz/track3.mp3");
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Music/Jazz/track4.mp3");

  dosyaYonetimSistemi.dosyaEkle("Media_Root/Photos/Vacation/photo1.jpg");
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Photos/Vacation/photo2.jpg");
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Photos/Events/photo3.jpg");
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Photos/Events/photo4.jpg");
  
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Videos/Movies/movie1.mp4");
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Videos/Movies/movie2.mp4");
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Videos/Documentaries/doc1.mp4");
  dosyaYonetimSistemi.dosyaEkle("Media_Root/Videos/Documentaries/doc2.mp4");
  
  dosyaYonetimSistemi.dosyaAra("track1.mp3");
  dosyaYonetimSistemi.agaciDolas("in-order");

  return 0;
}