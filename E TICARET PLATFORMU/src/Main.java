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

class Siparis {
    private static int siparisNo = 0;
    private int no;
    private Date tarih;
    private HashMap<Urun, Integer> urunler;
    private double toplamTutar;
    private boolean tamamlandi;

    public Siparis() {
        siparisNo++;
        this.no = siparisNo;
        this.tarih = new Date();
        this.urunler = new HashMap<>();
        this.toplamTutar = 0;
        this.tamamlandi = false;
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

    public boolean isTamamlandi() {
        return tamamlandi;
    }

    public void urunEkle(Urun urun, int miktar) {
        if (!tamamlandi) {
            if (urun.getStok() >= miktar) {
                urunler.put(urun, miktar);
                toplamTutar += urun.getFiyat() * miktar;
                urun.stokAzalt(miktar);
            } else {
                System.out.println("Stok yetersiz. Sipariş eklenemedi.");
            }
        } else {
            System.out.println("Bu sipariş tamamlandı, ürün eklenemez.");
        }
    }

    public void siparisiTamamla() {
        if (!urunler.isEmpty()) {
            tamamlandi = true;
            System.out.println("Sipariş tamamlandı. Toplam tutar: " + toplamTutar);
        } else {
            System.out.println("Sipariş boş, tamamlanamadı.");
        }
    }
}

class Kullanici {
    private String kullaniciAdi;
    private String sifre;
    private String ad;
    private String soyad;
    private String adres;
    private String telefon;
    private ArrayList<Siparis> siparisler;

    public Kullanici(String kullaniciAdi, String sifre, String ad, String soyad, String adres, String telefon) {
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.ad = ad;
        this.soyad = soyad;
        this.adres = adres;
        this.telefon = telefon;
        this.siparisler = new ArrayList<>();
    }

    // Getter ve setter metotları

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getAdres() {
        return adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public ArrayList<Siparis> getSiparisler() {
        return siparisler;
    }

    public void siparisOlustur(Siparis siparis) {
        siparisler.add(siparis);
    }
}

class ECommercePlatformu {
    private ArrayList<Urun> urunler;
    private ArrayList<Kullanici> kullanicilar;

    public ECommercePlatformu() {
        urunler = new ArrayList<>();
        kullanicilar = new ArrayList<>();
    }

    public void urunEkle(Urun urun) {
        urunler.add(urun);
    }

    public void urunleriListele() {
        System.out.println("Platformdaki Ürünler:");
        for (Urun urun : urunler) {
            System.out.println("Kod: " + urun.getKod() + ", Ad: " + urun.getAd() + ", Fiyat: " + urun.getFiyat() + ", Stok: " + urun.getStok());
        }
    }

    public void kullaniciEkle(Kullanici kullanici) {
        kullanicilar.add(kullanici);
    }

    public Kullanici kullaniciGiris(String kullaniciAdi, String sifre) {
        for (Kullanici kullanici : kullanicilar) {
            if (kullanici.getKullaniciAdi().equals(kullaniciAdi) && kullanici.getSifre().equals(sifre)) {
                return kullanici;
            }
        }
        return null;
    }

    public void sepeteEkle(Kullanici kullanici, Urun urun, int miktar) {
        Siparis siparis = new Siparis();
        siparis.urunEkle(urun, miktar);
        kullanici.siparisOlustur(siparis);
        System.out.println("Ürün sepete eklendi.");
    }

    public void siparisiTamamla(Kullanici kullanici) {
        ArrayList<Siparis> siparisler = kullanici.getSiparisler();
        for (Siparis siparis : siparisler) {
            if (!siparis.isTamamlandi()) {
                siparis.siparisiTamamla();
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        // E-ticaret platformunu oluştur
        ECommercePlatformu platform = new ECommercePlatformu();

        // Örnek ürünler ekle
        Urun urun1 = new Urun("P001", "Laptop", 3000, 10);
        Urun urun2 = new Urun("P002", "Akıllı Telefon", 2000, 20);
        Urun urun3 = new Urun("P003", "Tablet", 1500, 15);

        platform.urunEkle(urun1);
        platform.urunEkle(urun2);
        platform.urunEkle(urun3);

        // Platformdaki ürünleri listele
        platform.urunleriListele();

        // Örnek kullanıcılar oluştur
        Kullanici kullanici1 = new Kullanici("ahmet", "1234", "Ahmet", "Yılmaz", "İstanbul", "5551234567");
        Kullanici kullanici2 = new Kullanici("mehmet", "5678", "Mehmet", "Demir", "Ankara", "5557654321");

        platform.kullaniciEkle(kullanici1);
        platform.kullaniciEkle(kullanici2);

        // Kullanıcı girişi yap
        Kullanici aktifKullanici = platform.kullaniciGiris("ahmet", "1234");

        if (aktifKullanici != null) {
            // Örnek alışveriş yap
            platform.sepeteEkle(aktifKullanici, urun1, 2);
            platform.sepeteEkle(aktifKullanici, urun2, 1);
            platform.siparisiTamamla(aktifKullanici);
        }
    }
}
    