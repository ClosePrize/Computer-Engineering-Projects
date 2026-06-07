import msvcrt
import sys

kaydedilen_tuslar = []

try:
    while True:
        if msvcrt.kbhit():
            tus = msvcrt.getch()
            
            if tus == b'\x03':
                raise KeyboardInterrupt
            
            try:
                cozulmus_tus = tus.decode("utf-8")
                
                if cozulmus_tus == '\r':
                    kaydedilen_tuslar.append("[ENTER]\n")
                elif cozulmus_tus == '\x08':
                    kaydedilen_tuslar.append("[SİLME]")
                elif cozulmus_tus == ' ':
                    kaydedilen_tuslar.append("[BOŞLUK]")
                else:
                    kaydedilen_tuslar.append(cozulmus_tus)
            except UnicodeDecodeError:
                kaydedilen_tuslar.append("[ÖZEL_TUŞ]")

except KeyboardInterrupt:
    print("\n\n--- DİNLEME SONLANDIRILDI ---")
    print("Yakalanan Tuş Vuruşları Sırasıyla Aşağıdadır:\n")
    print("".join(kaydedilen_tuslar))
    sys.exit(0)