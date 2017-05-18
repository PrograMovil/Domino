package com.domino;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class Variables extends Application{

//    URL base del backend

    private static final String URLBase = "http://localhost:8084/UniversidadBackend/AndroidServlet?"; // Cambiarla segun donde se ejecute el backend


    public static String getURLBase() {
        return URLBase;
    }


}
