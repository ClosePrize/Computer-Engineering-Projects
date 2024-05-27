package Ödev2_2;

class Paralelkenar extends Dortgen implements Sekil_Interface{

    public Paralelkenar(Nokta nokta1, Nokta nokta2, Nokta nokta3, Nokta nokta4) {
    	super(nokta1, nokta2, nokta3, nokta4);	
    	}

	@Override
    public double alanıHesapla() {
    	double uzunKenar = Math.abs(nokta1.x-nokta2.x);
    	double yükseklik = Math.abs(nokta1.y-nokta3.y);
        return uzunKenar*yükseklik;
    }
	@Override
		public double kütleHesapla() {
	    	double kutle = yogunluk * (this.alanıHesapla()*yukseklik);
	    	return kutle;
	    }
}
