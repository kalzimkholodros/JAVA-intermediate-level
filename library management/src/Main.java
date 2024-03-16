import java.util.*;

class Kitap {
    private String ISBN;
    private String ad;
    private String yazar;
    private boolean oduncAlindiMi;

    public Kitap(String ISBN, String ad, String yazar) {
        this.ISBN = ISBN;
        this.ad = ad;
        this.yazar = yazar;
        this.oduncAlindiMi = false;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getAd() {
        return ad;
    }

    public String getYazar() {
        return yazar;
    }

    public boolean isOduncAlindiMi() {
        return oduncAlindiMi;
    }

    public void oduncAl() {
        oduncAlindiMi = true;
    }

    public void iadeEt() {
        oduncAlindiMi = false;
    }
}

class Kutuphane {
    private HashMap<String, Kitap> kitaplar;

    public Kutuphane() {
        kitaplar = new HashMap<>();
    }

    public void kitapEkle(Kitap kitap) {
        kitaplar.put(kitap.getISBN(), kitap);
    }

    public void kitapSil(String ISBN) {
        if (kitaplar.containsKey(ISBN)) {
            kitaplar.remove(ISBN);
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public void kitaplariListele() {
        System.out.println("Kütüphanedeki Kitaplar:");
        for (Kitap kitap : kitaplar.values()) {
            System.out.println("ISBN: " + kitap.getISBN() + ", Ad: " + kitap.getAd() + ", Yazar: " + kitap.getYazar() + ", Durum: " + (kitap.isOduncAlindiMi() ? "Ödünç Alındı" : "Müsait"));
        }
    }

    public void kitapOduncAl(String ISBN) {
        if (kitaplar.containsKey(ISBN)) {
            Kitap kitap = kitaplar.get(ISBN);
            if (!kitap.isOduncAlindiMi()) {
                kitap.oduncAl();
                System.out.println("Kitap ödünç alındı.");
            } else {
                System.out.println("Kitap zaten ödünç alınmış.");
            }
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }

    public void kitapIadeEt(String ISBN) {
        if (kitaplar.containsKey(ISBN)) {
            Kitap kitap = kitaplar.get(ISBN);
            if (kitap.isOduncAlindiMi()) {
                kitap.iadeEt();
                System.out.println("Kitap iade edildi.");
            } else {
                System.out.println("Bu kitap zaten kütüphanede.");
            }
        } else {
            System.out.println("Kitap bulunamadı.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Kutuphane kutuphane = new Kutuphane();

        // Örnek kitaplar oluştur
        Kitap kitap1 = new Kitap("978-0544003415", "Harry Potter and the Sorcerer's Stone", "J.K. Rowling");
        Kitap kitap2 = new Kitap("978-0141441153", "1984", "George Orwell");
        Kitap kitap3 = new Kitap("978-0446310789", "To Kill a Mockingbird", "Harper Lee");

        // Kitapları kütüphaneye ekle
        kutuphane.kitapEkle(kitap1);
        kutuphane.kitapEkle(kitap2);
        kutuphane.kitapEkle(kitap3);

        // Kitapları listele
        kutuphane.kitaplariListele();

        // Kitap ödünç al
        kutuphane.kitapOduncAl("978-0544003415");

        // Kitapları tekrar listele
        kutuphane.kitaplariListele();

        // Kitabı iade et
        kutuphane.kitapIadeEt("978-0544003415");

        // Kitapları tekrar listele
        kutuphane.kitaplariListele();
    }
}
