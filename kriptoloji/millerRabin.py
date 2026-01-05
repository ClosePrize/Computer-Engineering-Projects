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

print(f"1047294654891215415615347515675345464156156415657 sayısı: {miller_rabin(104729465489121541561534751567534546415615641565327)}")