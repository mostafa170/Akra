package com.kamel.akra.ui.ListanElmoshaf;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kamel.akra.network.QuranAPI;
import com.kamel.akra.ui.ListanElmoshaf.model.DataItem;
import com.kamel.akra.ui.ListanElmoshaf.model.ResponseQuran;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListanElmoshafViewModel extends AndroidViewModel {

    protected MutableLiveData<Integer> errorMessage;
    protected MutableLiveData<List<DataItem>> showSora;

    public ListanElmoshafViewModel(@NonNull Application application) {
        super(application);

        showSora = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<List<DataItem>> getShowSora() {
        return showSora;
    }

    public void getElmoshafListan(String lang,int reader_id){
        QuranAPI.getApis().getElmoshafListan(lang,reader_id).enqueue(new Callback<ResponseQuran>() {
            @Override
            public void onResponse(Call<ResponseQuran> call, Response<ResponseQuran> response) {
                if (response.isSuccessful()){

                    if ("القران الكريم".equals(String.valueOf(response.body().getMessage()))){
                        showSora.postValue(response.body().getData());
                    }else {
                        showSora.postValue(response.body().getData());
                    }
                }

            }
            @Override
            public void onFailure(Call<ResponseQuran> call, Throwable t) {
                errorMessage.postValue(1);
            }
        });
    }
}