package com.zhang.util;

/**
 * @author by SUNS3T
 * @Classname GlobalConfig
 * @Description TODO
 */
public class GlobalConfig {
    public static final Boolean Test = true;
    public enum ResponseCode{
        SUCCESS(0,"成功"),
        ERROR(1,"失败");

        public final int code;
        public final String desc;

        ResponseCode(int code, String desc) {
            this.code = code;
            this.desc = desc;
        }

        public int getCode(){
            return code;
        }
        public String getDesc(){
            return desc;
        }
    }
}
