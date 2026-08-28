package com.example.demo.tool;

import com.example.demo.entity.ProductOrder;
import com.example.demo.repository.ProductOrderRepository;
import org.springframework.ai.chat.model.ToolContext;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductOrderTool {
    @Autowired
    private ProductOrderRepository repository;

    @Tool(description = "상품 주문 목록을 조회합니다.")
    public String getProductOrders(ToolContext toolContext) {
        var orders = repository.findByMemberName((String) toolContext.getContext().get("username"));
        if (orders.isEmpty()) {
            return "주문 목록이 없습니다.";
        } else {
            StringBuilder builder = new StringBuilder("주문 목록은 다음과 같습니다.");
            for (ProductOrder order : orders) {
                builder.append("주문번호: " + order.getOrderNumber());
                builder.append(", 상품이름: " + order.getProductName());
                builder.append(", 배송주소: " + order.getShippingAddress());
                builder.append(", 배송상태: " + order.getShippingStatus());
                builder.append("\n");
            }
            return builder.toString();
        }
    }

    @Tool(description = "주문을 취소합니다.")
    String cancelProductOrder(@ToolParam(description = "주문번호") String orderNumber, ToolContext toolContext) {
        var productOrder = repository.findByOrderNumberAndMemberName(orderNumber, (String) toolContext.getContext().get("username"));
        if (productOrder.isPresent()) {
            if ("배송중".equals(productOrder.get().getShippingStatus())) {
                return "배송중인 상품은 취소할 수 없습니다.";
            } else {
                repository.delete(productOrder.get());
                return "주문이 취소 되었습니다.";
            }
        } else {
            return "없는 주문 번호입니다.";
        }
    }
}
