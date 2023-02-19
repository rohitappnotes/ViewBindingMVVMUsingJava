package com.architecture.ui.afterlogin.fragment.profile.viewmodel;

import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.ui.base.viewmodel.BaseViewModel;

public class ProfileViewModel extends BaseViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    public ProfileViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }
}
