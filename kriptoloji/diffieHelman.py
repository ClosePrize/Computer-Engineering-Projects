import random

class DiffieHellmanTaraf:
    def __init__(self, p, g, isim):
        self.p = p
        self.g = g
        self.isim = isim
        self.private_key = None 
        self.public_key = None 
        self.shared_secret = None 

    def ozel_ve_genel_anahtar_uret(self):
        self.private_key = random.randint(2, self.p - 2)
        
        self.public_key = pow(self.g, self.private_key, self.p)
        
        print(f"{self.isim} -> Özel Anahtarı: {self.private_key}")
        print(f"{self.isim} -> Genel Anahtarı (Gönderilecek): {self.public_key}")
        return self.public_key

    def ortak_sirri_hesapla(self, karsi_taraf_public_key):
        self.shared_secret = pow(karsi_taraf_public_key, self.private_key, self.p)
        
        print(f"{self.isim} -> Hesaplanan Ortak Sır: {self.shared_secret}")
        return self.shared_secret

print("\n--- DIFFIE-HELLMAN ANAHTAR DEĞİŞİMİ ---")

ortak_p = 23  
ortak_g = 5   

print(f"Ortak Parametreler -> p: {ortak_p}, g: {ortak_g}\n")

alice = DiffieHellmanTaraf(ortak_p, ortak_g, "Alice")
bob = DiffieHellmanTaraf(ortak_p, ortak_g, "Bob")

alice_public = alice.ozel_ve_genel_anahtar_uret()
bob_public = bob.ozel_ve_genel_anahtar_uret()

print("-" * 40)
print("... Ağ üzerinden Genel Anahtarlar takas ediliyor ...")
print("-" * 40)

alice_shared = alice.ortak_sirri_hesapla(bob_public)

bob_shared = bob.ortak_sirri_hesapla(alice_public)

print("\n--- SONUÇ ---")
if alice_shared == bob_shared:
    print(f"BAŞARILI! Her iki taraf da aynı sırda anlaştı: {alice_shared}")
    print("Bu sayı artık AES gibi simetrik bir şifreleme için anahtar olarak kullanılabilir.")
else:
    print("HATA! Anahtarlar uyuşmuyor.")