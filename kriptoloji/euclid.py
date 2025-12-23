def oklid(a, b):
    while b != 0:
        a, b = b, a % b
    return a

a = 55
m = 87

print("gcd(a, m) =", oklid(a, m))