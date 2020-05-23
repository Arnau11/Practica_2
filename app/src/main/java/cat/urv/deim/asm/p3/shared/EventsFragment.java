package cat.urv.deim.asm.p3.shared;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.models.Tag;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.R;

public class EventsFragment extends Fragment implements EventAdapter.onClickEventListener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    List<Event> eventList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DataProvider dataProvider = DataProvider.getInstance(getActivity());
        eventList = dataProvider.getEvents();

        mAdapter = new EventAdapter(getActivity(),eventList, this);

        recyclerView.setAdapter(mAdapter);

        return root;

    }

    @Override
    public void onEventClick(int position) {
        eventList.get(position);
        Boolean isAnonymous = getActivity().getIntent().getExtras().getBoolean(Global.IS_ANONYMOUS);

        Intent intent = new Intent(getActivity(),EventsDetailActivity.class);

        intent.putExtra(Global.POSITION, position);
        intent.putExtra(Global.IS_ANONYMOUS, isAnonymous);
        startActivity(intent);
    }
}
