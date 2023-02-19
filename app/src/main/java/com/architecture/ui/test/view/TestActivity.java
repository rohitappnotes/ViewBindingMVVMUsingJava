package com.architecture.ui.test.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import androidx.lifecycle.Observer;
import com.library.navigator.activity.ActivityNavigator;
import com.library.navigator.fragment.FragmentNavigator;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.databinding.ActivityTestBinding;
import com.architecture.ui.base.view.BaseActivity;
import com.architecture.ui.test.viewmodel.TestViewModel;
import com.architecture.ui.test.viewmodel.TestViewModelFactory;

public class TestActivity extends BaseActivity<ActivityTestBinding, TestViewModel>  {

    @Override
    protected String getActivityClassName() {
        return TestActivity.class.getSimpleName();
    }

    @Override
    protected ActivityNavigator getActivityNavigator() {
        return new ActivityNavigator(TestActivity.this);
    }

    @Override
    protected FragmentNavigator getFragmentNavigator() {
        return null;
    }

    @Override
    protected void doInOnCreate(Bundle savedInstanceState) {
    }

    @Override
    protected TestViewModel getViewModel() {
        LocalRepository localRepository = new LocalRepository(getApplicationContext());
        RemoteRepository remoteRepository = new RemoteRepository(getApplicationContext());
        TestViewModelFactory factory = new TestViewModelFactory(localRepository, remoteRepository);
        return viewModelProvider(TestViewModel.class, factory);
    }

    @Override
    protected void doBeforeSetContentView() {
    }

    @Override
    protected ActivityTestBinding getViewBinding(LayoutInflater inflater) {
        return ActivityTestBinding.inflate(inflater);
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