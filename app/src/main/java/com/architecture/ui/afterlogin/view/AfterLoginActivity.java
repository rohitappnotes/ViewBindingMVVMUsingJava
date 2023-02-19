package com.architecture.ui.afterlogin.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.architecture.R;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.databinding.ActivityAfterLoginBinding;
import com.architecture.ui.afterlogin.fragment.home.view.HomeFragment;
import com.architecture.ui.afterlogin.fragment.profile.view.ProfileFragment;
import com.architecture.ui.afterlogin.viewmodel.AfterLoginViewModel;
import com.architecture.ui.afterlogin.viewmodel.AfterLoginViewModelFactory;
import com.architecture.ui.base.view.BaseActivity;
import com.library.navigator.activity.ActivityNavigator;
import com.library.navigator.fragment.FragmentNavigator;

public class AfterLoginActivity extends BaseActivity<ActivityAfterLoginBinding, AfterLoginViewModel>  {

    @Override
    protected String getActivityClassName() {
        return AfterLoginActivity.class.getSimpleName();
    }

    @Override
    protected ActivityNavigator getActivityNavigator() {
        return new ActivityNavigator(AfterLoginActivity.this);
    }

    @Override
    protected FragmentNavigator getFragmentNavigator() {
        return new FragmentNavigator(getSupportFragmentManager(), R.id.fragmentContainer);
    }

    @Override
    protected void doInOnCreate(Bundle savedInstanceState) {
    }

    @Override
    protected AfterLoginViewModel getViewModel() {
        LocalRepository localRepository = new LocalRepository(getApplicationContext());
        RemoteRepository remoteRepository = new RemoteRepository(getApplicationContext());
        AfterLoginViewModelFactory factory = new AfterLoginViewModelFactory(localRepository, remoteRepository);
        return viewModelProvider(AfterLoginViewModel.class, factory);
    }

    @Override
    protected void doBeforeSetContentView() {
    }

    @Override
    protected ActivityAfterLoginBinding getViewBinding(LayoutInflater inflater) {
        return ActivityAfterLoginBinding.inflate(inflater);
    }

    @Override
    protected void setupStatusBar() {
    }

    @Override
    protected void setupAppBar() {
    }

    @Override
    protected void init() {
       /* HomeFragment homeFragment = new HomeFragment();
        mFragmentNavigator.push(homeFragment, false, true);*/
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
        mViewBinding.homeFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeFragment homeFragment = new HomeFragment();
                mFragmentNavigator.push(homeFragment, false, true);
            }
        });

        mViewBinding.profileFragmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileFragment profileFragment = new ProfileFragment();
                mFragmentNavigator.push(profileFragment, false, true);
            }
        });
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
