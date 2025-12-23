def modlu_us_hesapla(taban, us, mod):
    return pow(taban, us, mod)

# Örnek kullanım
taban = int(input("Tabanı gir: "))
us = int(input("Üssü gir: "))
mod = int(input("Modu gir: "))

sonuc = modlu_us_hesapla(taban, us, mod)
print("Sonuç:", sonuc)


