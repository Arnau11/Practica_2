package cat.urv.deim.asm.p3.shared;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.p2.common.R;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private Context mCtx;
    private List<Event> eventList;
    private onClickEventListener mOnClickEventListener;
    private boolean isAnonymous;

    public EventAdapter(Context mCtx, List<Event> eventList, onClickEventListener onClickEventListener, Boolean isAnonymous) {
        this.mCtx = mCtx;
        this.eventList = eventList;
        this.mOnClickEventListener=onClickEventListener;
        this.isAnonymous = isAnonymous;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewTitle, textViewDescription, textViewTags;
        public ImageView imageView;
        public onClickEventListener onClickEventListener;
        public ToggleButton toggleButtonFav, toggleButtonBook;


        public EventViewHolder(View itemView, onClickEventListener onClickEventListener) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewShortDesc);
            textViewTags = itemView.findViewById(R.id.textViewTags);
            imageView = itemView.findViewById(R.id.imageView);
            toggleButtonFav = itemView.findViewById(R.id.fav_icon);
            toggleButtonBook = itemView.findViewById(R.id.bookmark_icon);


            this.onClickEventListener = onClickEventListener;

            itemView.setOnClickListener(this);

            if (!isAnonymous) {
                toggleButtonFav.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        SharedPreferences prefs = mCtx.getSharedPreferences(Global.FAV_CHECKED+position, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();

                        if (toggleButtonFav.isChecked())
                            editor.putBoolean(Global.FAV_CHECKED+position, true);
                        else
                            editor.putBoolean(Global.FAV_CHECKED+position, false);

                        editor.commit();

                    }
                });

                toggleButtonBook.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        SharedPreferences prefs = mCtx.getSharedPreferences(Global.BOOK_CHECKED+position, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();

                        if (toggleButtonBook.isChecked())
                            editor.putBoolean(Global.BOOK_CHECKED+position, true);
                        else
                            editor.putBoolean(Global.BOOK_CHECKED+position, false);

                        editor.commit();

                    }
                });
            }

        };

        @Override
        public void onClick(View v) {
            onClickEventListener.onEventClick(getAdapterPosition());
        }
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        if(isAnonymous){
            view = inflater.inflate(R.layout.layout_event_anonymous,parent,false);
        }
        else{
            view = inflater.inflate(R.layout.layout_event,parent,false);
        }

        return new EventViewHolder(view, mOnClickEventListener);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        holder.textViewTitle.setText(eventList.get(position).getName());
        holder.textViewDescription.setText(eventList.get(position).getDescription());

        // getTags: method created in the class Global
        holder.textViewTags.setText(Global.getTags(eventList, position));

        Picasso.with(mCtx).load(eventList.get(position).getImageURL()).into(holder.imageView);

        if (!isAnonymous){

            SharedPreferences prefs = mCtx.getSharedPreferences(Global.FAV_CHECKED+position,Context.MODE_PRIVATE);
            boolean favChecked = prefs.getBoolean(Global.FAV_CHECKED+position, false);

            if(favChecked)
                holder.toggleButtonFav.setChecked(true);
            else
                holder.toggleButtonFav.setChecked(false);


            prefs = mCtx.getSharedPreferences(Global.BOOK_CHECKED+position,Context.MODE_PRIVATE);
            boolean bookChecked = prefs.getBoolean(Global.BOOK_CHECKED+position, false);

            if(bookChecked)
                holder.toggleButtonBook.setChecked(true);
            else
                holder.toggleButtonBook.setChecked(false);

        }

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    // Interface for sending the click information to the activity
    public interface onClickEventListener{
        void onEventClick(int position);
    }

}
