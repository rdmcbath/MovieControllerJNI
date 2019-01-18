//
// Created by Rebecca.McBath on 1/17/19.
//

#include <jni.h>
#include <string>
#include <android/log.h>
#include "MovieController.h"

extern "C" JNIEXPORT jstring JNICALL
Java_com_mcbath_rebecca_moviecontrollerjni_UI_MainActivity_stringFromJNI(JNIEnv *env, jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
