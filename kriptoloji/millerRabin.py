import random

def miller_rabin(n, k=10):
    if n == 2: return "Muhtemelen Asal"
    if n % 2 == 0 or n < 2: return "Asal Değil"

    s = 0
    d = n - 1
    while d % 2 == 0:
        d //= 2
        s += 1

    for _ in range(k):
        a = random.randint(2, n - 2)
        x = pow(a, d, n) 
        
        if x == 1 or x == n - 1:
            continue
        
        bilesik_tespit_edildi = True
        for _ in range(s - 1):
            x = pow(x, 2, n)
            if x == n - 1:
                bilesik_tespit_edildi = False
                break
        
        if bilesik_tespit_edildi:
            return "Asal Değil"

    return "Muhtemelen Asal"

MODP_2048 = int(
    "FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD1"
    "29024E088A67CC74020BBEA63B139B22514A08798E3404DD"
    "EF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245"
    "E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7ED"
    "EE386BFB5A899FA5AE9F24117C4B1FE649286651ECE65381"
    "FFFFFFFFFFFFFFFF",
    16
)

print(f"{MODP_2048} sayısı: {miller_rabin(MODP_2048)}")