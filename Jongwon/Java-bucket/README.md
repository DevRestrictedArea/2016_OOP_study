# bucket-prototype

## Ticket Monster Online Test
 
---

## Situation

Build bucket prototype for building coupon system.

- Input goods, coupon number must put in bucket.

- Show goods list and sale price in bucket window.

## Goods Example

| 코드 | 가격 | 이름 | 설명 |
|:---:|:---:|:---:|:---:|
| H | 900,000원 | 고급 게이밍 PC | 고사양 고급 PC |
| M | 550,000원 | 중급 게이밍 PC | 가성비 좋은 PC |
| L | 350,000원 | 알뜰 PC | 최소 구동환경 제공 |

## Coupon Example

| 코드 | 쿠폰명 | 할인 금액 | 사용 조건 |
|:---:|:---:|:---:|:---|
| C1 | 알뜰 PC 할인 쿠폰 | 50,000원(대당) | <ul><li>알뜰 PC 구매시 할인 가능</li><li>1대만 적용가능</li><li>단 다른 쿠폰과 중복 사용 불가능</li><ul> |
| C1-1 | 알뜰 PC 할인 쿠폰 2 | 40,000원(대당) | <ul><li>알뜰 PC 대량 구매시 할인 가능</li><li>5 ~ 10대까지만 적용 가능</li><li>단 다른 쿠폰과 중복 사용 불가능</li></ul>|
| C2 | 장바구니 쿠폰 | 전체 금액의 10% | <ul><li>장바구니 금액이 300,000원 이상일 때 사용가능</li><li> 단 다른 쿠폰과 중복 사용 불가능</li></ul> |
| C3 | 선착순 할인 쿠폰(10명) | 전체 금액의 20% | <ul><li>단 10명만 사용 가능</li><li>10명 이후 주문자는 C3쿠폰을 사용할 수 없음</li><li>동시에 수십명이 접속해서 주문 하더라도 10건만 사용되어야 함</li></ul> |

## Order

#### input

```
BucketID(6), UserID(4~12), BucketCouponCode, GoodsCode|Quantity[|GoodsCouponCode], [GoodsCode|Quantity|GoodsCouponCode, ...]
```

#### Example

```
123456,tmon,C3,H|1|C2
123457,tmon2,,L|2|C1,H|1
123457,tmon3,C3|C4,H|1,M|1,L|1
```

#### Output

```
장바구니 ID는 xxx입니다.
```

#### Bucket Window Output

```
L 1개 할인 전 금액 xxx원 할인 후 금액 xxx원 적용된 쿠폰 xx
H 2개 할인 전 금액 xxx원

전체 금액 xxxxx원 / 총 할인 금액 xxxx원 

경고 
C1 쿠폰은 중복 사용으로 인해 사용이 불가능합니다.
```

## Addition

- 프로토타입이므로 다양한 쿠폰 확장이 쉽도록 구성

- 쿠폰 사용에 대한 검증할 수 있는 코드 작성

- 쿠폰 사용시 동시성 문제를 해결 할 수 있는 코드 작성
