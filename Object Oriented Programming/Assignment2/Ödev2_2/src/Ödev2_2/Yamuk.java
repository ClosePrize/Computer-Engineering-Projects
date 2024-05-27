package Ödev2_2;

class Yamuk extends Dortgen implements Sekil_Interface{
    public Yamuk(Nokta nokta1, Nokta nokta2, Nokta nokta3, Nokta nokta4) {
        super(nokta1, nokta2, nokta3, nokta4);
    }

    @Override
    public double alanıHesapla() {
    	double kısaKenar = Math.abs(nokta1.x-nokta2.x);
    	double uzunKenar = Math.abs(nokta3.x-nokta4.x);
    	double yükseklik = Math.abs(nokta1.y-nokta3.y);
        return ((kısaKenar+uzunKenar)/2)*yükseklik; 
    }
    @Override
    public double kütleHesapla() {
    	double kutle = yogunluk * (this.alanıHesapla()*yukseklik);
    	return kutle;
    }
}