package com.architecture.factory;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.ui.test.viewmodel.TestViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private static volatile ViewModelFactory INSTANCE;

    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;

    public static ViewModelFactory getInstance(Context applicationContext) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    LocalRepository localRepository = new LocalRepository(applicationContext);
                    RemoteRepository remoteRepository = new RemoteRepository(applicationContext);
                    INSTANCE = new ViewModelFactory(localRepository, remoteRepository);
                }
            }
        }
        return INSTANCE;
    }

    public ViewModelFactory(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TestViewModel.class)) {
            return (T) new TestViewModel(localRepository, remoteRepository);
        } else if (modelClass.isAssignableFrom(TestViewModel.class)) {
            return (T) new TestViewModel(localRepository, remoteRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}