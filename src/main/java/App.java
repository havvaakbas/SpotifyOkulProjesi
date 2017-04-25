import java.util.Scanner;
import java.util.ArrayList;
public class App {
    public static Scanner scan;
    public static Scanner menu = new Scanner(System.in);

    public static ArrayList<User> userListesi = new ArrayList<User>();
    public static ArrayList<Song> songListesi = new ArrayList<Song>();
    public static ArrayList<Album> albumListesi = new ArrayList<Album>();
    public static ArrayList<Singer> singerListesi = new ArrayList<Singer>();

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
                    sarkiListele();
                    break;

                case 3:
                    sarkiciListele();
                    break;

                case 4:
                    albumListele();
                    break;
                case 5:
                    playlistOlusturma();
                    break;
                case 6:
                    playlistListele();
                    break;
                case 7:
                    playlisteSarkiEkle();
                    break;
                case 8:
                    System.out.println("Program sonlaniyor.");
                    menu.close();
                    System.exit(0);
                    break;
            }
        }
    }

    private static void menuyuGoruntule(){
        System.out.println("**************");
        System.out.println("*****Menu*****");
        System.out.println("1-Kullanici Ekleme");
        System.out.println("2-Sarki Listele");
        System.out.println("3-Sarkici Listele");
        System.out.println("4-Album Listele");
        System.out.println("5-Playlist Olustur");
        System.out.println("6-Playlist Listele");
        System.out.println("7-Playliste Sarki Ekle");
        System.out.println("8-Programdan Cik");

    }

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

    public static void sarkiciListele(){
        for(Singer i: singerListesi ){
            System.out.println(i.name);
            for(Album j: i.albumList){
                System.out.println(j.name);
            }
        }
    }

    public static void albumListele(){
        for(Album i: albumListesi ){
            System.out.println(i.name);
        }
    }

    public static void sarkiListele(){
        for(Song i: songListesi ){
            System.out.println(i.name + " - " + i.singer.name + " - " + i.duration + " dk. ");
            System.out.println("url: " + i.urlYoutube);
        }
    }

    public static void playlistOlusturma(){

        Scanner playlist = new Scanner(System.in);
        System.out.println("Playlistinizin adi: ");
        String playlistName = playlist.nextLine();
        Playlist newPlaylist = new Playlist();
        mevcutKullanici.playlist = newPlaylist;
        newPlaylist.name = playlistName;

        Scanner sarki = new Scanner(System.in);
        System.out.println("Eklemek istediginiz sarkiyi giriniz: ");
        String sarkiName = sarki.nextLine();
        for(Song i: songListesi){
            if (i.name.equals(sarkiName)){
                newPlaylist.songList.add(i);
                break;
            }
        }
    }

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

    public static void playlistListele(){
        System.out.println("Playlistinizin adi : " + mevcutKullanici.playlist.name);
        for(Song i: mevcutKullanici.playlist.songList ){
            System.out.println(i.name + " - " + i.singer.name );
        }
    }

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


    }


}
