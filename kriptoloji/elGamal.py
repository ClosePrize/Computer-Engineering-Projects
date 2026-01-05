import random

def ebob(a, b):
    while b:
        a, b = b, a % b
    return a

def moduler_ters(a, m):
    m0 = m
    y = 0
    x = 1
    if m == 1: return 0
    while a > 1:
        q = a // m
        t = m
        m = a % m
        a = t
        t = y
        y = x - q * y
        x = t
    if x < 0: x += m0
    return x

class ElGamal:
    def __init__(self):
        self.public_key = None
        self.private_key = None

    def anahtar_uret(self, p, g):
        self.p = p
        self.g = g
        
        self.private_key = random.randint(2, p - 2)
        
        y = pow(g, self.private_key, p)
        self.public_key = (p, g, y)
        
        print(f"El-Gamal Anahtarları Oluşturuldu:")
        print(f"Genel (p, g, y): {self.public_key}")
        print(f"Özel (x): {self.private_key}")

    def sifrele(self, mesaj_sayisi):
        p, g, y = self.public_key
        
        k = random.randint(2, p - 2)
        
        c1 = pow(g, k, p)
        
        s = pow(y, k, p)
        c2 = (mesaj_sayisi * s) % p
        
        return (c1, c2)

    def coz(self, sifreli_tuple):
        c1, c2 = sifreli_tuple
        x = self.private_key
        p = self.p
        
        s = pow(c1, x, p)
        
        s_ters = moduler_ters(s, p)
        
        cozulmus_mesaj = (c2 * s_ters) % p
        return cozulmus_mesaj

print("\n--- EL-GAMAL TESTİ ---")
elgamal = ElGamal()
elgamal.anahtar_uret(107, 2)

mesaj = 65
sifreli_veri = elgamal.sifrele(mesaj)
cozulmus_veri = elgamal.coz(sifreli_veri)

print(f"Orijinal Mesaj: {mesaj}")
print(f"Şifreli Veri (c1, c2): {sifreli_veri}")
print(f"Çözülmüş Mesaj: {cozulmus_veri}")