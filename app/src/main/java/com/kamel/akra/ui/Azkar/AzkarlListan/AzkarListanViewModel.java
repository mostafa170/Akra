package com.kamel.akra.ui.Azkar.AzkarlListan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kamel.akra.network.MyApplication;
import com.kamel.akra.ui.Azkar.AzkarlListan.model.AzkarItemData;
import com.kamel.akra.ui.Azkar.AzkarlListan.model.AzkarResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AzkarListanViewModel extends AndroidViewModel {

    protected MutableLiveData<Integer> errorMessage;
    protected MutableLiveData<List<AzkarItemData>> showAzakar;
    private CompositeDisposable compositeDisposable;

    public AzkarListanViewModel(@NonNull Application application) {
        super(application);

        showAzakar = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
    }

    public MutableLiveData<Integer> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<List<AzkarItemData>> getShowAzakar() {
        return showAzakar;
    }

        public void getAzkarListan(){
            MyApplication.getApis().getAzkarListan().enqueue(new Callback<List<AzkarItemData>>() {
                @Override
                public void onResponse(Call<List<AzkarItemData>> call, Response<List<AzkarItemData>> response) {
                    if (response.isSuccessful()){
                        showAzakar.postValue(response.body());
                    }else {
                        showAzakar.postValue(response.body());
                    }
                }

                @Override
                public void onFailure(Call<List<AzkarItemData>> call, Throwable t) {
                    errorMessage.postValue(1);
                }
            });
        }
}