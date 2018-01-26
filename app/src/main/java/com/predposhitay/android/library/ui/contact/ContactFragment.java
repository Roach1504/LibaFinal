package com.predposhitay.android.library.ui.contact;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.predposhitay.android.library.R;

public class ContactFragment extends Fragment{
    private DataAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_fragment,container,false);
        final GridView g = (GridView) view.findViewById(R.id.gridView1);
        g.setAdapter(new DataAdapter(getActivity()));
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                android.app.FragmentManager fragmentManager = getFragmentManager();
                ContactItemFragment referenceFragment = new ContactItemFragment(position);
                fragmentManager.beginTransaction()
                        .replace(R.id.content, referenceFragment)
                        .addToBackStack("myStack")
                        .commit();
            }
        });
        return view;
    }
}
