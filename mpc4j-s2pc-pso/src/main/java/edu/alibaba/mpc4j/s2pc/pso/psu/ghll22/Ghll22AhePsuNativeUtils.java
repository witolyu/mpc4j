package edu.alibaba.mpc4j.s2pc.pso.psu.ghll22;

import edu.alibaba.mpc4j.common.tool.CommonConstants;

import java.util.List;

public class Ghll22AhePsuNativeUtils {
    static {
        System.loadLibrary(CommonConstants.MPC4J_NATIVE_FHE_NAME);
    }

    private Ghll22AhePsuNativeUtils(){

    }

//    static native List<byte[]> genEncryptionParameters(int modulusDegree, long plainModulus, int[] coeffModulusBits);

    /**
     * 生成加密方案参数和公私钥。
     *
     * @param modulusDegree    多项式阶。
     * @param plainModulus     明文模数。
     * @param coeffModulus 系数模数。
     * @return Encryption parameters and keys。
     */
    static native List<byte[]> genEncryptionKeys(int modulusDegree,
                                                 long plainModulus, long coeffModulus);

    /**
     * Compute Encryption。
     *
     * @param encryptionParams 加密方案参数。
     * @param publicKey        公钥。
     * @param secretKey        私钥。
     * @param setElements       64 bits set input。
     * @return 索引信息。
     */
    static native List<byte[]> encrypt(byte[] encryptionParams, byte[] publicKey, byte[] secretKey, long[] setElements);


//    /**
//     * decrypt。
//     *
//     * @param encryptionParams  加密方案参数。
//     * @param secretKey         私钥。
//     * @param encryptedResponse 密文查询结果。
//     * @return 查询结果。
//     */
//    static native long[] decodeReply(byte[] encryptionParams, byte[] secretKey, byte[] encryptedResponse);


//    rerandomize with randomness

//    plainMultiplication

    static native void sayHello();

}
