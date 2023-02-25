package com.driver;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

 //   @Autowired
  //  OrderRepository orderRepository;
    OrderRepository orderRepository = new OrderRepository();
    public void addOrder(Order order){
        orderRepository.addOrder(order);
    }
    public void addPartner(String PartnerId){
        orderRepository.addPartner(PartnerId);
    }

    public void  addOrderPartnerPair(String OrderId, String PartnerId){
        orderRepository.addOrderPartnerPair(OrderId, PartnerId );
    }

    public Order getOrderById(String OrderId){
       return orderRepository.getOrderById(OrderId);
    }

    public DeliveryPartner getPartnerById(String partnerId){
      return orderRepository.getPartnerById(partnerId );
    }

    public int getOrderCountByPartnerId(String partnerId){
      return orderRepository.getOrderCountByPartnerId(partnerId);
    }

    public List<String> getOrdersByPartnerId(String PartnerId){
        return orderRepository.getOrdersByPartnerId(PartnerId);
    }

    public List<String>getAllOrders(){
        return orderRepository.getAllOrders();
      }

public int getCountOfUnassignedOrders(){
      return orderRepository.getCountOfUnassignedOrders();
}

public int getOrdersLeftAfterGivenTimeByPartnerId(String time,String PartnerId){
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,PartnerId);
}

public String getLastDeliveryTimeByPartnerId(String PartnerId){
        return orderRepository.getLastDeliveryTimeByPartnerId(PartnerId);
}

public void deletePartnerById(String PartnerId){
        orderRepository.deletePartnerById(PartnerId);
}

public void deleteOrderById(String OrderId){
        orderRepository.deleteOrderById(OrderId);
}

}
