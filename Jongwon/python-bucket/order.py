# -*- coding: UTF-8 -*-
from goods import Goods
import coupon
from coupon import Coupon
from bucket import Bucket
from wishes2obj import Wishes2Obj

pcH = Goods("H",900000,"고급 게이밍 PC","고사양 고급 PC")
pcM = Goods("M",550000,"중급 게이밍 PC","가성비 좋은 PC")
pcL = Goods("L",350000,"알뜰 PC","최소 구동환경 제공")
goodsList = (pcH, pcM, pcL)

c1 = Coupon("C1","알뜰 PC 할인 쿠폰",50000)
c1_1 = Coupon("C1-1","알뜰 PC 할인 쿠폰 2",40000)
c2 = Coupon("C2","장바구니 쿠폰",0.1)
c3 = Coupon("C3","선착순 할인 쿠폰(10명)",0.2)
couponList = (c1, c1_1, c2, c3)

query = input("Enter 'BucketID,UserID,BkCoupon,GoodsCode|Quantity|GdsCouponCode': ")
info=query.split(",")

bucketID = info[0]

userID = info[1]

bkCoupon = info[2]
bkCouponObj = list()
bkCouponList = bkCoupon.split("|")
for item in bkCouponList:
	for card in couponList:
		if item == card.code:
			bkCouponObj.append(card)

wishes = info[3:]
wishesObj = list()
for item in wishes:
	itemsplit = item.split("|")

	goodObj = None
	couponObj = list()
	count = int(itemsplit[1])

	for good in goodsList:
		if itemsplit[0] == good.code:
			goodObj = good

	for i in itemsplit[2:]:
		for card in couponList:
			if i == card.code:
				couponObj.append(card)

	wishesObj.append(Wishes2Obj(goodObj, count, couponObj))


print("장바구니 ID는 " + bucketID + " 입니다.")
bucketList = Bucket(bucketID, userID, bkCouponObj, wishesObj)

coupon.costinfo(bkCouponObj, wishesObj)

print("경고")
for item in wishesObj:
	for couponitem in item.couponobj:
		coupon.coupon_err(bkCouponObj, couponitem, item, bucketList.totalcost())
