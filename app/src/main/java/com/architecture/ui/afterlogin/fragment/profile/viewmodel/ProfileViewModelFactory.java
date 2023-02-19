package com.architecture.ui.afterlogin.fragment.profile.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;

public class ProfileViewModelFactory implements ViewModelProvider.Factory {

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;

    public ProfileViewModelFactory(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ProfileViewModel.class)) {
            return (T) new ProfileViewModel(localRepository, remoteRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
