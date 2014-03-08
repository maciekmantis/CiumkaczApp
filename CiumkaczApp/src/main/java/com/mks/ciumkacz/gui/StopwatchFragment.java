package com.mks.ciumkacz.gui;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mks.ciumkacz.R;
import com.mks.ciumkacz.controller.StopwatchAction;
import com.mks.ciumkacz.controller.StopwatchController;

public class StopwatchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private FeedListFragment.OnFeedsChangedListener mListener;

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
                stopwatchController.action(view, StopwatchAction.ACTION_PAUSE);
                updateFeeds();
            }
        });
        stopwatchStartLeft = ((Button) rootView.findViewById(R.id.stopwatch_start_left));
        stopwatchStartLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopwatchController.action(view, StopwatchAction.ACTION_LEFT);
                updateFeeds();
            }
        });
        stopwatchStartRight = ((Button) rootView.findViewById(R.id.stopwatch_start_right));
        stopwatchStartRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopwatchController.action(view, StopwatchAction.ACTION_LEFT);
                updateFeeds();
            }
        });

    }

    private void updateFeeds() {
        if (mListener != null) {
            mListener.updateFeeds();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (FeedListFragment.OnFeedsChangedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFeedsChangedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
