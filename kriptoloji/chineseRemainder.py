def oklid_teoremi(a, b):
    if b == 0:
        return a, 1, 0
    gcd, x1, y1 = oklid_teoremi(b, a % b)
    return gcd, y1, x1 - (a // b) * y1


def mod_inverse(a, m):
    gcd, x, _ = oklid_teoremi(a, m)
    if gcd != 1:
        return None
    return x % m


def simplify_congruence(a, b, m):
    """
    a*x ≡ b (mod m) denkleminden x ≡ r (mod m') haline getirir.
    """
    g, _, _ = oklid_teoremi(a, m)
    if b % g != 0:
        raise ValueError(f"{a}x ≡ {b} (mod {m}) çözümsüz!")
    # Tümünü gcd ile sadeleştir
    a_ = a // g
    b_ = b // g
    m_ = m // g
    inv = mod_inverse(a_, m_)
    if inv is None:
        raise ValueError(f"{a_} mod {m_} için ters yok!")
    x0 = (b_ * inv) % m_
    return x0, m_


def chinese_remainder_general(equations):
    """
    equations: [(a1,b1,m1), (a2,b2,m2), ...]
    her biri a_i*x ≡ b_i (mod m_i)
    """
    simplified = [simplify_congruence(a, b, m) for (a, b, m) in equations]
    residues = [r for r, _ in simplified]
    moduli = [m for _, m in simplified]
    return chinese_remainder(moduli, residues)


def chinese_remainder(n_list, a_list):
    N = 1
    for n in n_list:
        N *= n
    total = 0
    for n_i, a_i in zip(n_list, a_list):
        N_i = N // n_i
        inv = mod_inverse(N_i, n_i)
        total += a_i * N_i * inv
    return total % N


# ==== ÖRNEK ====
equations = [
    (15, 21, 48),  # 15x ≡ 21 (mod 48)
    (166, 46, 22), # 166x ≡ 46 (mod 22)
    (1, 5, 13)     # x ≡ 5 (mod 13)
]

x = chinese_remainder_general(equations)
print("x =", x)
