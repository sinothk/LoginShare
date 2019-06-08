package net.sourceforge.simcpux.bean;

/**
 * 类描述：微信支付请求
 * 创建人：田浩宏
 * 创建时间：2017/8/21 0021 上午 0:29
 * 修改人：
 * 修改时间：
 * 修改备注：
 */

public class AppPayRequest {

    private String appid ;//	String(32)	是	wx8888********8888	微信开放平台审核通过的应用APPID
    private String partnerid;//	String(32)	是	1900000109	微信支付分配的商户号
    private String prepayid;//	String(32)	是	WX1217752501201407033233368018	微信返回的支付交易会话ID
    private String packageValue ;//String(128)	是	Sign=WXPay	暂填写固定值Sign=WXPay
    private String noncestr;//	String(32)	是	5K8264ILTKCH16CQ2502SI8ZNMTM67VS	随机字符串，不长于32位。推荐随机数生成算法
    private String timestamp;//	String(10)	是	1412000000	时间戳，请见接口规则-参数规定
    private String sign;//	String(32)	是	C380BEC2BFD727A4B6845133519F3AD6	签名，详见签名生成算法注意：签名方式一定要与统一下单接口使用的一致


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getPrepayid() {
        return prepayid;
    }

    public void setPrepayid(String prepayid) {
        this.prepayid = prepayid;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getNoncestr() {
        return noncestr;
    }

    public void setNoncestr(String noncestr) {
        this.noncestr = noncestr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
