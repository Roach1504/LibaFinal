package com.predposhitay.android.library.ui.settings;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.predposhitay.android.library.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SettingsFragment extends Fragment implements settingMVP{

    View nav;
    @BindView(R.id.editText)
    EditText mEditText;
    @BindView(R.id.imageView)
    ImageView save;

    SharedPreferences mSettings;
    @BindView(R.id.checkBox)
    CheckBox mCheckBox;
    SharedPreferences user;
    SettingPresentr presentr;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        ButterKnife.bind(this, view);

        nav = getActivity().findViewById(R.id.navigation);
        mSettings = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE);
        user = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        presentr = new SettingPresentr(this, user);
        mCheckBox.setChecked(mSettings.getBoolean("Check",false));
        mEditText.setText(mSettings.getString("login",user.getString("login","Noname")));
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presentr.rename(mEditText.getText().toString(),user.getString("id", "-1"));
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        //user.edit().putString("login", mEditText.getText().toString()).apply();
        mSettings.edit().putBoolean("Check",(mCheckBox.isChecked())).apply();
    }

    @Override
    public void onStop() {
        super.onStop();
        //user.edit().putString("login", mEditText.getText().toString()).apply();
        mSettings.edit().putBoolean("Check",(mCheckBox.isChecked())).apply();

    }


    @Override
    public void message(String text) {
        Toast.makeText(getActivity(),text, Toast.LENGTH_SHORT).show();
    }
}
