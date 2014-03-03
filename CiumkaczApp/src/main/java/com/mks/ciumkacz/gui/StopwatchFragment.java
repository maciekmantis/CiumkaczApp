package com.mks.ciumkacz.gui;

import android.app.Activity;
import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mks.ciumkacz.R;
import com.mks.ciumkacz.controller.StopwatchController;


/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StopwatchFragment.OnStopwatchInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StopwatchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StopwatchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnStopwatchInteractionListener mListener;

    private StopwatchController stopwatchController;
    private View rootView;
    private TextView stopwatchTimeElapsedField;
    private Button stopwatchStartLeft;
    private Button stopwatchStartRight;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StopwatchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StopwatchFragment newInstance(String param1, String param2) {
        StopwatchFragment fragment = new StopwatchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_stopwatch, container, false);
        initComponents();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        stopwatchController = new StopwatchController(this);
    }

    private void initComponents() {
        stopwatchStartLeft = ((Button) rootView.findViewById(R.id.stopwatch_start_right));
        stopwatchStartRight = ((Button) rootView.findViewById(R.id.stopwatch_start_left));
        stopwatchTimeElapsedField = ((TextView) rootView.findViewById(R.id.stopwatch_time_elapsed));
        stopwatchTimeElapsedField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopwatchController.action(, StopwatchController.ACTION_PAUSE);
            }
        });
        stopwatchStartLeft = ((Button) rootView.findViewById(R.id.stopwatch_start_left));
        stopwatchStartLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopwatchController.action(, StopwatchController.ACTION_LEFT);
            }
        });
        stopwatchStartRight = ((Button) rootView.findViewById(R.id.stopwatch_start_right));
        stopwatchStartRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopwatchController.action(, StopwatchController.ACTION_LEFT);
            }
        });
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
//            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnStopwatchInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnStopwatchInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnStopwatchInteractionListener {
        // TODO: Update argument type and name
//        public void onFragmentInteraction(Uri uri);
    }

    public TextView getStopwatchTimeElapsedField() {
        return stopwatchTimeElapsedField;
    }

    public Button getStopwatchStartLeft() {
        return stopwatchStartLeft;
    }

    public Button getStopwatchStartRight() {
        return stopwatchStartRight;
    }

}