package com.yzsoft.wx.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yzsoft.wx.entity.Orders;
import com.yzsoft.wx.service.OrdersService;
import com.yzsoft.wx.utils.ExternalUtil;
import com.yzsoft.wx.utils.Result;
import com.yzsoft.wx.vo.OrdersQueryVo;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author easyzzh
 * @since 2024-10-30
 */
@RestController
@RequestMapping("/api")
public class OrdersController {

    @Resource
    private OrdersService ordersService;


    @PostMapping("/orders/list")
    public Result getOrderLists(HttpServletResponse response, HttpServletRequest request){
        OrdersQueryVo queryVo = new OrdersQueryVo();
        String pageSize = request.getParameter("pageSize");
        if(pageSize!=null){
            queryVo.setPageSize(Long.parseLong(pageSize));
        }
        String name = request.getParameter("username");
        String startDealTime = request.getParameter("startDealTime");
        String endDealTime = request.getParameter("endDealTime");
        String userId = request.getParameter("userId");
        String payState = request.getParameter("payState");
        IPage<Orders> page = new Page<>(queryVo.getPageNo(),queryVo.getPageSize());
        return Result.ok(ordersService.getOrdersListPyPage(page,name,startDealTime,endDealTime,userId,payState)).message("访问成功");
    }


    @Scheduled(cron = "00 01 00 * * *")
    public void executeTask() throws Exception{
        String msg = "";
        try {
            getApiData();
        }catch (Exception e){
            throw e;
        }
    }

    @PostMapping("/orders/savedata")
    public Result getApiData(){
        //格式化时间日期
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        //获取当前日期
        String currentDate = simpleDateFormat.format(new Date());
        //获取前一天日期
        String lastDate = String.valueOf(Integer.parseInt(currentDate) - 1);

        ExternalUtil utils = new ExternalUtil();
        // 获取Token
        String token = utils.getProcessToken();

        ArrayList<Orders> olist = utils.getPayList(token,lastDate);

        if(olist.size()>0){
            for (int i = 0;i<olist.size();i++){
                Orders ol = new Orders();
                ol.setOrderNo(olist.get(i).getOrderNo());
                ol.setParentOrderNo(olist.get(i).getParentOrderNo());
                ol.setUserName(olist.get(i).getUserName());
                ol.setUserNo(olist.get(i).getUserNo());
                ol.setMchNo(olist.get(i).getMchNo());
                ol.setMchName(olist.get(i).getMchName());
                ol.setDeviceNo(olist.get(i).getDeviceNo());
                ol.setOrderAmount(olist.get(i).getOrderAmount());
                ol.setDealAmount(olist.get(i).getDealAmount());
                ol.setDealTime(olist.get(i).getDealTime());
                ol.setOrderTime(olist.get(i).getOrderTime());
                ol.setPayTime(olist.get(i).getPayTime());
                ol.setPayState(olist.get(i).getPayState());
                ol.setPayChannel(olist.get(i).getPayChannel());
                ol.setRefundTime(olist.get(i).getRefundTime());
                ol.setAccId(olist.get(i).getAccId());
                ol.setChannelNo(olist.get(i).getChannelNo());
                ol.setChannelRefundNo(olist.get(i).getChannelRefundNo());
                ol.setExt(olist.get(i).getExt());
                ol.setDiscountAmount(olist.get(i).getDiscountAmount());
                ol.setOverAmount(olist.get(i).getOverAmount());
                ol.setReceiveAmount(olist.get(i).getReceiveAmount());
                ol.setScene(olist.get(i).getScene());
                ol.setOrgName(olist.get(i).getOrgName());
                ol.setOrgId(olist.get(i).getOrgId());
                ol.setTicketNum(olist.get(i).getTicketNum());
                ol.setIsDelay(olist.get(i).getIsDelay());
                ol.setUpdatedAt(olist.get(i).getUpdatedAt());
                ordersService.save(ol);
            }
            return Result.ok(olist).message("数据存储成功！！");
        }else{
            return Result.ok(olist).message("数据为空");
        }

    }

    @PostMapping("/orders/insert")
    public Result insert(HttpServletResponse response, HttpServletRequest request, @RequestBody OrdersRequest ordersRequest) {

        try {
            ArrayList<Orders> olist = ordersRequest.getOrders();
            if (olist.size() > 0) {
                for (int i = 0; i < olist.size(); i++) {
                    Orders ol = new Orders();
                    ol.setOrderNo(olist.get(i).getOrderNo());
                    ol.setUserNo(olist.get(i).getUserNo());
                    ol.setDealAmount(olist.get(i).getDealAmount());
                    // 设置其他属性...
                    ordersService.save(ol);
                }
                return Result.ok(olist).message("数据存储成功！！");
            } else {
                return Result.ok(olist).message("数据为空");
            }
        } catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            e.printStackTrace();
            return Result.error().message("数据存储失败：" + e.getMessage());
        }
    }
        /*
        if(olist.size()>0){
            for (int i = 0;i<olist.size();i++){
                Orders ol = new Orders();
                ol.setOrderNo(olist.get(i).getOrderNo());
                ol.setParentOrderNo(olist.get(i).getParentOrderNo());
                ol.setUserName(olist.get(i).getUserName());
                ol.setUserNo(olist.get(i).getUserNo());
                ol.setMchNo(olist.get(i).getMchNo());
                ol.setMchName(olist.get(i).getMchName());
                ol.setDeviceNo(olist.get(i).getDeviceNo());
                ol.setOrderAmount(olist.get(i).getOrderAmount());
                ol.setDealAmount(olist.get(i).getDealAmount());
                ol.setDealTime(olist.get(i).getDealTime());
                ol.setOrderTime(olist.get(i).getOrderTime());
                ol.setPayTime(olist.get(i).getPayTime());
                ol.setPayState(olist.get(i).getPayState());
                ol.setPayChannel(olist.get(i).getPayChannel());
                ol.setRefundTime(olist.get(i).getRefundTime());
                ol.setAccId(olist.get(i).getAccId());
                ol.setChannelNo(olist.get(i).getChannelNo());
                ol.setChannelRefundNo(olist.get(i).getChannelRefundNo());
                ol.setExt(olist.get(i).getExt());
                ol.setDiscountAmount(olist.get(i).getDiscountAmount());
                ol.setOverAmount(olist.get(i).getOverAmount());
                ol.setReceiveAmount(olist.get(i).getReceiveAmount());
                ol.setScene(olist.get(i).getScene());
                ol.setOrgName(olist.get(i).getOrgName());
                ol.setOrgId(olist.get(i).getOrgId());
                ol.setTicketNum(olist.get(i).getTicketNum());
                ol.setIsDelay(olist.get(i).getIsDelay());
                ol.setUpdatedAt(olist.get(i).getUpdatedAt());
                ordersService.save(ol);
            }
            return Result.ok(olist).message("数据存储成功！！");
        }else{
            return Result.ok(olist).message("数据为空");
        }

    }

         */
}

class OrdersRequest {
    private ArrayList<Orders> orders;

    public ArrayList<Orders> getOrders() {
        return orders;
    }

    public void setOrders(ArrayList<Orders> orders) {
        this.orders = orders;
    }
}