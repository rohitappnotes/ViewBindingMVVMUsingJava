package com.architecture.ui.base.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.architecture.ui.base.viewmodel.BaseViewModel;

public abstract class BaseFragment<VB extends ViewBinding, VM extends BaseViewModel> extends Fragment implements BaseFragmentView {

    private BaseActivity mActivity;

    /**
     * TAG
     */
    private String mTag;

    /**
     * My Lib Activity Navigator
     */
    protected com.library.navigator.activity.ActivityNavigator mActivityNavigator;

    /**
     * My Lib Fragment Navigator
     */
    protected com.library.navigator.fragment.FragmentNavigator mFragmentNavigator;

    /**
     * ViewModel
     */
    protected VM mViewModel;

    /**
     * ViewBinding
     */
    protected VB mViewBinding;

    /**
     * View
     */
    protected View rootView;

    /**
     * You need override this method for fragment Logcat tag
     * Example :
     * @Override
     * protected String getFragmentClassName() {
     *   return HomeFragment.class.getSimpleName();
     * }
     */
    protected abstract String getFragmentClassName();

    /**
     * You need override this method, you can use it for navigate to another activity, send and receive
     * data (bundle, serializable, parcelable) between activity
     */
    protected abstract com.library.navigator.activity.ActivityNavigator getActivityNavigator();

    /**
     * You need override this method, you can use it for add or replace fragment in activity or fragment,
     * send and receive data (bundle, serializable, parcelable) between activity
     */
    protected abstract com.library.navigator.fragment.FragmentNavigator getFragmentNavigator();

    /**
     * You need override this method, you can use it for bundle etc.,
     */
    protected abstract void doInOnCreate(Bundle savedInstanceState);

    /**
     * You need override this method.
     * Example :
     * @Override
     * protected HomeViewModel getViewModel() {
     *  LocalRepository localRepository = new LocalRepository(getContext());
     *  RemoteRepository remoteRepository = new RemoteRepository(getContext());
     *  HomeViewModelFactory factory = new HomeViewModelFactory(localRepository, remoteRepository);
     *  return viewModelProvider(HomeViewModel.class, factory);
     * }
     */
    protected abstract VM getViewModel();

    /**
     * You need override this method.
     * Example :
     * @Override
     * protected FragmentHomeBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
     *   return FragmentHomeBinding.inflate(inflater, container, false);
     * }
     */
    protected abstract VB getViewBinding(LayoutInflater inflater, ViewGroup container);

    protected abstract void init();
    protected abstract void initView();
    protected abstract void addTextChangedListener();
    protected abstract void setOnFocusChangeListener();
    protected abstract void setupObservers();
    protected abstract void setupListeners();

    private void initTag() {
        mTag = getFragmentClassName();
        if (mTag.length() > 23) {
            mTag = mTag.substring(0, 22);
        }
    }

    private void initViewModel() {
        mViewModel = getViewModel();
    }

    private void initViewBinding(LayoutInflater inflater, ViewGroup container) {
        mViewBinding = getViewBinding(inflater, container);
        rootView = mViewBinding.getRoot();
    }

    /*
     * OnAttach(Context context) is not call, If API level of the android version is lower than 23.
     * Because OnAttach(Context context) is added in API level 23.
     */
    @TargetApi(23)
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        initTag();
        Log.i(mTag, "onAttach(@NonNull Context context)");

        if (context instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) context;
            this.mActivity = baseActivity;
        }
    }

    /*
     * OnAttach(Activity activity) is not call, If API level of the android version is greater than 22.
     * Because OnAttach(Activity activity) is deprecated in API level 23, but must remain to allow compatibility with api<23
     */
    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        initTag();
        Log.i(mTag, "onAttach(@NonNull Activity activity)");

        if (activity instanceof BaseActivity) {
            BaseActivity baseActivity = (BaseActivity) activity;
            this.mActivity = baseActivity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(mTag, "onCreate(Bundle savedInstanceState)");

        mActivityNavigator = getActivityNavigator();
        mFragmentNavigator = getFragmentNavigator();

        doInOnCreate(savedInstanceState);

        initViewModel();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(mTag, "onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)");
        initViewBinding(inflater, container);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i(mTag, "onViewCreated(@NonNull View view, Bundle savedInstanceState)");

        init();
        initView();
        addTextChangedListener();
        setOnFocusChangeListener();
        setupObservers();
        setupListeners();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i(mTag, "onActivityCreated(@Nullable Bundle savedInstanceState)");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(mTag, "onStart()");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(mTag, "onResume()");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(mTag, "onPause()");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(mTag, "onStop()");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(mTag, "onDestroyView()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(mTag, "onDestroy()");
    }

    @Override
    public void onDetach() {
        if (null != mViewModel) {
            mViewModel = null;
        }
        if (null != mViewBinding) {
            mViewBinding = null;
        }
        if (null != rootView) {
            rootView = null;
        }
        if (null !=  mActivity) {
            mActivity = null;
        }
        super.onDetach();
        Log.i(mTag, "onDetach()");

    }
    /***********************************************************************************************
     *************************************Implemented Method****************************************
     **********************************************************************************************/
    @Override
    public void showShortToast(String message) {
        Log.i(mTag, message);
        getBaseActivity().showShortToast(message);
    }

    @Override
    public void showShortToast(int messageResId) {
        Log.i(mTag, getString(messageResId));
        getBaseActivity().showShortToast(getString(messageResId));
    }

    @Override
    public void showLongToast(String message) {
        Log.i(mTag, message);
        getBaseActivity().showShortToast(message);
    }

    @Override
    public void showLongToast(int messageResId) {
        Log.i(mTag, getString(messageResId));
        getBaseActivity().showLongToast(getString(messageResId));
    }

    @Override
    public void showIndefiniteSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        getBaseActivity().showIndefiniteSnackBar(parent, message, actionText, onClickListener);
    }

    @Override
    public void showIndefiniteSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        getBaseActivity().showIndefiniteSnackBar(parent, getString(messageResId), actionText, onClickListener);
    }

    @Override
    public void showShortSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        getBaseActivity().showShortSnackBar(parent, message, actionText, onClickListener);
    }

    @Override
    public void showShortSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        getBaseActivity().showShortSnackBar(parent, getString(messageResId), actionText, onClickListener);
    }

    @Override
    public void showLongSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        getBaseActivity().showLongSnackBar(parent, message, actionText, onClickListener);
    }

    @Override
    public void showLongSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        getBaseActivity().showLongSnackBar(parent, getString(messageResId), actionText, onClickListener);
    }

    @Override
    public void setVisible(View... views) {
        getBaseActivity().setVisible(views);
    }

    @Override
    public void setInVisible(View... views) {
        getBaseActivity().setInVisible(views);
    }

    @Override
    public void setGone(View... views) {
        getBaseActivity().setGone(views);
    }

    @Override
    public void hideKeyboard() {
        getBaseActivity().hideKeyboard();
    }
    /***********************************************************************************************
     ****************************************ViewModel Helper***************************************
     **********************************************************************************************/
    protected <VMC extends ViewModel> VMC viewModelProvider(@NonNull Class<VMC> viewModelClass) {
        return new ViewModelProvider(this).get(viewModelClass);
    }

    protected <VMC extends ViewModel> VMC viewModelProvider(@NonNull Class<VMC> viewModelClass, @NonNull ViewModelProvider.Factory factory) {
        return new ViewModelProvider(this, factory).get(viewModelClass);
    }
    /***********************************************************************************************
     ********************************************Helper*********************************************
     **********************************************************************************************/
    public BaseActivity getBaseActivity() {
        return mActivity;
    }
}
