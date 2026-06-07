import time
from PIL import ImageGrab

print("İşlem: Mevcut klasöre 5 saniye arayla toplam 3 adet ekran görüntüsü kaydedilecektir.\n")

for i in range(1, 4):
    print(f"[{i}/3] Ekran görüntüsü 5 saniye içinde alınacak.")
    
    time.sleep(5)
    
    try:
        ekran_goruntusu = ImageGrab.grab()
        dosya_adi = f"ekran_goruntusu_{i}.png"
        
        ekran_goruntusu.save(dosya_adi)
        print(f" -> Başarılı: {dosya_adi} ismiyle sisteme kaydedildi.\n")
        
    except Exception as e:
        print(f" -> Hata oluştu: Ekran görüntüsü alınamadı. Detay: {e}")

print("Kaydedilen resim dosyalarını bu betiğin çalıştığı klasörde inceleyebilirsiniz.")