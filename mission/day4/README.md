# 미션 - Day4

* [1. 아래 코드와 설명을 보고, [섹션 3. 논리, 사고의 흐름]에서 이야기하는 내용을 중심으로 읽기 좋은 코드로 리팩토링해 봅시다.](#1-아래-코드와-설명을-보고-섹션-3-논리-사고의-흐름에서-이야기하는-내용을-중심으로-읽기-좋은-코드로-리팩토링해-봅시다)
* [2. SOLID에 대하여 자기만의 언어로 정리해 봅시다.](#2-solid에-대하여-자기만의-언어로-정리해-봅시다)
---
### 1. 아래 코드와 설명을 보고, [섹션 3. 논리, 사고의 흐름]에서 이야기하는 내용을 중심으로 읽기 좋은 코드로 리팩토링해 봅시다.
#### asis
```java
public boolean validateOrder(Order order) {
    if (order.getItems().size() == 0) {
        log.info("주문 항목이 없습니다.");
        return false;
    } else {
        if (order.getTotalPrice() > 0) {
            if (!order.hasCustomerInfo()) {
                log.info("사용자 정보가 없습니다.");
                return false;
            } else {
                return true;
            }
        } else if (!(order.getTotalPrice() > 0)) {
            log.info("올바르지 않은 총 가격입니다.");
            return false;
        }
    }
    return true;
}
```
<br>

##### tobe

```java
public boolean validateOrder(Order order) {
    if (isEmptyItems(order)) {
        log.info("주문 항목이 없습니다.");
        return false;
    }

    if (isEmptyTotalPrice(order)) {
        log.info("올바르지 않은 총 가격입니다.");
        return false;
    }

    if (hasNoCustomerInfo(order)) {
        log.info("사용자 정보가 없습니다.");
        return false;
    }

    return true;
}

private boolean isEmptyItems(Order order) {
    return order.getItems().size() == 0;
}

private boolean isEmptyTotalPrice(Order order) {
    return order.getTotalPrice() <= 0;
}

private boolean hasNoCustomerInfo(Order order) {
    return !order.hasCustomerInfo();
}
```

<br>

### 2. SOLID에 대하여 자기만의 언어로 정리해 봅시다.
