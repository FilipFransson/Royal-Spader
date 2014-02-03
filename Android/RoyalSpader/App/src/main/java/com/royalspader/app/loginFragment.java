package com.royalspader.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by cissi on 2014-01-22.
 */
public class loginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);



        final Button button = (Button)rootView.findViewById(R.id.btnOK);
        button.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity().getApplicationContext(), "Inloggad!", Toast.LENGTH_SHORT).show();

                FragmentTransaction tx = getActivity().getSupportFragmentManager().beginTransaction();
                tx.replace(R.id.container,Fragment.instantiate(getActivity(),"com.royalspader.app.kassarFragment"));
                tx.commit();
            }
        });


        return rootView;
    }
}
