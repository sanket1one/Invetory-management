package dev.project.orderservice.proxy;

import dev.project.orderservice.model.CartItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "product-service", url = "product-service:8100/api/v1/products")
public interface ProductProxy {
    @GetMapping("/{skuCode}")
    public CartItem getOne(@PathVariable("skuCode") String skuCode);
}
