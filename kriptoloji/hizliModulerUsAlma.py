def hizli_us_alma(taban, us, mod):
    binary_us = bin(us)[2:]  
    sonuc = 1
    
    for bit in binary_us:
        sonuc = (sonuc * sonuc) % mod       
        if bit == '1':
            sonuc = (sonuc * taban) % mod         
    return sonuc

print(hizli_us_alma(7, 560, 561))