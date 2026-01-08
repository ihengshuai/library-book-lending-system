package com.hs.auth.utils;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 随机取名工具类
 * 支持：中文姓名、趣味昵称、字母数字用户名生成
 */
public class NicknameUtil {


    // 随机数实例（线程安全）
    private static final Random RANDOM = new Random();

    // ====================== 中文姓名相关配置 ======================
    // 常用姓氏（可根据需求扩展）
    private static final List<String> FAMILY_NAMES = Arrays.asList(
            "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈",
            "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
            "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏"
    );

    // 常用单字名（偏中性）
    private static final List<String> SINGLE_NAMES = Arrays.asList(
            "伟", "芳", "娜", "敏", "静", "强", "磊", "洋", "杰", "娟",
            "涛", "明", "超", "华", "军", "丽", "红", "平", "刚", "勇"
    );

    // 常用双字名组合（偏中性）
    private static final List<String> DOUBLE_NAMES = Arrays.asList(
            "伟杰", "丽娜", "建华", "秀英", "志强", "红梅", "晓燕", "光明",
            "海涛", "丽娟", "鹏飞", "文静", "宇轩", "雨欣", "浩然", "雨桐"
    );

    // ====================== 趣味昵称相关配置 ======================
    // 昵称前缀（形容词）
    private static final List<String> NICKNAME_PREFIX = Arrays.asList(
            "帅气的", "可爱的", "温柔的", "霸气的", "呆萌的", "高冷的",
            "快乐的", "佛系的", "硬核的", "文艺的", "憨憨的", "酷酷的"
    );

    // 昵称后缀（名词）
    private static final List<String> NICKNAME_SUFFIX = Arrays.asList(
            "小猫咪", "小兔子", "小老虎", "小太阳", "小云朵", "小星星",
            "打工人", "干饭人", "追梦人", "旅行者", "铲屎官", "干饭魂"
    );

    // ====================== 核心方法 ======================

    /**
     * 生成随机中文姓名（支持单字名/双字名随机）
     *
     * @return 随机中文姓名（如：张伟、李丽娜）
     */
    public static String generateChineseName() {
        // 随机选姓氏
        String familyName = FAMILY_NAMES.get(RANDOM.nextInt(FAMILY_NAMES.size()));
        // 50%概率生成单字名，50%概率生成双字名
        boolean isSingle = RANDOM.nextBoolean();
        String givenName = isSingle
                ? SINGLE_NAMES.get(RANDOM.nextInt(SINGLE_NAMES.size()))
                : DOUBLE_NAMES.get(RANDOM.nextInt(DOUBLE_NAMES.size()));
        return familyName + givenName;
    }

    /**
     * 生成趣味随机昵称
     *
     * @return 趣味昵称（如：帅气的小猫咪、佛系的打工人）
     */
    public static String generateFunnyNickname() {
        String prefix = NICKNAME_PREFIX.get(RANDOM.nextInt(NICKNAME_PREFIX.size()));
        String suffix = NICKNAME_SUFFIX.get(RANDOM.nextInt(NICKNAME_SUFFIX.size()));
        return prefix + suffix;
    }

    /**
     * 生成随机用户名（字母+数字组合）
     *
     * @param length 用户名长度（建议6-16位）
     * @return 随机用户名（如：a8s97d、b789sdf89）
     */
    public static String generateUsername(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("用户名长度不能小于1");
        }
        // 字符池：小写字母 + 数字
        String charPool = "abcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(charPool.length());
            sb.append(charPool.charAt(index));
        }
        return sb.toString();
    }

    /**
     * 生成随机用户名（默认长度8位）
     *
     * @return 8位随机用户名
     */
    public static String generateUsername() {
        return generateUsername(8);
    }

    /**
     * 批量生成随机名称
     *
     * @param generator 名称生成器（如 RandomNameUtil::generateChineseName）
     * @param count     生成数量
     * @return 随机名称列表
     */
    public static List<String> batchGenerate(java.util.function.Supplier<String> generator, int count) {
        if (count < 1) {
            throw new IllegalArgumentException("生成数量不能小于1");
        }
        List<String> names = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            names.add(generator.get());
        }
        return names;
    }

    // 测试示例
    public static void main(String[] args) {
        // 生成1个中文姓名
        System.out.println("随机中文姓名：" + generateChineseName());
        // 生成1个趣味昵称
        System.out.println("随机趣味昵称：" + generateFunnyNickname());
        // 生成8位用户名
        System.out.println("随机用户名(8位)：" + generateUsername());
        // 生成10位用户名
        System.out.println("随机用户名(10位)：" + generateUsername(10));
        // 批量生成5个中文姓名
        List<String> batchNames = batchGenerate(NicknameUtil::generateChineseName, 5);
        System.out.println("批量生成的中文姓名：" + batchNames);
    }
}


