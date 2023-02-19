package com.architecture.ui.afterlogin.viewmodel;

import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.ui.base.viewmodel.BaseViewModel;

public class AfterLoginViewModel extends BaseViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    public AfterLoginViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }
}
