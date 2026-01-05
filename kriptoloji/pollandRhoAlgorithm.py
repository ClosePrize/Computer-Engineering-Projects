import random

def extended_gcd(a, b):
    if a == 0:
        return b, 0, 1
    else:
        g, y, x = extended_gcd(b % a, a)
        return g, x - (b // a) * y, y

def update_step(x, a, b, p, n, alpha, beta):
    subset = x % 3
    if subset == 1: # S1
        x_new = (beta * x) % p
        b_new = (b + 1) % n
        a_new = a
    elif subset == 0: # S0
        x_new = (x * x) % p
        a_new = (2 * a) % n
        b_new = (2 * b) % n
    else: # S2
        x_new = (alpha * x) % p
        a_new = (a + 1) % n
        b_new = b
    return x_new, a_new, b_new

def solve_pollard_rho_auto(p, alpha, beta):
    n = p - 1
    attempt = 0
    
    print(f"\nHEDEF: p={p}, alpha={alpha}, beta={beta}")
    print("Doğru başlangıç değerleri aranıyor, lütfen bekleyiniz...")
    
    while True:
        attempt += 1
        print(f"\r--- Deneme #{attempt} ---", end="", flush=True)
        # 1. Rastgele Başlangıç Değerleri Seç
        # x, a, b değerlerini rastgele seçiyoruz ki "kötü" döngülerden kurtulalım.
        x_start = random.randint(1, n)
        a_start = random.randint(0, n)
        b_start = random.randint(0, n)
        
        # Kaplumbağa ve Tavşan Hazırlığı
        x_t, a_t, b_t = x_start, a_start, b_start
        x_h, a_h, b_h = x_start, a_start, b_start
        
        # Bu denemenin verilerini tutacak liste (Başarılı olursa yazdırmak için)
        history = []
        
        i = 0
        solved = False
        collision_found = False
        
        # Döngüyü başlat
        while True:
            i += 1
            
            # Adım İlerleme
            x_t, a_t, b_t = update_step(x_t, a_t, b_t, p, n, alpha, beta)
            
            x_h, a_h, b_h = update_step(x_h, a_h, b_h, p, n, alpha, beta) # Tavşan 1
            x_h, a_h, b_h = update_step(x_h, a_h, b_h, p, n, alpha, beta) # Tavşan 2
            
            # Kayıt et
            history.append(f"{i:<4} | {x_t:<8} {a_t:<6} {b_t:<6} | {x_h:<10} {a_h:<6} {b_h:<6}")
            
            # Çakışma Kontrolü
            if x_t == x_h:
                collision_found = True
                break
            
            # Sonsuz döngü koruması (Çok uzarsa bu denemeyi iptal et)
            if i > 200: 
                break
        
        if not collision_found:
            continue # Çakışma bulamadıysa (çok nadir), yeni sayı dene
            
        # Denklem Çözme Denemesi
        B_diff = (b_t - b_h) % n
        A_diff = (a_h - a_t) % n
        g, u, v = extended_gcd(B_diff, n)
        
        if A_diff % g == 0: # EBOB bölüyor mu? EVET!
            # Çözümü bulduk demektir
            x0 = (u * (A_diff // g)) % (n // g)
            
            # Doğrulama
            final_x = None
            for k in range(g):
                sol = x0 + k * (n // g)
                if pow(alpha, sol, p) == beta:
                    final_x = sol
                    break
            
            if final_x is not None:
                # BAŞARILI! Tabloyu ve sonucu yazdır.
                print("\n" + "="*85)
                print(f"✅ BAŞARILI DENEME (Deneme #{attempt})")
                print(f"Başlangıç Değerleri: x={x_start}, a={a_start}, b={b_start}")
                print("="*85)
                print(f"{'i':<4} | {'x_t':<8} {'a_t':<6} {'b_t':<6} | {'x_h (2i)':<10} {'a_h':<6} {'b_h':<6}")
                print("-" * 85)
                for line in history:
                    print(line)
                print("-" * 85)
                print(f"Çakışma: x_t = {x_t} ve x_h = {x_h}")
                print(f"Denklem: x * ({B_diff}) ≡ {A_diff} (mod {n})")
                print(f"🚀 SONUÇ: x = {final_x}")
                print("="*85)
                return # Programdan çık
                
        # Eğer buraya geldiyse, EBOB bölmüyordur. Sessizce döngüye devam et.

# Çalıştır
if __name__ == "__main__":
    solve_pollard_rho_auto(809, 89, 618)