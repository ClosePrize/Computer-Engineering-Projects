def extended_oklid_teoremi(a, b):

    if b == 0:
        return a, 1, 0  
    else:
        gcd, x1, y1 = extended_oklid_teoremi(b, a % b)
        x = y1
        y = x1 - (a // b) * y1
        return gcd, x, y


a = 55
m = 87

gcd, x, y = extended_oklid_teoremi(a, m)

if gcd != 1:
    print("Ters yok, çünkü gcd(a, m) =", gcd)
else:
    inverse = x % m 
    print(f"{a} sayısının {m} modundaki tersi: {inverse}")
