package cat.urv.deim.asm.p3.shared;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.List;
import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;
import cat.urv.deim.asm.p2.common.R;



public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private Context mCtx;
    private List<Event> eventList;
    private onClickEventListener mOnClickEventListener;

    public EventAdapter(Context mCtx, List<Event> eventList, onClickEventListener onClickEventListener) {
        this.mCtx = mCtx;
        this.eventList = eventList;
        this.mOnClickEventListener=onClickEventListener;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewTitle, textViewDescription, textViewTags;
        public ImageView imageView;
        public onClickEventListener onClickEventListener;


        public EventViewHolder(View itemView, onClickEventListener onClickEventListener) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewShortDesc);
            textViewTags = itemView.findViewById(R.id.textViewTags);
            imageView = itemView.findViewById(R.id.imageView);

            this.onClickEventListener = onClickEventListener;

            itemView.setOnClickListener(this);

        };

        @Override
        public void onClick(View v) {
            onClickEventListener.onEventClick(getAdapterPosition());
        }
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_event,parent,false);

        return new EventViewHolder(view, mOnClickEventListener);
    }

    @Override
    public void onBindViewHolder(EventViewHolder holder, int position) {

        holder.textViewTitle.setText(eventList.get(position).getName());
        holder.textViewDescription.setText(eventList.get(position).getDescription());

        // getTags: method created in the class Global
        holder.textViewTags.setText(Global.getTags(eventList, position));

        Picasso.with(mCtx).load(eventList.get(position).getImageURL()).into(holder.imageView);

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
