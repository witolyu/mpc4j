package edu.alibaba.mpc4j.common.tool.crypto.hash;

import com.google.common.base.Preconditions;
import edu.alibaba.mpc4j.common.tool.CommonConstants;
import edu.alibaba.mpc4j.common.tool.crypto.hash.HashFactory.HashType;
import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;

/**
 * 哈希函数一致性测试。
 *
 * @author Weiran Liu
 * @date 2022/01/07
 */
@RunWith(Parameterized.class)
@Ignore
public class HashConsistencyTest {
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
        // BLAKE_2B_160
        configurationParams.add(new Object[] {"Blake2b160", HashType.BC_BLAKE_2B_160, HashType.NATIVE_BLAKE_2B_160});
        // SHA256
        configurationParams.add(new Object[] {"Sha256", HashType.JDK_SHA256, HashType.NATIVE_SHA256});

        return configurationParams;
    }

    /**
     * JDK类型
     */
    private final HashType jdkType;
    /**
     * 本地类型
     */
    private final HashType nativeType;

    public HashConsistencyTest(String name, HashType jdkType, HashType nativeType) {
        Preconditions.checkArgument(StringUtils.isNotBlank(name));
        this.jdkType = jdkType;
        this.nativeType = nativeType;
    }

    @Test
    public void testInputConsistency() {
        testConsistency(CommonConstants.STATS_BYTE_LENGTH, CommonConstants.BLOCK_BYTE_LENGTH);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH - 1, CommonConstants.BLOCK_BYTE_LENGTH);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, CommonConstants.BLOCK_BYTE_LENGTH);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH + 1, CommonConstants.BLOCK_BYTE_LENGTH);
        testConsistency(2 * CommonConstants.BLOCK_BYTE_LENGTH - 1, CommonConstants.BLOCK_BYTE_LENGTH);
        testConsistency(2 * CommonConstants.BLOCK_BYTE_LENGTH, CommonConstants.BLOCK_BYTE_LENGTH);
        testConsistency(2 * CommonConstants.BLOCK_BYTE_LENGTH + 1, CommonConstants.BLOCK_BYTE_LENGTH);
    }

    @Test
    public void testOutputConsistency() {
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, 1);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, CommonConstants.STATS_BYTE_LENGTH - 1);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, CommonConstants.STATS_BYTE_LENGTH);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, CommonConstants.STATS_BYTE_LENGTH + 1);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, CommonConstants.BLOCK_BYTE_LENGTH - 1);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, CommonConstants.BLOCK_BYTE_LENGTH);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, CommonConstants.BLOCK_BYTE_LENGTH + 1);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, 2 * CommonConstants.BLOCK_BYTE_LENGTH - 1);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, 2 * CommonConstants.BLOCK_BYTE_LENGTH);
        testConsistency(CommonConstants.BLOCK_BYTE_LENGTH, 2 * CommonConstants.BLOCK_BYTE_LENGTH + 1);
    }

    private void testConsistency(int messageByteLength, int outputByteLength) {
        if (outputByteLength <= HashFactory.getUnitByteLength(jdkType)) {
            Hash jdkHash = HashFactory.createInstance(jdkType, outputByteLength);
            Hash nativeHash = HashFactory.createInstance(nativeType, outputByteLength);
            for (int i = 0; i < MAX_RANDOM_ROUND; i++) {
                byte[] message = new byte[messageByteLength];
                SECURE_RANDOM.nextBytes(message);
                byte[] jdkOutput = jdkHash.digestToBytes(message);
                byte[] nativeOutput = nativeHash.digestToBytes(message);
                Assert.assertArrayEquals(jdkOutput, nativeOutput);
            }
        }
    }
}
