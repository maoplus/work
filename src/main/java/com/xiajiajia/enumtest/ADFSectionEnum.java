package com.xiajiajia.enumtest;

public enum ADFSectionEnum {
    CONTACTS("Contacts"), PAYMENTINSTRUCTIONS("PaymentInstructions"), ADDITIONALCOMMENTS(
            "AdditionalComments"), LOGO("Logo"), WITHHOLDING_REPORTING(
            "Withholding Tax/FATCA Reporting");
    
    private String name;
    
    private ADFSectionEnum(String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public static ADFSectionEnum fromString(String displayName) {
        if (displayName != null) {
            for (ADFSectionEnum sectionEnum : ADFSectionEnum.values()) {
                if (displayName.equalsIgnoreCase(sectionEnum.getName())) {
                    return sectionEnum;
                }
            }
        }
        return null;
    }
    
}