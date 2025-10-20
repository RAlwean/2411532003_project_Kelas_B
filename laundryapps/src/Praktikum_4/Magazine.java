package Praktikum_4;
public class Magazine extends Book {
    private String Kategori;

    public Magazine(String title, String author, String Kategori) {
        super(title, author);
        this.Kategori = Kategori;
    }

    public String getKategori() {return Kategori;}
    }
