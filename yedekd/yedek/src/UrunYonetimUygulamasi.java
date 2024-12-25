import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.io.File;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;

public class UrunYonetimUygulamasi {



    static ArrayList<Urun> urunListesi = new ArrayList<>();
    static ArrayList<Urun> sepetListesi = new ArrayList<>();
    static ArrayList<Urun> kullaniciUrunleri = new ArrayList<>();
    static ArrayList<Urun> satinAlinanUrunler = new ArrayList<>();
    static ArrayList<Kullanici> kullanicilar = new ArrayList<>(); // Kayıtlı kullanıcılar
    static String girisYapilanKullanici = null; // Giriş yapan kullanıcıyı takip et
    static int urunKodSayaci = 10000; // 5 haneli ürün kodları için başlangıç

    public static void main(String[] args) {

FlatIntelliJLaf.setup();
varsayilanUrunleriEkle();
        girisSayfasiniGoster();
    }

    public static void varsayilanUrunleriEkle() {
        urunListesi.add(new Urun(urunKoduOlustur(), "Zara", "Bere", "199 TL", "lavinyakids", "https://www.kaft.com/static/images/cache/1200/bere_spleetentar_23371_1200_1200.jpg?cacheID=1698827162000"));
        urunListesi.add(new Urun(urunKoduOlustur(), "Zara", "Bere", "199 TL", "lavinyakids", "http://example.com/bere.jpg"));
        urunListesi.add(new Urun(urunKoduOlustur(), "Diğer", "Kedi Tuvaleti", "249 TL", "thomvejerrypetshop", "https://example.com/kedi_tuvaleti.jpg"));
        urunListesi.add(new Urun(urunKoduOlustur(), "Diğer", "Kolye", "499 TL", "egemeltemi", "https://example.com/kolye.jpg"));
    }

    public static String urunKoduOlustur() {
        return String.valueOf(urunKodSayaci++);
    }

    public static void girisSayfasiniGoster() {
        JFrame girisCercevesi = new JFrame("Giriş Yap");
        girisCercevesi.setSize(400, 300);
        girisCercevesi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        girisCercevesi.setLayout(new GridLayout(4, 2));

        JLabel epostaLabel = new JLabel("Email:");
        JTextField epostaAlani = new JTextField();
        JLabel sifreLabel = new JLabel("Şifre:");
        JPasswordField sifreAlani = new JPasswordField();

        JButton girisYapButonu = new JButton("Giriş Yap");
        JButton kayitOlButonu = new JButton("Üye Ol");

        girisCercevesi.add(epostaLabel);
        girisCercevesi.add(epostaAlani);
        girisCercevesi.add(sifreLabel);
        girisCercevesi.add(sifreAlani);
        girisCercevesi.add(girisYapButonu);
        girisCercevesi.add(kayitOlButonu);

        girisYapButonu.addActionListener(e -> {
            String eposta = epostaAlani.getText();
            String sifre = new String(sifreAlani.getPassword());

            boolean dogrulamaBasarili = false;
            for (Kullanici kullanici : kullanicilar) {
                if (kullanici.getEposta().equals(eposta) && kullanici.getSifre().equals(sifre)) {
                    girisYapilanKullanici = eposta;
                    dogrulamaBasarili = true;
                    break;
                }
            }

            if (dogrulamaBasarili) {
                JOptionPane.showMessageDialog(girisCercevesi, "Giriş başarılı!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                girisCercevesi.dispose();
                anaSayfayiGoster();
            } else {
                JOptionPane.showMessageDialog(girisCercevesi, "Email veya şifre hatalı!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        kayitOlButonu.addActionListener(e -> {
            girisCercevesi.dispose();
            kayitSayfasiniGoster();
        });

        girisCercevesi.setVisible(true);
    }

    public static void kayitSayfasiniGoster() {
        JFrame kayitCercevesi = new JFrame("Üye Ol");
        kayitCercevesi.setSize(400, 400);
        kayitCercevesi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kayitCercevesi.setLayout(new GridLayout(5, 2));

        JLabel adSoyadLabel = new JLabel("Ad Soyad:");
        JTextField adSoyadAlani = new JTextField();
        JLabel epostaLabel = new JLabel("Email:");
        JTextField epostaAlani = new JTextField();
        JLabel sifreLabel = new JLabel("Şifre:");
        JPasswordField sifreAlani = new JPasswordField();
        JLabel tcLabel = new JLabel("TC Kimlik No:");
        JTextField tcAlani = new JTextField();

        JButton kayitOlButonu = new JButton("Üye Ol");

        kayitCercevesi.add(adSoyadLabel);
        kayitCercevesi.add(adSoyadAlani);
        kayitCercevesi.add(epostaLabel);
        kayitCercevesi.add(epostaAlani);
        kayitCercevesi.add(sifreLabel);
        kayitCercevesi.add(sifreAlani);
        kayitCercevesi.add(tcLabel);
        kayitCercevesi.add(tcAlani);
        kayitCercevesi.add(new JLabel());
        kayitCercevesi.add(kayitOlButonu);

        kayitOlButonu.addActionListener(e -> {
            String adSoyad = adSoyadAlani.getText();
            String eposta = epostaAlani.getText();
            String sifre = new String(sifreAlani.getPassword());
            String tc = tcAlani.getText();

            /*if (tc.length() != 11 || !tc.matches("\\d+")) {
                JOptionPane.showMessageDialog(kayitCercevesi, "TC Kimlik numarası 11 haneli ve sadece rakamlardan oluşmalıdır!", "Hata", JOptionPane.ERROR_MESSAGE);
                return;
            }*/

            kullanicilar.add(new Kullanici(adSoyad, eposta, sifre, tc));
            JOptionPane.showMessageDialog(kayitCercevesi, "Kayıt başarılı!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            kayitCercevesi.dispose();
            girisSayfasiniGoster();
        });

        kayitCercevesi.setVisible(true);
    }

    public static void anaSayfayiGoster() {
        JFrame anaCerceve = new JFrame("Anasayfa");
        anaCerceve.setSize(900, 600);
        anaCerceve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        anaCerceve.setLayout(new BorderLayout());

        // Üst Panel
        JPanel ustPaneli = new JPanel();
        ustPaneli.setLayout(new FlowLayout());
        JButton anaSayfaButonu = new JButton("Anasayfa");
        JButton benimUrunlerimButonu = new JButton("Satıştaki Ürünlerim");
        JButton sepetButonu = new JButton("Sepetim");
        JButton urunSatButonu = new JButton("Ürün Sat");
        JButton satinAldiklarimButonu = new JButton("Satın Aldıklarım");

        ustPaneli.add(anaSayfaButonu);
        ustPaneli.add(benimUrunlerimButonu);
        ustPaneli.add(sepetButonu);
        ustPaneli.add(urunSatButonu);
        ustPaneli.add(satinAldiklarimButonu);
        anaCerceve.add(ustPaneli, BorderLayout.NORTH);

        // Ana İçerik Paneli
        JPanel icerikPaneli = new JPanel(new BorderLayout());
        anaCerceve.add(icerikPaneli, BorderLayout.CENTER);

        // Anasayfa için Ürün Paneli
        JPanel urunPaneli = new JPanel();
        urunPaneli.setLayout(new GridLayout(2, 3, 10, 10));
        urunPaneliniGuncelle(urunPaneli, urunListesi, "anasayfa");

        JScrollPane urunKaydirmaPane = new JScrollPane(urunPaneli);
        icerikPaneli.add(urunKaydirmaPane, BorderLayout.CENTER);

        // Arama Çubuğu
        JPanel aramaPaneli = new JPanel();
        aramaPaneli.setLayout(new FlowLayout());
        JTextField aramaAlani = new JTextField(10);
        JButton aramaButonu = new JButton("Ara");
        aramaPaneli.add(new JLabel("Ürün Kodu: "));
        aramaPaneli.add(aramaAlani);
        aramaPaneli.add(aramaButonu);
        icerikPaneli.add(aramaPaneli, BorderLayout.NORTH);

        // Olay Dinleyiciler
        anaSayfaButonu.addActionListener(e -> {
            urunPaneli.setName("anasayfa");
            urunPaneliniGuncelle(urunPaneli, urunListesi, "anasayfa");
        });

        benimUrunlerimButonu.addActionListener(e -> urunPaneliniGuncelle(urunPaneli, kullaniciUrunleri, "benimUrunlerim"));

        sepetButonu.addActionListener(e -> {
            urunPaneli.setName("sepet");
            urunPaneliniGuncelle(urunPaneli, sepetListesi, "sepet");
        });

        satinAldiklarimButonu.addActionListener(e -> urunPaneliniGuncelle(urunPaneli, satinAlinanUrunler, "satinAlinanUrunler"));

        urunSatButonu.addActionListener(e -> urunSatSayfasiniGoster());

        aramaButonu.addActionListener(e -> {
            String aramaKodu = aramaAlani.getText();
            if (!aramaKodu.isEmpty()) {
                ArrayList<Urun> aramaSonuclari = new ArrayList<>();
                for (Urun urun : urunListesi) {
                    if (urun.getUrunKodu().equals(aramaKodu)) {
                        aramaSonuclari.add(urun);
                        break;
                    }
                }
                urunPaneliniGuncelle(urunPaneli, aramaSonuclari, "aramaSonuclari");
            } else {
                JOptionPane.showMessageDialog(anaCerceve, "Lütfen bir ürün kodu giriniz!", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        });

        anaCerceve.setVisible(true);
    }

    public static void urunSatSayfasiniGoster() {
        JFrame urunSatCercevesi = new JFrame("Ürün Sat");
        urunSatCercevesi.setSize(400, 400);
        urunSatCercevesi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        urunSatCercevesi.setLayout(new GridLayout(6, 2));

        JLabel baslikLabel = new JLabel("Ürün Başlığı:");
        JTextField baslikAlani = new JTextField();
        JLabel turLabel = new JLabel("Ürün Türü:");
        JTextField turAlani = new JTextField();
        JLabel fiyatLabel = new JLabel("Fiyat:");
        JTextField fiyatAlani = new JTextField();
        JLabel saticiLabel = new JLabel("Satıcı:");
        JTextField saticiAlani = new JTextField(girisYapilanKullanici);
        saticiAlani.setEditable(false);
        JLabel fotoLabel = new JLabel("Fotoğraf (Dosya):");

        // Dosya seçme butonu
        JButton dosyaSecButonu = new JButton("Dosya Seç");
        JTextField fotoAlani = new JTextField();
        fotoAlani.setEditable(false);

        JButton ekleButonu = new JButton("Ekle");

        urunSatCercevesi.add(baslikLabel);
        urunSatCercevesi.add(baslikAlani);
        urunSatCercevesi.add(turLabel);
        urunSatCercevesi.add(turAlani);
        urunSatCercevesi.add(fiyatLabel);
        urunSatCercevesi.add(fiyatAlani);
        urunSatCercevesi.add(saticiLabel);
        urunSatCercevesi.add(saticiAlani);
        urunSatCercevesi.add(fotoLabel);
        urunSatCercevesi.add(fotoAlani);
        urunSatCercevesi.add(dosyaSecButonu);
        urunSatCercevesi.add(ekleButonu);

        dosyaSecButonu.addActionListener(e -> {
            JFileChooser dosyaSecici = new JFileChooser();
            int sonuc = dosyaSecici.showOpenDialog(null);
            if (sonuc == JFileChooser.APPROVE_OPTION) {
                File dosya = dosyaSecici.getSelectedFile();
                fotoAlani.setText(dosya.getAbsolutePath());
            }
        });

        ekleButonu.addActionListener(e -> {
            String baslik = baslikAlani.getText();
            String tur = turAlani.getText();
            String fiyat = fiyatAlani.getText();
            String foto = fotoAlani.getText();

            if (baslik.isEmpty() || tur.isEmpty() || fiyat.isEmpty() || foto.isEmpty()) {
                JOptionPane.showMessageDialog(urunSatCercevesi, "Lütfen tüm alanları doldurun.", "Hata", JOptionPane.ERROR_MESSAGE);
            } else {
                Urun yeniUrun = new Urun(urunKoduOlustur(), tur, baslik, fiyat, saticiAlani.getText(), foto);
                kullaniciUrunleri.add(yeniUrun);
                urunListesi.add(yeniUrun);
                JOptionPane.showMessageDialog(urunSatCercevesi, "Ürün başarıyla eklendi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                urunSatCercevesi.dispose();
            }
        });

        urunSatCercevesi.setVisible(true);
    }
    public static void urunPaneliniGuncelle(JPanel urunPaneli, ArrayList<Urun> urunler, String gorunumTuru) {
        urunPaneli.removeAll(); // Paneli sıfırla

        for (Urun urun : urunler) {
            // Yeni bir ürün kartı paneli oluşturuyoruz
            JPanel urunKarti = new JPanel();
            urunKarti.setLayout(new BoxLayout(urunKarti, BoxLayout.Y_AXIS));
            urunKarti.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            urunKarti.setBackground(Color.WHITE);

            // Fotoğrafı 100x100 boyutunda ayarlıyoruz
            ImageIcon originalIcon = new ImageIcon(urun.getFoto());
            Image image = originalIcon.getImage().getScaledInstance(300, 150, Image.SCALE_SMOOTH); // Fotoğrafı 300x150 boyutuna getiriyoruz
            JLabel fotoLabel = new JLabel(new ImageIcon(image)); // Ürün fotoğrafı

            // Ürün bilgilerini ekliyoruz
            JLabel kodLabel = new JLabel("Ürün Kodu: " + urun.getUrunKodu()); // Ürün kodu
            JLabel isimLabel = new JLabel("Başlık: " + urun.getBaslik()); // Ürün başlığı
            JLabel turLabel = new JLabel("Tür: " + urun.getTur()); // Ürün türü
            JLabel fiyatLabel = new JLabel("Fiyat: " + urun.getFiyat()); // Ürün fiyatı
            JLabel saticiLabel = new JLabel("Satıcı: " + urun.getSatici()); // Satıcı ismi

            // "Ürünü İncele" butonunu ekliyoruz
            JButton detayButonu = new JButton("Ürünü İncele");
            detayButonu.addActionListener(e -> urunDetaylariniGoster(urun));

            // Butonları görünüm türüne göre düzenliyoruz
            if ("anasayfa".equals(gorunumTuru)) {
                // Anasayfa görünümünde: "Sepete Ekle" ve "Ürünü İncele"
                JButton sepeteEkleButonu = new JButton("Sepete Ekle");
                sepeteEkleButonu.addActionListener(e -> {
                    sepetListesi.add(urun); // Ürünü sepete ekle
                    JOptionPane.showMessageDialog(urunKarti, "Ürün sepete eklendi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                });

                urunKarti.add(sepeteEkleButonu); // Sepete Ekle butonunu ekle
            } else if ("sepet".equals(gorunumTuru)) {
                // Sepetim görünümünde: "Sepetten Sil", "Satın Al" ve "Ürünü İncele"
                JButton silButonu = new JButton("Sepetten Sil");
                JButton satinAlButonu = new JButton("Satın Al");

                // "Sepetten Sil" butonu işlevi
                silButonu.addActionListener(e -> {
                    sepetListesi.remove(urun); // Ürünü sepetten kaldır
                    urunPaneliniGuncelle(urunPaneli, sepetListesi, "sepet"); // Sepeti güncelle
                    JOptionPane.showMessageDialog(urunKarti, "Ürün sepetten silindi!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                });


                // "Satın Al" butonu işlevi
                satinAlButonu.addActionListener(e -> {
                    satinAlinanUrunler.add(urun); // Ürünü satın alınanlar listesine ekle
                    sepetListesi.remove(urun); // Ürünü sepetten kaldır
                    urunPaneliniGuncelle(urunPaneli, sepetListesi, "sepet"); // Sepeti güncelle
                    JOptionPane.showMessageDialog(urunKarti, "Ürün satın alındı!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                });

                urunKarti.add(silButonu); // Sepetten Sil butonunu ekle
                urunKarti.add(satinAlButonu); // Satın Al butonunu ekle
            }
            if ("benimUrunlerim".equals(gorunumTuru)) {
                // Benim Ürünlerim görünümünde: "Satıştan Kaldır" ve "Ürünü İncele"
                JButton silButonu = new JButton("Satıştan Kaldır");
                silButonu.addActionListener(e -> {
                    // Ürünü hem kullanıcının ürünlerinden hem de genel listeden kaldır
                    kullaniciUrunleri.remove(urun);
                    urunListesi.remove(urun);
                    urunPaneliniGuncelle(urunPaneli, kullaniciUrunleri, "benimUrunlerim");
                    JOptionPane.showMessageDialog(urunKarti, "Ürün satıştan kaldırıldı!", "Bilgi", JOptionPane.INFORMATION_MESSAGE);
                });

                urunKarti.add(silButonu);
            }

            // Ürün bilgilerini ekliyoruz
            urunKarti.add(fotoLabel);
            urunKarti.add(kodLabel);
            urunKarti.add(isimLabel);
            urunKarti.add(turLabel);
            urunKarti.add(fiyatLabel);
            urunKarti.add(saticiLabel);
            urunKarti.add(detayButonu); // "Ürünü İncele" butonunu ekle

            urunPaneli.add(urunKarti); // Ürün kartını panele ekle
        }

        urunPaneli.revalidate(); // Paneli yeniden düzenle
        urunPaneli.repaint(); // Paneli yeniden çiz
    }


    public static void urunDetaylariniGoster(Urun urun) {
        // Ürün detaylarını gösterecek yeni bir pencere oluşturuyoruz
        JFrame detayPenceresi = new JFrame("Ürün Detayı");
        detayPenceresi.setSize(400, 400);
        detayPenceresi.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Pencereyi kapatma işlemi
        detayPenceresi.setLayout(new BorderLayout());

        // Ürün detayları için panel oluşturuyoruz
        JPanel detayPaneli = new JPanel();
        detayPaneli.setLayout(new BoxLayout(detayPaneli, BoxLayout.Y_AXIS));

        // Ürün fotoğrafı, başlık, fiyat vb. bilgileri ekliyoruz
        JLabel fotoLabel = new JLabel(new ImageIcon(urun.getFoto()));
        JLabel kodLabel = new JLabel("Ürün Kodu: " + urun.getUrunKodu());
        JLabel isimLabel = new JLabel("Başlık: " + urun.getBaslik());
        JLabel turLabel = new JLabel("Tür: " + urun.getTur());
        JLabel fiyatLabel = new JLabel("Fiyat: " + urun.getFiyat());
        JLabel saticiLabel = new JLabel("Satıcı: " + urun.getSatici());

        // Soru sormak için bir buton ekliyoruz (isteğe bağlı)
        JButton soruSorButonu = new JButton("Soru Sor");
        soruSorButonu.addActionListener(e -> {
            String soru = JOptionPane.showInputDialog(detayPenceresi, "Sorunuzu yazınız:", "Soru Sor", JOptionPane.PLAIN_MESSAGE);
            if (soru != null && !soru.isEmpty()) {
                JOptionPane.showMessageDialog(detayPenceresi, "Sorunuz iletildi: " + soru, "Bilgi", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Detayları panelde gösteriyoruz
        detayPaneli.add(fotoLabel);
        detayPaneli.add(kodLabel);
        detayPaneli.add(isimLabel);
        detayPaneli.add(turLabel);
        detayPaneli.add(fiyatLabel);
        detayPaneli.add(saticiLabel);
        detayPaneli.add(soruSorButonu);

        detayPenceresi.add(detayPaneli, BorderLayout.CENTER); // Paneli pencereye ekle
        detayPenceresi.setVisible(true); // Pencereyi görünür yap
    }

    static class Urun {
        private String urunKodu;
        private String baslik;
        private String tur;
        private String fiyat;
        private String satici;
        private String foto;

        public Urun(String urunKodu, String baslik, String tur, String fiyat, String satici, String foto) {
            this.urunKodu = urunKodu;
            this.baslik = baslik;
            this.tur = tur;
            this.fiyat = fiyat;
            this.satici = satici;
            this.foto = foto;
        }

        public String getUrunKodu() {
            return urunKodu;
        }

        public String getBaslik() {
            return baslik;
        }

        public String getTur() {
            return tur;
        }

        public String getFiyat() {
            return fiyat;
        }

        public String getSatici() {
            return satici;
        }

        public String getFoto() {
            return foto;
        }
    }

    static class Kullanici {
        private String isim;
        private String eposta;
        private String sifre;
        private String tc;

        public Kullanici(String isim, String eposta, String sifre, String tc) {
            this.isim = isim;
            this.eposta = eposta;
            this.sifre = sifre;
            this.tc = tc;
        }

        public String getIsim() {
            return isim;
        }

        public String getEposta() {
            return eposta;
        }

        public String getSifre() {
            return sifre;
        }

        public String getTc() {
            return tc;
        }
    }}

