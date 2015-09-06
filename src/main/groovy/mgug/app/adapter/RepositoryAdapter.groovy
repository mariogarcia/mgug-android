package mgug.app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import groovy.transform.InheritConstructors
import mgug.app.R
import mgug.app.domain.Repository

@InheritConstructors
class RepositoryAdapter extends ArrayAdapter<Repository> {

    @Override
    View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
        View rowView            = inflater.inflate(R.layout.repository_item, parent, false)
        Repository repository   = getItem(position)

        rowView.findViewById(R.id.repository_item_title).text       = repository.name
        rowView.findViewById(R.id.repository_item_description).text = repository.description.take(60)

        return rowView
    }

}