package Ödev2_1;

class Dortgen {
    Nokta nokta1;
    Nokta nokta2;
    Nokta nokta3;
    Nokta nokta4;

    public Dortgen(Nokta nokta1, Nokta nokta2, Nokta nokta3, Nokta nokta4) {
        this.nokta1 = nokta1;
        this.nokta2 = nokta2;
        this.nokta3 = nokta3;
        this.nokta4 = nokta4;
    }

    public double alanıHesapla() {
    	double kenar1 = Math.abs((nokta1.x)-(nokta2.x));
    	double kenar2 = Math.abs((nokta1.y-nokta3.y));
        return kenar1*kenar2;
    }
}