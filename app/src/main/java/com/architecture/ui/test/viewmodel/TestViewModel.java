package com.architecture.ui.test.viewmodel;

import androidx.lifecycle.MutableLiveData;
import com.architecture.data.repository.LocalRepository;
import com.architecture.data.repository.RemoteRepository;
import com.architecture.ui.base.viewmodel.BaseViewModel;

public class TestViewModel extends BaseViewModel {

    private LocalRepository localRepository;
    private RemoteRepository remoteRepository;

    private MutableLiveData<String> localData;
    private MutableLiveData<String> remoteData;
    private MutableLiveData<String> yourName;

    public TestViewModel(LocalRepository localRepository, RemoteRepository remoteRepository) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
    }

    public MutableLiveData<String> local() {
        if (localData == null) {
            localData = new MutableLiveData<String>();
        }
        return localData;
    }

    public void getLocalData() {
        localData.setValue(localRepository.getData());
    }

    public MutableLiveData<String> remote() {
        if (remoteData == null) {
            remoteData = new MutableLiveData<String>();
        }
        return remoteData;
    }

    public void getRemoteData() {
        remoteData.setValue(remoteRepository.getData());
    }

    public MutableLiveData<String> yourName() {
        if (yourName == null) {
            yourName = new MutableLiveData<String>();
        }
        return yourName;
    }

    public void setYourName(String name) {
        yourName.setValue(name);
    }
}
