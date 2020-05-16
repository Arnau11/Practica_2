package cat.urv.deim.asm.p3.shared;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> questionList;
    private HashMap<String,List<String>> answerList;

    public ExpandableListViewAdapter(Context context, List<String> questionList, HashMap<String, List<String>> answerList) {
        this.context = context;
        this.questionList = questionList;
        this.answerList = answerList;
    }

    //chapter=question y topic=asnwer

    @Override
    public int getGroupCount() {
        return this.questionList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.answerList.get(this.questionList.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
