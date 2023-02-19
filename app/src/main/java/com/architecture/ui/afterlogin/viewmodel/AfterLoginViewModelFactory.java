package com.architecture.ui.afterlogin.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.ui.navigationhost.viewmodel.NavigationHostViewModel;

public class AfterLoginViewModelFactory implements ViewModelProvider.Factory {

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;

    public AfterLoginViewModelFactory(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(AfterLoginViewModel.class)) {
            return (T) new AfterLoginViewModel(localRepository, remoteRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
