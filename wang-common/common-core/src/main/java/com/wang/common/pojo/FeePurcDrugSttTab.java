package com.wang.common.pojo;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 费用较高的集中采购药品统计表
 * @TableName fee_purc_drug_stt_tab
 */
@TableName(value ="fee_purc_drug_stt_tab")
@Data
public class FeePurcDrugSttTab implements Serializable {
    /**
     * RID
     */
    @TableId(value = "RID")
    private String rid;

    /**
     * 医保区划
     */
    @TableField(value = "ADMDVS")
    private String admdvs;

    /**
     * 医保区划名称
     */
    @TableField(value = "ADMDVS_NAME")
    private String admdvsName;

    /**
     * 药品通用名称
     */
    @TableField(value = "DRUG_GENNAME")
    private String drugGenname;

    /**
     * 企业名称
     */
    @TableField(value = "ENTP_NAME")
    private String entpName;

    /**
     * 规格
     */
    @TableField(value = "SPEC")
    private String spec;

    /**
     * 包装
     */
    @TableField(value = "PAC")
    private String pac;

    /**
     * 医药代码
     */
    @TableField(value = "DRUG_CODE")
    private String drugCode;

    /**
     * 属性
     */
    @TableField(value = "ATTR")
    private String attr;

    /**
     * 排序
     */
    @TableField(value = "SRT")
    private String srt;

    /**
     * 门诊费用
     */
    @TableField(value = "OTP_FEE")
    private Integer otpFee;

    /**
     * 住院费用
     */
    @TableField(value = "INPF")
    private Integer inpf;

    /**
     * 药店费用
     */
    @TableField(value = "PHAC_FEE")
    private Integer phacFee;

    /**
     * 创建时间
     */
    @TableField(value = "CRTE_TIME")
    private Date crteTime;

    /**
     * 修改时间
     */
    @TableField(value = "MODI_TIME")
    private Date modiTime;

    /**
     * 
     */
    @TableField(value = "SETL_TIME")
    private String setlTime;

    private static final long serialVersionUID = 1L;
}