package com.daiwei.utils;

/**
 * 常量
 * @author david
 *
 * 2017年7月16日 下午10:43:37
 */
public class Constant {

	/**
	 * 菜单类型
	 * 
	 * @author chenshun
	 * @email sunlightcs@gmail.com
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum SexType {
        /**
         * 男性
         */
    	MALE(0,"男"),
        /**
         * 女性
         */
        FEMAL(1,"女"),
        /**
         * 其他、保密
         */
        OTHER(2,"其他");

        private int value;
        private String name;

        
        public static SexType get(int v) {  
            String str = String.valueOf(v);  
            return get(str);  
        }  
      
        public static SexType get(String str) {  
            for (SexType e : values()) {  
                if(e.toString().equals(str)) {  
                    return e;  
                }  
            }  
            return null;  
        }  
        
        private SexType(int value,String name) {
            this.value = value;
            this.name = name;
        }

        public int getValue() {
            return value;
        }
        
        public String getName() {
            return name;
        }
    }
   
    /**
     * 定时任务状态
     * 
     * @author chenshun
     * @email sunlightcs@gmail.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

}
