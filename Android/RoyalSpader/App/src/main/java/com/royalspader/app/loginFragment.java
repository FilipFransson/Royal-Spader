package com.royalspader.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by cissi on 2014-01-22.
 */
public class LoginFragment extends Fragment {

    EditText anvandrnamn;
    EditText losenord;
    Button btnok;
    APIManager api;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        anvandrnamn = (EditText)rootView.findViewById(R.id.username);
        losenord = (EditText)rootView.findViewById(R.id.password);
        btnok = (Button)rootView.findViewById(R.id.btnOK);
        api = new APIManager();

        final Button button = (Button)rootView.findViewById(R.id.btnOK);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = anvandrnamn.getText().toString();
                String userPass = losenord.getText().toString();
                JSONObject loginData;
                String loginStuff;
                try {
                    loginData = new JSONObject("{username:"+userEmail+" ,password:"+userPass+"}");

                    loginStuff = api.login(getActivity(), loginData);
                    System.out.println(loginStuff);


                    Toast.makeText(getActivity().getApplicationContext(), "Inloggad!", Toast.LENGTH_SHORT).show();

                    FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                    tx.replace(R.id.container,Fragment.instantiate(getActivity(),"com.royalspader.app.BagFragment"));
                    tx.addToBackStack(null);
                    tx.commit();

                    if (view == btnok) {


                        Toast.makeText(getActivity().getApplicationContext(), "Inloggad!", Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });


        return rootView;
    }
}
