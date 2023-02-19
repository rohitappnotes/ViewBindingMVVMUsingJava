package com.architecture.ui.base.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;
import com.architecture.R;
import com.architecture.ui.base.viewmodel.BaseViewModel;
import com.google.android.material.snackbar.Snackbar;
import com.library.networkconnectivity.NetworkReceiver;
import com.library.networkconnectivity.NetworkStateChangeListener;
import com.library.networkconnectivity.NetworkStateChangeReceiver;

public abstract class BaseActivity<VB extends ViewBinding, VM extends BaseViewModel> extends AppCompatActivity implements BaseActivityView {

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
     * You need override this method for activity Logcat tag
     * Example :
     * @Override
     * protected String getActivityClassName() {
     *      return TestActivity.class.getSimpleName();
     * }
     */
    protected abstract String getActivityClassName();

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
     * You need override this method, you can use it for bundle, handleIntent(intent) etc.,
     */
    protected abstract void doInOnCreate(Bundle savedInstanceState);

    /**
     * You need override this method.
     * Example :
     * @Override
     * protected TestViewModel getViewModel() {
     *      LocalRepository localRepository = new LocalRepository(getApplicationContext());
     *      RemoteRepository remoteRepository = new RemoteRepository(getApplicationContext());
     *      TestViewModelFactory factory = new TestViewModelFactory(localRepository, remoteRepository);
     *      return viewModelProvider(TestViewModel.class, factory);
     * }
     */
    protected abstract VM getViewModel();

    /**
     * Here you can setting a theme dynamically for activity
     * Example :
     * protected void onCreate(Bundle savedInstanceState) {
     *      super.onCreate(savedInstanceState);
     *      //The call goes right after super.onCreate() and before setContentView()
     *      setTheme(android.R.style.Theme_Translucent_NoTitleBar);
     *      setContentView(R.layout.activity_main);
     * }
     */
    protected abstract void doBeforeSetContentView();

    /**
     * You need override this method.
     * Example :
     * @Override
     * protected ActivityTestBinding getViewBinding(LayoutInflater layoutInflater) {
     *      return ActivityTestBinding.inflate(layoutInflater);
     * }
     */
    protected abstract VB getViewBinding(LayoutInflater inflater);

    protected abstract void setupStatusBar();
    protected abstract void setupAppBar();
    protected abstract void init();
    protected abstract void initView();
    protected abstract void addTextChangedListener();
    protected abstract void setOnFocusChangeListener();
    protected abstract void setupObservers();
    protected abstract void setupListeners();
    protected abstract void setupNavigationBar();

    private void initTag() {
        mTag = getActivityClassName();
        if (mTag.length() > 23) {
            mTag = mTag.substring(0, 22); // first 22 chars
        }
    }

    private void initViewModel() {
        mViewModel = getViewModel();
    }

    private void initViewBinding(LayoutInflater inflater) {
        mViewBinding = getViewBinding(inflater);
        rootView = mViewBinding.getRoot();
    }

    private NetworkReceiver networkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTag();
        Log.i(mTag, "onCreate(Bundle savedInstanceState)");

        networkReceiver = new NetworkReceiver(this);
        checkInternetConnection();

        mActivityNavigator = getActivityNavigator();
        mFragmentNavigator = getFragmentNavigator();

        doInOnCreate(savedInstanceState);

        initViewModel();

        doBeforeSetContentView();
        
        initViewBinding(getLayoutInflater());
        setContentView(rootView);

        setupStatusBar();
        setupAppBar();
        init();
        initView();
        addTextChangedListener();
        setOnFocusChangeListener();
        setupObservers();
        setupListeners();
        setupNavigationBar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(mTag, "onStart()");
    }

    @Override
    protected void onRestart() { /* Only called after onStop() */
        super.onRestart();
        Log.i(mTag, "onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(mTag, "onResume()");
        networkReceiver.registerNetworkReceiver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(mTag, "onPause()");
        networkReceiver.unregisterNetworkReceiver(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(mTag, "onStop()");
    }

    @Override
    protected void onDestroy() {
        if (null != mViewModel) {
            mViewModel = null;
        }
        if (null != mViewBinding) {
            mViewBinding = null;
        }
        if (null != rootView) {
            rootView = null;
        }
        super.onDestroy();
        Log.i(mTag, "onDestroy()");
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        Log.i(mTag, "startActivity(Intent intent)");
        overridePendingTransitionEnter();
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        Log.i(mTag, "startActivityForResult(Intent intent, int requestCode)");
        overridePendingTransitionEnter();
    }

    @Override
    public void finish() {
        super.finish();
        Log.i(mTag, "finish()");
        overridePendingTransitionExit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.i(mTag, "onBackPressed()");
    }
    /***********************************************************************************************
     ******************************************App Language*****************************************
     **********************************************************************************************/

    /***********************************************************************************************
     *************************************Implemented Method****************************************
     **********************************************************************************************/
    @Override
    public void showShortToast(String message) {
        Log.i(mTag, message);
        toast(message, Toast.LENGTH_SHORT);
    }

    @Override
    public void showShortToast(int messageResId) {
        Log.i(mTag, getString(messageResId));
        toast(getString(messageResId), Toast.LENGTH_SHORT);
    }

    @Override
    public void showLongToast(String message) {
        Log.i(mTag, message);
        toast(message, Toast.LENGTH_LONG);
    }

    @Override
    public void showLongToast(int messageResId) {
        Log.i(mTag, getString(messageResId));
        toast(getString(messageResId), Toast.LENGTH_LONG);
    }

    @Override
    public void showIndefiniteSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        snackBar(parent, message, Snackbar.LENGTH_INDEFINITE, actionText, onClickListener);
    }

    @Override
    public void showIndefiniteSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        snackBar(parent, getString(messageResId), Snackbar.LENGTH_INDEFINITE, actionText, onClickListener);
    }

    @Override
    public void showShortSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        snackBar(parent, message, Snackbar.LENGTH_SHORT, actionText, onClickListener);
    }

    @Override
    public void showShortSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        snackBar(parent, getString(messageResId), Snackbar.LENGTH_SHORT, actionText, onClickListener);
    }

    @Override
    public void showLongSnackBar(View parent, String message, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, message);
        snackBar(parent, message, Snackbar.LENGTH_LONG, actionText, onClickListener);
    }

    @Override
    public void showLongSnackBar(View parent, int messageResId, String actionText, View.OnClickListener onClickListener) {
        Log.i(mTag, getString(messageResId));
        snackBar(parent, getString(messageResId), Snackbar.LENGTH_LONG, actionText, onClickListener);
    }

    @Override
    public void setVisible(View... views) {
        for (View v : views) {
            v.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setInVisible(View... views) {
        for (View v : views) {
            v.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setGone(View... views) {
        for (View v : views) {
            v.setVisibility(View.GONE);
        }
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            if (inputMethodManager != null) {
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }
    /***********************************************************************************************
     ****************************************ViewModel Helper***************************************
     **********************************************************************************************/
    protected <VMC extends ViewModel> VM viewModelProvider(@NonNull Class<VM> viewModelClass) {
        return new ViewModelProvider(this).get(viewModelClass);
    }

    protected <VMC extends ViewModel> VM viewModelProvider(@NonNull Class<VM> viewModelClass, @NonNull ViewModelProvider.Factory factory) {
        return new ViewModelProvider(this, factory).get(viewModelClass);
    }
    /***********************************************************************************************
     *****************************************StatusBar Helper**************************************
     **********************************************************************************************/

    /***********************************************************************************************
     *****************************************AppBar Helper*****************************************
     **********************************************************************************************/
    protected void setupToolBar(Toolbar toolbar, @ColorInt int backgroundColor, int navigationIcon, int title) {
        if (toolbar != null) {
            setSupportActionBar(toolbar);

            toolbar.setBackgroundColor(backgroundColor);

            toolbar.setNavigationIcon(AppCompatResources.getDrawable(getApplicationContext(), navigationIcon));
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            toolbar.setTitle(getResources().getString(title));
        }
    }
    /***********************************************************************************************
     **************************************NavigationBar Helper*************************************
     **********************************************************************************************/

    /***********************************************************************************************
     ********************************************Helper*********************************************
     **********************************************************************************************/
    protected void overridePendingTransitionEnter() {
        overridePendingTransition(R.anim.slide_in_from_right, R.anim.slide_out_to_left);
    }

    protected void overridePendingTransitionExit() {
        overridePendingTransition(R.anim.slide_in_from_left, R.anim.slide_out_to_right);
    }

    private void checkInternetConnection() {
        NetworkStateChangeReceiver.setNetworkStateChangeListener(new NetworkStateChangeListener() {
            @Override
            public void networkAvailable() {
                Log.i(mTag, "INTERNET CONNECT");
                //showShortToast("Internet Connect");
            }

            @Override
            public void networkUnavailable() {
                Log.i(mTag, "INTERNET DISCONNECT");
                showShortToast("Internet Disconnect");
            }
        });
    }

    private void toast(String message, int length) {
        Toast toast = Toast.makeText(getApplicationContext(), message, length);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void snackBar(View parent, String snackBarText, int length, String actionText, View.OnClickListener onClickListener) {
        Snackbar snackbar = Snackbar.make(parent, snackBarText, length);
        snackbar.setBackgroundTint(Color.parseColor("#FF0000")); // background red
        snackbar.setTextColor(Color.parseColor("#FFFFFF")); // snackBarText white
        if (actionText != null && onClickListener != null) {
            snackbar.setActionTextColor(Color.parseColor("#008505")); // actionText green
            snackbar.setAction(actionText, onClickListener);
        }
        snackbar.show();
    }
}
