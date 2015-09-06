package mgug.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import groovy.transform.CompileStatic
import mgug.app.R
import mgug.app.domain.Topic

@CompileStatic
class TopicAdapter extends ArrayAdapter<Topic> {

    TopicAdapter(Context context, int resource) {
        super(context, resource)
    }

    @Override
    View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        View rowView = inflater.inflate(R.layout.topic_item, parent, false)

        Topic topic = this.getItem(position)

        TextView descriptionView = (TextView) rowView.findViewById(R.id.topic_item_description)
        TextView authorView = (TextView) rowView.findViewById(R.id.topic_item_author)
        CheckBox checkedView = (CheckBox) rowView.findViewById(R.id.topic_item_checked)
        TextView pointsView = (TextView) rowView.findViewById(R.id.topic_item_points)

        descriptionView.text    = topic.description
        authorView.text         = topic.author
        checkedView.checked     = topic.checked
        pointsView.text         = topic.votes.toString()

        return rowView
    }

}