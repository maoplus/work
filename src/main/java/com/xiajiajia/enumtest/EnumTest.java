package com.xiajiajia.enumtest;
import java.sql.Types;
public class EnumTest {
    public enum DeliveryScheduleEnum {
        ON_EXECUTION_OR_UPLOAD("On Execution/Upload"), ON_TRADE_SETTLEMENT(
                "On Trade Settlement");
        
        private String displayName;
        
        private DeliveryScheduleEnum(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return this.displayName;
        }
        public static DeliveryScheduleEnum fromString(String displayNameFromUI) {
            if (displayNameFromUI != null) {
                for (DeliveryScheduleEnum deliveryScheduleEnum : DeliveryScheduleEnum
                        .values()) {
                    if (displayNameFromUI.equalsIgnoreCase(deliveryScheduleEnum
                            .getDisplayName())) {
                        return deliveryScheduleEnum;
                    }
                }
            }
            return null;
        }
    }
    
    public enum DeliveryTypeEnum {
        INDIVIDUAL_PDF("Individual PDF's"), ZIPPED_PDF("Zipped PDF's"), MERGED_PDF(
                "Merged PDF's");
        
        private String displayName;
        
        private DeliveryTypeEnum(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return this.displayName;
        }
        
        public static DeliveryTypeEnum fromString(String displayNameFromUI) {
            if (displayNameFromUI != null) {
                for (DeliveryTypeEnum deliveryTypeEnum : DeliveryTypeEnum
                        .values()) {
                    if (displayNameFromUI.equalsIgnoreCase(deliveryTypeEnum
                            .getDisplayName())) {
                        return deliveryTypeEnum;
                    }
                }
            }
            return null;
        }
        
    }
    
    public static void main(String[] args) {
        System.out.println( );
        
    }
}
