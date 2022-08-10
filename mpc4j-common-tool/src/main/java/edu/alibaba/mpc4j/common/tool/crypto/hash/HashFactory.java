package edu.alibaba.mpc4j.common.tool.crypto.hash;

import edu.alibaba.mpc4j.common.tool.EnvType;

/**
 * 哈希函数工厂类。
 *
 * @author Weiran Liu
 * @date 2021/12/01
 */
public class HashFactory {
    /**
     * 私有构造函数。
     */
    private HashFactory() {
        // empty
    }

    /**
     * 哈希函数类型
     */
    public enum HashType {
        /**
         * JDK的SHA256
         */
        JDK_SHA256,
        /**
         * 本地的SHA256
         */
        NATIVE_SHA256,
        /**
         * Bouncy Castle的SM3
         */
        BC_SM3,
        /**
         * Bouncy Castle的Blake2b160
         */
        BC_BLAKE_2B_160,
        /**
         * 本地Blake2b160
         */
        NATIVE_BLAKE_2B_160,
        /**
         * 本地Blake3
         */
        NATIVE_BLAKE_3,
    }

    /**
     * 返回哈希函数的单位输出字节长度。
     *
     * @param hashType 哈希函数类型。
     * @return 单位输出字节长度。
     */
    public static int getUnitByteLength(HashType hashType) {
        switch (hashType) {
            case JDK_SHA256:
                return JdkSha256Hash.UNIT_BYTE_LENGTH;
            case NATIVE_SHA256:
                return NativeSha256Hash.UNIT_BYTE_LENGTH;
            case BC_SM3:
                return BcSm3Hash.UNIT_BYTE_LENGTH;
            case BC_BLAKE_2B_160:
                return BcBlake2b160Hash.UNIT_BYTE_LENGTH;
            case NATIVE_BLAKE_2B_160:
                return NativeBlake2b160Hash.UNIT_BYTE_LENGTH;
            case NATIVE_BLAKE_3:
                return NativeBlake3Hash.UNIT_BYTE_LENGTH;
            default:
                throw new IllegalArgumentException("Invalid HashType " + hashType.name());
        }
    }

    /**
     * 创建哈希函数实例。
     *
     * @param hashType         哈希函数类型。
     * @param outputByteLength 输出字节长度。
     * @return 哈希函数实例。
     */
    public static Hash createInstance(HashType hashType, int outputByteLength) {
        switch (hashType) {
            case JDK_SHA256:
                return new JdkSha256Hash(outputByteLength);
            case NATIVE_SHA256:
                return new NativeSha256Hash(outputByteLength);
            case BC_SM3:
                return new BcSm3Hash(outputByteLength);
            case BC_BLAKE_2B_160:
                return new BcBlake2b160Hash(outputByteLength);
            case NATIVE_BLAKE_2B_160:
                return new NativeBlake2b160Hash(outputByteLength);
            case NATIVE_BLAKE_3:
                return new NativeBlake3Hash(outputByteLength);
            default:
                throw new IllegalArgumentException("Invalid HashType " + hashType.name());
        }
    }

    /**
     * 创建哈希函数实例。
     *
     * @param envType 哈希函数类型。
     * @return 哈希函数实例。
     */
    public static Hash createInstance(EnvType envType, int outputByteLength) {
        switch (envType) {
            case STANDARD:
            case STANDARD_JDK:
                return new JdkSha256Hash(outputByteLength);
            case INLAND:
            case INLAND_JDK:
                return new BcSm3Hash(outputByteLength);
            default:
                throw new IllegalArgumentException("Invalid EnvType " + envType.name());
        }
    }
}