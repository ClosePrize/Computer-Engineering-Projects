import random

def jacobi_sembolu_hesaplama(a, n):
    if n <= 0 or n % 2 == 0: return 0
    j = 1
    if a < 0:
        a = -a
        if n % 4 == 3: j = -j
    while a != 0:
        while a % 2 == 0:
            a //= 2
            if n % 8 == 3 or n % 8 == 5: j = -j
        a, n = n, a
        if a % 4 == 3 and n % 4 == 3: j = -j
        a %= n
    return j if n == 1 else 0

def solovay_strassen(n, k=10):
    if n == 2: return "Muhtemelen Asal"
    if n % 2 == 0 or n < 2: return "Asal Değil"

    for _ in range(k):
        a = random.randint(2, n - 1)
        jacobi = jacobi_sembolu_hesaplama(a, n)
        
        if jacobi == -1: 
            jacobi = n - 1
            
        euler_ussu = pow(a, (n - 1) // 2, n)
        
        if jacobi == 0 or euler_ussu != jacobi:
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

print(f"{MODP_2048} sayısı: {solovay_strassen(MODP_2048)}")