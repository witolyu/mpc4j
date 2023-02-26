#include "edu_alibaba_mpc4j_s2pc_pso_psu_ghll22_Ghll22AhePsuNativeUtils.h"
//#include "../apsi.h"
//#include "../serialize.h"
//#include "../utils.h"
//#include "../polynomials.h"

using namespace std;
using namespace seal;

JNIEXPORT jobject JNICALL Java_edu_alibaba_mpc4j_s2pc_pso_psu_ghll22_Ghll22AhePsuNativeUtils_genEncryptionParameters(
    JNIEnv *env, jclass, jint poly_modulus_degree, jlong plain_modulus, jintArray coeff_modulus_bits) {
    uint32_t coeff_size = env->GetArrayLength(coeff_modulus_bits);
    jint* coeff_ptr = env->GetIntArrayElements(coeff_modulus_bits, JNI_FALSE);
    vector<int> bit_sizes(coeff_ptr, coeff_ptr + coeff_size);
    EncryptionParameters parms = generate_encryption_parameters(scheme_type::bfv, poly_modulus_degree, plain_modulus, bit_sizes);
    SEALContext context = SEALContext(parms);
    KeyGenerator key_gen = KeyGenerator(context);
    const SecretKey &secret_key = key_gen.secret_key();
    Serializable relin_keys = key_gen.create_relin_keys();
    Serializable public_key = key_gen.create_public_key();
    return serialize_relin_public_secret_keys(env, parms, relin_keys, public_key, secret_key);
}

JNIEXPORT void JNICALL Java_edu_alibaba_mpc4j_s2pc_pso_psu_ghll22_Ghll22AhePsuNativeUtils_sayHello
  (JNIEnv* env, jobject thisObject) {
    std::cout << "Hello from C++ !!" << std::endl;
}


