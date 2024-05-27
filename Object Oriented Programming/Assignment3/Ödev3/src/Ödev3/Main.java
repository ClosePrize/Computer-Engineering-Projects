package Ödev3;
import java.util.Scanner;
import java.io.*;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		Ogrenci[] ogrenci_bilgileri = new Ogrenci[10];

        System.out.println("1. Yeni öğrenci kaydetme");
        System.out.println("2. Harfe göre öğrencileri listeleme");
        System.out.println("3. Not güncelleme");

        while(true) {
	        System.out.print("Yapmak istediğiniz işlemin sayısını giriniz:");
	        int tercih = input.nextInt();
	        input.nextLine();
	        
	        if(tercih == 1) {
	        	yeniOgrenciKaydet(ogrenci_bilgileri);
	        	break;
	        }
	        else if(tercih == 2) {
	        	ogrencileriListele(ogrenci_bilgileri);
	        	break;
	        }
	        else if(tercih == 3) {
	        	notGuncelle(ogrenci_bilgileri);
	        	break;
	        }
	        else {
	        	System.out.println("Hatalı bir seçim yaptınız. Lütfen tekrar deneyiniz.");
	        }
        }     
    }

    public static void yeniOgrenciKaydet(Ogrenci[] ogrenci_bilgileri) {
    	Scanner input = new Scanner(System.in);
        try {
            FileOutputStream file = new FileOutputStream("C:/Users/circi/eclipse-workspace/Ödev3/src/ogrenci_bilgileri.txt", true);
            PrintStream yaz = new PrintStream(file);

            System.out.print("Öğrencinin adını ve soyadını giriniz: ");
            String ad_soyad = input.nextLine();

            System.out.print("Öğrencinin numarasını giriniz: ");
            int numara = input.nextInt();

            System.out.print("Öğrencinin doğum yılını giriniz: ");
            int dogum_yili = input.nextInt();

            System.out.print("Öğrencinin not ortalamasını giriniz: ");
            int not_ortalamasi = input.nextInt();
            
            for(int i = 0; i<10;i++) {
            	if(ogrenci_bilgileri[i] == null) {
            		ogrenci_bilgileri[i] = new Ogrenci();
            		ogrenci_bilgileri[i].ad_soyad = ad_soyad;
            		ogrenci_bilgileri[i].ogrenci_no = numara;
            		ogrenci_bilgileri[i].dogum_yili = dogum_yili;
            		ogrenci_bilgileri[i].not_ortalaması = not_ortalamasi;
            		yaz.println(ad_soyad + ", " + numara + ", " + dogum_yili + ", " + not_ortalamasi);
            		break;
        		}
            }
            System.out.println("Öğrenci kaydedildi.");
        } catch (IOException e) {}
    }

    public static void ogrencileriListele(Ogrenci[] ogrenci_bilgileri) {
    	Scanner input = new Scanner(System.in);
        try {
            System.out.print("Listelemek istediğiniz harfi girin: ");
            char harf = input.nextLine().charAt(0);

            FileInputStream file = new FileInputStream("C:/Users/circi/eclipse-workspace/Ödev3/src/ogrenci_bilgileri.txt");
            Scanner arama = new Scanner(file);

            while (arama.hasNextLine()) {
                String satir = arama.nextLine();
                String[] ogrenciBilgileri = satir.split(", ");
                String adSoyad = ogrenciBilgileri[0];

                if (Character.toUpperCase(adSoyad.charAt(0)) == Character.toUpperCase(harf)) {
                    System.out.println(satir);
                }
            }
        } 
        catch (IOException e) {}
    }

    public static void notGuncelle(Ogrenci[] ogrenci_bilgileri) {
    	Scanner input = new Scanner(System.in);
        try {
        	File dosya = new File("C:/Users/circi/eclipse-workspace/Ödev3/src/ogrenci_bilgileri.txt");
        	
            System.out.print("Notunu güncellemek istediğiniz öğrencinin numarasını giriniz: ");
            int ogr_no = input.nextInt();

            System.out.print("Öğrencinin yeni notunu giriniz: ");
            int yeni_not = input.nextInt();
            
            String satir;
            BufferedReader reader = new BufferedReader(new FileReader(dosya));
            int a = 0;
            while ((satir = reader.readLine()) != null) {
                String[] satir_bilgisi = satir.split(", ");
                ogrenci_bilgileri[a] = new Ogrenci();
                ogrenci_bilgileri[a].ad_soyad = satir_bilgisi[0];
                ogrenci_bilgileri[a].ogrenci_no = Integer.parseInt(satir_bilgisi[1]);
                ogrenci_bilgileri[a].dogum_yili = Integer.parseInt(satir_bilgisi[2]);
                ogrenci_bilgileri[a].not_ortalaması = Integer.parseInt(satir_bilgisi[3]);
                a++;
            }
            boolean ogr_exist = false;
            for(int i = 0; i<10;i++) {
            	if(ogrenci_bilgileri[i] !=null && ogrenci_bilgileri[i].ogrenci_no == ogr_no) {
            		ogrenci_bilgileri[i].not_ortalaması = yeni_not;
            		ogr_exist  = true;
            	}
            }
            if(ogr_exist) {
            	System.out.println("Not güncellendi.");
            }
            else {
            	System.out.println("Belirtilen öğrenci numarasına ilişkin bir öğrenci bulunamadı.");
            }
            FileOutputStream file = new FileOutputStream("C:/Users/circi/eclipse-workspace/Ödev3/src/ogrenci_bilgileri.txt", false);
        	PrintStream yaz = new PrintStream(file);
            
            for(int i = 0; i<10;i++) {
            	if(ogrenci_bilgileri[i] != null) {
            		yaz.println(ogrenci_bilgileri[i].ad_soyad + ", " + ogrenci_bilgileri[i].ogrenci_no + ", " + ogrenci_bilgileri[i].dogum_yili + ", " + ogrenci_bilgileri[i].not_ortalaması);
            	}
            }     
        } catch (IOException e) {}
    }

}
