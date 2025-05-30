package org.example.service;

import org.example.dao.PurchaseOrderDao;
import org.example.model.Order;
import org.example.model.PurchaseItem;
import org.example.model.PurchaseOrder;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class PurchaseService {
    private final PurchaseOrderDao dao = new PurchaseOrderDao();

    public void checkout(int userId, String address, String paymentMethod, List<Order> cart) {
        PurchaseOrder po = new PurchaseOrder();
        po.setUserId(userId);
        po.setAddress(address);
        po.setPaymentMethod(paymentMethod);

        try {
            int poId = dao.saveOrder(po);
            List<PurchaseItem> items = cart.stream().map(o -> {
                PurchaseItem it = new PurchaseItem();
                it.setPurchaseOrderId(poId);
                it.setOrderId(o.getId());
                it.setPrice(o.getPrice());
                it.setQuantity(1);
                return it;
            }).collect(Collectors.toList());
            dao.saveItems(items);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<PurchaseOrder> listUserOrders(int userId) {
        try {
            return dao.findByUser(userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
