package com.architecture.ui.afterlogin.fragment.home.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.Observer;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.databinding.FragmentHomeBinding;
import com.architecture.ui.afterlogin.fragment.home.viewmodel.HomeViewModel;
import com.architecture.ui.afterlogin.fragment.home.viewmodel.HomeViewModelFactory;
import com.architecture.ui.base.view.BaseFragment;
import com.library.navigator.activity.ActivityNavigator;
import com.library.navigator.fragment.FragmentNavigator;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> {

    @Override
    protected String getFragmentClassName() {
        return HomeFragment.class.getSimpleName();
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
    protected HomeViewModel getViewModel() {
        LocalRepository localRepository = new LocalRepository(getContext());
        RemoteRepository remoteRepository = new RemoteRepository(getContext());
        HomeViewModelFactory factory = new HomeViewModelFactory(localRepository, remoteRepository);
        return viewModelProvider(HomeViewModel.class, factory);
    }

    @Override
    protected FragmentHomeBinding getViewBinding(LayoutInflater inflater, ViewGroup container) {
        return FragmentHomeBinding.inflate(inflater, container, false);
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
        final Observer<String> localObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mViewBinding.localDataTextView.setText(s);
            }
        };
        mViewModel.local().observe(this, localObserver);
        mViewModel.getLocalData();

        final Observer<String> remoteObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mViewBinding.remoteDataTextView.setText(s);
            }
        };
        mViewModel.remote().observe(this, remoteObserver);
        mViewModel.getRemoteData();

        final Observer<String> yourNameObserver = new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mViewBinding.youNameTextView.setText(s);
            }
        };
        mViewModel.yourName().observe(this, yourNameObserver);
    }

    @Override
    protected void setupListeners() {
        mViewBinding.submitMaterialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = mViewBinding.yourNameTextInputEditText.getText().toString().trim();
                mViewModel.setYourName("Your name is "+name);
            }
        });
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