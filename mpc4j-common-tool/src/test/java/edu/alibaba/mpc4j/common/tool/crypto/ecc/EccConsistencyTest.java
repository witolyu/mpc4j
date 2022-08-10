package edu.alibaba.mpc4j.common.tool.crypto.ecc;

import com.google.common.base.Preconditions;
import edu.alibaba.mpc4j.common.tool.CommonConstants;
import edu.alibaba.mpc4j.common.tool.crypto.ecc.EccFactory.EccType;
import edu.alibaba.mpc4j.common.tool.utils.BigIntegerUtils;
import org.apache.commons.lang3.StringUtils;
import org.bouncycastle.math.ec.ECPoint;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 椭圆曲线一致性测试。
 *
 * @author Weiran Liu
 * @date 2022/01/07
 */
@RunWith(Parameterized.class)
@Ignore
public class EccConsistencyTest {
    /**
     * 最大随机轮数
     */
    private static final int MAX_RANDOM_ROUND = 100;
    /**
     * 随机状态
     */
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> configurations() {
        Collection<Object[]> configurationParams = new ArrayList<>();
        // SEC_P256_K1
        configurationParams.add(new Object[] {"SecP256k1", EccType.BC_SEC_P256_K1, EccType.MCL_SEC_P256_K1});
        // SEC_P256_R1
        configurationParams.add(new Object[] {"SecP256r1", EccType.BC_SEC_P256_R1, EccType.MCL_SEC_P256_R1});

        return configurationParams;
    }

    /**
     * JDK类型
     */
    private final EccType jdkType;
    /**
     * 本地类型
     */
    private final EccType nativeType;

    public EccConsistencyTest(String name, EccType jdkType, EccType nativeType) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name));
        this.jdkType = jdkType;
        this.nativeType = nativeType;
    }

    @Test
    public void testEcDomainParameters() {
        Ecc jdkEcc = EccFactory.createInstance(jdkType);
        Ecc mclEcc = EccFactory.createInstance(nativeType);
        Assert.assertEquals(jdkEcc.getEcDomainParameters(), mclEcc.getEcDomainParameters());
    }

    @Test
    public void testHashToPoint() {
        Ecc jdkEcc = EccFactory.createInstance(jdkType);
        Ecc mclEcc = EccFactory.createInstance(nativeType);
        for (int i = 0; i < MAX_RANDOM_ROUND; i++) {
            byte[] message = new byte[CommonConstants.BLOCK_BYTE_LENGTH];
            SECURE_RANDOM.nextBytes(message);
            ECPoint jdkHash = jdkEcc.hashToCurve(message);
            ECPoint mclHash = mclEcc.hashToCurve(message);
            Assert.assertEquals(jdkHash, mclHash);
        }
    }

    @Test
    public void testMultiply() {
        Ecc jdkEcc = EccFactory.createInstance(jdkType);
        Ecc mclEcc = EccFactory.createInstance(nativeType);
        BigInteger n = jdkEcc.getN();
        for (int i = 0; i < MAX_RANDOM_ROUND; i++) {
            BigInteger alpha = BigIntegerUtils.randomPositive(n, SECURE_RANDOM);
            ECPoint jdkMultiply = jdkEcc.multiply(jdkEcc.getG(), alpha);
            ECPoint mclMultiply = mclEcc.multiply(mclEcc.getG(), alpha);
            Assert.assertEquals(jdkMultiply, mclMultiply);
        }
    }

    @Test
    public void testPrecompute() {
        Ecc jdkEcc = EccFactory.createInstance(jdkType);
        Ecc mclEcc = EccFactory.createInstance(nativeType);
        BigInteger n = jdkEcc.getN();
        jdkEcc.precompute(jdkEcc.getG());
        mclEcc.precompute(mclEcc.getG());
        for (int i = 0; i < MAX_RANDOM_ROUND; i++) {
            BigInteger alpha = BigIntegerUtils.randomPositive(n, SECURE_RANDOM);
            ECPoint jdkMultiply = jdkEcc.multiply(jdkEcc.getG(), alpha);
            ECPoint mclMultiply = mclEcc.multiply(mclEcc.getG(), alpha);
            Assert.assertEquals(jdkMultiply, mclMultiply);
        }
        jdkEcc.destroyPrecompute(jdkEcc.getG());
        mclEcc.destroyPrecompute(mclEcc.getG());
    }
}
