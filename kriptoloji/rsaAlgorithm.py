import random

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
    def anahtar_uret(self, bit):
        e = 65537

        p = asal_uret(bit // 2)
        q = asal_uret(bit // 2)

        n = p * q
        phi = (p - 1) * (q - 1)

        d = moduler_ters(e, phi)

        self.public_key = (e, n)
        self.private_key = (d, n)

        print(f"\nRSA Anahtarları Oluşturuldu")
        print(f"n bit uzunluğu: {n.bit_length()}")

    def sifrele(self, mesaj):
        e, n = self.public_key
        return pow(mesaj, e, n)

    def coz(self, sifreli):
        d, n = self.private_key
        return pow(sifreli, d, n)


def asal_mi(n, k=10):
    if n < 2:
        return False
    for p in [2, 3, 5, 7, 11, 13, 17, 19, 23]:
        if n % p == 0:
            return n == p

    r, d = 0, n - 1
    while d % 2 == 0:
        r += 1
        d //= 2

    for _ in range(k):
        a = random.randrange(2, n - 2)
        x = pow(a, d, n)
        if x == 1 or x == n - 1:
            continue
        for _ in range(r - 1):
            x = pow(x, 2, n)
            if x == n - 1:
                break
        else:
            return False
    return True

def asal_uret(bit):
    while True:
        n = random.getrandbits(bit) | (1 << bit - 1) | 1
        if asal_mi(n):
            return n


def rsa_test(p, q, mesaj):
    rsa = RSA()
    rsa.anahtar_uret(p, q)

    sifreli = rsa.sifrele(mesaj)
    cozulmus = rsa.coz(sifreli)

    print(f"Mesaj: {mesaj}")
    print(f"Şifreli Mesaj: {sifreli}")
    print(f"Çözülmüş Mesaj: {cozulmus}")

    assert mesaj == cozulmus, "❌ HATA: RSA çözme başarısız!"
    print("✅ Test başarılı\n")


def rsa_test(bit):
    rsa = RSA()
    rsa.anahtar_uret(bit)

    mesaj = 123456789
    sifreli = rsa.sifrele(mesaj)
    cozulmus = rsa.coz(sifreli)

    print(f"Mesaj: {mesaj}")
    print(f"Çözülmüş: {cozulmus}")

    assert mesaj == cozulmus
    print("✅ Test başarılı\n")

print("\n=========== RSA TESTLERİ ===========")
rsa_test(1024)
rsa_test(2048)