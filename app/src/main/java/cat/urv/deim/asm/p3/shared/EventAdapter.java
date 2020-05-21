package cat.urv.deim.asm.p3.shared;

import android.app.LauncherActivity;
import android.content.Context;
import android.media.MediaDrm;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cat.urv.deim.asm.p2.common.R;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private Context mCtx;
    private List<EventList> eventsList;
    private onClickEventListener mOnClickEventListener;
    public static boolean liked = false;
    public static boolean bookMarked = false;

    public EventAdapter(Context mCtx, List<EventList> eventList, onClickEventListener onClickEventListener) {
        this.mCtx = mCtx;
        this.eventsList = eventList;
        this.mOnClickEventListener=onClickEventListener;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textViewTitle, textViewShortDesc, textViewDate;
        public ImageView imageView;
        public Button like, bookmark;
        public onClickEventListener onClickEventListener;


        public EventViewHolder(View itemView, onClickEventListener onClickEventListener) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewDate= itemView.findViewById(R.id.textViewDate);
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
        final EventList eventList = eventsList.get(position);

        holder.textViewTitle.setText(eventList.getTitle());
        holder.textViewShortDesc.setText(eventList.getShortDesc());
        holder.textViewDate.setText(eventList.getDate());

        Picasso.with(mCtx).load(eventList.getImageURL()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }

    // Interface for sending the click information to the activity
    public interface onClickEventListener{
        void onEventClick(int position);
    }

}
