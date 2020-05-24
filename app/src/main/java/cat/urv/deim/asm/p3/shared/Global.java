package cat.urv.deim.asm.p3.shared;

import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;

public class Global {
    public static final String IS_ANONYMOUS = "isAnonymous";
    public static final String FROM_MENU = "fromMenu";
    public static final String NEED_TUTORIAL = "NeedTutorial";
    public static final String CORRECT_PASSWORD = "123456";
    public static final String CORRECT_EMAIL = "sandra.adams@email.com";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String POSITION = "position";

    public static String getTags(List<Event> e, int pos){

        StringBuilder tags = new StringBuilder("");

        for (Tag tag:e.get(pos).getTags()){
            tags.append(tag.getName());
            tags.append(", ");
        }

        String tag = tags.toString();
        tag = tag.substring(0,tag.length()-2);

        return tag;
    }


}
