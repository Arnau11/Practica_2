package cat.urv.deim.asm.p3.shared;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.urv.deim.asm.p2.common.R;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder>{

    private Context mCtx;
    private List<Event> eventList;
    private onItemClickListener mListener;

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public class EventViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle, textViewShortDesc, textViewDate;
        ImageView imageView;

        public EventViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewDate= itemView.findViewById(R.id.textViewDate);
            imageView = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null){
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION)
                            mListener.onItemClick(position);

                    }
                }
            });

        }
    }

    public EventAdapter(Context mCtx, List<Event> eventList) {
        this.mCtx = mCtx;
        this.eventList = eventList;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_event, null);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventViewHolder holder, int position) {

        Event event = eventList.get(position);

        holder.textViewTitle.setText(event.getTitle());
        holder.textViewShortDesc.setText(event.getShortDesc());
        holder.textViewDate.setText(event.getDate());

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(event.getImage(),null));
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

}
