import java.util.*;

class Ogrenci {
    private String ad;
    private String soyad;
    private String numara;
    private HashMap<String, Integer> notlar;

    public Ogrenci(String ad, String soyad, String numara) {
        this.ad = ad;
        this.soyad = soyad;
        this.numara = numara;
        this.notlar = new HashMap<>();
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getNumara() {
        return numara;
    }

    public void notEkle(String ders, int not) {
        notlar.put(ders, not);
    }

    public HashMap<String, Integer> getNotlar() {
        return notlar;
    }
}

class NotYonetimSistemi {
    private HashMap<String, Ogrenci> ogrenciler;

    public NotYonetimSistemi() {
        ogrenciler = new HashMap<>();
    }

    public void ogrenciEkle(Ogrenci ogrenci) {
        ogrenciler.put(ogrenci.getNumara(), ogrenci);
    }

    public void notGir(String numara, String ders, int not) {
        if (ogrenciler.containsKey(numara)) {
            Ogrenci ogrenci = ogrenciler.get(numara);
            ogrenci.notEkle(ders, not);
        } else {
            System.out.println("Öğrenci bulunamadı.");
        }
    }

    public void ogrenciRaporu(String numara) {
        if (ogrenciler.containsKey(numara)) {
            Ogrenci ogrenci = ogrenciler.get(numara);
            System.out.println("Öğrenci: " + ogrenci.getAd() + " " + ogrenci.getSoyad());
            System.out.println("Numara: " + ogrenci.getNumara());
            System.out.println("Notlar:");
            HashMap<String, Integer> notlar = ogrenci.getNotlar();
            for (Map.Entry<String, Integer> entry : notlar.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Öğrenci bulunamadı.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        NotYonetimSistemi notSistemi = new NotYonetimSistemi();

        Ogrenci ogrenci1 = new Ogrenci("Ahmet", "Yılmaz", "12345");
        Ogrenci ogrenci2 = new Ogrenci("Ayşe", "Demir", "54321");

        notSistemi.ogrenciEkle(ogrenci1);
        notSistemi.ogrenciEkle(ogrenci2);

        notSistemi.notGir("12345", "Matematik", 85);
        notSistemi.notGir("12345", "Fizik", 90);
        notSistemi.notGir("54321", "Matematik", 75);
        notSistemi.notGir("54321", "Fizik", 80);

        notSistemi.ogrenciRaporu("12345");
        System.out.println();
        notSistemi.ogrenciRaporu("54321");
    }
}
