/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class edu_alibaba_mpc4j_s2pc_pir_keyword_cmg21_Cmg21KwPirNativeServer */

#ifndef _Included_edu_alibaba_mpc4j_s2pc_pir_keyword_cmg21_Cmg21KwPirNativeServer
#define _Included_edu_alibaba_mpc4j_s2pc_pir_keyword_cmg21_Cmg21KwPirNativeServer
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     edu_alibaba_mpc4j_s2pc_pir_keyword_cmg21_Cmg21KwPirNativeServer
 * Method:    computeEncryptedPowers
 * Signature: ([B[BLjava/util/List;[[I[II)Ljava/util/List;
 */
JNIEXPORT jobject JNICALL Java_edu_alibaba_mpc4j_s2pc_pir_keyword_cmg21_Cmg21KwPirNativeServer_computeEncryptedPowers
  (JNIEnv *, jclass, jbyteArray, jbyteArray, jobject, jobjectArray, jintArray, jint);

/*
 * Class:     edu_alibaba_mpc4j_s2pc_pir_keyword_cmg21_Cmg21KwPirNativeServer
 * Method:    computeMatches
 * Signature: ([B[B[B[[JLjava/util/List;I)[B
 */
JNIEXPORT jbyteArray JNICALL Java_edu_alibaba_mpc4j_s2pc_pir_keyword_cmg21_Cmg21KwPirNativeServer_computeMatches
  (JNIEnv *, jclass, jbyteArray, jbyteArray, jbyteArray, jobjectArray, jobject, jint);

/*
 * Class:     edu_alibaba_mpc4j_s2pc_pir_keyword_cmg21_Cmg21KwPirNativeServer
 * Method:    computeMatchesNaiveMethod
 * Signature: ([B[B[B[[JLjava/util/List;)[B
 */
JNIEXPORT jbyteArray JNICALL Java_edu_alibaba_mpc4j_s2pc_pir_keyword_cmg21_Cmg21KwPirNativeServer_computeMatchesNaiveMethod
  (JNIEnv *, jclass, jbyteArray, jbyteArray, jbyteArray, jobjectArray, jobject);

#ifdef __cplusplus
}
#endif
#endif
