package com.architecture.ui.navigationhost.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;

public class NavigationHostViewModelFactory implements ViewModelProvider.Factory {

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;

    public NavigationHostViewModelFactory(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NavigationHostViewModel.class)) {
            return (T) new NavigationHostViewModel(localRepository, remoteRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
