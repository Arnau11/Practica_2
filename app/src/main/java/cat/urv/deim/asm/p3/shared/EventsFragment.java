package cat.urv.deim.asm.p3.shared;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.R;

public class EventsFragment extends Fragment implements EventAdapter.onClickEventListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    private List<EventList> eventsList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DataProvider dataProvider = DataProvider.getInstance(getActivity());
        List<Event> event = dataProvider.getEvents();

        eventsList = new ArrayList<>();

        String tags = "";

        for (int i = 0; i<event.size(); i++){
            tags = "";

            for (Tag tag:event.get(i).getTags()){
                tags=tags+", "+tag.getName();
            }
            tags=tags.substring(1,tags.length());
            EventList eventList = new EventList(event.get(i).getName(), tags, event.get(i).getImageURL());
            eventsList.add(eventList);
        }

        mAdapter = new EventAdapter(getActivity(),eventsList, this);

        recyclerView.setAdapter(mAdapter);

        return root;

    }

    @Override
    public void onEventClick(int position) {
        eventsList.get(position);
        Intent intent = new Intent(getActivity(),EventsDetailActivity.class);
        intent.putExtra("Position", position);
        startActivity(intent);
    }
}
