package Ödev2_1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
        Kare[] kareler = new Kare[10];
        Dikdortgen[] dikdörtgenler = new Dikdortgen[10];
        Yamuk[] yamuklar = new Yamuk[10];
        Paralelkenar[] paralelkenarlar = new Paralelkenar[10];

        for(int i=0;i<10;i++) {
        
	        System.out.println("Yamuk, Paralelkenar, Dikdortgen veya Kare şekillerinden hangisini tanımlamak istiyorsunuz?");
	        String secim = input.next();
	       
	        System.out.println("1. Köşe noktasının x koordinatını girin:"); 
	        double x1 = input.nextDouble();
	        System.out.println("1. Köşe noktasının y koordinatını girin:");
	        double y1 = input.nextDouble();
	        input.nextLine(); 
	        System.out.println("2. Köşe noktasının x koordinatını girin:"); 
	        double x2 = input.nextDouble();
	        System.out.println("2. Köşe noktasının y koordinatını girin:");
	        double y2 = input.nextDouble();
	        input.nextLine(); 
	        System.out.println("3. Köşe noktasının x koordinatını girin:"); 
	        double x3 = input.nextDouble();
	        System.out.println("3. Köşe noktasının y koordinatını girin:");
	        double y3 = input.nextDouble();
	        input.nextLine(); 
	        System.out.println("4. Köşe noktasının x koordinatını girin:"); 
	        double x4 = input.nextDouble();
	        System.out.println("4. Köşe noktasının y koordinatını girin:");
	        double y4 = input.nextDouble();
	        input.nextLine(); 
	        
	        Nokta nokta1 = new Nokta(x1, y1);
	        Nokta nokta2 = new Nokta(x2, y2);
	        Nokta nokta3 = new Nokta(x3, y3);
	        Nokta nokta4 = new Nokta(x4, y4);
	        
	        secim = secim.toLowerCase();
	        if (secim.equals("kare")){
	        	kareler[i] = new Kare(nokta1,nokta2,nokta3,nokta4);
	        }
	        else if (secim.equals("dikdörtgen")){
	        	dikdörtgenler[i] = new Dikdortgen(nokta1,nokta2,nokta3,nokta4);
	        }
	        else if (secim.equals("yamuk")){
	        	yamuklar[i] = new Yamuk(nokta1,nokta2,nokta3,nokta4);
	        }
	        else if (secim.equals("paralelkenar")){
	        	paralelkenarlar[i] = new Paralelkenar(nokta1,nokta2,nokta3,nokta4);
	        }
	        else {
	        	System.out.println("Geçersiz seçim!");
	        	i = i-1;
	        }
	        while(true) {
	        	System.out.println("Farklı bir şekil tanımlamak ister misiniz?(evet/hayır): "); 
		        String cevap = input.next().toLowerCase();
		        input.nextLine(); 
		        if(cevap.equals("evet")) {
		        	break;
		        }
		        else if(cevap.equals("hayır")) {
		        	i=10;
		        	break;
		        }
		        else {
		        	System.out.println("Hatalı tuşlama yapıldı!"); 
		        }
	        }
        }

        for (int i = 0;i<10;i++) {
        	if(kareler[i] != null) {
        		double alan = kareler[i].alanıHesapla();
            	System.out.println((i+1)+". Karenin Alanı: " + alan);
            }
        	else {}
        }
        for (int i = 0;i<10;i++) {
        	if(dikdörtgenler[i] != null) {
        		double alan = dikdörtgenler[i].alanıHesapla();
            	System.out.println((i+1)+". Dikdörtgenin Alanı: " + alan);
            }
        	else {}
        }
        for (int i = 0;i<10;i++) {
        	if(yamuklar[i] != null) {
        		double alan = yamuklar[i].alanıHesapla();
            	System.out.println((i+1)+". Yamuğun Alanı: " + alan);
            }
        	else {}
        }
        for (int i = 0;i<10;i++) {
        	if(paralelkenarlar[i] != null) {
        		double alan = paralelkenarlar[i].alanıHesapla();
            	System.out.println((i+1)+". Paralelkenarın Alanı: " + alan);
            }
        	else {}
        }
    }
}
