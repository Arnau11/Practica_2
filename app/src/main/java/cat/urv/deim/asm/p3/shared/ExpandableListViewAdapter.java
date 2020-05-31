package cat.urv.deim.asm.p3.shared;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import cat.urv.deim.asm.p2.common.R;

public class  ExpandableListViewAdapter extends BaseExpandableListAdapter {

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
        return this.questionList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this.answerList.get(this.questionList.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }



    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String questionTitle = (String) getGroup(groupPosition);

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.question_list,null);
        }

        TextView  questionTv = convertView.findViewById(R.id.question_tv);
        questionTv.setText(questionTitle);

        return convertView;
    }

    //chapter=question y topic=asnwer

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String asnwerTitle = (String) getChild(groupPosition, childPosition);

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.answer_list,null);
        }

        TextView  answerTv = convertView.findViewById(R.id.answer_tv);
        answerTv.setText(asnwerTitle);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
