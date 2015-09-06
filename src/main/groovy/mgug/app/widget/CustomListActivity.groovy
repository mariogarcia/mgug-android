package mgug.app.widget

import android.app.ListActivity
import android.widget.ArrayAdapter
import android.widget.ListAdapter

class CustomListActivity<T extends ArrayAdapter<?>> extends ListActivity {

    T arrayAdapter

    @Override
    void setListAdapter(ListAdapter adapter) {
        super.setListAdapter(adapter)
        this.arrayAdapter = adapter
    }

}