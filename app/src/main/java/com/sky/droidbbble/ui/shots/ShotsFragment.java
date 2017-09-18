package com.sky.droidbbble.ui.shots;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.util.BatchingListUpdateCallback;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sky.dribbble.R;
import com.sky.droidbbble.data.model.Shots;
import com.sky.droidbbble.utils.DiffUtil;

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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        mRefreshLayout.setRefreshing(true);
        mRefreshLayout.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.addOnScrollListener(mScrollListener);

        mShotsAdapter = new ShotsAdapter(getActivity(), mListener);
        mRecyclerView.setAdapter(mShotsAdapter);

        mShotsPresenter = new ShotsPresenter();
        mShotsPresenter.attachView(this);
        mShotsPresenter.getShots(PER_PAGE, mPage);

        return view;
    }

    @Override
    public void onRefresh() {
        mPage = 1;
//        if (mShotsList != null && mShotsList.size() > 0) {
//            mShotsList.clear();
//        }
        mShotsPresenter.getShots(PER_PAGE, mPage);
    }

    @Override
    public void showLoading() {
        mRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showShots(final List<Shots> shotsList) {
        if (mShotsList == null) {
            mShotsList = new ArrayList<>();
        }
//        mShotsList.addAll(shotsList);
//        mShotsAdapter.setData(mShotsList);
//        mShotsAdapter.notifyDataSetChanged();
        List<Shots> oldList = mShotsAdapter.getData();
        ShotsDiffCallback callback = new ShotsDiffCallback(oldList, shotsList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(callback, false);
        mShotsAdapter.setData(shotsList);
        diffResult.dispatchUpdatesTo(mShotsAdapter);
//        diffResult.dispatchUpdatesTo(new BatchingListUpdateCallback(new ListUpdateCallback() {
//            @Override
//            public void onInserted(int position, int count) {
//                Timber.d("onInserted: position = " + position + ", count = " + count);
//                mShotsAdapter.notifyItemRangeInserted(position, count);
//            }
//
//            @Override
//            public void onRemoved(int position, int count) {
//                Timber.d("onRemoved: position = " + position + ", count = " + count);
//            }
//
//            @Override
//            public void onMoved(int fromPosition, int toPosition) {
//                Timber.d("onMoved: fromPosition = " + fromPosition + ", toPosition = " + toPosition);
//            }
//
//            @Override
//            public void onChanged(int position, int count, Object payload) {
//                Timber.d("onChanged: position = " + position + ", count = " + count + ", payload = " + payload);
//            }
//        }));
    }

    @Override
    public void showEmpty() {
        mRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(), "shots is empty.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError() {
        mRefreshLayout.setRefreshing(false);
        Toast.makeText(getActivity(), "something error", Toast.LENGTH_SHORT).show();
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
                mPage++;
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
