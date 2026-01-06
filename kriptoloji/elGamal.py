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
        print(f"Public keys (p, g, y): {self.public_key}")
        print(f"Private key (x): {self.private_key}")

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



MODP_1024 = int(
    "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1"
    "29024E088A67CC74020BBEA63B139B22514A08798E3404DD"
    "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245"
    "E485B576625E7EC6F44C42E9A63A3620FFFFFFFFFFFFFFFF",
    16
)

MODP_2048 = int(
    "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1"
    "29024E088A67CC74020BBEA63B139B22514A08798E3404DD"
    "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245"
    "E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED"
    "EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE65381"
    "FFFFFFFFFFFFFFFF",
    16
)

g = 2


def elgamal_test(p, g, mesaj):
    elgamal = ElGamal()
    elgamal.anahtar_uret(p, g)

    sifreli = elgamal.sifrele(mesaj)
    cozulmus = elgamal.coz(sifreli)

    print(f"Mesaj: {mesaj}")
    print(f"Şifreli (c1, c2):")
    print(f"c1 = {sifreli[0]}")
    print(f"c2 = {sifreli[1]}")
    print(f"Çözülmüş Mesaj: {cozulmus}")

    assert mesaj == cozulmus, "❌ HATA: Çözme başarısız!"
    print("✅ Test başarılı\n")


print("\n=========== ELGAMAL TESTLERİ ===========")

mesaj = 123456789

print("🔐 1024-bit ElGamal Testi")
elgamal_test(MODP_1024, g, mesaj)

print("🔐 2048-bit ElGamal Testi")
elgamal_test(MODP_2048, g, mesaj)