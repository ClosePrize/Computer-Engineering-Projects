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

class RSA:
    def __init__(self):
        self.public_key = None
        self.private_key = None

    def anahtar_uret(self, p, q):
        n = p * q
        phi = (p - 1) * (q - 1)

        e = random.randrange(2, phi)
        while ebob(e, phi) != 1:
            e = random.randrange(2, phi)

        d = moduler_ters(e, phi)

        self.public_key = (e, n)
        self.private_key = (d, n)
        
        print(f"RSA Anahtarları Oluşturuldu:")
        print(f"Genel Anahtar (Public): {self.public_key}")
        print(f"Özel Anahtar (Private): {self.private_key}")

    def sifrele(self, mesaj_sayisi):
        e, n = self.public_key
        sifreli_mesaj = pow(mesaj_sayisi, e, n)
        return sifreli_mesaj

    def coz(self, sifreli_mesaj):
        d, n = self.private_key
        cozulmus_mesaj = pow(sifreli_mesaj, d, n)
        return cozulmus_mesaj

print("\n--- RSA TESTİ ---")
rsa = RSA()
rsa.anahtar_uret(61, 53)

mesaj = 123 
sifreli = rsa.sifrele(mesaj)
cozulmus = rsa.coz(sifreli)

print(f"Orijinal Mesaj: {mesaj}")
print(f"Şifreli Mesaj : {sifreli}")
print(f"Çözülmüş Mesaj: {cozulmus}")