import cv2
import numpy as np

def metni_binary_yap(mesaj):
    """Gelen metni 8 bitlik ikilik (binary) formata çevirir."""
    return ''.join(format(ord(karakter), '08b') for karakter in mesaj)

def mesaj_gizle(orijinal_resim_yolu, gizli_mesaj, cikti_resim_yolu):
    """Least Significant Bit yöntemi ile resmin içine metin gizler."""
    
    resim_dizisi = np.fromfile(orijinal_resim_yolu, np.uint8)
    resim = cv2.imdecode(resim_dizisi, cv2.IMREAD_COLOR)
    
    if resim is None:
        print("Hata: Resim okunamadı! Lütfen dosya yolunu kontrol et.")
        return

    gizli_mesaj += "#####" 
    binary_mesaj = metni_binary_yap(gizli_mesaj)
    veri_indeksi = 0
    veri_uzunlugu = len(binary_mesaj)

    for satir in resim:
        for piksel in satir:
            for i in range(3): 
                if veri_indeksi < veri_uzunlugu:
                    piksel[i] = int(bin(piksel[i])[2:-1] + binary_mesaj[veri_indeksi], 2)
                    veri_indeksi += 1

    is_success, buffer = cv2.imencode(".png", resim)
    if is_success:
        buffer.tofile(cikti_resim_yolu)
        print(f"Başarılı! Gizli mesaj '{cikti_resim_yolu}' dosyasına kaydedildi.")
    else:
        print("Hata: Resim kaydedilemedi.")

def mesaj_cikar(stego_resim_yolu):
    """İçinde veri gizlenmiş resimden mesajı okur ve çıkarır."""
    
    resim_dizisi = np.fromfile(stego_resim_yolu, np.uint8)
    resim = cv2.imdecode(resim_dizisi, cv2.IMREAD_COLOR)

    if resim is None:
        print("Hata: Şifreli resim okunamadı!")
        return

    binary_veri = ""

    for satir in resim:
        for piksel in satir:
            for i in range(3):
                binary_veri += bin(piksel[i])[-1]

    tum_baytlar = [binary_veri[i: i+8] for i in range(0, len(binary_veri), 8)]
    cozulmus_mesaj = ""

    for bayt in tum_baytlar:
        cozulmus_mesaj += chr(int(bayt, 2))
        if cozulmus_mesaj[-5:] == "#####":
            break

    print("Sistemden Çıkarılan Gizli Mesaj:", cozulmus_mesaj[:-5])

mesaj_gizle(r"Computer-Engineering-Projects\Bilgisayar Ağlarında Güvenlik\ornek.png", "Bu mesaj sadece steganografi algoritmasi ile okunabilir.", "gizli_resim.png")

mesaj_cikar("gizli_resim.png")