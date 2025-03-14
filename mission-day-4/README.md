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

1. SRP
   * 변경이 있을 때 파급 효과가 적어야 한다.
   * 변경이 있을 때 하나의 클래스, 하나의 지점만 고쳐야 한다.
2. OCP
   * 인터페이스를 구현한 새로운 클래스를 하나 만들어서 새로운 기능을 구현
   * 인터페이스를 구현한 새로운 클래스를 만드는 것은 기존 코드를 변경한게 아니라 확장에는 열려 있고 변경에 닫혀 있다.
3. LSP
   * 하위 클래스는 인터페이스 규약을 다 지켜야 한다.
   * 하위 클래스를 대신 사용하더라도 의도대로 동작해야 한다.
4. ISP
   * 하나의 범용적인 인터페이스보다 적당히 분리된 인터페이스 여러개가 낫다.
5. DIP
   * 구현체를 바라보지 말고 인터페이스를 바라봐야한다.
   * 추상화에 의존해야지 구체화에 의존하면 안된다.