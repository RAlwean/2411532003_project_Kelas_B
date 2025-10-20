package Praktikum_4;
public class TextBook extends Book {
    private String BidangStudi;

    public TextBook(String title, String author, String BidangStudi) {
        super(title, author);
        this.BidangStudi = BidangStudi;
    }

    public String getBidangStudi() {return BidangStudi;}
    }
