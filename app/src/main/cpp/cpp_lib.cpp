//
// Created by ASUS on 1/1/2021.
//

#include <jni.h>
#include <string>
#include <iostream>

extern "C"
JNIEXPORT jstring JNICALL
Java_id_ac_ui_cs_mobileprogramming_rahmadiantiopratama_chickennfriend_fragment_HidanganListFragment_sapaan(
        JNIEnv *env, jobject thiz, jstring nama
        ){
    const char *name = env->GetStringUTFChars(nama, NULL);
    char pesan[60] = "Selamat Datang, ";
    jstring hasil;

    strcat(pesan, name);
    env-> ReleaseStringUTFChars(nama, name);
    puts(pesan);
    hasil = env->NewStringUTF(pesan);
    return hasil;
}

