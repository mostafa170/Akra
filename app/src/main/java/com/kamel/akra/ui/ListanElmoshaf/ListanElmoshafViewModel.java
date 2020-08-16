package com.kamel.akra.ui.ListanElmoshaf;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.kamel.akra.network.QuranAPI;
import com.kamel.akra.ui.ListanElmoshaf.model.ResponseElmoshaf;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListanElmoshafViewModel extends AndroidViewModel {

    protected MutableLiveData<Integer> errorMessage;
    protected MutableLiveData<List<ResponseElmoshaf>> showSora;

    public ListanElmoshafViewModel(@NonNull Application application) {
        super(application);

        showSora = new MutableLiveData<>();
        errorMessage = new MutableLiveData<>();
    }

    public MutableLiveData<Integer> getErrorMessage() {
        return errorMessage;
    }

    public MutableLiveData<List<ResponseElmoshaf>> getShowSora() {
        return showSora;
    }

    public void getElmoshafListan(String lang,int reader_id){
        QuranAPI.getApis().getElmoshafListan(lang,reader_id).enqueue(new Callback<List<ResponseElmoshaf>>() {
            @Override
            public void onResponse(Call<List<ResponseElmoshaf>> call, Response<List<ResponseElmoshaf>> response) {
                if (response.isSuccessful()){
                    showSora.postValue(response.body());
                }else {
                    showSora.postValue(response.body());
                }
            }
            @Override
            public void onFailure(Call<List<ResponseElmoshaf>> call, Throwable t) {
                errorMessage.postValue(1);
            }
        });
    }
}