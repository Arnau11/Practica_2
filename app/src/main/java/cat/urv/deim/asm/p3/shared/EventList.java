package cat.urv.deim.asm.p3.shared;

import android.widget.ImageView;

public class EventList {
    private String name;
    private String description;
    private String imageURL;
    private String type;
    private String webURL;
    private String tags;

    public EventList(String name, String description, String tags, String imageURL) {
        this.name = name;
        this.description = description;
        this.type=type;
        this.webURL=webURL;
        this.imageURL=imageURL;
        this.tags = tags;
    }

    public EventList(String name, String tags, String imageURL) {
        this.name = name;
        this.tags = tags;
        this.imageURL=imageURL;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getType() {
        return type;
    }

    public String getWebURL() {
        return webURL;
    }

    public String getTags() {
        return tags;
    }


}
