package cat.urv.deim.asm.p3.shared;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import cat.urv.deim.asm.libraries.commanagerdc.models.Event;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

public class EventsFragment extends Fragment implements EventAdapter.onClickEventListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;

    List<Event> eventList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        try{
            DataProvider dataProvider = DataProvider.getInstance(getActivity());
            eventList = dataProvider.getEvents();

            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
            final SharedPreferences.Editor editor = prefs.edit();
            final boolean isAnonymous = prefs.getBoolean(Global.IS_ANONYMOUS, true);

            mAdapter = new EventAdapter(getActivity(),eventList, this, isAnonymous);

            recyclerView.setAdapter(mAdapter);
        }
        catch (NullPointerException exception){
            Log.e(TAG,"Error accessing data");
        }

        return root;

    }

    @Override
    public void onEventClick(int position) {
        eventList.get(position);
        Intent intent = new Intent(getActivity(),EventsDetailActivity.class);
        intent.putExtra(Global.POSITION, position);
        startActivity(intent);
    }
}
