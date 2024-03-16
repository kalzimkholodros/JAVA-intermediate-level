import java.util.*;

class Urun {
    private String kod;
    private String ad;
    private double fiyat;
    private int stok;

    public Urun(String kod, String ad, double fiyat, int stok) {
        this.kod = kod;
        this.ad = ad;
        this.fiyat = fiyat;
        this.stok = stok;
    }

    public String getKod() {
        return kod;
    }

    public String getAd() {
        return ad;
    }

    public double getFiyat() {
        return fiyat;
    }

    public int getStok() {
        return stok;
    }

    public void stokArtir(int miktar) {
        stok += miktar;
    }

    public void stokAzalt(int miktar) {
        if (miktar <= stok) {
            stok -= miktar;
        } else {
            System.out.println("Stok yetersiz.");
        }
    }
}

class OtomasyonSistemi {
    private HashMap<String, Urun> urunler;

    public OtomasyonSistemi() {
        urunler = new HashMap<>();
    }

    public void urunEkle(Urun urun) {
        urunler.put(urun.getKod(), urun);
    }

    public void urunSil(String kod) {
        if (urunler.containsKey(kod)) {
            urunler.remove(kod);
        } else {
            System.out.println("Ürün bulunamadı.");
        }
    }

    public void urunleriListele() {
        System.out.println("Mevcut Ürünler:");
        for (Urun urun : urunler.values()) {
            System.out.println("Kod: " + urun.getKod() + ", Ad: " + urun.getAd() + ", Fiyat: " + urun.getFiyat() + ", Stok: " + urun.getStok());
        }
    }

    public HashMap<String, Urun> getUrunler() {
        return urunler;
    }
}

public class Main {
    public static void main(String[] args) {
        OtomasyonSistemi otomasyonSistemi = new OtomasyonSistemi();

        // Örnek ürünler oluştur
        Urun urun1 = new Urun("P001", "Laptop", 3000, 10);
        Urun urun2 = new Urun("P002", "Akıllı Telefon", 2000, 20);
        Urun urun3 = new Urun("P003", "Tablet", 1500, 15);

        // Ürünleri ekleyin
        otomasyonSistemi.urunEkle(urun1);
        otomasyonSistemi.urunEkle(urun2);
        otomasyonSistemi.urunEkle(urun3);

        // Ürünleri listele
        otomasyonSistemi.urunleriListele();

        // Stok güncelleme örneği
        otomasyonSistemi.getUrunler().get("P001").stokArtir(5);
        otomasyonSistemi.getUrunler().get("P002").stokAzalt(3);

        // Ürünleri tekrar listele
        otomasyonSistemi.urunleriListele();
    }
}
