package com.pmdm.virgen.pueblosconnavigationdraweb.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


/*
Garantiza que la informacíon no se pierda, debido a un movimiento de pantalla
o cualquier interacción con el menú o la orientación con el dispositivo.
 */
public class GalleryViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}