package com.example.weather;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;

public class settings_fragment extends Fragment {

    private TextView username;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        SharedPreferences preferences = this.getActivity().getSharedPreferences(sfName.sharedPreferencename, Context.MODE_PRIVATE);
        String myusername = preferences.getString("username", "NO USERNAME");
        username.setText(myusername);

        view = inflater.inflate(R.layout.settings, container, false);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radioGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean checked = ((RadioButton) view).isChecked();
                switch (view.getId()) {
                    case R.id.c:
                        if (checked)
                            break;

                    case R.id.f:
                        if (checked)
                            break;

                    default:
                }
            }
        });
        return view;
    }
}
