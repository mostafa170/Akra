package com.kamel.akra.ui.Azkar.AzkarlListan;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kamel.akra.network.QuranAPI;
import com.kamel.akra.ui.Azkar.AzkarlListan.model.AzkarItemData;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AzkarListanViewModel extends AndroidViewModel {

    protected MutableLiveData<Integer> errorMessage;
    protected MutableLiveData<List<AzkarItemData>> showAzakar;

    public AzkarListanViewModel(@NonNull Application application) {
        super(application);

        showAzakar = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<List<AzkarItemData>> getShowAzakar() {
        return showAzakar;
    }

        public void getAzkarListan(){
            QuranAPI.getApis().getAzkarListan().enqueue(new Callback<List<AzkarItemData>>() {
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