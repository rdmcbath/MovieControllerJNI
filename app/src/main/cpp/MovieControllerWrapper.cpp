//
// Created by Rebecca.McBath on 1/17/19.
//

#include <jni.h>
#include <android/log.h>
#include "MovieControllerWrapper.h"
#include "MovieController.h"
#include "handle.h"

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_Actor
 * Method:    getAge
 * Signature: ()I
 */
extern "C" JNIEXPORT jint JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Actor_getAge(JNIEnv *env, jobject obj) {
    auto *p = getHandle<movies::Actor>(env, obj);
    if(p == 0) return 0;
    return p->age;
}

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_Actor
 * Method:    getName
 * Signature: ()Ljava/lang/String;
 */
extern "C" JNIEXPORT jstring JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Actor_getName
        (JNIEnv *env, jobject obj) {
    auto *p = getHandle<movies::Actor>(env, obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->name.c_str());
}

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_Actor
 * Method:    getImageUrl
 * Signature: ()Ljava/lang/String;
 */
extern "C" JNIEXPORT jstring JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Actor_getImageUrl
        (JNIEnv *env, jobject obj) {
    movies::Actor *p = getHandle<movies::Actor>(env, obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->imageUrl.c_str());
}

/*
* Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_Movie
* Method:    getName
* Signature: ()Ljava/lang/String;
*/
extern "C" JNIEXPORT jstring JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Movie_getName
        (JNIEnv *env, jobject obj) {
    movies::Movie *p = getHandle<movies::Movie>(env, obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->name.c_str());
}

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_Movie
 * Method:    getLastUpdate
 * Signature: ()I
 */
extern "C" JNIEXPORT jint JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Movie_getLastUpdate
        (JNIEnv *env, jobject obj) {
    movies::Movie *p = getHandle<movies::Movie>(env, obj);
    return p->lastUpdated;
}

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_manager_MovieManager
 * Method:    createManager
 * Signature: ()J
 */
extern "C" JNIEXPORT jlong JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Manager_MovieManager_jniCreateManager
        (JNIEnv *env, jclass type) {
    movies::MovieController *controller = new movies::MovieController();
    if(controller == 0) return 0;

    return (jlong) controller;
}

extern "C" /*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_manager_MovieManager
 * Method:    delete
 */
void Java_com_mcbath_rebecca_moviecontrollerjni_Manager_MovieManager_delete(JNIEnv *env, jobject obj) {
    movies::MovieController *p = getHandle<movies::MovieController>(env, obj);
    setHandle<movies::MovieController>(env, obj, 0);
    delete p;
}

/*
* Class:     Java_com_mcbath_rebecca_moviecontrollerjni_manager_MovieManager
* Method:    getMovieList
* Signature: ()J
*/
extern "C" JNIEXPORT jlongArray JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Manager_MovieManager_jniGetMovieList
        (JNIEnv *env, jobject obj) {

    movies::MovieController *controller = getHandle<movies::MovieController>(env, obj);
    if(controller == 0) return 0;

    std::vector<movies::Movie*> movies = controller->getMovies();

    int size = movies.size();
    jlongArray result = env->NewLongArray(size);

    jlong arr [size];
    //convert int[] to jlong[]
    for(int i = 0; i< size; i++) {
        arr[i] = (jlong) movies[i];
    }

    env-> SetLongArrayRegion(result,0,size,&arr[0]);

    return result;
}

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_manager_MovieManager
 * Method:    getMovieDetails
 * Signature: (Ljava/lang/String;)J
 */
extern "C" JNIEXPORT jlong JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Manager_MovieManager_jniGetMovieDetails
        (JNIEnv * env, jobject obj, jstring movie_name) {

    movies::MovieController *controller = getHandle<movies::MovieController>(env, obj);
    if(controller == 0) return 0;

    const char *str = env->GetStringUTFChars(movie_name,0);
    movies::MovieDetail *detail = controller->getMovieDetail(str);

    return (jlong) detail;
}

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_MovieDetail
 * Method:    getName
 * Signature: ()Ljava/lang/String;
 */
extern "C" JNIEXPORT jstring JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_MovieDetail_getName
        (JNIEnv * env, jobject obj){
    movies::MovieDetail *p = getHandle<movies::MovieDetail>(env,obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->name.c_str());
}

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_MovieDetail
 * Method:    getScore
 * Signature: ()F
 */
extern "C" JNIEXPORT jfloat JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_MovieDetail_getScore
        (JNIEnv * env, jobject obj){
    movies::MovieDetail *p = getHandle<movies::MovieDetail>(env,obj);
    if(p == 0) return 0;
    return (jfloat)p->score;
}

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_MovieDetail
 * Method:    getDescription
 * Signature: ()Ljava/lang/String;
 */
extern "C" JNIEXPORT jstring JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_MovieDetail_getDescription
        (JNIEnv * env, jobject obj){
    movies::MovieDetail *p = getHandle<movies::MovieDetail>(env,obj);
    if(p == 0) return 0;

    return env->NewStringUTF(p->description.c_str());
}

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_MovieDetail
 * Method:    getActors
 * Signature: ()[J
 */
extern "C" JNIEXPORT jlongArray JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_MovieDetail_getActors
        (JNIEnv * env, jobject obj){

    movies::MovieDetail *p = getHandle<movies::MovieDetail>(env,obj);
    if(p == 0) return 0;

    std::vector<movies::Actor*> actors = p->actors;

    int size = actors.size();
    jlongArray result = env->NewLongArray(size);

    jlong arr [size];
    //convert int[] to jlong[]
    for(int i = 0; i< size; i++) {
        arr[i] = (jlong) actors[i];
    }

    env-> SetLongArrayRegion(result,0,size,&arr[0]);

    return result;
}
