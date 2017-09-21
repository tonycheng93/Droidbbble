package com.sky.droidbbble.ui.shots;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sky.dribbble.R;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.widget.VegaLayoutManager;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ShotsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener,
        IShotsView {

    private static final String TAG = "ShotsFragment";

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private ShotsPresenter mShotsPresenter;
    private static final int PER_PAGE = 10;
    private int mPage = 1;
    private ShotsAdapter mShotsAdapter;
    private List<Shots> mShotsList;

    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ShotsFragment() {
    }

    public static ShotsFragment newInstance() {
        return new ShotsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnScrollListener(mScrollListener);

        mShotsAdapter = new ShotsAdapter(getActivity(), mListener);
        mRecyclerView.setAdapter(mShotsAdapter);

        mShotsPresenter = new ShotsPresenter();
        mShotsPresenter.attachView(this);
        mShotsPresenter.getShots(PER_PAGE, 1);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onRefresh() {
        Timber.d("invoke onRefresh");
        mPage = 1;
        mShotsPresenter.getShots(PER_PAGE, mPage);
    }

    @Override
    public void showLoading() {
        if (!isRefreshing()) {
            mRefreshLayout.setRefreshing(true);
        }
    }

    @Override
    public void showShots(final List<Shots> shotsList) {
        if (mShotsList == null) {
            mShotsList = new ArrayList<>();
        }
        if (mPage == 1) {
            mShotsList.addAll(0, shotsList);
        } else {
            mShotsList.addAll(shotsList);
        }
        mShotsAdapter.setData(mShotsList);
        mShotsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showEmpty() {
        if (isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        Toast.makeText(getActivity(), "shots is empty.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        if (isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void showError() {
        if (isRefreshing()) {
            mRefreshLayout.setRefreshing(false);
        }
        Toast.makeText(getActivity(), "something error", Toast.LENGTH_SHORT).show();
    }

    /**
     * check whether the SwipeRefreshLayout is loading
     */
    private boolean isRefreshing() {
        return mRefreshLayout.isRefreshing();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    private RecyclerView.OnScrollListener mScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView
                    .getLayoutManager();
            int totalItemCount = recyclerView.getAdapter().getItemCount();
            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
            if (lastVisibleItemPosition == totalItemCount - 1
                    && newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItemPosition > 0) {
                mPage = mPage + 1;
                mShotsPresenter.getShots(PER_PAGE, mPage);
            }
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

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
    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(Shots item);
    }
}
