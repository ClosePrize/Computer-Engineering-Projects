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

print(f"97 sayısı: {solovay_strassen(97)}")