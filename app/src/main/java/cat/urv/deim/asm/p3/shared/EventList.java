package cat.urv.deim.asm.p3.shared;

import android.widget.ImageView;

public class EventList {
    private int id;
    private String title;
    private String shortDesc;
    private String date;
    private String imageURL;

    public EventList(String title, String shortDesc, String date, String imageURL) {
        this.title = title;
        this.shortDesc = shortDesc;
        this.date=date;
        this.imageURL=imageURL;
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

    public String getImageURL() {
        return imageURL;
    }

}
