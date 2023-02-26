package edu.alibaba.mpc4j.s2pc.pso.psu.ghll22;

import edu.alibaba.mpc4j.common.tool.CommonConstants;

import java.util.List;

public class Ghll22AhePsuNativeUtils {
    static {
        System.loadLibrary(CommonConstants.MPC4J_NATIVE_FHE_NAME);
    }

    private Ghll22AhePsuNativeUtils(){

    }

    static native List<byte[]> genEncryptionParameters(int modulusDegree, long plainModulus, int[] coeffModulusBits);

    static native void sayHello();

}
