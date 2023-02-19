package com.architecture.ui.afterlogin.fragment.profile.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.databinding.FragmentProfileBinding;
import com.architecture.ui.afterlogin.fragment.profile.viewmodel.ProfileViewModel;
import com.architecture.ui.afterlogin.fragment.profile.viewmodel.ProfileViewModelFactory;
import com.architecture.ui.base.view.BaseFragment;
import com.library.navigator.activity.ActivityNavigator;
import com.library.navigator.fragment.FragmentNavigator;

public class ProfileFragment extends BaseFragment<FragmentProfileBinding, ProfileViewModel> {

    @Override
    protected String getFragmentClassName() {
        return ProfileFragment.class.getSimpleName();
    }

    @Override
    protected ActivityNavigator getActivityNavigator() {
        return new ActivityNavigator(getActivity());
    }

    @Override
    protected FragmentNavigator getFragmentNavigator() {
        return null;
    }

    @Override
    protected void doInOnCreate(Bundle savedInstanceState) {
    }

    @Override
    protected ProfileViewModel getViewModel() {
        LocalRepository localRepository = new LocalRepository(getContext());
        RemoteRepository remoteRepository = new RemoteRepository(getContext());
        ProfileViewModelFactory factory = new ProfileViewModelFactory(localRepository, remoteRepository);
        return viewModelProvider(ProfileViewModel.class, factory);
    }

    @Override
    protected FragmentProfileBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentProfileBinding.inflate(inflater, container, false);
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

    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
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