package com.architecture.ui.test.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;

public class TestViewModelFactory implements ViewModelProvider.Factory {

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;

    public TestViewModelFactory(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TestViewModel.class)) {
            return (T) new TestViewModel(localRepository, remoteRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
