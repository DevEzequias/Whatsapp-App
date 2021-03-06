package com.devezequias.whatsapp.helper;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {

    public static boolean validarpermissoes(String[] permissoes, Activity activity, int requestCode){

        if(Build.VERSION.SDK_INT >= 23){

            List<String> listapermissoes = new ArrayList<>();

            /*Percorre as permissões passadas,
            verificando uma a uma, se já tem a permissão liberada */

            for (String permissao: permissoes){
                Boolean temPermissao = ContextCompat.checkSelfPermission(activity, permissao) == PackageManager.PERMISSION_GRANTED;
                if (!temPermissao) listapermissoes.add(permissao);
            }

            //Caso a lista esteja vazia, não é necessário solicitar permissão
            if (listapermissoes.isEmpty()) return true;
            String[] novaspermissoes = new String[listapermissoes.size()];
            listapermissoes.toArray(novaspermissoes);

            //Solicita permissão
            ActivityCompat.requestPermissions(activity, novaspermissoes, requestCode);
        }

        return true;
    }
}
