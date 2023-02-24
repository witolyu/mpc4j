package edu.alibaba.mpc4j.s2pc.pso.psu.ghll22;

import edu.alibaba.mpc4j.common.rpc.desc.PtoDesc;
import edu.alibaba.mpc4j.common.rpc.desc.PtoDescManager;

/**
 * GHLL22-AHE-PSU协议信息。论文来源：
 * Dov Gordon, Carmit Hazay, Phi Hung Le, Mingyu Liang. More Efficient (Reusable) Private Set Union. eprint.
 *
 * @author Mingyu Liang
 * @date 2022/12/06
 */
class Ghll22AhePsuPtoDesc implements PtoDesc {
    /**
     * 协议ID, pkePSU protocol ID + 2
     */
    private static final int PTO_ID = Math.abs((int)3242597016769861556L);
    /**
     * 协议名称
     */
    private static final String PTO_NAME = "GHLL22_AHE_PSU";

    /**
     * 协议步骤
     */
    enum PtoStep {
        //TODO modify the protocol steps for AHE
        /**
         * 服务端发送OVDM密钥
         */
        SERVER_SEND_OVDM_KEYS,
        /**
         * 客户端发送公钥
         */
        CLIENT_SEND_PK,
        /**
         * 客户端发送OVDM密文
         */
        CLIENT_SEND_OVDM_KEM,
        /**
         * 客户端发送OVDM负载
         */
        CLIENT_SEND_OVDM_CT,
        /**
         * 客户端发送重随机化密文
         */
        SERVER_SEND_RERAND_KEM,
        /**
         * 客户端发送重随机化负载
         */
        SERVER_SEND_RERAND_CT,
        /**
         * 服务端发送加密元素
         */
        SERVER_SEND_ENC_ELEMENTS,
    }

    /**
     * 单例模式
     */
    private static final Ghll22AhePsuPtoDesc INSTANCE = new Ghll22AhePsuPtoDesc();

    /**
     * 私有构造函数
     */
    private Ghll22AhePsuPtoDesc() {
        // empty
    }

    public static PtoDesc getInstance() {
        return INSTANCE;
    }

    static {
        PtoDescManager.registerPtoDesc(getInstance());
    }

    @Override
    public int getPtoId() {
        return PTO_ID;
    }

    @Override
    public String getPtoName() {
        return PTO_NAME;
    }
}

