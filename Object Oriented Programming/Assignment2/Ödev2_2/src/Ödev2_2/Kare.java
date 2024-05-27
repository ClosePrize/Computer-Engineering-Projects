package Ödev2_2;

class Kare extends Dortgen implements Sekil_Interface{
    public Kare(Nokta nokta1, Nokta nokta2, Nokta nokta3, Nokta nokta4) {
        super(nokta1, nokta2, nokta3, nokta4);
    }
    @Override
    public double kütleHesapla() {
    	double kutle = yogunluk * (this.alanıHesapla()*yukseklik);
    	return kutle;
    }
    @Override
    public double alanıHesapla() {
    	double kenar1 = Math.abs((nokta1.x)-(nokta2.x));
    	double kenar2 = Math.abs((nokta1.y-nokta3.y));
        return kenar1*kenar2;
    }
}
