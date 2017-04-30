import java.util.Scanner;
import java.util.ArrayList;
public class App {
    public static Scanner scan;
    public static Scanner menu = new Scanner(System.in);
    //Uygulamanın listeleri
    public static ArrayList<User> userListesi = new ArrayList<User>();
    public static ArrayList<Song> songListesi = new ArrayList<Song>();
    public static ArrayList<Album> albumListesi = new ArrayList<Album>();
    public static ArrayList<Singer> singerListesi = new ArrayList<Singer>();
    //sistemde kayıtlı olan kullanıcı
    public static User mevcutKullanici = new User();
    public static void main( String[] args ) {
        System.out.println("Hosgeldiniz");
        dataOlusturma();
        while (true) {
            menuyuGoruntule();
            System.out.println("Seciminizi Yapiniz: ");
            int secim = menu.nextInt();

            switch (secim) {
                case 1:
                    kullaniciEkle();
                    break;
                case 2:
                    girisYapma();
                    break;
                case 3:
                    sarkiListele();
                    break;
                case 4:
                    sarkiciListele();
                    break;
                case 5:
                    albumListele();
                    break;
                case 6:
                    playlistOlusturma();
                    break;
                case 7:
                    playlistListele();
                    break;
                case 8:
                    playlisteSarkiEkle();
                    break;
                case 9:
                    System.out.println("Program sonlaniyor.");
                    menu.close();
                    System.exit(0);
                    break;
            }
        }
    }
    //menüyü kullanıcıya listeleyen fonksiyon
    private static void menuyuGoruntule(){
        System.out.println("**************");
        System.out.println("*****Menu*****");
        System.out.println("1-Kullanici Ekleme");
        System.out.println("2-Sisteme Giris Yapma");
        System.out.println("3-Sarki Listele");
        System.out.println("4-Sarkici Listele");
        System.out.println("5-Album Listele");
        System.out.println("6-Playlist Olustur");
        System.out.println("7-Playlist Listele");
        System.out.println("8-Playliste Sarki Ekle");
        System.out.println("9-Programdan Cik");

    }
    //kullanıcı ekleyen fonksiyon
    public static void kullaniciEkle(){
        scan = new Scanner(System.in);
        System.out.println("Kullanici Adinizi Giriniz: ");
        String kullaniciAdi = scan.nextLine();

        System.out.println("Parolanizi Giriniz: ");
        String parola = scan.nextLine();

        mevcutKullanici.userName = kullaniciAdi;
        mevcutKullanici.password = parola;
        System.out.println(kullaniciAdi + " isimli kullanici eklendi.");

        userListesi.add(mevcutKullanici);
    }

    public static void girisYapma(){
        scan = new Scanner(System.in);
        System.out.println("Kullanici Adinizi Giriniz: ");
        String kullaniciAdi = scan.nextLine();

        System.out.println("Parolanizi Giriniz: ");
        String parola = scan.nextLine();
        boolean kontrol = false;
        for(User i: userListesi ){
            if(i.userName.equals(kullaniciAdi) && i.password.equals(parola)) {
                kontrol = true;
            }
        }
        if(kontrol == false){
            System.out.println("Yanlis girdiniz. Tekrar deneyiniz");
        }
        else{
            System.out.println("Giris yapildi");
            kontrol = false;
        }
    }

    //şarkıcı listeleyen fonksiyon
    public static void sarkiciListele(){
        for(Singer i: singerListesi ){
            System.out.println(i.name);
            for(Album j: i.albumList){
                System.out.println(j.name);
            }
        }
    }
    //album listeleyen fonksiyon
    public static void albumListele(){
        for(Album i: albumListesi ){
            System.out.println(i.name);
        }
    }
    //şarkı listeyleyen fonksiyon
    public static void sarkiListele(){
        for(Song i: songListesi ){
            System.out.println(i.name + " - " + i.singer.name + " - " + i.duration + " dk. ");
            System.out.println("url: " + i.urlYoutube);
        }
    }
    //playlist oluşturan fonksiyon
    public static void playlistOlusturma(){
        //kullanıcıdan playlistinin adını alma
        Scanner playlist = new Scanner(System.in);
        System.out.println("Playlistinizin adi: ");
        String playlistName = playlist.nextLine();
        Playlist newPlaylist = new Playlist();
        mevcutKullanici.playlist = newPlaylist;
        newPlaylist.name = playlistName;
        //kullanicidan playliste eklenecek şarkının ismini alma
        Scanner sarki = new Scanner(System.in);
        System.out.println("Eklemek istediginiz sarkiyi giriniz: ");
        String sarkiName = sarki.nextLine();
        boolean sarkiciBulundu = false;
        for(Song i: songListesi){
            if (i.name.toLowerCase().equals(sarkiName.toLowerCase())){
                newPlaylist.songList.add(i);
                sarkiciBulundu = true;
                System.out.println("Aradiginiz sarkici listeye eklendi");
                break;
            }
        }
        if(sarkiciBulundu == false) {
            System.out.println("Aradiginiz sarkici bulunamadi");
        }
        else {
            sarkiciBulundu = false;
        }
    }
    //mevcut playliste sarkı ekleyen fonksiyon
    public static void playlisteSarkiEkle(){
        if(mevcutKullanici.playlist.name == null){
            System.out.println("Playlistiniz bulunmamamktadir önce playlist olusturunuz.");
        }
        else {
            System.out.println("Mevcut playlistiniz: " + mevcutKullanici.playlist.name);
            Scanner sarki = new Scanner(System.in);
            System.out.println("Eklemek istediginiz sarkiyi giriniz: ");
            String sarkiName = sarki.nextLine();
            for(Song i: songListesi){
                if (i.name.equals(sarkiName)){
                    mevcutKullanici.playlist.songList.add(i);
                    break;
                }
            }
        }
    }
    //playlistin içindeki şarkıları listeleyen fonksiyon
    public static void playlistListele(){
        System.out.println("Playlistinizin adi : " + mevcutKullanici.playlist.name);
        for(Song i: mevcutKullanici.playlist.songList ){
            System.out.println(i.name + " - " + i.singer.name );
        }
    }
    //Test datası oluşturan fonksiyon
    public static void dataOlusturma(){
        Singer newSinger = new Singer();
        newSinger.name = "Tarkan";
        singerListesi.add(newSinger);
        Song newSong = new Song();
        newSong.name = "Dudu";
        newSong.duration = 3;
        newSong.urlYoutube = "www.deneme.com";
        newSong.singer = newSinger;
        songListesi.add(newSong);
        Song newSong1 = new Song();
        newSong1.name = "Bu Sarkilar da Olmasa";
        newSong1.duration = 3;
        newSong1.urlYoutube = "www.deneme.com";
        newSong1.singer = newSinger;
        songListesi.add(newSong1);
        Song newSong2 = new Song();
        newSong2.name = "Gulumse Kaderine";
        newSong2.duration = 3;
        newSong2.urlYoutube = "www.deneme.com";
        newSong2.singer = newSinger;
        songListesi.add(newSong2);
        Song newSong3 = new Song();
        newSong3.name = "Sorma Kalbim";
        newSong3.duration = 3;
        newSong3.urlYoutube = "www.deneme.com";
        newSong3.singer = newSinger;
        songListesi.add(newSong3);
        Song newSong4 = new Song();
        newSong4.name = "Uzun Ince Bir Yoldayim";
        newSong4.duration = 3;
        newSong4.urlYoutube = "www.deneme.com";
        newSong4.singer = newSinger;
        songListesi.add(newSong4);
        Album newAlbum = new Album();
        newAlbum.name = "Dudu";
        albumListesi.add(newAlbum);
        newAlbum.songList.add(newSong);
        newAlbum.songList.add(newSong1);
        newAlbum.songList.add(newSong2);
        newAlbum.songList.add(newSong3);
        newAlbum.songList.add(newSong4);
        newSinger.albumList.add(newAlbum);
        User testUser = new User();
        testUser.userName = "Havva";
        testUser.password = "123456";
        userListesi.add(testUser);
    }


}
