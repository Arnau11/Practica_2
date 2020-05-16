package cat.urv.deim.asm.p3.shared;

public class Event {
    private int id;
    private String title;
    private String shortDesc;
    private String date;
    private int image;


    public Event(int id, String title, String shortDesc, String date, int image) {
        this.id = id;
        this.title = title;
        this.shortDesc = shortDesc;
        this.date=date;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getDate() {
        return date;
    }

    public int getImage() {
        return image;
    }

}
