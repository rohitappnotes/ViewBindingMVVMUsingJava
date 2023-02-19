package com.architecture.ui.navigationhost.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.databinding.ActivityNavigationHostBinding;
import com.architecture.ui.base.view.BaseActivity;
import com.architecture.ui.navigationhost.viewmodel.NavigationHostViewModel;
import com.architecture.ui.navigationhost.viewmodel.NavigationHostViewModelFactory;
import com.library.navigator.activity.ActivityNavigator;
import com.library.navigator.fragment.FragmentNavigator;

public class NavigationHostActivity extends BaseActivity<ActivityNavigationHostBinding, NavigationHostViewModel> {


    @Override
    protected String getActivityClassName() {
        return NavigationHostActivity.class.getSimpleName();
    }

    @Override
    protected ActivityNavigator getActivityNavigator() {
        return new ActivityNavigator(NavigationHostActivity.this);
    }

    @Override
    protected FragmentNavigator getFragmentNavigator() {
        return null;
    }

    @Override
    protected void doInOnCreate(Bundle savedInstanceState) {
        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
    }

    @Override
    protected NavigationHostViewModel getViewModel() {
        LocalRepository localRepository = new LocalRepository(getApplicationContext());
        RemoteRepository remoteRepository = new RemoteRepository(getApplicationContext());
        NavigationHostViewModelFactory factory = new NavigationHostViewModelFactory(localRepository, remoteRepository);
        return viewModelProvider(NavigationHostViewModel.class, factory);
    }

    @Override
    protected void doBeforeSetContentView() {
    }

    @Override
    protected ActivityNavigationHostBinding getViewBinding(LayoutInflater inflater) {
        return ActivityNavigationHostBinding.inflate(inflater);
    }

    @Override
    protected void setupStatusBar() {
    }

    @Override
    protected void setupAppBar() {
    }

    @Override
    protected void init() {
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void addTextChangedListener() {
    }

    @Override
    protected void setOnFocusChangeListener() {
    }

    @Override
    protected void setupObservers() {
    }

    @Override
    protected void setupListeners() {
    }

    @Override
    protected void setupNavigationBar() {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProgressBar() {
    }

    @Override
    public void hideProgressBar() {
    }

    @Override
    public void showProgressDialog() {
    }

    @Override
    public void hideProgressDialog() {
    }
    /***********************************************************************************************
     ********************************************Permission*****************************************
     **********************************************************************************************/

    /***********************************************************************************************
     ********************************************Validations****************************************
     **********************************************************************************************/

    /***********************************************************************************************
     ********************************************Open Activity**************************************
     **********************************************************************************************/

    /***********************************************************************************************
     **********************************************Helper*******************************************
     **********************************************************************************************/
    private void setupCategoryRecyclerView() {
    }
}