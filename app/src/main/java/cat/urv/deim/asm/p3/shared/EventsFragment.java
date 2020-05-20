package cat.urv.deim.asm.p3.shared;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cat.urv.deim.asm.libraries.commanagerdc.models.Article;
import cat.urv.deim.asm.libraries.commanagerdc.models.CalendarItem;
import cat.urv.deim.asm.libraries.commanagerdc.models.Faq;
import cat.urv.deim.asm.libraries.commanagerdc.models.New;
import cat.urv.deim.asm.libraries.commanagerdc.providers.DataProvider;
import cat.urv.deim.asm.p2.common.LoginScreen;
import cat.urv.deim.asm.p2.common.MainActivity;
import cat.urv.deim.asm.p2.common.R;

public class EventsFragment extends Fragment {

    List<Event> eventList;
    RecyclerView recyclerView;
    EventAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DataProvider dataProvider = ;

        try {
            Object dataArray[] = {
                    dataProvider.getEvents()
            };

            for (Object obj : dataArray) {
                ArrayList<Object> list = (ArrayList<Object>) obj;

                if (list.get(0).getClass() == Faq.class) {
                    showFaq((Faq) list.get(0));
                } else if (list.get(0).getClass() == New.class) {
                    showNew((New) list.get(0));
                } else if (list.get(0).getClass() == Article.class) {
                    showArticle((Article) list.get(0));
                } else if (list.get(0).getClass() == cat.urv.deim.asm.libraries.commanagerdc.models.Event.class) {
                    showEvent((cat.urv.deim.asm.libraries.commanagerdc.models.Event) list.get(0));
                } else if (list.get(0).getClass() == CalendarItem.class) {
                    showCalendar((CalendarItem) list.get(0));
                } else {
                    Log.e(TAG, "Type not supported");
                }


            }

        } catch (NullPointerException exception) {
            Log.e(TAG, "Error accessing data");
        }







        showEvent((cat.urv.deim.asm.libraries.commanagerdc.models.Event) list.get(0));



        eventList.add(new Event(1, "Títol 1", "Descripció 1", "Data 1", R.drawable.help_2));
        eventList.add(new Event(2, "Títol 2", "Descripció 2", "Data 2", R.drawable.help_1));
        eventList.add(new Event(3, "Títol 3", "Descripció 3", "Data 3", R.drawable.help_1));
        eventList.add(new Event(4, "Títol 4", "Descripció 4", "Data 4", R.drawable.help_1));
        eventList.add(new Event(5, "Títol 5", "Descripció 5", "Data 5", R.drawable.help_1));

        mAdapter = new EventAdapter(getActivity(),eventList);
        recyclerView.setAdapter(mAdapter);


        mAdapter.setOnItemClickListener(new EventAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // TODO: depending on the position, go to a different event
                // TODO: retornar a la posició del recycleview on estava
                Intent intent = new Intent(getActivity(), EventsDetailActivity.class);
                startActivityForResult(intent, 0);
            }

        });

       return root;

    }


}
