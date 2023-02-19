package com.architecture.ui.navigationhost.viewmodel;

import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.ui.base.viewmodel.BaseViewModel;

public class NavigationHostViewModel extends BaseViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    public NavigationHostViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }
}
