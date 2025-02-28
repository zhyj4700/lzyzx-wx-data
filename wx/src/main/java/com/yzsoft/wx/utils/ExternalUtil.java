package com.yzsoft.wx.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yzsoft.wx.entity.Orders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ExternalUtil {
    RestTemplate template;
    /**
     * 登录获取Token
     * @return
     */
    public String getProcessToken() {
        template = new RestTemplate();
        URI uri = null;
        try {
            uri = new URI("https://open.wecard.qq.com/cgi-bin/oauth2/token");
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept-Charset", "UTF-8");
            headers.set("Content-Type", "application/json; charset=utf-8");
            TokenParams request = new TokenParams();
            request.setApp_key("49A9CD68B4EE449E");
            request.setApp_secret("57F231104B16A60E45C6C20A101973B2");
            request.setGrant_type("client_credentials");
            request.setScope("base");
            request.setOcode("1014652851");
            HttpEntity<Object> requestEntity = new HttpEntity<>(request, headers);
            String s = template.postForObject(uri, requestEntity, String.class);
            JSONObject jsonObject = JSON.parseObject(s);
            return jsonObject.getString("access_token");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "获取token错误";
    }


    /**
     * 根据token获取外部数据
     * @param token
     * @param date
     * @return
     */

    public ArrayList<Orders> getPayList(String token, String date) {
        template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept-Charset", "UTF-8");
        headers.set("Content-Type", "application/json; charset=utf-8");
        String page = "1";
        String pageSize = "500";//默认最大500条每页
        String type= "order";
        Map<String, String> params = new HashMap<String, String>();
        ArrayList<Orders> olist = new ArrayList<>();
        for (int i = 1;i<=1000;i++){
            page = String.valueOf(i);
            params.put("access_token",token);
            params.put("date",date);
            params.put("page",page);
            params.put("pageSize",pageSize);
            params.put("type","order");
            String url = "https://open.wecard.qq.com/cgi-bin/pay/provider/v4/order-detail?access_token={access_token}&date={date}&page={page}&pageSize={pageSize}&type={type}";
            ResponseEntity<String> responseEntity = template.getForEntity(url,String.class,params);
            JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
            // 获取返回的数据data:第一层数据
            JSONObject data = jsonObject.getJSONObject("data");
            // 获取第二层数据，是数组类型
            JSONArray accounts = data.getJSONArray("accounts");
            //获取第三层数据，是对象类型
            JSONObject list = accounts.getJSONObject(0);
            // 获取list数据
            JSONArray payList = list.getJSONArray("list");

            for (int j = 0;j<payList.size();j++){
                Orders ol = new Orders();
                JSONObject json = payList.getJSONObject(j);
                ol.setOrderNo(json.getString("order_no"));
                ol.setParentOrderNo(json.getString("parent_order_no"));
                ol.setUserName(json.getString("user_name"));
                ol.setUserNo(json.getString("user_no"));
                ol.setMchNo(json.getString("mch_no"));
                ol.setMchName(json.getString("mch_name"));
                ol.setDeviceNo(json.getString("device_no"));
                ol.setOrderAmount(json.getString("order_amount"));
                ol.setDealAmount(json.getString("deal_amount"));
                ol.setDealTime(json.getString("deal_time"));
                ol.setOrderTime(json.getString("order_time"));
                ol.setPayTime(json.getString("pay_time"));
                ol.setPayState(json.getString("pay_state"));
                ol.setPayChannel(json.getString("pay_channel"));
                ol.setRefundTime(json.getString("refund_time"));
                ol.setAccId(json.getString("acc_id"));
                ol.setChannelNo(json.getString("channel_no"));
                ol.setChannelRefundNo(json.getString("channel_refund_no"));
                ol.setExt(json.getString("ext"));
                ol.setDiscountAmount(json.getString("discount_amount"));
                ol.setOverAmount(json.getString("over_amount"));
                ol.setReceiveAmount(json.getString("receive_amount"));
                ol.setScene(json.getString("scene"));
                ol.setOrgName(json.getString("org_name"));
                ol.setOrgId(json.getString("org_id"));
                ol.setTicketNum(json.getString("ticket_num"));
                ol.setIsDelay(json.getString("is_delay"));
                ol.setUpdatedAt(json.getString("updated_at"));
                olist.add(ol);
            }
            //System.out.println(data.getString());
            if(data.getString("has_more").equals("1")){
                continue;
            }else{
                break;
            }

        }
        return olist;
    }
}