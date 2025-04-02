package com.ajfm.abcd_plantas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ajfm.abcd_plantas.modelos.criterioGeneral;

import java.util.List;

public class ModeloVision extends ViewModel {
    //Valores de prueba
    private MutableLiveData<String> myData = new MutableLiveData<>();
    public LiveData<String> getMyData() {
        return myData;
    }
    public void setMyData(String newData) {
        myData.setValue(newData);
    }

    //Proporciona los valores para obtener la consuStringIni en el Fragment 2
    private MutableLiveData<String> MuttextSelectD = new MutableLiveData<>();
    public void setMuttextSelectD(String newSelectD) { MuttextSelectD.setValue(newSelectD); }
    public LiveData<String> getMuttextSelectD() { return MuttextSelectD; }

    private MutableLiveData<String> MuttextSelectF = new MutableLiveData<>();
    public void setMuttextSelectF(String newSelectF) { MuttextSelectF.setValue(newSelectF); }
    public LiveData<String> getMuttextSelectF() { return MuttextSelectF; }

    private MutableLiveData<String> MuttextSelectG = new MutableLiveData<>();
    public void setMuttextSelectG(String newSelectG) { MuttextSelectG.setValue(newSelectG); }
    public LiveData<String> getMuttextSelectG() { return MuttextSelectG; }

    private MutableLiveData<String> MuttextSelectE = new MutableLiveData<>();
    public void setMuttextSelectE(String newSelectE) { MuttextSelectE.setValue(newSelectE); }
    public LiveData<String> getMuttextSelectE() { return MuttextSelectE; }

    private MutableLiveData<Integer> MutnumSelect = new MutableLiveData<>();
    public void setMutnumSelect(int newNumSelect) { MutnumSelect.setValue(newNumSelect); }
    public LiveData<Integer> getMutnumSelect() { return MutnumSelect; }

    //Conserva y podría transmitir los valores de la clasificación actual en Fragment2
    //por si se cambia de fragment que al volver al F2 siga por donde se quedó
    private MutableLiveData<List<Integer>> mutfines = new MutableLiveData<>();
    public LiveData<List<Integer>> getMutfines() { return mutfines; }
    public void setMutfines(List<Integer> newFines) { mutfines.setValue(newFines); }

    private MutableLiveData<List<criterioGeneral>> mutcriterios = new MutableLiveData<>();
    public LiveData<List<criterioGeneral>> getMutcriterios() { return mutcriterios; }
    public void setMutcriterios(List<criterioGeneral> newCriterios) { mutcriterios.setValue(newCriterios); }

    public MutableLiveData<String> pieFig = new MutableLiveData<>();
    public LiveData<String> getPieFig() { return pieFig; }
    public void setPieFig(String newPieFig) { pieFig.setValue(newPieFig); }

    public MutableLiveData<String> pieLam = new MutableLiveData<>();
    public LiveData<String> getPieLam() { return pieLam; }
    public void setPieLam(String newPieLam) { pieLam.setValue(newPieLam); }

    public MutableLiveData<Integer> resIdFig = new MutableLiveData<>();
    public LiveData<Integer> getResIdFig() { return resIdFig; }
    public void setresIdFig(Integer newResIdFig) { resIdFig.setValue(newResIdFig); }

    public MutableLiveData<Integer> resIdLam = new MutableLiveData<>();
    public LiveData<Integer> getResIdLam() { return resIdLam;}
    public void setresIdLam(Integer newFileLam) { resIdLam.setValue(newFileLam); }

    public MutableLiveData<Integer> origen = new MutableLiveData<>();
    public LiveData<Integer> getOrigen() { return origen; }
    public void setOrigen(Integer newOrigen) { origen.setValue(newOrigen); }

    public MutableLiveData<String> consuIni = new MutableLiveData<>();
    public LiveData<String> getConsuIni() { return consuIni; }
    public void setConsuIni(String newConsuIni) { consuIni.setValue(newConsuIni); }

    public MutableLiveData<List<criterioGeneral>> tcritSelect2 = new MutableLiveData<>();
    public LiveData<List<criterioGeneral>> getTcritSelect2() { return tcritSelect2; }
    public void setTcritSelect2(List<criterioGeneral> newTcritSelect2) { tcritSelect2.setValue(newTcritSelect2); }

    public MutableLiveData<List<Integer>> tfines = new MutableLiveData<>();
    public LiveData<List<Integer>> gettfines() { return tfines; }
    public void settfines(List<Integer> newTfines) { tfines.setValue(newTfines); }

}
