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

    // Getter ve setter metotları

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

class Satis {
    private static int satisNo = 0;
    private int no;
    private Date tarih;
    private HashMap<Urun, Integer> urunler;
    private double toplamTutar;

    public Satis() {
        satisNo++;
        this.no = satisNo;
        this.tarih = new Date();
        this.urunler = new HashMap<>();
        this.toplamTutar = 0;
    }

    // Getter ve setter metotları

    public int getNo() {
        return no;
    }

    public Date getTarih() {
        return tarih;
    }

    public HashMap<Urun, Integer> getUrunler() {
        return urunler;
    }

    public double getToplamTutar() {
        return toplamTutar;
    }

    public void urunEkle(Urun urun, int miktar) {
        if (urun.getStok() >= miktar) {
            urunler.put(urun, miktar);
            toplamTutar += urun.getFiyat() * miktar;
            urun.stokAzalt(miktar);
        } else {
            System.out.println("Stok yetersiz. Satış yapılamadı.");
        }
    }

    public void satisTamamla() {
        System.out.println("Satış tamamlandı. Toplam tutar: " + toplamTutar);
    }
}

class Musteri {
    private String ad;
    private String soyad;
    private String telefon;

    public Musteri(String ad, String soyad, String telefon) {
        this.ad = ad;
        this.soyad = soyad;
        this.telefon = telefon;
    }

    // Getter ve setter metotları

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getTelefon() {
        return telefon;
    }
}

class Fatura {
    private static int faturaNo = 0;
    private int no;
    private Satis satis;
    private Musteri musteri;
    private double tutar;

    public Fatura(Satis satis, Musteri musteri) {
        faturaNo++;
        this.no = faturaNo;
        this.satis = satis;
        this.musteri = musteri;
        this.tutar = satis.getToplamTutar();
    }

    // Getter ve setter metotları

    public int getNo() {
        return no;
    }

    public Satis getSatis() {
        return satis;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public double getTutar() {
        return tutar;
    }

    public void faturaYazdir() {
        System.out.println("Fatura No: " + no);
        System.out.println("Satış Tarihi: " + satis.getTarih());
        System.out.println("Müşteri: " + musteri.getAd() + " " + musteri.getSoyad() + " - Telefon: " + musteri.getTelefon());
        System.out.println("Satılan Ürünler:");
        for (Map.Entry<Urun, Integer> entry : satis.getUrunler().entrySet()) {
            System.out.println(entry.getKey().getAd() + " - Adet: " + entry.getValue() + " - Fiyat: " + entry.getKey().getFiyat());
        }
        System.out.println("Toplam Tutar: " + tutar);
    }
}

class StokYonetimi {
    private HashMap<String, Urun> urunler;

    public StokYonetimi() {
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
        System.out.println("Stoktaki Ürünler:");
        for (Urun urun : urunler.values()) {
            System.out.println("Kod: " + urun.getKod() + ", Ad: " + urun.getAd() + ", Fiyat: " + urun.getFiyat() + ", Stok: " + urun.getStok());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // Stok yönetimi oluştur
        StokYonetimi stokYonetimi = new StokYonetimi();

        // Örnek ürünler ekle
        Urun urun1 = new Urun("P001", "Laptop", 3000, 10);
        Urun urun2 = new Urun("P002", "Akıllı Telefon", 2000, 20);
        Urun urun3 = new Urun("P003", "Tablet", 1500, 15);

        stokYonetimi.urunEkle(urun1);
        stokYonetimi.urunEkle(urun2);
        stokYonetimi.urunEkle(urun3);

        // Stoktaki ürünleri listele
        stokYonetimi.urunleriListele();

        // Örnek müşteri oluştur
        Musteri musteri = new Musteri("Ahmet", "Yılmaz", "5551234567");

        // Örnek satış oluştur
        Satis satis = new Satis();
        satis.urunEkle(urun1, 2);
        satis.urunEkle(urun2, 1);
        satis.satisTamamla();

        // Örnek fatura oluştur
        Fatura fatura = new Fatura(satis, musteri);
        fatura.faturaYazdir();

        // Stoktaki ürünleri tekrar listele
        stokYonetimi.urunleriListele();
    }
}
