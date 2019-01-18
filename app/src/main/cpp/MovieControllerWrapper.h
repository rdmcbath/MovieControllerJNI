//
// Created by Rebecca.McBath on 1/17/19.
//

#include <jni.h>

#ifndef HIGHRISE_MOVIECONTROLLERWRAPPER_H
#define HIGHRISE_MOVIECONTROLLERWRAPPER_H

#ifdef __cplusplus
#endif

extern "C" {

/*
 * Class:     cJava_com_mcbath_rebecca_moviecontrollerjni_model_Actor
 * Method:    getAge
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Actor_getAge (JNIEnv *, jobject);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_Actor
 * Method:    getName
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Actor_getName
        (JNIEnv *, jobject);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_Actor
 * Method:    getImageUrl
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Actor_getImageUrl
        (JNIEnv *, jobject);

/*
* Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_Movie
* Method:    getName
* Signature: ()Ljava/lang/String;
*/
JNIEXPORT jstring JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Movie_getName
        (JNIEnv *, jobject);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_Movie
 * Method:    getLastUpdate
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_Movie_getLastUpdate
        (JNIEnv *, jobject);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_MovieDetail
 * Method:    getScore
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_MovieDetail_getScore
        (JNIEnv *, jobject);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_MovieDetail
 * Method:    getActors
 * Signature: ()[J
 */
JNIEXPORT jlongArray JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_MovieDetail_getActors
        (JNIEnv *, jobject);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_model_MovieDetail
 * Method:    getDescription
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Model_MovieDetail_getDescription
        (JNIEnv *, jobject);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_manager_MovieManager
 * Method:    jniCreateManager
 */
JNIEXPORT jlong JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Manager_MovieManager_jniCreateManager
        (JNIEnv *, jclass);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_manager_MovieManager
 * Method:    delete
 */
void Java_com_mcbath_rebecca_moviecontrollerjni_Manager_MovieManager_delete(JNIEnv *, jobject);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_manager_MovieManager
 * Method:    getMovieList
 * Signature: ()[J
 */
JNIEXPORT jlongArray JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Manager_MovieManager_jniGetMovieList
        (JNIEnv *, jobject);

/*
 * Class:     Java_com_mcbath_rebecca_moviecontrollerjni_manager_MovieManager
 * Method:    getMovieDetails
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_com_mcbath_rebecca_moviecontrollerjni_Manager_MovieManager_jniGetMovieDetails
        (JNIEnv *, jobject, jstring);

#ifdef __cplusplus
}
#endif

#endif //HIGHRISE_MOVIECONTROLLERWRAPPER_H
