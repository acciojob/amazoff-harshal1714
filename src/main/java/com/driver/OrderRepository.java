package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String, Order> orderHashMap;
    HashMap<String, DeliveryPartner> DeliveryPartnerHashMap;

    HashMap<String, List<String>> OrderPartnerHashMap;

    HashMap<String, String> OrderId_PartnerId_Map;

    public OrderRepository() {
        this.orderHashMap = new HashMap<>();
        this.DeliveryPartnerHashMap = new HashMap<>();
        this.OrderPartnerHashMap = new HashMap<>();
        this.OrderId_PartnerId_Map = new HashMap<>();
    }

    public void addOrder(Order order){
        orderHashMap.put(order.getId(), order);
    }

    public void addPartner(String PartnerId) {
        DeliveryPartner deliveryPartner = new DeliveryPartner(PartnerId);

        DeliveryPartnerHashMap.put(PartnerId, deliveryPartner);
    }

    public void addOrderPartnerPair(String OrderId, String PartnerId) {
        int numberOfOrders = DeliveryPartnerHashMap.get(PartnerId).getNumberOfOrders();
        if(!OrderPartnerHashMap.containsKey(PartnerId)) {
            List<String> list = new ArrayList<>();
            list.add(OrderId);
            OrderPartnerHashMap.put(PartnerId, list);
            numberOfOrders++;
        } else {
            OrderPartnerHashMap.get(PartnerId).add(OrderId);
            numberOfOrders++;
        }
        OrderId_PartnerId_Map.put(OrderId, PartnerId);
        DeliveryPartnerHashMap.get(PartnerId).setNumberOfOrders(numberOfOrders);
    }

    public Order getOrderById(String OrderId) {
        return orderHashMap.get(OrderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return DeliveryPartnerHashMap.get(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId) {
        int count = 0;
        for (String s : OrderPartnerHashMap.get(partnerId)) {
            count++;
        }
        return count;
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        List<String> list = new ArrayList<>();
        for (String order : OrderPartnerHashMap.get(partnerId)) {
            list.add(order);
        }
        return list;
    }

    public List<String> getAllOrders() {
        List<String> list = new ArrayList<>();
        for (String order : orderHashMap.keySet()) {
            list.add(order);
        }
        return list;
    }

    public int getCountOfUnassignedOrders() {
        int totalNumberOfOrders = 0;
        for (String s : DeliveryPartnerHashMap.keySet()) {
            int n = DeliveryPartnerHashMap.get(s).getNumberOfOrders();
            totalNumberOfOrders += n;
        }
        return orderHashMap.size() - totalNumberOfOrders;
        //int count = 0;
//        for(String orderID : orderHashMap.keySet()){
//            if(!orderPartnerHashMap.containsValue(orderID)){
//                count++;
//            }
//        }
//        return count;
    }


    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
        int count = 0;
        int deliveryTime = Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3));
        for (String s : OrderPartnerHashMap.get(partnerId)) {
            if (orderHashMap.get(s).getDeliveryTime() > deliveryTime) {
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        List<String> list = OrderPartnerHashMap.get(partnerId);
        int n = list.size();
        String s = list.get(n-1);
        int time = orderHashMap.get(s).getDeliveryTime();

        return Integer.toString(time);
    }

    public void deletePartnerById(String PartnerId){
        OrderPartnerHashMap.remove(PartnerId);
        DeliveryPartnerHashMap.remove(PartnerId);
    }

    public void deleteOrderById(String orderId){
        for(String s : OrderPartnerHashMap.keySet()){
            boolean flag =false;
            for(String s1 : OrderPartnerHashMap.get(s)){
                if(s1.equals(orderId)){
                    OrderPartnerHashMap.remove(s);
                    flag=true;
                    break;
                }
            }
            if(flag==true) break;
//            if(orderPartnerHashMap.get(s).contains(orderId)){
//                orderPartnerHashMap.remove(s);
//            }
        }
        orderHashMap.remove(orderId);
    }
}


